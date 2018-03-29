package com.hladkevych.menu.service;

import com.hladkevych.menu.dto.ResultDTO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.hladkevych.menu.utils.mlp.MlpConfiguration;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ggladko97 on 12.05.17.
 */
@Service
public class RecipeServiceImpl implements RecipeService {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(RecipeService.class);

    private static final int TOP_COUNT = 8;

    private static List<String> dummyDiets
            = Arrays.asList("ATKINS_1", "ATKINS_2", "ATKINS_3", "DR_SEARS_ZONE_DIET",
                    "DASH_DIET", "WHOLE30", "LOW_SODIUM_D", "GLUTEN_FREE_D", "THEPALEO_DIET",
                    "VEGETARIAN_D", "SOUTH_BEACH_D", "VEGAN", "HCG_DIET");

    private static List<String> dummyProducts
            = Arrays.asList("Tomato", "Onion", "Broccoli", "Coconut", "Chili", "Jalapeno Pepper",
                    "Cilantro", "Pustka", "Lemon", "Lime", "Pumpkin", "Eggs", "Bacon", "Butter", "Avocado",
                    "Cheese",
                    "Blue Cheese", "Olive oil", "Rosemary", "Mayonnaise", "Sour Cream", "Red Sweet Pepper",
                    "Bell Peppers", "Basil", "Mustard", "Vinegar", "Ham", "Pork", "Chicken ", "Garlic",
                    "Parsley",
                    "Tamari", "Salmon", "Tilapia", "Trout", "Cabbage ", "Cauliflower", "Mushrooms", "Salt",
                    "Black Pepper", "Wine", "Almonds", "Ginger",
                    "Pineapple", "Walnut", "Potato", "Apple", "Carrot", "Turkey", "Milk", "Cucumber");

    private List<Integer> indexes = null;
    private List<ResultDTO> results = null;

    @Override
    public List<String> listProducts() {
        return dummyProducts;
    }

    @Override
    public List<ResultDTO> getResults() {
        if (results != null) {
            logger.info("indexes:" + indexes.toString());

            return results;

        }
        return null;
    }

    @Override
    public void fillTestDataSet(List<String> data) throws FileNotFoundException {
        //StringUtils.arrayToCommaDelimitedString((String[])data.toArray());
        List<String> result = new ArrayList<>();
        logger.info("Input for conversion: " + data.toString());
//    result.add("0");
        for (int i = 0; i < 52; i++) {
            result.add("0");
        }
        Map<String, Integer> indexedList = new HashMap<>();
        for (int i = 0; i < dummyProducts.size(); i++) {
            indexedList.put(dummyProducts.get(i), i);
        }

        for (String s : data) {
            logger.info("Trying to get from dummy: " + s);
            Integer position = null;
            try {
                position = indexedList.get(s);
            } catch (Exception e ) {
                logger.error("NPE in input list");
            }
            if (position == null) {
                //do nothing, that a bug
            } else {
                result.set(position, "1");
            }
        }

        String toFile
                = StringUtils.arrayToCommaDelimitedString(result.toArray(new String[result.size()]));
        ClassLoader classLoader = getClass().getClassLoader();
        PrintWriter writer = new PrintWriter(classLoader.getResource("prod_test_beta.csv").getFile());

        System.out.println("To file:" + toFile);
        writer.write(toFile);
        writer.flush();
        writer.close();
    }

    @Override
    public String classifyInstance() {
        MlpConfiguration configuration = new MlpConfiguration(51, 13, 1000, 9);
        INDArray testResults = configuration.validateNetwork();
        System.out.println("TESTS:" + testResults);
        try {
            INDArray actuallResults
                    = configuration.classifyRow(configuration.readDataFromCsv("prod_test_beta.csv"));
            System.out.println("ActualResults\n" + actuallResults.toString());
            System.out.println("================END===================");
            Map<Integer, Double> resultsWithIndexes = new HashMap<>();
            for (int i = 0; i < actuallResults.length(); i++) {
                resultsWithIndexes.put(i, actuallResults.getDouble(i));
            }
            System.out.println("Unsorted map:" + resultsWithIndexes);
            Map<Integer, Double> sortedMap
                    = sortLHM(resultsWithIndexes);
            logger.info("Sorted map:" + sortedMap);
            results = new ArrayList<>();
            int iter = 0;
            for (Map.Entry<Integer, Double> entry : sortedMap.entrySet()) {
                if (iter < TOP_COUNT) {
                    ResultDTO resultDTO = new ResultDTO();
                    resultDTO.setName(dummyDiets.get(entry.getKey()));
                    resultDTO.setProcentProbability(String.valueOf((int) (entry.getValue() * 100)));
                    if (indexes.contains(entry.getKey())) {
                        results.add(resultDTO);
                    }
                }
                iter++;
            }
            logger.info("Results DTO: " + results.toString());
            return String.valueOf(actuallResults.maxNumber().floatValue());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(RecipeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<String> listDiets() {
        return dummyDiets;
    }

    @Override
    public void generateSearchIndexes(List<String> diets) {
        indexes = new ArrayList<>();
        diets.forEach(d -> {
            indexes.add(dummyDiets.indexOf(d));
        });
    }

    public List<Integer> getIndexes() throws NullPointerException {
        if (indexes != null) {
            return indexes;
        }
        throw new NullPointerException("Generate indexes table before using");
    }

    private Map<Integer, Double> sortLHM(Map<Integer, Double> map) {
        List<Map.Entry<Integer, Double>> entries
                = new ArrayList<>(map.entrySet());
        Collections.sort(entries, (Map.Entry<Integer, Double> a, Map.Entry<Integer, Double> b) -> b.getValue().compareTo(a.getValue()));
        Map<Integer, Double> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, Double> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}

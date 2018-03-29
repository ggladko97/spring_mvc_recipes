package com.hladkevych.menu.service;

import com.hladkevych.menu.dto.ResultDTO;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by ggladko97 on 12.05.17.
 */
public interface RecipeService {
  List<String> listProducts();

  void fillTestDataSet(List<String> data) throws FileNotFoundException;

  String classifyInstance();

  List<String> listDiets();
  
  List<ResultDTO> getResults();

  void generateSearchIndexes(List<String> diets);
}

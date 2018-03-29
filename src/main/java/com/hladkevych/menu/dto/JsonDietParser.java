package com.hladkevych.menu.dto;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ggladko97 on 04.02.18.
 */
public class JsonDietParser {

    public static List<DietDTO> parseFromJson(File path) throws IOException {
        Gson gson = new Gson();
        StringBuilder builder = new StringBuilder();
       
        System.out.println("FILE READED: " + path.getAbsolutePath());
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println("Read line: " + line);
                builder.append(line);
            }
        }
        List<DietDTO> result = gson.fromJson(builder.toString(), new TypeToken<ArrayList<DietDTO>>() {}.getType());

        System.out.println("Read json: " + result);

        //ObjectMapper mapper=new ObjectMapper();
        //return mapper
        //    .readValue(new File(path), ArrayList.class);
        return result;
    }
}

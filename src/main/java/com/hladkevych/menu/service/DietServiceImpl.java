package com.hladkevych.menu.service;

import com.hladkevych.menu.dto.DietDTO;
import com.hladkevych.menu.dto.JsonDietParser;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by ggladko97 on 04.02.18.
 */
public class DietServiceImpl {
  public List<DietDTO> listDiets() {
    List<DietDTO> dietDTO = null;
    try {
      dietDTO = JsonDietParser.parseFromJson(new File(this.getClass().getClassLoader().getResource("diets_desc.json").getFile()));
    } catch (IOException e) {
      System.out.println("IOE while reading JSON\n" + e);
    }
    return dietDTO;
  }
}

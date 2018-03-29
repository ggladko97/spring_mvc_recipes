package com.hladkevych.menu.controllers;

import com.hladkevych.menu.dto.DietDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hladkevych.menu.service.DietServiceImpl;
import com.hladkevych.menu.service.RecipeService;

/**
 * Created by ggladko97 on 04.02.18.
 */

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/load")
public class RestController {
private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(RestController.class);
  @Autowired
  private DietServiceImpl dietService;

  @RequestMapping("/diet-{id}")
  @ResponseBody
  public DietDTO loadDetails(@PathVariable String id) {
    List<DietDTO> dietDTOS = dietService.listDiets();
    logger.info("DietsDTO from file: " + dietDTOS.toString());
    for(DietDTO diett : dietDTOS) {
      if (diett.getTitle().equals(id)) {
        logger.info("Found: " + diett);
        return diett;
      }
    }

    return null;
  }
}

package com.hladkevych.menu.controllers;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import com.hladkevych.menu.dto.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.hladkevych.menu.service.DietServiceImpl;
import com.hladkevych.menu.service.RecipeService;

/**
 * Created by ggladko97 on 14.05.17.
 */
@Controller
@RequestMapping("/home")
public class BuyController {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(BuyController.class);
    @Autowired
    private RecipeService recipeService;

    @Autowired
    private DietServiceImpl dietService;

    @RequestMapping("/")
    public String initHome(ModelMap model) {
        List<String> products = recipeService.listProducts();
        model.addAttribute("products", products);
        return "home";
    }

    @RequestMapping("/start")
    public ModelAndView startPage() {
        return new ModelAndView("parameters");
    }

    @RequestMapping("/init-diets")
    public String initDiets(ModelMap model) {
        model.addAttribute("diets", dietService.listDiets());
        logger.info("DIETS from by: " + dietService.listDiets());
        return "parameters";
    }

    @RequestMapping("/submit-search")
    public ModelAndView submitSearch(@RequestParam(value = "titles") List<String> titles, ModelMap model) {
        System.out.println("Titles: " + titles);
        System.out.println("success");
        try {
            recipeService.fillTestDataSet(titles);
            String response = recipeService.classifyInstance();
            System.out.println("Result:" + response);
            model.addAttribute("results", recipeService.getResults());
        } catch (Exception e) {
            ResultDTO resultDTO = new ResultDTO();
            resultDTO.setName("Error in classification process, see logs");
            resultDTO.setLinkToRecipes("");
            resultDTO.setProcentProbability("0");
            model.addAttribute("results", resultDTO);
        } finally {
            return new ModelAndView("results");
        }
    }

    @RequestMapping("/submit-init-diets")
    public ModelAndView submitInitRecipes(@RequestParam(value = "diets") List<String> diets, ModelMap model) {
        System.out.println("Titles: " + diets);
        System.out.println("success");
        if (!diets.isEmpty() || diets != null) {
            recipeService.generateSearchIndexes(diets);
        }

        //try {
        //  recipeService.fillTestDataSet(titles);
        //  String response = recipeService.classifyInstance();
        //  System.out.println("Result:" + response);
        //} catch (FileNotFoundException e) {
        //
        //}
        List<String> products = recipeService.listProducts();
        model.addAttribute("products", products);
        return new ModelAndView("home");
    }

//    @RequestMapping("/results")
//    public String openResults(ModelMap model) {
//        
//        model.put("results", recipeService.getResults());
//        return "results";
//    }
    /**
     * Updating data about client
     *
     * @param ticket - object with data about client
     * @param modelMap - for transfer
     * @return redirection to the main page
     */
    //@RequestMapping(value = "/buy-page", method = RequestMethod.POST)
    //public ModelAndView buy(@ModelAttribute(value = "ticket") /*@Valid*/ Client ticket, ModelMap modelMap) {
    //  if (!ticket.getFirstName().equals(null)){
    //    //throw error something goes wrong
    //
    //  }
    //
    //  recipeService.insert(ticket);
    //  return new ModelAndView("redirect:/");
    //}
}

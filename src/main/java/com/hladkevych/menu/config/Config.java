/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hladkevych.menu.config;

import com.hladkevych.menu.service.DietServiceImpl;
import com.hladkevych.menu.service.RecipeService;
import com.hladkevych.menu.service.RecipeServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 *
 * @author hladlyev
 */
@Configuration
@ComponentScan("com.hladkevych.menu")
@EnableWebMvc   
public class Config extends WebMvcConfigurerAdapter {  
      
    @Bean  
    public UrlBasedViewResolver setupViewResolver() {  
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();  
        resolver.setPrefix("/WEB-INF/jsp/");  
        resolver.setSuffix(".jsp");  
        resolver.setViewClass(JstlView.class);  
        return resolver;  
    }  
    
    @Bean
    public DietServiceImpl dietServiceImpl() {
        return new DietServiceImpl();
    }
    
    @Bean
    public RecipeService recipeService() {
        return new RecipeServiceImpl();
    }
}  
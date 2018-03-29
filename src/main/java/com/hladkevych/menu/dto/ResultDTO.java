/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hladkevych.menu.dto;

/**
 *
 * @author hladlyev
 */
public class ResultDTO {
    private String name;
    private String procentProbability;
    private String linkToRecipes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProcentProbability() {
        return procentProbability;
    }

    public void setProcentProbability(String procentProbability) {
        this.procentProbability = procentProbability;
    }

    public String getLinkToRecipes() {
        return linkToRecipes;
    }

    public void setLinkToRecipes(String linkToRecipes) {
        this.linkToRecipes = linkToRecipes;
    }

    @Override
    public String toString() {
        return "ResultDTO{" + "name=" + name + ", procentProbability=" + procentProbability + ", linkToRecipes=" + linkToRecipes + '}';
    }
    
    
}

package com.fanaticaltest.datadriventest.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samykacem on 04.10.18.
 */
public class Feature {

    private Integer id;
    private String name;
    private String description;
    private List<Scenario> scenarios;

    //Constructor
    public Feature(String name, String description, List<Scenario> scenarios) {
        this.name = name;
        this.description = description;
        this.scenarios = scenarios;
    }

    public Feature(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Feature() {
    }

    //Getter and Setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Scenario> getScenarios() {
        return scenarios;
    }

    public void setScenarios(ArrayList<Scenario> scenarios) {
        this.scenarios = scenarios;
    }

    //Methods
    public String toGherkinsFile()
    {
        String buildFeature = "";

        buildFeature += "Feature: " + this.name + "\n\n";

        buildFeature += "\t" + this.description + "\n\n";

        if (this.scenarios.size()>0)
        {
            for (Scenario scenario : this.scenarios)
            {
                buildFeature += scenario.toGherkinsFile();
            }
        }

        return buildFeature;
    }
}

package com.fanaticaltest.datadriventest.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samykacem on 02.10.18.
 */
public class Step {

    private Integer id;
    private GherkinsKeyword gherkinsKeyword;
    private String phrase;
    private List<Module> modules;
    private Scenario scenario;

    //Constructor
    public Step(GherkinsKeyword gherkinsKeyword, String phrase) {
        this.gherkinsKeyword = gherkinsKeyword;
        this.phrase = phrase;
    }

    //Getter and Setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GherkinsKeyword getGherkinsKeyword() {
        return gherkinsKeyword;
    }

    public void setGherkinsKeyword(GherkinsKeyword gherkinsKeyword) {
        this.gherkinsKeyword = gherkinsKeyword;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(ArrayList<Module> modules) {
        this.modules = modules;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    //Methods
    public String toGherkinsFile(){
        return String.valueOf(this.gherkinsKeyword) + " " + this.phrase;
    }

    public String toPython()
    {
        String buildPythonFile = "";

        if (this.modules.size()>0)
        {
            for (Module module : this.modules)
            {
                buildPythonFile += module.toPython();
            }
        }

        return buildPythonFile;
    }

}
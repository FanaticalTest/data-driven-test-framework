package com.fanaticaltest.datadriventest.models;


import java.util.ArrayList;
import java.util.List;

public class PythonDef {

    private Integer id;
    private String name;
    private String description;
    private List<Module> modules;
    private Scenario scenario;

    //Constructors
    public PythonDef(String name, String description) {
        this.name = name;
        this.description = description;
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
    public String toPython() {
        String snippets = "";

        if (this.modules.size()>0)
        {
            for (Module m : this.modules)
            {
                snippets += m.toPython();
            }
        }
        return snippets;
    }
}
package com.fanaticaltest.datadriventest.models;

public class GherkinsTag {

    private Integer id;
    private String name;
    private String value;
    private Scenario scenario;

    //Constructor
    public GherkinsTag(String name, String value) {
        this.name = name;
        this.value = value;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    //Method
    public String toGherkinsFile()
    {
        return "@" + getName() + "=" + getValue() + " ";
    }
}
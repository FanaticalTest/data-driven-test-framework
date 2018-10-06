package com.fanaticaltest.datadriventest.models;

public class PythonDependencies {

    private Integer id;
    private String name;
    private String description;
    private String snippet;

    //Constructor
    public PythonDependencies(String name, String description, String snippet) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.snippet = snippet;
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

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    //Method
    public String toPython()
    {
        return this.snippet;
    }
}
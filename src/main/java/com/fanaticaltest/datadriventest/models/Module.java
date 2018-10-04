package com.fanaticaltest.datadriventest.models;


import java.util.ArrayList;

public class Module {

    private Integer id;
    private String name;
    private String comment;
    private String snippet;
    private ArrayList<ModuleParameters> moduleParameter;
    private Integer position;

    // Constructors
    public Module(Integer id, String name, String snippet) {
        this.id = id;
        this.name = name;
        this.snippet = snippet;
        this.moduleParameter = new ArrayList<ModuleParameters>();
        this.position = 0;
    }

    public Module(Integer id, String name, String snippet, ArrayList<ModuleParameters> moduleParameter) {
        this.id = id;
        this.name = name;
        this.snippet = snippet;
        this.moduleParameter = moduleParameter;
        this.position = 0;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public ArrayList<ModuleParameters> getModuleParameter() {
        return moduleParameter;
    }

    public void setModuleParameter(ArrayList<ModuleParameters> moduleParameter) {
        this.moduleParameter = moduleParameter;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    // Methods
    private String snippetParametrized(){
        if (moduleParameter.size() > 0) {
            for (ModuleParameters mp : moduleParameter) {
                snippet = snippet.replace("%%" + mp.getName() + "%%", mp.getValue());
            }
        }
        return snippet;
    }

    @Override
    public String toString() {
        return snippetParametrized();
    }
}

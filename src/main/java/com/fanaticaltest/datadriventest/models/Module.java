package com.fanaticaltest.datadriventest.models;


import java.util.ArrayList;
import java.util.List;

public class Module {

    private Integer id;
    private String name;
    private String comment;
    private String snippet;
    private List<ModuleParameters> moduleParameter;
    private Integer position;
    private Step step;

    // Constructors
    public Module(String name, String snippet) {
        this.name = name;
        this.snippet = snippet;
        this.moduleParameter = new ArrayList<ModuleParameters>();
        this.position = 0;
    }

    public Module(String name, String snippet, List<ModuleParameters> moduleParameter) {
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

    public List<ModuleParameters> getModuleParameter() {
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

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    // Methods
    private String snippetParametrized(){
        if (this.moduleParameter.size() > 0) {
            for (ModuleParameters mp : this.moduleParameter) {
                this.snippet = this.snippet.replace("%%" + mp.getName() + "%%", mp.getValue());
            }
        }
        return this.snippet;
    }

    public String toPython() {
        return snippetParametrized();
    }
}
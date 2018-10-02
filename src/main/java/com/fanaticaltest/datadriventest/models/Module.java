package com.fanaticaltest.datadriventest.models;


import java.util.ArrayList;

public class Module {

    private Integer id;
    private String name;
    private String comment;
    private String snippet;
    private ArrayList<ModuleParameters> moduleParameter;

    public Module(Integer id, String name, String snippet) {
        this.id = id;
        this.name = name;
        this.snippet = snippet;
        this.moduleParameter = new ArrayList<ModuleParameters>();
    }

    public Module(Integer id, String name, String snippet, ArrayList<ModuleParameters> moduleParameter) {
        this.id = id;
        this.name = name;
        this.snippet = snippet;
        this.moduleParameter = moduleParameter;
    }

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

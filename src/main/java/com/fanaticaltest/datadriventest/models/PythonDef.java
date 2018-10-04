package com.fanaticaltest.datadriventest.models;


import java.util.ArrayList;

public class PythonDef {

    private Integer id;
    private String name;
    private String description;
    private ArrayList<Module> modules;

    //Constructors
    public PythonDef(Integer id, String name, String description) {
        this.id = id;
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

    public ArrayList<Module> getModules() {
        return modules;
    }

    public void setModules(ArrayList<Module> modules) {
        this.modules = modules;
    }


    //Methods
    @Override
    public String toString() {
        String snippets = "";

        if (modules.size()>0)
        {
            for (Module m : modules)
            {
                snippets += m.toString();
            }
        }
        return snippets;
    }
}

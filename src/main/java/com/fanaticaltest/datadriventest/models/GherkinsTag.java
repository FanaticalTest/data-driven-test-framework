package com.fanaticaltest.datadriventest.models;

public class GherkinsTag extends ModuleParameters {

    //Constructor
    public GherkinsTag(Integer id, String name, String value) {
        super(id, name, value);
    }

    //Method
    public String toGherkinsFile()
    {
        return "@" + super.getName() + "=" + super.getValue() + " ";
    }
}
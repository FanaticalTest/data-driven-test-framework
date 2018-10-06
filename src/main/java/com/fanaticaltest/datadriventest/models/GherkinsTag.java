package com.fanaticaltest.datadriventest.models;

public class GherkinsTag extends ModuleParameters {

    //Constructor
    public GherkinsTag( String name, String value) {
        super( name, value);
    }

    //Method
    public String toGherkinsFile()
    {
        return "@" + super.getName() + "=" + super.getValue() + " ";
    }
}
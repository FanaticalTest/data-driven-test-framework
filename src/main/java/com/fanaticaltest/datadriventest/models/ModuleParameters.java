package com.fanaticaltest.datadriventest.models;


import javax.persistence.*;

@Entity
public class ModuleParameters {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;
    private String value;

    @ManyToOne
    private Module module;

    //constructors
    public ModuleParameters () {}

    public ModuleParameters (String name, String value) {
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

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    //Methods

    @Override
    public String toString() {
        return "ModuleParameters{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", module=" + module +
                '}';
    }
}

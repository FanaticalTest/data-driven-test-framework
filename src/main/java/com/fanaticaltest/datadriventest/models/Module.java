package com.fanaticaltest.datadriventest.models;


public class Module {

    private Integer id;
    private String name;
    private String comment;
    private String snippet;

    public Module(Integer id, String name, String snippet) {
        this.id = id;
        this.name = name;
        this.snippet = snippet;
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

    @Override
    public String toString() {
        return snippet;
    }
}

package com.fanaticaltest.datadriventest.models;

/**
 * Created by samykacem on 02.10.18.
 */
public class Step {

    private Integer id;
    private GherkinsKeyword gherkinsKeyword;
    private String phrase;

    public Step(Integer id, GherkinsKeyword gherkinsKeyword, String phrase) {
        this.id = id;
        this.gherkinsKeyword = gherkinsKeyword;
        this.phrase = phrase;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GherkinsKeyword getGherkinsKeyword() {
        return gherkinsKeyword;
    }

    public void setGherkinsKeyword(GherkinsKeyword gherkinsKeyword) {
        this.gherkinsKeyword = gherkinsKeyword;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    @Override
    public String toString() {
        return String.valueOf(gherkinsKeyword) + " " + phrase;
    }

}

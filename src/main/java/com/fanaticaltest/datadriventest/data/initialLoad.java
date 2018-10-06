package com.fanaticaltest.datadriventest.data;

import com.fanaticaltest.datadriventest.models.*;

import java.util.ArrayList;

public class initialLoad {

    private static final String indentationOnce = "\t";
    private static final String indentationTwice = "\t\t";
    private static final String newLine = "\n";

    public static Feature generateDemoFeature()
    {
        Feature feature = new Feature("User searches for article", "This is a description of the feature file");


        Scenario scenario = new Scenario();

        ArrayList<GherkinsTag> gherkinsTags = new ArrayList<GherkinsTag>();
        ArrayList<Step> steps = new ArrayList<Step>();

        scenario.setId(1);
        scenario.setName("User is searching from the home page");
        scenario.setDescription("This is a description");
        gherkinsTags.add(new GherkinsTag("testId", "100.1"));
        gherkinsTags.add(new GherkinsTag("browser", "firefox"));
        scenario.setGherkinsTags(gherkinsTags);

        ArrayList<ModuleParameters> parametersArrayList = new ArrayList<ModuleParameters>();
        //Step 1
        String snippet = indentationTwice + "driver = self.driver" + newLine;
        snippet += indentationTwice + "driver.get(\"%%site_url%%\")" + newLine;
        snippet += indentationTwice + "self.assertIn(\"%%title%%\", driver.title)" + newLine;
        Module moduleOne = new Module("the user is on the home page", snippet);
        parametersArrayList.add(new ModuleParameters("site_url","http://www.python.org"));
        parametersArrayList.add(new ModuleParameters("title","Python"));
        moduleOne.setModuleParameter(parametersArrayList);
        parametersArrayList.clear();
        ArrayList<Module> moduleOnes = new ArrayList<Module>();
        moduleOnes.add(moduleOne);

        //Step 2
        snippet = indentationTwice + "elem = driver.find_element_by_name(\"%%search_element%%\")" + newLine;
        snippet += indentationTwice + "elem.send_keys(\"%%search_value%%\")" + newLine;
        snippet += indentationTwice + "elem.send_keys(Keys.RETURN)" + newLine;
        Module moduleTwo = new Module("the user enters the keyword \"pycon\"", snippet);
        parametersArrayList.add(new ModuleParameters("search_element","q"));
        parametersArrayList.add(new ModuleParameters("search_value","pycon"));
        moduleTwo.setModuleParameter(parametersArrayList);
        parametersArrayList.clear();
        ArrayList<Module> moduleTwos = new ArrayList<Module>();
        moduleTwos.add(moduleTwo);

        //Step 3
        snippet = indentationTwice + "assert \"No results found.\" not in driver.page_source" + newLine;
        Module moduleThree = new Module("the user does not see the error message \"No results found.\"", snippet);
        ArrayList<Module> moduleThrees = new ArrayList<Module>();
        moduleThrees.add(moduleThree);


        Step stepOne = new Step(GherkinsKeyword.GIVEN, "the user is on the home page");
        stepOne.setModules(moduleOnes);
        Step stepTwo = new Step(GherkinsKeyword.WHEN, "the user enters the keyword \"pycon\"");
        stepTwo.setModules(moduleTwos);
        Step stepThree = new Step(GherkinsKeyword.THEN, "the user does not see the error message \"No results found.\"");
        stepThree.setModules(moduleThrees);

        steps.add(stepOne);
        steps.add(stepTwo);
        steps.add(stepThree);
        scenario.setSteps(steps);

        ArrayList<Scenario> scenarios = new ArrayList<Scenario>();
        scenarios.add(scenario);

        feature.setScenarios(scenarios);

        return feature;
    }
}

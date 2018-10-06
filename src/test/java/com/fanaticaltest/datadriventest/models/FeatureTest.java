package com.fanaticaltest.datadriventest.models;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static com.fanaticaltest.datadriventest.data.initialLoad.generateDemoFeature;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FeatureTest {

    private static final Logger log = LoggerFactory.getLogger(FeatureTest.class);
    private static final String outPutFilePath = "./src/test/resources/outputFiles/";

    @Test
    public void createFeatureFile() throws IOException
    {
        Scenario scenario = new Scenario();
        ArrayList<GherkinsTag> gherkinsTags = new ArrayList<GherkinsTag>();
        ArrayList<Step> steps = new ArrayList<Step>();

        scenario.setId(1);
        scenario.setName("User is searching from the home page");
        scenario.setDescription("This is a description");
        gherkinsTags.add(new GherkinsTag("testId", "100.1"));
        gherkinsTags.add(new GherkinsTag("browser", "firefox"));
        scenario.setGherkinsTags(gherkinsTags);
        steps.add(new Step(GherkinsKeyword.GIVEN, "the user is on the home page"));
        steps.add(new Step(GherkinsKeyword.WHEN, "the user enters the keyword \"pycon\""));
        steps.add(new Step(GherkinsKeyword.THEN, "the user does not see the error message \"No results found.\""));
        scenario.setSteps(steps);

        ArrayList<Scenario> scenarios = new ArrayList<Scenario>();
        scenarios.add(scenario);
        scenarios.add(scenario);
        scenarios.add(scenario);

        Feature feature = new Feature("User searches for article",
                "This is a description of the feature file", scenarios);

        log.info(feature.toGherkinsFile());

        FileWriter fileWriter = new FileWriter(outPutFilePath +"feature.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf(feature.toGherkinsFile());
        printWriter.close();

        assert (feature.toGherkinsFile().contains("Feature: User searches for article\n"));
        assert (feature.toGherkinsFile().contains("\tThis is a description of the feature file\n"));
        assert (feature.toGherkinsFile().contains("\t@testId=100.1 @browser=firefox \n"));
        assert (feature.toGherkinsFile().contains("\tScenario: User is searching from the home page\n"));
        assert (feature.toGherkinsFile().contains("\t\tGIVEN the user is on the home page\n"));
        assert (feature.toGherkinsFile().contains("\t\tWHEN the user enters the keyword \"pycon\"\n"));
        assert (feature.toGherkinsFile().contains("\t\tTHEN the user does not see the error message \"No results found.\"\n"));
    }

    @Test
    public void createFeatureFileFromInitialLoad() throws IOException
    {
        Feature feature = new Feature();
        feature = generateDemoFeature();

        log.info(feature.toGherkinsFile());

        FileWriter fileWriter = new FileWriter(outPutFilePath +"featureFromInitialLoad.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf(feature.toGherkinsFile());
        printWriter.close();
    }
}

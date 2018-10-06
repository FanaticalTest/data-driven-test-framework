package com.fanaticaltest.datadriventest.models;

import java.util.ArrayList;
import java.util.List;

public class Scenario {

    private Integer id;
    private String name;
    private String description;
    private List<GherkinsTag> gherkinsTags;
    private List<Step> steps;
    private PythonDependencies pythonDependencies;
    private PythonDefSetup pythonDefSetup;
    private PythonDefTearDown pythonDefTearDown;
    private Feature feature;

    //Constructor
    public Scenario() {}

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
        this.name = "Scenario: " + name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<GherkinsTag> getGherkinsTags() {
        return gherkinsTags;
    }

    public void setGherkinsTags(ArrayList<GherkinsTag> gherkinsTags) {
        this.gherkinsTags = gherkinsTags;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public PythonDependencies getPythonDependencies() {
        return pythonDependencies;
    }

    public void setPythonDependencies(PythonDependencies pythonDependencies) {
        this.pythonDependencies = pythonDependencies;
    }

    public PythonDefSetup getPythonDefSetup() {
        return pythonDefSetup;
    }

    public void setPythonDefSetup(PythonDefSetup pythonDefSetup) {
        this.pythonDefSetup = pythonDefSetup;
    }

    public PythonDefTearDown getPythonDefTearDown() {
        return pythonDefTearDown;
    }

    public void setPythonDefTearDown(PythonDefTearDown pythonDefTearDown) {
        this.pythonDefTearDown = pythonDefTearDown;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    //Methods
    public String toGherkinsFile()
    {
        String buildScenario = "";

        if (this.gherkinsTags.size()>0)
        {
            buildScenario += "\t";
            for (GherkinsTag gherkinsTag : gherkinsTags)
            {
                buildScenario += gherkinsTag.toGherkinsFile();
            }
        }

        buildScenario += "\n";

        buildScenario += "\t" + this.name + "\n";

        if (this.steps.size()>0)
        {
            for (Step step : steps)
            {
                buildScenario += "\t\t" + step.toGherkinsFile() + "\n";
            }
        }

        buildScenario += "\n";

        return buildScenario;
    }

    public String toPython()
    {
        String buildPythonFile = "";

        buildPythonFile += this.pythonDependencies.toPython() + "\n";

        buildPythonFile += "class DataDriverTest(unittest.TestCase):" + "\n\n";

        buildPythonFile += this.pythonDefSetup.toPython()+ "\n";

        buildPythonFile += "\tdef test(self):\n";

        if (this.steps.size()>0)
        {
            for (Step step : this.steps)
            {
                buildPythonFile += step.toPython();
            }
        }

        buildPythonFile += "\n";

        buildPythonFile += this.pythonDefTearDown.toPython()+ "\n";

        buildPythonFile += "if __name__ == \"__main__\":" + "\n";
        buildPythonFile += "\tunittest.main()" + "\n";

        return buildPythonFile;
    }
}
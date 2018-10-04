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

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScenarioTest {

    private static final Logger log = LoggerFactory.getLogger(ScenarioTest.class);
    private static final String outPutFilePath = "./src/test/resources/outputFiles/";
    private static final String indentationOnce = "\t";
    private static final String indentationTwice = "\t\t";
    private static final String newLine = "\n";

    @Test
    public void createGherkinsTag()
    {
        GherkinsTag gherkinsTag = new GherkinsTag(1,"testId", "100.1");
        log.info(gherkinsTag.toGherkinsFile());

        assert (gherkinsTag.toGherkinsFile().equals("@testId=100.1 "));
    }

    @Test
    public void createPythonDependencies()
    {
        String dependencies = "import unittest" + newLine;
        dependencies += "from selenium import webdriver" + newLine;
        dependencies += "from selenium.webdriver.common.keys import Keys" + newLine;

        PythonDependencies pythonDependencies = new PythonDependencies(1,"name","description", dependencies);
        log.info(pythonDependencies.toPython());

        assert (pythonDependencies.toPython().contains("import unittest\n"));
        assert (pythonDependencies.toPython().contains("from selenium import webdriver\n"));
        assert (pythonDependencies.toPython().contains("from selenium.webdriver.common.keys import Keys\n"));
    }

    @Test
    public void createScenarioGherkinsFile() throws IOException
    {
        Scenario scenario = new Scenario();
        ArrayList<GherkinsTag> gherkinsTags = new ArrayList<GherkinsTag>();
        ArrayList<Step> steps = new ArrayList<Step>();

        scenario.setId(1);
        scenario.setName("User is searching from the home page");
        scenario.setDescription("This is a description");
        gherkinsTags.add(new GherkinsTag(1,"testId", "100.1"));
        gherkinsTags.add(new GherkinsTag(2,"browser", "firefox"));
        scenario.setGherkinsTags(gherkinsTags);
        steps.add(new Step(1, GherkinsKeyword.GIVEN, "the user is on the home page"));
        steps.add(new Step(2, GherkinsKeyword.WHEN, "the user enters the keyword \"pycon\""));
        steps.add(new Step(3, GherkinsKeyword.THEN, "the user does not see the error message \"No results found.\""));
        scenario.setSteps(steps);

        log.info(scenario.toGherkinsFile());

        FileWriter fileWriter = new FileWriter(outPutFilePath +"scenario.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf(scenario.toGherkinsFile());
        printWriter.close();

        assert (scenario.toGherkinsFile().contains("\t@testId=100.1 @browser=firefox \n"));
        assert (scenario.toGherkinsFile().contains("\tScenario: User is searching from the home page\n"));
        assert (scenario.toGherkinsFile().contains("\t\tGIVEN the user is on the home page\n"));
        assert (scenario.toGherkinsFile().contains("\t\tWHEN the user enters the keyword \"pycon\"\n"));
        assert (scenario.toGherkinsFile().contains("\t\tTHEN the user does not see the error message \"No results found.\"\n"));
    }

    @Test
    public void createScenarioGherkinsFileMinimal() throws IOException
    {
        Scenario scenario = new Scenario();
        ArrayList<GherkinsTag> gherkinsTags = new ArrayList<GherkinsTag>();
        ArrayList<Step> steps = new ArrayList<Step>();

        scenario.setId(1);
        scenario.setName("User is searching from the home page");
        scenario.setDescription("This is a description");
        scenario.setGherkinsTags(gherkinsTags);
        steps.add(new Step(1, GherkinsKeyword.GIVEN, "the user is on the home page"));
        scenario.setSteps(steps);

        log.info(scenario.toGherkinsFile());

        FileWriter fileWriter = new FileWriter(outPutFilePath +"scenarioMinimal.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf(scenario.toGherkinsFile());
        printWriter.close();

        assert (scenario.toGherkinsFile().contains("\tScenario: User is searching from the home page\n"));
        assert (scenario.toGherkinsFile().contains("\t\tGIVEN the user is on the home page\n"));
    }

    @Test
    public void createScenarioPythonFile() throws IOException
    {
        Scenario scenario = new Scenario();

        //Add dependencies
        String dependencies = "import unittest" + newLine;
        dependencies += "from selenium import webdriver" + newLine;
        dependencies += "from selenium.webdriver.common.keys import Keys" + newLine;
        scenario.setPythonDependencies(new PythonDependencies(1,"name","description", dependencies));

        //Add pythonDefSetup
        PythonDefSetup pythonDefSetup = new PythonDefSetup(1,"Python Setup", "This is a description");
        ArrayList<Module> modulesDefSetup = new ArrayList<Module>();

        String snippetDefSetup = indentationOnce + "def setUp(self):" + newLine;
        snippetDefSetup += indentationTwice + "self.driver = webdriver.Remote(" + newLine;
        snippetDefSetup += indentationOnce + indentationTwice + "command_executor='http://localhost:4444/wd/hub'," + newLine;
        snippetDefSetup += indentationOnce + indentationTwice +
                "desired_capabilities={'browserName': 'firefox', 'version': '3', 'javascriptEnabled': True})" +
                newLine;

        modulesDefSetup.add(new Module(1,"Setup module", snippetDefSetup));
        pythonDefSetup.setModules(modulesDefSetup);

        scenario.setPythonDefSetup(pythonDefSetup);

        scenario.setSteps(buildSteps());

        // Add pythonDefTearDown
        PythonDefTearDown pythonDefTearDown = new PythonDefTearDown(1,"Python TearDown", "This is a description");
        ArrayList<Module> modulesDefTearDown = new ArrayList<Module>();

        String snippetDefTearDown = indentationOnce + "def tearDown(self):" + newLine;
        snippetDefTearDown += indentationTwice + "self.driver.close()" + newLine;

        modulesDefTearDown.add(new Module(1,"Setup module", snippetDefTearDown));
        pythonDefTearDown.setModules(modulesDefTearDown);

        scenario.setPythonDefTearDown(pythonDefTearDown);

        //log and file
        log.info(scenario.toPython());

        FileWriter fileWriter = new FileWriter(outPutFilePath +"scenarioPythonFile.py");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf(scenario.toPython());
        printWriter.close();

        //validations
        assert (scenario.toPython().contains("import unittest\n"));
        assert (scenario.toPython().contains("from selenium import webdriver\n"));
        assert (scenario.toPython().contains("from selenium.webdriver.common.keys import Keys\n"));
        assert (scenario.toPython().contains("\tdef setUp(self):\n"));
        assert (scenario.toPython().contains("\t\tself.driver = webdriver.Remote(\n"));
        assert (scenario.toPython().contains("\t\t\tcommand_executor='http://localhost:4444/wd/hub',\n"));
        assert (scenario.toPython().contains("\t\t\tdesired_capabilities={'browserName': 'firefox', 'version': '3', 'javascriptEnabled': True})\n"));
        assert (scenario.toPython().contains("\tdef tearDown(self):\n"));
        assert (scenario.toPython().contains("\t\tself.driver.close()\n"));
        assert (scenario.toPython().contains("\tdef test(self):\n"));
        assert (scenario.toPython().contains("\t\tdriver = self.driver\n"));
        assert (scenario.toPython().contains("\t\tassert \"No results found.\" not in driver.page_source\n"));

    }

    public ArrayList<Step> buildSteps ()
    {
        ArrayList<Step> steps = new ArrayList<Step>();

        //Step 1
        String snippet = indentationTwice + "driver = self.driver" + newLine;
        snippet += indentationTwice + "driver.get(\"http://www.python.org\")" + newLine;
        snippet += indentationTwice + "self.assertIn(\"Python\", driver.title)" + newLine;
        steps.add(buildStep(1,GherkinsKeyword.GIVEN,"the user is on the home page",snippet,1));

        //Step 2
        snippet = indentationTwice + "elem = driver.find_element_by_name(\"q\")" + newLine;
        snippet += indentationTwice + "elem.send_keys(\"pycon\")" + newLine;
        snippet += indentationTwice + "elem.send_keys(Keys.RETURN)" + newLine;
        steps.add(buildStep(2,GherkinsKeyword.WHEN,"the user enters the keyword \"pycon\"",snippet,1));

        //Step 3
        snippet = indentationTwice + "assert \"No results found.\" not in driver.page_source" + newLine;
        steps.add(buildStep(3,GherkinsKeyword.THEN,"the user does not see the error message \"No results found.\"",snippet,1));

        return steps;
    }

    private Step buildStep(Integer id, GherkinsKeyword gherkinsKeyword, String phrase, String snippet,
                           Integer moduleId)
    {
        Step step = new Step(id,gherkinsKeyword,phrase);
        Module module = new Module(moduleId,"Module name", snippet);
        module.setPosition(1);
        module.setComment("This is a comment");
        ArrayList<Module> modules = new ArrayList<Module>();
        modules.add(module);
        step.setModules(modules);

        return step;
    }
}
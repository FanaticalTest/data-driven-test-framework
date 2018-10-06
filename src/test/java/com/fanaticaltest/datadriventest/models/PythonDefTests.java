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
public class PythonDefTests {

    private static final Logger log = LoggerFactory.getLogger(PythonDefTests.class);
    private static final String outPutFilePath = "./src/test/resources/outputFiles/";
    private static final String indentationOnce = "\t";
    private static final String indentationTwice = "\t\t";
    private static final String newLine = "\n";

    @Test
    public void createPythonDefSetup() throws IOException
    {
        PythonDefSetup pythonDefSetup = new PythonDefSetup("Python Setup", "This is a description");
        ArrayList<Module> modules = new ArrayList<Module>();

        String snippet = indentationOnce + "def setUp(self):" + newLine;
        snippet += indentationTwice + "self.driver = webdriver.Remote(" + newLine;
        snippet += indentationOnce + indentationTwice + "command_executor='http://localhost:4444/wd/hub'," + newLine;
        snippet += indentationOnce + indentationTwice +
                "desired_capabilities={'browserName': 'firefox', 'version': '3', 'javascriptEnabled': True})" +
                newLine;

        Module module = new Module("Setup module", snippet);

        modules.add(module);
        pythonDefSetup.setModules(modules);

        log.info(module.toPython());

        FileWriter fileWriter = new FileWriter(outPutFilePath +"pythonDefSetup.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf(pythonDefSetup.toPython());
        printWriter.close();

        assert (pythonDefSetup.toPython().contains("\tdef setUp(self):\n"));
        assert (pythonDefSetup.toPython().contains("\t\tself.driver = webdriver.Remote(\n"));
        assert (pythonDefSetup.toPython().contains("\t\t\tcommand_executor='http://localhost:4444/wd/hub',\n"));
        assert (pythonDefSetup.toPython().contains("\t\t\tdesired_capabilities={'browserName': 'firefox', 'version': '3', 'javascriptEnabled': True})\n"));
    }

    @Test
    public void createPythonDefTearDown() throws IOException
    {
        PythonDefTearDown pythonDefTearDown = new PythonDefTearDown("Python TearDown", "This is a description");
        ArrayList<Module> modules = new ArrayList<Module>();

        String snippet = indentationOnce + "def tearDown(self):" + newLine;
        snippet += indentationTwice + "self.driver.close()" + newLine;

        Module module = new Module("Setup module", snippet);

        modules.add(module);
        pythonDefTearDown.setModules(modules);

        log.info(pythonDefTearDown.toPython());

        FileWriter fileWriter = new FileWriter(outPutFilePath +"pythonDeftearDown.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf(pythonDefTearDown.toPython());
        printWriter.close();

        assert (pythonDefTearDown.toPython().contains("\tdef tearDown(self):\n"));
        assert (pythonDefTearDown.toPython().contains("\t\tself.driver.close()\n"));
    }

}
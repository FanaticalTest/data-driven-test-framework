package com.fanaticaltest.datadriventest.models;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StepTests {

    private static final Logger log = LoggerFactory.getLogger(StepTests.class);
    private static final String outPutFilePath = "./src/test/resources/outputFiles/";
    private static final String indentation = "\t\t";
    private static final String newLine = "\n";

    @Test
    public void createStep() throws IOException
    {
        Step step = new Step(GherkinsKeyword.WHEN,"the user enters the keyword \"pycon\"");
        String stringValidation = "WHEN the user enters the keyword \"pycon\"";

        //Create a module, ArrayList of Module and add it in the Step
        String snippet = indentation + "elem = driver.find_element_by_name(\"q\")" + newLine;
        snippet += indentation + "elem.send_keys(\"pycon\")" + newLine;
        snippet += indentation + "elem.send_keys(Keys.RETURN)" + newLine;
        Module module = new Module("Find element", snippet);
        module.setPosition(1);
        module.setComment("This is a comment");
        ArrayList<Module> modules = new ArrayList<Module>();
        modules.add(module);
        step.setModules(modules);

        log.info(step.toGherkinsFile());

        FileWriter fileWriter = new FileWriter(outPutFilePath +"step.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf(step.toGherkinsFile());
        printWriter.close();

        log.info(step.toPython());

        fileWriter = new FileWriter(outPutFilePath +"stepPython.txt");
        printWriter = new PrintWriter(fileWriter);
        printWriter.printf(step.toPython());
        printWriter.close();

        assert (step.toGherkinsFile().equals(stringValidation));
        assert (step.toPython().contains("\telem = driver.find_element_by_name(\"q\")\n"));
        assert (step.toPython().contains("\telem.send_keys(\"pycon\")\n"));
        assert (step.toPython().contains("\telem.send_keys(Keys.RETURN)\n"));
    }
}
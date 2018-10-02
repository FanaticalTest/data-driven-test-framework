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

@RunWith(SpringRunner.class)
@SpringBootTest
public class StepTests {

    private static final Logger log = LoggerFactory.getLogger(StepTests.class);
    private static final String outPutFilePath = "./src/test/resources/outputFiles/";

    @Test
    public void createStep() throws IOException
    {
        Step step = new Step(1,GherkinsKeyword.WHEN,"the user enters the keyword \"pycon\"");
        String stringValidation = "WHEN the user enters the keyword \"pycon\"";

        FileWriter fileWriter = new FileWriter(outPutFilePath +"step.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf(step.toString());
        printWriter.close();

        assert (step.toString().equals(stringValidation));
    }
}

package com.fanaticaltest.datadriventest.models;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModulesTests {

    private static final Logger log = LoggerFactory.getLogger(ModulesTests.class);
    private static final String outPutFilePath = "./src/test/resources/outputFiles/";
    private static final String indentation = "\t\t";
    private static final String newLine = "\n";

    @Test
    public void createSnippetFindElement() throws IOException
    {
        String snippet = indentation + "elem = driver.find_element_by_name(\"q\")" + newLine;
        snippet += indentation + "elem.send_keys(\"pycon\")" + newLine;
        snippet += indentation + "elem.send_keys(Keys.RETURN)" + newLine;
        Module module = new Module(1,"Find element", snippet);
        log.info(module.toString());

        FileWriter fileWriter = new FileWriter(outPutFilePath +"snippetFindElement.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf(module.toString());
        printWriter.close();
    }

    @Test
    public void createSnippetFindElementWithParam() throws IOException
    {
        ArrayList<ModuleParameters> mp= new ArrayList<ModuleParameters>();
        mp.add(new ModuleParameters(1,"var","elem"));
        mp.add(new ModuleParameters(2,"id_element","q"));
        mp.add(new ModuleParameters(3,"search_value","pycon"));

        String snippet = indentation + "%%var%% = driver.find_element_by_name(\"%%id_element%%\")" + newLine;
        snippet += indentation + "%%var%%.send_keys(\"%%search_value%%\")" + newLine;
        snippet += indentation + "%%var%%.send_keys(Keys.RETURN)" + newLine;
        Module module = new Module(1,"Find element", snippet, mp);
        log.info(module.toString());

        FileWriter fileWriter = new FileWriter(outPutFilePath +"snippetWithParamFindElement.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf(module.toString());
        printWriter.close();
    }

    @Test
    public void compareWithAndWithoutParam()
    {
        String snippet = indentation + "elem = driver.find_element_by_name(\"q\")" + newLine;
        snippet += indentation + "elem.send_keys(\"pycon\")" + newLine;
        snippet += indentation + "elem.send_keys(Keys.RETURN)" + newLine;
        Module module = new Module(1,"Find element", snippet);
        log.info(module.toString());

        ArrayList<ModuleParameters> mp= new ArrayList<ModuleParameters>();
        mp.add(new ModuleParameters(1,"var","elem"));
        mp.add(new ModuleParameters(2,"id_element","q"));
        mp.add(new ModuleParameters(3,"search_value","pycon"));

        String snippetWP = indentation + "%%var%% = driver.find_element_by_name(\"%%id_element%%\")" + newLine;
        snippetWP += indentation + "%%var%%.send_keys(\"%%search_value%%\")" + newLine;
        snippetWP += indentation + "%%var%%.send_keys(Keys.RETURN)" + newLine;
        Module moduleWP = new Module(1,"Find element", snippetWP, mp);
        log.info(moduleWP.toString());

        assert (module.toString().equals(moduleWP.toString()));
    }
}

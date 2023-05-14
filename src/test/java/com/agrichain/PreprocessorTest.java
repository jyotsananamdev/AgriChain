package com.agrichain;

import com.agrichain.drivers.impl.ChromeProvider;
import com.agrichain.drivers.impl.FirefoxProvider;
import com.agrichain.utility.ExcelReader;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.TestNG;
import org.testng.annotations.*;
import org.testng.annotations.Optional;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class PreprocessorTest {
    public static WebDriver driver = null;
    private Properties prop;
    private final String propertyFilePath= "src/main/resources/Environment.properties";
    public static String url;
    public static String env_excel;
    public static String browser;
    public ExcelReader er;

    @BeforeMethod(alwaysRun=true)
    public void startUp()
    {
        new PreprocessorTest().readConstants();
        if(browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeProvider().createDriver();
        }
        else if(browser.equalsIgnoreCase("firefox")){
            driver = new FirefoxProvider().createDriver();
        }else {
            driver = new ChromeProvider().createDriver();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public static WebDriver getDriver()
    {
        return driver;
    }

    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }

    public  void readConstants()
    {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            prop = new Properties();
            try {
                prop.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Environment.properties not found at " + propertyFilePath);
        }
        if(prop.getProperty("url")!= null)
            url= prop.getProperty("url");
        if(prop.getProperty("enviornmentexcel")!= null)
            env_excel= prop.getProperty("enviornmentexcel");
        if(prop.getProperty("browser")!= null)
            browser= prop.getProperty("browser");
    }

    public void  preProcessor() throws Exception  {
        readConstants();
        dynamicTestNGXML();
    }

    public void dynamicTestNGXML() throws IOException {


        HashMap<String, HashMap<String, String>> returnData = er.getTestData("testcases");
        HashMap<String, String> testData = new HashMap<String, String>();
        List<String> moduleList = new ArrayList<>();
        for (Map.Entry<String, HashMap<String, String>> map : returnData.entrySet()) {
            if (map.getValue().get("Execute").equalsIgnoreCase("yes")) {
                moduleList.add(map.getValue().get("Module"));
            }
        }
       // logger.info("Generating testNG from previous run status sheet...");
        XmlSuite suite = new XmlSuite();
        suite.setName("automation");
        XmlTest testTag = new XmlTest(suite);
        testTag.setName("automationTests");
       // testTag.addParameter("browser","chrome");
        suite.setThreadCount(1);

        suite.setParallel(XmlSuite.ParallelMode.CLASSES);


        //testTag.setThreadCount();
        List<XmlClass> classes = new ArrayList<XmlClass>();

        XmlClass classtmp = null;
        for (int i = 0; i < moduleList.size(); i++) {
            try {
                classtmp = new XmlClass("com.agrichain.tests." + moduleList.get(i));
            } catch (Exception e) {

                System.exit(0);
            }
            classes.add(classtmp);
        }


        testTag.setXmlClasses(classes);
        List<XmlSuite> suite_a = new ArrayList<XmlSuite>();
        suite_a.add(suite);
        TestNG tng = new TestNG();
        tng.setXmlSuites(suite_a);

        System.out.println(suite.toXml());

        tng.run();

    }
}

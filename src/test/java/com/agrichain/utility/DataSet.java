package com.agrichain.utility;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataSet  {

    public static ExcelReader er;


    @DataProvider(name = "testData")
    public Object[][] getTestData() throws IOException {
        // Create a HashMap with test data
        System.out.println("hello");
        HashMap<String, HashMap<String, String>> returnData=er.getTestData("testdata");
        HashMap<String, String> testData = new HashMap<String, String>();
        for(Map.Entry<String, HashMap<String, String>> map: returnData.entrySet())
        {
            testData.put(map.getValue().get("InputData"), map.getValue().get("outputData"));
        }
        // Create a 2D array to hold data
        Object[][] data = new Object[testData.size()][2];

        // Iterate through the HashMap and add data to array
        int i = 0;
        for (Map.Entry<String, String> entry : testData.entrySet()) {
            data[i][0] = entry.getKey();
            data[i][1] = entry.getValue();
            i++;
        }

        // Return data array
        return data;
    }
}

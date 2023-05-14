
package com.agrichain;

import org.testng.annotations.Test;


public class Runner {


    @Test
    public void testSuite() {

        try {
            new PreprocessorTest().preProcessor();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
    }
}
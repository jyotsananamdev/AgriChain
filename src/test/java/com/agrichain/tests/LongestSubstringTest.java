package com.agrichain.tests;
import com.agrichain.BaseTest;
import com.agrichain.utility.DataSet;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;


@Listeners(com.agrichain.utility.ExecutionCycle.class)

public class LongestSubstringTest extends BaseTest {

    @Test(dataProvider = "testData",dataProviderClass= DataSet.class)
    public void longestSubstring(String input,String output)
    {
        cPage.openUrl(url);
        System.out.println("input: "+input+"output: "+output );
        lPage.enterValue(input);
        String result= lPage.getResult();
        Assert.assertEquals(output,result);


    }
}

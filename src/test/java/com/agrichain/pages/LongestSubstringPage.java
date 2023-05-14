package com.agrichain.pages;

import com.agrichain.CommonTest;
import org.openqa.selenium.By;

public class LongestSubstringPage extends CommonTest {
    By input =By.id("input");
    By submit =By.id("submit");
    By result= By.id("result");
    public void enterValue(String text)
    {
        sendKeys(input,text);
        click(submit);
    }
    public String getResult()
    {
        return getText(result);
    }


}

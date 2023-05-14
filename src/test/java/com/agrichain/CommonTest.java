package com.agrichain;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CommonTest  extends PreprocessorTest {
WebDriverWait wait = null;
Map<String, String> map= new ConcurrentHashMap<String, String>();

    /*Method used to click on element by-
    * pass by(arg) */
    public void click(By by)
    {
        waitForElementToBeClickable(by, 30);
        getDriver().findElement(by).click();
    }

    /*Method used to sendKeys on element*/
    public void sendKeys(By by,String inputText)
    {
        waitForElementToBeVisible(by, 30);
        find(by).sendKeys(inputText);
    }

    /*Method used to wait for Webelement to be visible*/
    public void waitForElementToBeVisible(By by, long sec)
    {
        wait = new WebDriverWait(getDriver(), sec);
        wait.until(ExpectedConditions.visibilityOf(find(by)));
    }

    /*Method used to wait for Webelement to be clickable*/
    public void waitForElementToBeClickable(By by, long sec)
    {
        wait = new WebDriverWait(getDriver(), sec);
        wait.until(ExpectedConditions.visibilityOf(find(by)));
    }

    /*Method used to verify Webelement is present on the page or not*/
    public Boolean isElementPresent(By by)
    {
        int count= getDriver().findElements(by).size();
        return count>0;
    }

    /*Method used to return Webelement*/
    public WebElement find(By by)
    {
        return getDriver().findElement(by);
    }

    /*Method used to return List<Webelement>*/
    public List<WebElement> finds(By by)
    {
        return getDriver().findElements(by);
    }

    /*Method used to get attribute value of webelement*/
    public String getAttributeValue(By by, String attribute)
    {
        return find(by).getAttribute(attribute);
    }

    /*Method used to get text of webelement*/
    public String getText(By by)
    {
        waitForElementToBeVisible(by, 20);
        return find(by).getText();
    }

    /*Method used to get value of webelement*/
    public String getValue(By by)
    {
        waitForElementToBeVisible(by, 20);
        return find(by).getAttribute("value");
    }

    /*Method used to open Url*/
    public void openUrl(String url)
    {
        getDriver().get(url);
    }

    /*Method used to return current url*/
    public String getPageURL()
    {
        return getDriver().getCurrentUrl();
    }

    /*Method used to select from dropdown by value*/
    public void selectByValue(By by, String value)
    {
        Select sel= new Select(find(by));
        sel.selectByValue(value);
    }

    /*Method used to select from dropdown by visible text*/
    public void selectByText(By by, String visibleText)
    {
        Select sel= new Select(find(by));
        sel.selectByVisibleText(visibleText);
    }

    public void sleep(int n)
    {
        try {
            Thread.sleep(1000*n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*Method used to switch on child window*/
    public void switchWindow()
    {
        //Get handles of the windows
        String mainWindowHandle= getDriver().getWindowHandle();
        Set<String> allWindowHandles = getDriver().getWindowHandles();
        Iterator<String> iterator = allWindowHandles.iterator();

        // Here we will check if child window has other child windows and will fetch the heading of the child window
        while (iterator.hasNext()) {
            String ChildWindow = iterator.next();
            if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
                getDriver().switchTo().window(ChildWindow);
            }
        }
    }

}

package utils.helpers;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.driver.WebDrivers;
import utils.helpers.elementHelper.ElementMap;
import utils.helpers.elementHelper.ElementResponse;
import utils.helpers.elementHelper.Elements;
import java.util.concurrent.TimeUnit;
import java.time.Duration;

public class Helper {
    public WebDriver webDriver = null;

    public static PropertyManager propertyManager = new PropertyManager();

    WebDrivers webDrivers = new WebDrivers();

    public Helper() {
        webDriver = webDrivers.createAndGetDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().deleteAllCookies();
        webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    public void tearDown() {
        webDriver.quit();
        WebDrivers.clearSession();
    }

    public WebElement waitUntilElementIsVisible(WebElement element, long startTime) {
        webDriver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        WebDriverWait subWait = new WebDriverWait(webDriver, Duration.ofMillis(1));
        if ((System.currentTimeMillis() - startTime) > 10000) {
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return null;
        }
        try {
            subWait.until(ExpectedConditions.visibilityOf(element));
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return element;
        } catch (StaleElementReferenceException | TimeoutException e) {
            return waitUntilElementIsVisible(element, startTime);
        }
    }

    public WebElement waitUntilElementIsClickable(WebElement element, long startTime) {
        WebDriver subDriver = webDriver;
        subDriver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        WebDriverWait subWait = new WebDriverWait(subDriver, Duration.ofMillis(1));
        if ((System.currentTimeMillis() - startTime) > 15000) {
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return null;
        }
        try {
            subWait.until(ExpectedConditions.elementToBeClickable(element));
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return element;
        } catch (StaleElementReferenceException | ElementClickInterceptedException | TimeoutException e) {
            System.out.println(e.getMessage());
            return waitUntilElementIsClickable(element, startTime);
        }
    }

    public WebElement centerElement(WebElement element) {

        String scrollScript = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

        ((JavascriptExecutor) webDriver).executeScript(scrollScript, element);
        waitForGivenTime(1);
        return element;
    }

    public void waitForGivenTime(int seconds)  {
        long milliseconds = (seconds * 1000L);
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Assert.fail(e.getMessage());
        }
    }

    public By getBy(String key) {
        ElementResponse elementInfo = ElementMap.INSTANCE.findElementInfoByKey(key);
        return Elements.getElementInfoToBy(elementInfo);
    }

    public WebElement getElementWithText(String text) {
        try {
            return webDriver.findElement(By.xpath("//*[text()='" + text + "']"));
        } catch (ElementNotFoundException e) {
            Assert.fail(e.getMessage());
            return null;
        }
    }

    public void checkElementsForPage() {
        String state = (String) ((JavascriptExecutor) webDriver).executeScript("return document.readyState");
        long startTime = System.currentTimeMillis();
        while (!state.equals("complete") && (System.currentTimeMillis() - startTime) < 7000) {
            waitForGivenTime(1);
            state = (String) ((JavascriptExecutor) webDriver).executeScript("return document.readyState");
    }
    }
}

package actions;

import org.junit.Assume;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.helpers.Helper;
import utils.helpers.dataStoreHelper.DataStoreMap;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Action extends Helper {

    public WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));

    DataStoreMap dataStoreMap = new DataStoreMap();

    public void clickElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (TimeoutException e) {
            Assert.fail(e.getMessage());
        }
    }

    public WebElement findElement(String key) {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 7000) {
            try {
                return wait.until(ExpectedConditions.presenceOfElementLocated(getBy(key)));
            } catch (StaleElementReferenceException e) {
                System.out.println(e);
            }
        }
        return null;
    }

    public List<WebElement> findElements(String key) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getBy(key)));
    }

    public void clearAndFillInput(String key, String text) {
        try {
            centerElement(waitUntilElementIsVisible(findElement(key), System.currentTimeMillis())).clear();
            centerElement(waitUntilElementIsVisible(findElement(key), System.currentTimeMillis())).sendKeys(text);
        } catch (NoSuchElementException  e) {
            Assert.fail(e.getMessage());
        }
    }

    public void waitForTime(int seconds) {
        waitForGivenTime(seconds);
    }

    public void verifyUrl(String url) {
        Assert.assertTrue(webDriver.getCurrentUrl().contains(url));
    }

    public void assertTextEqual(WebElement key, String text) {
        String textProduct = wait.until(ExpectedConditions.visibilityOf(key)).getText();
        Assert.assertEquals(textProduct, text);
    }

    public void clickRandomProduct(String key) {
        List<WebElement> elementsList = findElements(key);
        if (!elementsList.isEmpty()) {
            WebElement[] elementsArray = elementsList.toArray(new WebElement[elementsList.size()]);
            Random rand = new Random();
            WebElement randomElement = elementsArray[rand.nextInt(elementsArray.length)];
            clickElement(randomElement);
        }
    }

    public void keepRunningCodesNewTab() {
        for (String winHandle : webDriver.getWindowHandles()) {
            webDriver.switchTo().window(winHandle);
        }
    }

    public void clickElementWithText(String value) {
        try {
            centerElement(waitUntilElementIsClickable(getElementWithText(value), System.currentTimeMillis())).click();
        }  catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void dropDownSelect(WebElement key, String value) {
        centerElement(waitUntilElementIsVisible(key, System.currentTimeMillis())).click();
        clickElementWithText(value);
    }

    public void clickCenterElement(WebElement key) {
        centerElement(waitUntilElementIsClickable(key, System.currentTimeMillis())).click();
    }

    public void checkNoReviews() {
        List<WebElement> element = webDriver.findElements(By.className("hermes-ProductRate-module-iigXxhEaE3_4WHSctvzs"));
        if(element.size()==1)
        {Assert.assertTrue(true,"There is no reviews");Assume.assumeTrue(false);}
    }

    public void checkInProductDetailLikeButton(WebElement key) {
        String text = centerElement(waitUntilElementIsVisible(key, System.currentTimeMillis())).getText();
        if(text.equals("Beğendin"))
        {Assert.assertTrue(true,"He/She already has liked this product");}
        else
        {clickCenterElement(key);}
    }

    public void findElementAndSetTextToDataStore(WebElement key, String text) {
        String productPrice = centerElement(waitUntilElementIsVisible(key, System.currentTimeMillis())).getText();
        dataStoreMap.setContext(text, productPrice);
    }

    public void checkTwoStoredValueEquality(String textFirst, String textSecond) {
        Assert.assertEquals(dataStoreMap.getContext(textFirst), dataStoreMap.getContext(textSecond));
    }
}
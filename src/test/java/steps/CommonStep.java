package steps;

import io.cucumber.java.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CommonPage;

public class CommonStep {

    public CommonPage page;

    @Before
        public void setUp(){
        page = new CommonPage();
    }

    @After
    public void tearDown() {
        page.tearDown();
    }

    @Then("Click {string} element")
    public void click(String key) {
        page.click(key);
    }

    @And("Fill {string} field with {string}")
    public void clearAndFill(String key, String text) {
        page.clearAndFill(key, text);
    }

    @And("Wait for given seconds {int}")
    public void waitForGivenSeconds(int seconds) {
        page.waitForTime(seconds);
    }

    @When("Check page url contains {string}")
    public void checkPageUrlContains(String url) {page.verifyUrl(url);}

    @Then("Check equality of element text {string} and with text {string}")
    public void checkTextEquals(String key,String text) {
        page.assertTextEquals(key,text);
    }

    @And("Click {string} random product")
    public void randomProduct(String key) {
        page.clickRandomProduct(key);
    }

    @And("Keep running codes in new tab")
    public void keepRunningCodesNewTabs() {
        page.keepRunningCodesNewTab();
    }

    @And("Click {string} drop down and select {string} with text")
    public void clickStringDropDownAndSelectText(String key, String value) {
        page.dropDown(key, value);
    }

    @And("Click {string} center element")
    public void clickCenterElementButton(String key) {
        page.clickCenterElementButton(key);
    }

    @Then("Check with element if there is no reviews")
    public void checkNoReview() {
        page.checkNoReviews();
    }

    @Then("Check elements for Pages")
    public void checkElementsForPages() {
        page.checkElementsForPage();
    }

    @Then("Check {string} like button for product")
    public void checkInProductDetailLike(String test) {
        page.checkInProductDetailLike(test);
    }

    @And("Get {string} text of field and assign it to variable {string}")
    public void elementGetText(String key, String text) {
        page.elementGetText(key, text);
    }

    @Then("Check get first variable {string} and get second variable {string}")
    public void checkAndCompare2Variables(String textFirst, String textSecond) {
        page.checkTwoStoredValueEquality(textFirst, textSecond);
    }
}
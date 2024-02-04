package utils.driver;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utils.helpers.PropertyManager;
import utils.helpers.Helper;

public class DriverFactory {
    private WebDriver driver;
    String browserName;
    String domain;
    public  WebDriver initializeAndGetChromeDrivers(){
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        PropertyManager propertyManager = Helper.propertyManager;
        browserName = propertyManager.getProperty("env.properties","browser");
        domain = propertyManager.getProperty("env.properties", "domain");
        switch (browserName.toLowerCase()){
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("disable-notifications");
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "/drivers/chromedriver.exe");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+ "/drivers/geckodriver.exe");
                driver = new FirefoxDriver(firefoxOptions);
                break;
            default:
                Assert.fail("Please your choose browser");
                return null ;
        }
        driver.get(domain);
        return driver;
    }
}

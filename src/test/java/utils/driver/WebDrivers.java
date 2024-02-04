package utils.driver;

import org.openqa.selenium.WebDriver;

public class WebDrivers {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    DriverFactory driverFactory = new DriverFactory();

    public WebDriver createAndGetDriver() {

        if (driver.get() != null) {
            driver.get().quit();
        }
        driver.set(driverFactory.initializeAndGetChromeDrivers());
        return driver.get();
    }

    public static void clearSession() { driver.set(null); }
}
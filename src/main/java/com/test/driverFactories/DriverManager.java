package com.test.driverFactories;

import org.openqa.selenium.WebDriver;

import java.util.Objects;

public abstract class DriverManager {

    protected WebDriver driver;

    public abstract WebDriver createDriver();

    /**
     * Quit driver
     */
    public void quitDriver() {
        if (Objects.nonNull(driver)) {
            driver.quit();
            driver = null;
        }
    }

    /**
     * Get driver
     *
     * @return WebDriver
     */
    public WebDriver getDriver() {
        if (Objects.isNull(driver)) {
            driver = createDriver();
        }
        return driver;
    }

}

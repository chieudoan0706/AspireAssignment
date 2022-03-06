package com.test.pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.of(30, ChronoUnit.SECONDS));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.of(15, ChronoUnit.SECONDS));
    }

    public abstract boolean isAt();

}

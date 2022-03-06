package com.test.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {

    private static final Logger logger = LogManager.getLogger(LoginPage.class.getCanonicalName());
    @FindBy(css = "#login")
    WebElement emailInput;
    @FindBy(css = "#password")
    WebElement passwordInput;
    @FindBy(xpath = "//button[text()='Log in']")
    WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void goTo(String url) {
        logger.info("[Action]: Go to url");
        driver.get(url);
    }

    public void clickLoginButton() {
        logger.info("[Action]: Click on Login button");
        loginButton.click();
    }

    public void inputEmail(String email) {
        logger.info("[Action]: Input email {} to username input", email);
        emailInput.sendKeys(email);
    }

    public void inputPassword(String password) {
        logger.info("[Action]: Input password {} to password input", password);
        passwordInput.sendKeys(password);
    }

    public void loginToAspireOdo(String email, String password) {
        logger.info("[Action]: Login to Aspire Odo");
        inputEmail(email);
        inputPassword(password);
        clickLoginButton();
    }

    @Override
    public boolean isAt() {
        logger.info("[Checkpoint]: Verify that we are at page {}", LoginPage.class.getSimpleName());
        return wait.until(ExpectedConditions.visibilityOf(loginButton)).isDisplayed();
    }

}

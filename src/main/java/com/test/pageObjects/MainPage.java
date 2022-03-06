package com.test.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends AbstractPage {

    private static final Logger logger = (Logger) LogManager.getLogger(MainPage.class.getCanonicalName());
    @FindBy(xpath = "//div[text()='Inventory']")
    private WebElement inventoryIcon;
    @FindBy(xpath = "//a[@id='result_app_2']")
    private WebElement manufacturingIcon;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickOnInventoryIcon() {
        logger.info("[Action]: Click on Inventory icon");
        inventoryIcon.click();
    }

    public void clickOnManufacturingIcon() {
        logger.info("[Action]: Click on Manufacturing icon");
        manufacturingIcon.click();
    }

    @Override
    public boolean isAt() {
        logger.info("[Checkpoint]: Verify that we are at page {}", MainPage.class.getSimpleName());
        return wait.until(ExpectedConditions.visibilityOf(inventoryIcon)).isDisplayed();
    }
}

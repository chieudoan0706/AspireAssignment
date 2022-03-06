package com.test.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InventoryPage extends AbstractPage {

    private static final Logger logger = LogManager.getLogger(InventoryPage.class.getCanonicalName());
    @FindBy(xpath = "//span[text()='Inventory Overview']")
    private WebElement inventoryBreadCrumb;
    @FindBy(xpath = "//span[text()='Products']")
    private WebElement productsMenuToggle;
    @FindBy(xpath = "//a[text()='Products']")
    private WebElement productsSubMenu;

    public InventoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickOnProductsMenuToggle() {
        logger.info("[Action]: Click on Products toggle");
        productsMenuToggle.click();
    }

    public void clickOnProductsSubMenu() {
        logger.info("[Action]: Click on Products sub menu");
        productsSubMenu.click();
    }

    @Override
    public boolean isAt() {
        logger.info("[Checkpoint]: Verify that we are at page {}", InventoryPage.class.getSimpleName());
        return wait.until(ExpectedConditions.visibilityOf(inventoryBreadCrumb)).isDisplayed();
    }

}

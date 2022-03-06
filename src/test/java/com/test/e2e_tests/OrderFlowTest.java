package com.test.e2e_tests;

import com.google.common.util.concurrent.Uninterruptibles;
import com.test.driverFactories.DriverManager;
import com.test.driverFactories.DriverManagerFactory;
import com.test.driverFactories.DriverType;
import com.test.pageObjects.*;
import com.test.utilities.RandomValueGenerator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class OrderFlowTest extends AbstractTest {

    DriverManager driverManager;
    WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private InventoryPage inventoryPage;
    private ProductPage productPage;
    private ManufactoringPage manufactoringPage;

    private String productName;

    @BeforeClass
    public void setUp() {
        driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
        driver = driverManager.getDriver();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        inventoryPage = new InventoryPage(driver);
        productPage = new ProductPage(driver);
        manufactoringPage = new ManufactoringPage(driver);
        productName = RandomValueGenerator.getProductName();
    }

    @Test
    @Parameters({"url"})
    public void launchUrl(String url) {
        loginPage.goTo(url);
        loginPage.isAt();
    }

    @Test(dependsOnMethods = {"launchUrl"})
    @Parameters({"email", "password"})
    public void login(String email, String password) {
        loginPage.loginToAspireOdo(email, password);
        mainPage.isAt();
    }

    @Test(dependsOnMethods = {"login"})
    public void navigateInventoryFeature() {
        mainPage.clickOnInventoryIcon();
        inventoryPage.isAt();
    }

    @Test(dependsOnMethods = {"navigateInventoryFeature"})
    public void goToProductsPage() {
        inventoryPage.clickOnProductsMenuToggle();
        inventoryPage.clickOnProductsSubMenu();
        productPage.isAt();
    }

    @Test(dependsOnMethods = {"goToProductsPage"})
    public void createNewProduct() {
        productPage.createNewProduct(productName);
        Uninterruptibles.sleepUninterruptibly(10, TimeUnit.SECONDS);
    }

    @Test(dependsOnMethods = {"createNewProduct"})
    public void updateQuantity() {
        productPage.updateQuantity();
    }

    @Test(dependsOnMethods = {"updateQuantity"})
    public void clickOnApplicationMenu() {
        productPage.clickOnApplicationIcon();
    }

    @Test(dependsOnMethods = {"clickOnApplicationMenu"})
    public void clickOnManufacturingIcon() {
        mainPage.clickOnManufacturingIcon();
        manufactoringPage.isAt();
    }

    @Test(dependsOnMethods = {"clickOnManufacturingIcon"})
    public void createManufactoringOrder() throws Exception {
        manufactoringPage.createOrder(productName);
    }

    @Test(dependsOnMethods = {"createManufactoringOrder"})
    public void updateManufactoringOrder() {
        manufactoringPage.clickOnMarkAsDoneButton();
        manufactoringPage.clickOnOkButton();
        manufactoringPage.clickOnApplyButton();
    }

    @Test(dependsOnMethods = {"updateManufactoringOrder"})
    public void verifyOrderDetails() {
        manufactoringPage.verifyOrderDetails(productName);
    }

    @AfterClass
    public void tearDown() {
        driverManager.quitDriver();
    }
}

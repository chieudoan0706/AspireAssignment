package com.test.pageObjects;

import com.test.constants.ProductConstants;
import com.test.utilities.RandomValueGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ProductPage extends AbstractPage {

    private static final Logger logger = (Logger) LogManager.getLogger(ProductPage.class.getCanonicalName());
    @FindBy(xpath = "//ol[@class='breadcrumb']//span[text()='Products']")
    private WebElement productBreadCrumb;
    @FindBy(xpath = "//div[@class='o_action_manager']//div[@class='o_control_panel']/div[@class='o_cp_bottom']//button[contains(text(), 'Create')]")
    private WebElement createButton;
    @FindBy(xpath = "//button[contains(text(), 'Edit')]")
    private WebElement editButton;
    @FindBy(xpath = "//input[@name='name']")
    private WebElement productNameInput;
    @FindBy(xpath = "//div[@class='o_content']//div[@class='tab-content']//table/tbody/tr/td/select[@name='detailed_type']")
    private WebElement productTypeDropdown;
    @FindBy(xpath = "//label[text()='Unit of Measure']/../following-sibling::td//input")
    private WebElement productUnitOfMeasure;
    @FindBy(xpath = "//ul[contains(@class,'ui-menu')]/li[contains(@class,'ui-menu-item')]/a")
    private List<WebElement> dropdownOptions;
    @FindBy(xpath = "//label[text()='Purchase UoM']/../following-sibling::td//input")
    private WebElement purchaseUnitOfMeasure;
    @FindBy(xpath = "//label[text()='Internal Reference']/../following-sibling::td//input")
    private WebElement internalReferenceInput;
    @FindBy(xpath = "//label[text()='Barcode']/../following-sibling::td//input")
    private WebElement barCodeInput;
    @FindBy(xpath = "//button[@title='Save record' and contains(text(), 'Save')]")
    private WebElement saveButton;
    @FindBy(xpath = "//button[descendant::text()='Update Quantity']")
    private WebElement updateQuantityButton;
    @FindBy(xpath = "//div[@class='o_content']//table[contains(@class, 'o_list_table_ungrouped')]/tbody/tr/td/input[@name='inventory_quantity']")
    private WebElement countedQuantityField;
    @FindBy(xpath = "//header/nav/a[@title = 'Home menu']")
    private WebElement applicationIcon;

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private void clickOnCreateButton() {
        logger.info("[Action]: Click on Create button");
        wait.until(ExpectedConditions.elementToBeClickable(createButton));
        createButton.click();
    }

    private void inputProductName(String productName) {
        logger.info("[Action]: Input product name with text {}", productName);
        wait.until(ExpectedConditions.visibilityOf(productNameInput));
        productNameInput.sendKeys(productName);
    }

    private void selectProductType() {
        logger.info("[Action]: Select product type");
        Select typeDropdown = new Select(productTypeDropdown);
        typeDropdown.selectByVisibleText(ProductConstants.TypeConstants.STORABLE_PRODUCT.getType());
    }

    private void clickOnSaveButton() {
        logger.info("[Action]: Click on Save button");
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveButton.click();
    }

    private void selectProductUnitOfMeasure() {
        logger.info("[Action]: Select product unit of measure");
        productUnitOfMeasure.click();
    }

    private void selectPurchaseUnitOfMeasure() {
        logger.info("[Action]: Select purchase unit of measure");
        purchaseUnitOfMeasure.click();
    }

    private void clickOnOptionWithText(String text) {
        logger.info("[Action]: Click on purchase unit of measure with text {}", text);
        wait.until(ExpectedConditions.visibilityOfAllElements(dropdownOptions));
        WebElement element = dropdownOptions.stream().filter(el -> el.getText().trim().equals(text))
                .findFirst().orElseThrow(() -> new NoSuchElementException("We don't have option with text " + text));
        if (Objects.nonNull(element)) {
            element.click();
        }
    }

    private void inputInternalReference() {
        logger.info("[Action]: Input internal reference");
        internalReferenceInput.clear();
        internalReferenceInput.sendKeys(RandomValueGenerator.getProductInternalReference());
    }

    private void inputBarCode() {
        logger.info("[Action]: Input bar code");
        barCodeInput.clear();
        barCodeInput.sendKeys(RandomValueGenerator.getProductBarCode());
    }

    public void createNewProduct(String productName) {
        clickOnCreateButton();
        inputProductName(productName);
        selectProductType();
        selectProductUnitOfMeasure();
        clickOnOptionWithText(ProductConstants.UnitMeasureConstants.getRandomProductUnitMeasure());
        inputInternalReference();
        inputBarCode();
        clickOnSaveButton();
    }

    private void waitUntilEditButtonPresent() {
        logger.info("[Wait]: Wait until edit button present");
        wait.until(ExpectedConditions.visibilityOf(editButton)).isDisplayed();
    }

    private void clickOnUpdateQuantityButton() {
        logger.info("[Action]: Click on Update Quantity button");
        wait.until(ExpectedConditions.visibilityOf(updateQuantityButton));
        updateQuantityButton.click();
    }

    private void inputCountedQuantity() {
        logger.info("[Action]: Input counted quantity");
        countedQuantityField.sendKeys(String.valueOf(RandomValueGenerator.getRandomNumber(11, 100)));
    }

    public void updateQuantity() {
        waitUntilEditButtonPresent();
        clickOnUpdateQuantityButton();
        clickOnCreateButton();
        inputCountedQuantity();
        clickOnSaveButton();
    }

    public void clickOnApplicationIcon() {
        logger.info("[Action]: Click on Application Menu");
        applicationIcon.click();
    }

    @Override
    public boolean isAt() {
        logger.info("[Checkpoint]: Verify that we are at page {}", ProductPage.class.getSimpleName());
        return wait.until(ExpectedConditions.visibilityOf(productBreadCrumb)).isDisplayed();
    }
}

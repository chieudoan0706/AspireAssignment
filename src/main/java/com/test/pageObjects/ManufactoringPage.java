package com.test.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ManufactoringPage extends AbstractPage {

    private static final Logger logger = (Logger) LogManager.getLogger(ManufactoringPage.class.getCanonicalName());
    @FindBy(xpath = "//*[text()='Manufacturing Orders']")
    private WebElement pageLabel;
    @FindBy(xpath = "//div[@class='o_action_manager']//div[@class='o_control_panel']/div[@class='o_cp_bottom']//button[contains(text(), 'Create')]")
    private WebElement createButton;
    @FindBy(xpath = "//div[@class='o_content']//div[contains(@class,'o_form_sheet')]//table/tbody/tr/td//div[@name='product_id']//input")
    private WebElement productInput;
    @FindBy(xpath = "//ul[contains(@class,'ui-autocomplete')]/li/a")
    private List<WebElement> productAutoCompletionList;
    @FindBy(xpath = "//button[descendant::text()='Confirm']")
    private WebElement confirmButton;
    @FindBy(xpath = "//button[descendant::text()='Mark as Done' and not(contains(@class,'o_invisible_modifier'))]")
    private WebElement markAsDoneButton;
    @FindBy(xpath = "//div[@class='o_dialog_container']//button[descendant::text()='Ok']")
    private WebElement okButton;
    @FindBy(xpath = "//div[@class='o_content']//table/tbody/tr/td/a[@name='product_id']/span")
    private WebElement productName;
    @FindBy(xpath = "//div[contains(@class,'o_dialog_container')]/div[@class='o_dialog']//div[@class='modal-content']/footer//button[descendant::text()='Apply']")
    private WebElement applyPopupButton;

    public ManufactoringPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private void clickOnCreateButton() {
        logger.info("[Action]: Click on Create button");
        createButton.click();
    }

    private void clickOnConfirmButton() {
        logger.info("[Action]: Click on Confirm button");
        confirmButton.click();
    }

    public void clickOnMarkAsDoneButton() {
        logger.info("[Action]: Click on Mark As Done button");
        wait.until(ExpectedConditions.elementToBeClickable(markAsDoneButton));
        markAsDoneButton.click();
    }

    public void clickOnOkButton() {
        logger.info("[Action]: Click on OK button");
        okButton.click();
    }

    private void inputProductName(String productName) {
        logger.info("[Action]: Input product name {}", productName);
        productInput.sendKeys(productName);
    }

    private void clickOnAutoSuggestionProductWithName(String productName) throws Exception {
        logger.info("[Action]: Click on auto-suggestion product with name {}", productName);
        wait.until(ExpectedConditions.visibilityOfAllElements(productAutoCompletionList));
        productAutoCompletionList.stream().filter(el -> el.getText().trim().contains(productName))
                .findFirst()
                .orElseThrow(() -> new Exception("Can not find auto-suggestion with text " + productName))
                .click();
    }

    public void createOrder(String productName) throws Exception {
        clickOnCreateButton();
        inputProductName(productName);
        clickOnAutoSuggestionProductWithName(productName);
        clickOnConfirmButton();
    }

    public void clickOnApplyButton() {
        applyPopupButton.click();
    }

    public void verifyOrderDetails(String name) {
        assertThat(productName.getText()).contains(name);
    }

    @Override
    public boolean isAt() {
        logger.info("[Checkpoint]: Verify that we are at page {}", ManufactoringPage.class.getSimpleName());
        return wait.until(ExpectedConditions.visibilityOf(pageLabel)).isDisplayed();
    }


}

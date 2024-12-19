package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PersonalDetailsPage {
    WebDriver driver;

    @FindBy(xpath = "//*[text()='Full Name']")
    private WebElement fullNameInput;

    @FindBy(xpath = "//*[text()='Email']")
    private WebElement emailInput;

    @FindBy(xpath = "//*[text()='Phone Number']")
    private WebElement phoneInput;
    
    @FindBy(xpath = "//*[text()='Next']")
    private WebElement NextButton;

    @FindBy(xpath = "//input[@id='payment_btnProceedPayment']")
    private WebElement proceedPaymentButton;

    public PersonalDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillDetails(String name, String email, String phone) {
        fullNameInput.sendKeys(name);
        phoneInput.sendKeys(phone);
        emailInput.sendKeys(email);
        NextButton.click();    }

    public void proceedToPayment() {
        proceedPaymentButton.click();
    }
}

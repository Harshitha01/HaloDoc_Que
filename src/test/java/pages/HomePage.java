package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;

    @FindBy(xpath = "//input[@id='txtOriginGeneral']")
    WebElement sourceField;
    
    @FindBy(xpath = "//*[text()='Cameron Highlands']")
    WebElement fromplace;
    
    
    @FindBy(xpath = "//*[@id='txtDestinationGeneral']")
    private WebElement destinationField;
    
    @FindBy(xpath = "//*[text()='Kuala Lumpur']")
    WebElement Toplace;
    
    
    @FindBy(id = "txtDepartDateBooking")
    private WebElement datepicker;
    
    @FindBy(xpath = "//a[@class='ui-datepicker-next ui-corner-all']")
    WebElement next;
    
    @FindBy(xpath = "//span[text()='January' or text()='2025']")
    WebElement monthYear;
    
    @FindBy(xpath= "//table[@class='ui-datepicker-calendar']//a[text()='20']")
    private WebElement date;
  //table[@class='ui-datepicker-calendar']//a[text()='20']
    
    @FindBy(id = "btnBusSearchNewGeneral")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectSource() {
        sourceField.click();// Open the dropdown
        fromplace.click();
        
        //sourceField.sendKeys(source);
        
    }

    public void selectDestination() {
    	destinationField.click();// Open the dropdown
        Toplace.click();
    	//destinationField.sendKeys(destination);
        
    }

    public void selectDate() {
        datepicker.click();
        next.click();
        monthYear.click();
        date.click();
  
        
    }

    public void searchBus() {
        searchButton.click();
    }
}

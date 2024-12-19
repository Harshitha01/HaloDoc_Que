package bus_Ticket_Automation_Tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.BusResultsPage;
import pages.PersonalDetailsPage;
import utilities.DriverSetup;

public class BusBookingTest {
    WebDriver driver;
    HomePage homePage;
    BusResultsPage busResultsPage;
    PersonalDetailsPage personalDetailsPage;

    @BeforeClass
    public void setup() {
        driver = DriverSetup.getDriver();
        driver.get("https://www.busonlineticket.com/booking/bus-tickets.aspx");
        homePage = new HomePage(driver);
        busResultsPage = new BusResultsPage(driver);
        personalDetailsPage = new PersonalDetailsPage(driver);
    }

    @Test
    public void testBusBooking() {
        homePage.selectSource();
        homePage.selectDestination();
        homePage.selectDate();
        homePage.searchBus();

        WebElement cheapestBus = busResultsPage.selectCheapestBus();
        cheapestBus.click();

        personalDetailsPage.fillDetails("John Doe", "johndoe@example.com", "1234567890");
        personalDetailsPage.proceedToPayment();

        String alertMessage = driver.switchTo().alert().getText();
        System.out.println("Alert Message: " + alertMessage);
        Assert.assertTrue(alertMessage.contains("payment"), "Alert does not match!");
    }

	/*
	 * @AfterClass public void teardown() { DriverSetup.quitDriver(); }
	 */
}

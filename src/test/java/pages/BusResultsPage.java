package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BusResultsPage {
    private final WebDriver driver;

    // Locators for bus rows and cost elements
    @FindBy(xpath = "//a[@class='trip-item trip-item-md']") // Adjust this locator as per your page structure
    private List<WebElement> busRows;

    @FindBy(xpath = "//p[@class='text-lg']") // Adjust this locator for the adult cost element
    private List<WebElement> costElements;

    // Constructor to initialize elements
    public BusResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Select the bus with the lowest adult cost. If costs are the same for all buses, select the first one.
     * @return WebElement representing the bus row with the lowest cost, or null if no buses are found.
     */
    public WebElement selectCheapestBus() {
        if (busRows.isEmpty() || costElements.isEmpty()) {
            System.out.println("No buses or cost elements found on the page.");
            return null; // Or throw an exception if you prefer
        }

        if (busRows.size() != costElements.size()) {
            throw new IllegalStateException("Mismatch between the number of bus rows and cost elements.");
        }

        double firstCost = parseCost(costElements.get(0).getText());
        Set<Double> uniqueCosts = new HashSet<>();

        // Add all costs to a set to identify if they are the same
        for (WebElement costElement : costElements) {
            uniqueCosts.add(parseCost(costElement.getText()));
        }

        // Check if all costs are the same
        if (uniqueCosts.size() == 1) {
            System.out.println("All buses have the same cost: RM " + firstCost);
            return busRows.get(0); // Select the first bus by default
        }

        // If costs are different, find the cheapest
        int minIndex = 0;
        double minCost = Double.MAX_VALUE;

        for (int i = 0; i < costElements.size(); i++) {
            double cost = parseCost(costElements.get(i).getText());
            if (cost < minCost) {
                minCost = cost;
                minIndex = i;
            }
        }

        System.out.println("Cheapest bus found with cost: RM " + minCost);
        return busRows.get(minIndex);
    }

    /**
     * Select up to 6 white seats from the bus layout or all white seats if less than 6 are available.
     * @param busRow The WebElement representing the selected bus row.
     */
    public void selectWhiteSeats(WebElement busRow) {
        // Click on the selected bus row to view the seating layout
        busRow.click();

        // Wait for the seat layout to load (use WebDriverWait if necessary)
        List<WebElement> whiteSeats = busRow.findElements(By.xpath("//img[contains(@src, 'white-v.png')]"));

        if (whiteSeats.isEmpty()) {
            System.out.println("No white seats are available for selection.");
            return;
        }

        // Select up to 6 white seats or all white seats if less than 6 are available
        int seatsToSelect = Math.min(6, whiteSeats.size());
        for (int i = 0; i < seatsToSelect; i++) {
            whiteSeats.get(i).click(); // Select each white seat
        }

        System.out.println("Selected " + seatsToSelect + " white seats.");
    }


    /**
     * Parse the cost from a string and handle exceptions gracefully.
     * @param costText The cost string (e.g., "RM 35.50").
     * @return Parsed cost as a double.
     */
    private double parseCost(String costText) {
        try {
            return Double.parseDouble(costText.replace("RM", "").trim());
        } catch (NumberFormatException e) {
            System.err.println("Failed to parse cost: " + costText);
            return Double.MAX_VALUE; // Return a very high value to exclude this row
        }
    }
}

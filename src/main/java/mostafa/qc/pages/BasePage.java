package mostafa.qc.pages;

/*
this is a Custom utility class (ConfigReader) read configuration values (like browser type, base URL, credentials, timeouts)
from a properties file or environment variables.
Help keeping test code clean and flexible — instead of hardcoding values, you centralize them in a config file.
 */
import mostafa.qc.utils.ConfigReader;
/*
    we are stopping here right now
 */
import mostafa.qc.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * ============================================================================
 * BasePage Class
 * ============================================================================
 * Description: Parent class for all page objects. Provides common methods
 *              and initializes PageFactory for @FindBy annotations.
 *              All page classes must extend this class.

 * Design Pattern: Page Object Model (POM)
 * Inheritance: All page classes extend BasePage
 * Author: Mostafa QC
 * ============================================================================
 */
public class BasePage {

    // ========================================================================
    // INSTANCE VARIABLES
    // ========================================================================

    /** WebDriver instance shared across all page methods */
    protected WebDriver driver;

    // ========================================================================
    // CONSTRUCTOR
    // ========================================================================

    /**
     * Initializes the WebDriver and PageFactory for @FindBy annotations.
     * Called by all child page class constructors via super(driver).
     *
     * @param driver WebDriver instance from DriverFactory
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;

        // Initialize @FindBy annotated elements in child page classes
        PageFactory.initElements(driver, this);
    }

    // ========================================================================
    // NAVIGATION METHODS
    // ========================================================================

    /**
     * Navigates to a page using a relative URL path.
     * Base URL is read from config.properties.
     *
     * @param relativePath Relative URL path (e.g., "/login", "/cart")
     */
    protected void navigateTo(String relativePath) {
        driver.get(ConfigReader.getBaseUrl() + relativePath);
    }

    /**
     * Returns the current page URL.
     *
     * @return Current URL as a String
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Returns the current page title.
     *
     * @return Page title as a String
     */
    protected String getPageTitle() {
        return driver.getTitle();
    }

    // ========================================================================
    // ELEMENT INTERACTION METHODS
    // ========================================================================

    /**
     * Clears a field and types text into it.
     * Waits for element to be visible before typing.
     *
     * @param element WebElement to type into
     * @param text    Text to enter
     */
    protected void type(WebElement element, String text) {
        WaitUtils.waitForVisibility(driver, element);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Clicks a WebElement.
     * Waits for element to be clickable before clicking.
     *
     * @param element WebElement to click
     */
    protected void click(WebElement element) {
        WaitUtils.waitForClickability(driver, element);
        element.click();
    }

    /**
     * Gets the visible text of a WebElement.
     * Waits for element to be visible before reading text.
     *
     * @param element WebElement to read text from
     * @return Visible text of the element
     */
    protected String getText(WebElement element) {
        WaitUtils.waitForVisibility(driver, element);
        return element.getText();
    }

    /**
     * Checks if a WebElement is displayed on the page.
     *
     * @param element WebElement to check
     * @return true if element is displayed, false otherwise
     */
    protected boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            // Element not found or not visible
            return false;
        }
    }
}
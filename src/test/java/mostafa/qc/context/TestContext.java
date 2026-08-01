package mostafa.qc.context;

import mostafa.qc.pages.*;
import mostafa.qc.utils.DriverFactory;
import org.openqa.selenium.WebDriver;

/**
 * ============================================================================
 * TestContext Class
 * ============================================================================
 * Description: Shared context object that holds page instances and state
 *              across multiple step definition classes within a scenario.
 *
 * Why needed:
 *   - Cucumber creates a new instance of each step definition class per scenario
 *   - Without shared context, page objects would be duplicated across step classes
 *   - TestContext acts as a single source of truth for all page objects
 *   - Injected via Cucumber's PicoContainer dependency injection
 *
 * Usage:
 *   - Add TestContext as a constructor parameter in any step definition class
 *   - Cucumber automatically injects the same instance across all step classes
 *
 * Author: Mostafa QC
 * ============================================================================
 */
public class TestContext {

    // ========================================================================
    // INSTANCE VARIABLES
    // ========================================================================

    /** Shared WebDriver instance for this scenario */
    private final WebDriver driver;

    // ========================================================================
    // PAGE OBJECT INSTANCES
    // ========================================================================

    /** Home page object - shared across step classes */
    public final HomePage homePage;

    /** Login page object - shared across step classes */
    public final LoginPage loginPage;

    /** Registration page object - shared across step classes */
    public final RegistrationPage registrationPage;

    /** Search results page object - shared across step classes */
    public final SearchResultsPage searchResultsPage;

    /** Product detail page object - shared across step classes */
    public final ProductPage productPage;

    /** Shopping cart page object - shared across step classes */
    public final CartPage cartPage;

    /** Checkout page object - shared across step classes */
    public final CheckoutPage checkoutPage;

    // ========================================================================
    // SCENARIO STATE - Shared data between steps
    // ========================================================================

    /** Stores the last generated unique email for verification */
    public String lastGeneratedEmail;

    /** Stores the last placed order number for verification */
    public String lastOrderNumber;

    /** Stores the cart total before coupon for comparison */
    public String cartTotalBeforeCoupon;

    // ========================================================================
    // CONSTRUCTOR
    // ========================================================================

    /**
     * Initializes all page objects using the current WebDriver instance.
     * Called automatically by PicoContainer at the start of each scenario.
     */
    public TestContext() {
        // Get the thread-safe WebDriver instance for this scenario
        this.driver = DriverFactory.getDriver();

        // Initialize all page objects with the same driver instance
        this.homePage          = new HomePage(driver);
        this.loginPage         = new LoginPage(driver);
        this.registrationPage  = new RegistrationPage(driver);
        this.searchResultsPage = new SearchResultsPage(driver);
        this.productPage       = new ProductPage(driver);
        this.cartPage          = new CartPage(driver);
        this.checkoutPage      = new CheckoutPage(driver);
    }

    // ========================================================================
    // HELPER METHODS
    // ========================================================================

    /**
     * Returns the WebDriver instance for direct use when needed.
     *
     * @return WebDriver instance for this scenario
     */
    public WebDriver getDriver() {
        return driver;
    }
}
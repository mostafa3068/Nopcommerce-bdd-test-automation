package mostafa.qc.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import mostafa.qc.pages.CartPage;
import mostafa.qc.pages.HomePage;
import mostafa.qc.pages.ProductPage;
import mostafa.qc.pages.SearchResultsPage;
import mostafa.qc.utils.DriverFactory;
import mostafa.qc.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ============================================================================
 * Cart Step Definitions
 * ============================================================================
 * Description: Step definitions for shopping cart feature scenarios.
 * Maps Gherkin steps to Java automation code.
 * Author: Mostafa QC
 * ============================================================================
 */
public class CartSteps {

    // ========================================================================
    // INSTANCE VARIABLES
    // ========================================================================

    private final WebDriver driver;
    private final HomePage homePage;
    private final SearchResultsPage searchResultsPage;
    private final ProductPage productPage;
    private final CartPage cartPage;

    /** Stores initial cart total for comparison */
    private String initialCartTotal;

    // ========================================================================
    // CONSTRUCTOR
    // ========================================================================

    /**
     * Initializes page objects with the current WebDriver instance.
     */
    public CartSteps() {
        this.driver = DriverFactory.getDriver();
        this.homePage = new HomePage(driver);
        this.searchResultsPage = new SearchResultsPage(driver);
        this.productPage = new ProductPage(driver);
        this.cartPage = new CartPage(driver);
    }

    // ========================================================================
    // GIVEN STEPS
    // ========================================================================

    /**
     * Ensures user has a product in the cart.
     * Adds a product if cart is empty.
     */
    @Given("the user has a product in the cart")
    public void theUserHasAProductInTheCart() {
        // Navigate to home and add a product
        homePage.open();
        homePage.searchFor("laptop");

        // Click first product and add to cart
        searchResultsPage.clickProductByIndex(0);
        productPage.clickAddToCart();

        // Verify product was added
        assertThat(productPage.isSuccessNotificationDisplayed())
                .as("Product should be added to cart")
                .isTrue();
    }

    /**
     * Navigates to the cart page.
     */
    @Given("the user is on the cart page")
    public void theUserIsOnTheCartPage() {
        cartPage.open();
    }

    /**
     * Verifies cart is empty.
     */
    @Given("the cart is empty")
    public void theCartIsEmpty() {
        // this is used in order to escape the implicit waits without it the test would take 1 minute
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        cartPage.open();

        // Remove all items if cart is not empty
        if (!cartPage.isCartEmpty()) {
            cartPage.removeAllItems();
        }

        assertThat(cartPage.isCartEmpty())
                .as("Cart should be empty")
                .isTrue();
        System.out.println("from inside cart is empty");
        System.out.println(cartPage.isCartEmpty());
    }

    // ========================================================================
    // WHEN STEPS
    // ========================================================================

    /**
     * Clicks on the first product in search results.
     */
    @When("the user clicks on the first product")
    public void theUserClicksOnTheFirstProduct() {
        searchResultsPage.clickProductByIndex(0);
    }

    /**
     * Clicks add to cart button on product page.
     */
    @When("the user clicks add to cart button")
    public void theUserClicksAddToCartButton() {
        productPage.clickAddToCart();
    }

    /**
     * Opens the cart page.
     */
    @When("the user opens the cart page")
    public void theUserOpensTheCartPage() {
        cartPage.open();
        // Store initial total for later comparison
        if (!cartPage.isCartEmpty()) {
            initialCartTotal = cartPage.getOrderTotal();
        }
    }

    /**
     * Removes the first item from cart.
     */
    @When("the user removes the first item from cart")
    public void theUserRemovesTheFirstItemFromCart() {
        cartPage.removeItem(0);

    }

    /**
     * Updates quantity of first item in cart.
     *
     * @param quantity New quantity
     */
    @When("the user updates the quantity to {int}")
    public void theUserUpdatesTheQuantityTo(int quantity) {
        initialCartTotal = cartPage.getOrderTotal();
        cartPage.updateQuantity(0, quantity);
    }

    /**
     * Refreshes the current page.
     */
    @When("the user refreshes the page")
    public void theUserRefreshesThePage() {
        WaitUtils.waitForSeconds(3);
        driver.navigate().refresh();
    }

    /**
     * Attempts to proceed to checkout.
     */
    @When("the user tries to proceed to checkout")
    public void theUserTriesToProceedToCheckout() {
        // Try to click checkout - should fail or show message for empty cart
        try {
            cartPage.proceedToCheckout();
        } catch (Exception e) {
            // Expected for empty cart
        }
    }

    /**
     * Proceeds to checkout from cart.
     */
    @When("the user proceeds to checkout")
    public void theUserProceedsToCheckout() {
        cartPage.proceedToCheckout();
    }

    // ========================================================================
    // THEN STEPS
    // ========================================================================

    /**
     * Verifies success notification is displayed.
     *
     * @param expectedMessage Expected notification message
     */
    @Then("the user should see success notification {string}")
    public void theUserShouldSeeSuccessNotification(String expectedMessage) {
        assertThat(productPage.isSuccessNotificationDisplayed())
                .as("Success notification should be displayed")
                .isTrue();

        assertThat(productPage.getSuccessNotificationText())
                .as("Notification should contain expected message")
                .containsIgnoringCase(expectedMessage);
    }

    /**
     * Verifies cart contains specified number of items.
     *
     * @param expectedCount Expected item count
     */
    @Then("the cart should contain {int} item")
    public void theCartShouldContainItem(int expectedCount) {
        cartPage.open();
        System.out.println(" from inside the count of the cart");
        System.out.println("cart count is "+cartPage.getCartItemCount());
        assertThat(cartPage.getCartItemCount())
                .as("Cart should contain expected number of items")
                .isEqualTo(expectedCount);
    }

    /**
     * Verifies cart is empty.
     */
    @Then("the cart should be empty")
    public void theCartShouldBeEmpty() {
        System.out.println(cartPage.isCartEmpty());
        assertThat(cartPage.isCartEmpty())
                .as("Cart should be empty")
                .isTrue();
    }

    /**
     * Verifies cart totals have been updated.
     */
    @Then("the cart totals should be updated")
    public void theCartTotalsShouldBeUpdated() {
        String newTotal = cartPage.getOrderTotal();
        assertThat(newTotal)
                .as("Cart total should be different after quantity update")
                .isNotEqualTo(initialCartTotal);
    }

    /**
     * Verifies cart still contains the product after refresh.
     */
    @Then("the cart should still contain the product")
    public void theCartShouldStillContainTheProduct() {
        System.out.println("From inside cart page check");
        System.out.println(cartPage.isCartEmpty());
        assertThat(cartPage.isCartEmpty())
                .as("Cart should not be empty after refresh")
                .isFalse();
    }

    /**
     * Verifies empty cart message is displayed.
     */
    @Then("the user should see empty cart message")
    public void theUserShouldSeeEmptyCartMessage() {
        assertThat(cartPage.isCartEmpty())
                .as("Cart should show empty message")
                .isTrue();
    }
}
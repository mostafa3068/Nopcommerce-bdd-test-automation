package mostafa.qc.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import mostafa.qc.pages.CartPage;
import mostafa.qc.utils.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ============================================================================
 * Coupon Step Definitions
 * ============================================================================
 * Description: Step definitions for coupon/promotions feature scenarios.
 * Maps Gherkin steps to Java automation code.
 * Author: Mostafa QC
 * ============================================================================
 */
public class CouponSteps {

    // ========================================================================
    // INSTANCE VARIABLES
    // ========================================================================

    private final WebDriver driver;
    private final CartPage cartPage;

    /** Stores original cart total before coupon */
    private String originalTotal;

    // ========================================================================
    // CONSTRUCTOR
    // ========================================================================

    /**
     * Initializes page objects with the current WebDriver instance.
     */
    public CouponSteps() {
        this.driver = DriverFactory.getDriver();
        this.cartPage = new CartPage(driver);
    }

    // ========================================================================
    // GIVEN STEPS
    // ========================================================================

    /**
     * Verifies user has applied a specific coupon.
     *
     * @param couponCode Coupon code that should be applied
     */
    @Given("the user has applied coupon {string}")
    public void theUserHasAppliedCoupon(String couponCode) {
        // Store original total
        originalTotal = cartPage.getOrderTotal();

        // Apply the coupon
        enterCouponCode(couponCode);
        clickApplyCouponButton();
    }

    // ========================================================================
    // WHEN STEPS
    // ========================================================================

    /**
     * Enters coupon code in the coupon field.
     *
     * @param couponCode Coupon code to enter
     */
    @When("the user enters coupon code {string}")
    public void enterCouponCode(String couponCode) {
        // Store original total before applying coupon
        if (originalTotal == null) {
            originalTotal = cartPage.getOrderTotal();
        }

        // Find and fill coupon input
        WebElement couponInput = driver.findElement(By.id("discountcouponcode"));
        couponInput.clear();
        couponInput.sendKeys(couponCode);
    }

    /**
     * Clicks the apply coupon button.
     */
    @When("the user clicks apply coupon button")
    public void clickApplyCouponButton() {
        WebElement applyButton = driver.findElement(By.id("applydiscountcouponcode"));
        applyButton.click();
    }

    /**
     * Removes the applied coupon.
     */
    @When("the user removes the coupon")
    public void theUserRemovesTheCoupon() {
        WebElement removeButton = driver.findElement(By.cssSelector(".remove-discount-button"));
        removeButton.click();
    }

    // ========================================================================
    // THEN STEPS
    // ========================================================================

    /**
     * Verifies coupon was applied successfully.
     */
    @Then("the coupon should be applied successfully")
    public void theCouponShouldBeAppliedSuccessfully() {
        // Check for success message or discount line
        WebElement successMessage = driver.findElement(By.cssSelector(".coupon-box .message-success"));
        System.out.println("success message: " + successMessage.getText());
        assertThat(successMessage.getText())
                .as("Coupon success message should be displayed")
                .containsIgnoringCase("applied");
    }

    /**
     * Verifies cart total reflects the discount.
     */
    @Then("the cart total should reflect the discount")
    public void theCartTotalShouldReflectTheDiscount() {
        String newTotal = cartPage.getOrderTotal();
        System.out.println("old total: "+ originalTotal);
        System.out.println("new total: " + newTotal);
        assertThat(newTotal)
                .as("Cart total should be different after coupon applied")
                .isNotEqualTo(originalTotal);
    }

    /**
     * Verifies coupon was removed.
     */
    @Then("the coupon should be removed")
    public void theCouponShouldBeRemoved() {
        // Verify no discount line or coupon removed message
        // 1. Find elements (plural) so it doesn't throw a NoSuchElementException if missing
        List<WebElement> successMessages = driver.findElements(By.cssSelector(".coupon-box .message-success"));

        // 2. Assert that the list is empty (the message box does not exist)
        System.out.println("success messages (empty list of size): " + successMessages.size()); // it should return 0
        assertThat(successMessages)
                .as("Coupon success message box should be completely removed")
                .isEmpty();

    }

    /**
     * Verifies cart total returns to original price.
     */
    @Then("the cart total should return to original price")
    public void theCartTotalShouldReturnToOriginalPrice() {
        String currentTotal = cartPage.getOrderTotal();
        assertThat(currentTotal)
                .as("Cart total should return to original")
                .isEqualTo(originalTotal);
    }

    /**
     * Verifies coupon error message is displayed.
     *
     * @param expectedMessage Expected error message
     */
    @Then("the user should see coupon error message {string}")
    public void theUserShouldSeeCouponErrorMessage(String expectedMessage) {
        WebElement errorMessage = driver.findElement(By.cssSelector(".coupon-box .message-failure"));
        System.out.println("error message: " + errorMessage.getText());
        assertThat(errorMessage.getText())
                .as("Coupon error message should contain expected text")
                .containsIgnoringCase(expectedMessage);
    }
}
package mostafa.qc.pages;

import mostafa.qc.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * ============================================================================
 * ProductPage Page Object
 * ============================================================================
 * Description: Represents a nopCommerce product detail page.
 *              Contains elements and actions for viewing product details
 *              and adding products to the cart.
 * Author: Mostafa QC
 * URL: /product/{product-name}
 * ============================================================================
 */
public class ProductPage extends BasePage {

    // ========================================================================
    // WEB ELEMENTS - Located using @FindBy annotations (PageFactory)
    // ========================================================================

    /** Product name/title heading */
    @FindBy(css = ".product-name h1")
    private WebElement productName;

    /** Product price element */
    @FindBy(css = ".product-price span")
    private WebElement productPrice;

    /** Quantity input field */
    @FindBy(css = "input.qty-input")
    private WebElement quantityInput;

    /** "Add to cart" button */
    @FindBy(css = "button.button-1.add-to-cart-button")
    private WebElement addToCartButton;

    /** "Add to wishlist" button */
    @FindBy(css = "button.button-2.add-to-wishlist-button")
    private WebElement addToWishlistButton;

    /** Success notification bar shown after adding to cart */
    @FindBy(css = ".bar-notification.success .content")
    private WebElement successNotification;

    /** Product description section */
    @FindBy(css = ".full-description")
    private WebElement productDescription;

    // ========================================================================
    // CONSTRUCTOR
    // ========================================================================

    /**
     * Initializes ProductPage with WebDriver and PageFactory.
     *
     * @param driver WebDriver instance from DriverFactory
     */
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    // ========================================================================
    // PAGE ACTIONS
    // ========================================================================

    /**
     * Clicks the "Add to Cart" button.
     * Waits for the success notification to appear after clicking.
     */
    public void clickAddToCart() {
        click(addToCartButton);

        // Wait for success notification to appear
        WaitUtils.waitForVisibility(driver, successNotification);
    }

    /**
     * Sets the product quantity before adding to cart.
     *
     * @param quantity Desired quantity
     */
    public void setQuantity(int quantity) {
        type(quantityInput, String.valueOf(quantity));
    }

    /**
     * Clicks the "Add to Wishlist" button.
     */
    public void clickAddToWishlist() {
        click(addToWishlistButton);
    }

    // ========================================================================
    // PAGE VERIFICATIONS
    // ========================================================================

    /**
     * Gets the product name displayed on the page.
     *
     * @return Product name text
     */
    public String getProductName() {
        return getText(productName);
    }

    /**
     * Gets the product price displayed on the page.
     *
     * @return Product price text (e.g., "$1,200.00")
     */
    public String getProductPrice() {
        return getText(productPrice);
    }

    /**
     * Checks if the success notification is displayed after adding to cart.
     *
     * @return true if success notification is visible
     */
    public boolean isSuccessNotificationDisplayed() {
        return isDisplayed(successNotification);
    }

    /**
     * Gets the text of the success notification.
     *
     * @return Success notification text
     */
    public String getSuccessNotificationText() {
        return getText(successNotification);
    }
}
package mostafa.qc.pages;

import mostafa.qc.constants.Constants;
import mostafa.qc.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * ============================================================================
 * CartPage Page Object
 * ============================================================================
 * Description: Represents the nopCommerce shopping cart page.
 *              Contains elements and actions for managing cart items,
 *              updating quantities, applying coupons, and proceeding to checkout.
 * Author: Mostafa QC
 * URL: /cart
 * ============================================================================
 */
public class CartPage extends BasePage {

    // ========================================================================
    // WEB ELEMENTS - Located using @FindBy annotations (PageFactory)
    // ========================================================================

    /** List of all cart item rows */
    // ✅ Correct — actual cart rows are <tr> inside tbody of table.cart
    @FindBy(css = "table.cart tbody tr")
    private List<WebElement> cartItems;

    /** List of remove buttons for each cart item */
    // ✅ Correct — actual HTML uses name="updatecart" on the button inside .remove-from-cart
    @FindBy(css = "td.remove-from-cart button")
    private List<WebElement> removeButtons;

    /** List of quantity input fields for each cart item */
    @FindBy(css = "input.qty-input")
    private List<WebElement> quantityInputs;

    /** "Update shopping cart" button */
    @FindBy(id = "updatecart")
    private WebElement updateCartButton;

    /** Order total price element */
    // ✅ Correct — the actual total is inside tr.order-total > td > span > strong
    @FindBy(css = "tr.order-total .value-summary strong")
    private WebElement orderTotal;

    /** "Proceed to checkout" button */
    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    /** Empty cart message */
    @FindBy(css = ".no-data")
    private WebElement emptyCartMessage;

    /** Coupon code input field */
    @FindBy(id = "discountcouponcode")
    private WebElement couponCodeInput;

    /** Apply coupon button */
    @FindBy(id = "applydiscountcouponcode")
    private WebElement applyCouponButton;






    // ========================================================================
    // CONSTRUCTOR
    // ========================================================================

    /**
     * Initializes CartPage with WebDriver and PageFactory.
     *
     * @param driver WebDriver instance from DriverFactory
     */
    public CartPage(WebDriver driver) {
        super(driver);
    }

    // ========================================================================
    // PAGE ACTIONS
    // ========================================================================

    /**
     * Navigates directly to the shopping cart page.
     */
    public void open() {
        navigateTo(Constants.CART_URL);
    }

    /**
     * Removes a cart item by its index.
     *
     * @param index Zero-based index of the item to remove
     */
    public void removeItem(int index) {
        if (index < removeButtons.size()) {
            click(removeButtons.get(index));

            // Wait for cart to update after removal
            WaitUtils.waitForInvisibility(driver, By.cssSelector(".ajax-loading"));
        } else {
            throw new IndexOutOfBoundsException(
                    "Item index " + index + " is out of bounds. Total items: " + removeButtons.size()
            );
        }
    }

    /**
     * Removes all items from the cart.
     * Iterates and removes until cart is empty.
     */
    public void removeAllItems() {
        // Remove items from last to first to avoid index shifting
        for (int i = removeButtons.size() - 1; i >= 0; i--) {
            removeItem(i);
        }
    }

    /**
     * Updates the quantity of a cart item.
     *
     * @param index    Zero-based index of the item
     * @param quantity New quantity to set
     */
    public void updateQuantity(int index, int quantity) {


        // used javascript here instead of selenium because i can't interact with the page with selenium as update button is hidden
        if (index < quantityInputs.size()) {
            WebElement input = quantityInputs.get(index);
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // 1. Set the value via JavaScript
            js.executeScript("arguments[0].value = arguments[1];", input, String.valueOf(quantity));

            // 2. Trigger click on the hidden updatecart button via JavaScript
            js.executeScript("document.getElementById('updatecart').click();");

            // 3. Wait for page to reload/update
            WaitUtils.waitForInvisibility(driver, By.cssSelector(".ajax-loading"));
        }


        //     will check this method again
        //        if (index < quantityInputs.size()) {
//            type(quantityInputs.get(index), String.valueOf(quantity));
//            click(updateCartButton);
//
//            // Wait for cart totals to update
//            WaitUtils.waitForInvisibility(driver, By.cssSelector(".ajax-loading"));
//        }
    }

    /**
     * Clicks the "Proceed to Checkout" button.
     */
    public void proceedToCheckout() {
        click(checkoutButton);
    }

    /**
     * Applies a coupon code to the cart.
     *
     * @param couponCode Coupon code to apply
     */
    public void applyCoupon(String couponCode) {
        type(couponCodeInput, couponCode);
        click(applyCouponButton);
    }

    // ========================================================================
    // PAGE VERIFICATIONS
    // ========================================================================

    /**
     * Checks if the cart is empty.
     *
     * @return true if cart has no items
     */
    public boolean isCartEmpty() {
        return cartItems.isEmpty() || isDisplayed(emptyCartMessage);
    }

    /**
     * Gets the number of items currently in the cart.
     *
     * @return Number of cart item rows
     */
    public int getCartItemCount() {
        return cartItems.size();
    }

    /**
     * Gets the order total displayed in the cart.
     *
     * @return Order total text (e.g., "$1,200.00")
     */
    public String getOrderTotal() {
        return getText(orderTotal);
    }
}
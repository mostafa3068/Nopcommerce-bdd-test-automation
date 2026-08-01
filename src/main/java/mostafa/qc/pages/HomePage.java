package mostafa.qc.pages;

import mostafa.qc.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * ============================================================================
 * HomePage Page Object
 * ============================================================================
 * Description: Represents the nopCommerce home page.
 *              Contains elements and actions available on the home page
 *              such as navigation, search, and header interactions.
 * Author: Mostafa QC
 * URL: /
 * ============================================================================
 */
public class HomePage extends BasePage {

    // ========================================================================
    // WEB ELEMENTS - Located using @FindBy annotations (PageFactory)
    // ========================================================================

    /** Search input box in the header */
    @FindBy(id = "small-searchterms")
    private WebElement searchBox;

    /** Search submit button */
    @FindBy(css = "button.button-1.search-box-button")
    private WebElement searchButton;

    /** "Log in" link in the top navigation */
    @FindBy(css = "a.ico-login")
    private WebElement loginLink;

    /** "Log out" link - visible only when user is logged in */
    @FindBy(css = "a.ico-logout")
    private WebElement logoutLink;

    /** "Register" link in the top navigation */
    @FindBy(css = "a.ico-register")
    private WebElement registerLink;

    /** Shopping cart link showing item count */
    @FindBy(css = "a.ico-cart")
    private WebElement cartLink;

    /** Cart quantity indicator in the header */
    @FindBy(css = "span.cart-qty")
    private WebElement cartQuantity;

    // ========================================================================
    // CONSTRUCTOR
    // ========================================================================

    /**
     * Initializes HomePage with WebDriver and PageFactory.
     *
     * @param driver WebDriver instance from DriverFactory
     */
    public HomePage(WebDriver driver) {
        super(driver);
    }

    // ========================================================================
    // PAGE ACTIONS
    // ========================================================================

    /**
     * Navigates to the home page.
     */
    public void open() {
        navigateTo("/");
    }

    /**
     * Performs a product search using the search box.
     *
     * @param keyword Search keyword to enter
     */
    public void searchFor(String keyword) {
        type(searchBox, keyword);
        click(searchButton);
    }

    /**
     * type only in the search box.
     *
     * @param keyword Search keyword to enter
     */
    public void typeInto(String keyword) {
        type(searchBox, keyword);
    }

    /**
     * Clicks the login link in the navigation.
     */
    public void clickLoginLink() {
        click(loginLink);
    }

    /**
     * Clicks the register link in the navigation.
     */
    public void clickRegisterLink() {
        click(registerLink);
    }

    /**
     * Clicks the logout link to log out the current user.
     */
    public void clickLogout() {
        click(logoutLink);
    }

    /**
     * Clicks the cart icon to open the shopping cart.
     */
    public void clickCartLink() {
        click(cartLink);
    }

    // ========================================================================
    // PAGE VERIFICATIONS
    // ========================================================================

    /**
     * Checks if the user is currently logged in.
     * Determined by the presence of the logout link.
     *
     * @return true if logout link is visible (user is logged in)
     */
    public boolean isUserLoggedIn() {
        return isDisplayed(logoutLink);
    }

    /**
     * Gets the current cart item count from the header.
     *
     * @return Cart quantity as a String (e.g., "(2)")
     */
    public String getCartQuantity() {
        return getText(cartQuantity);
    }
}
package mostafa.qc.constants;

/**
 * ============================================================================
 * Constants Class
 * ============================================================================
 * Description: Centralizes all hardcoded values used across the framework.
 *              Avoids magic numbers/strings scattered throughout the code.
 *              All fields are public static final (true constants).
 * Author: Mostafa QC
 * ============================================================================
 */
public class Constants {

    // ========================================================================
    // CONSTRUCTOR - Private to prevent instantiation
    // ========================================================================
    private Constants() {
        // Utility class - should not be instantiated
    }

    // ========================================================================
    // TIMEOUT CONSTANTS (in seconds)
    // ========================================================================

    /** Default explicit wait timeout for most elements */
    public static final int DEFAULT_TIMEOUT = 10;

    /** Extended timeout for slow-loading pages or elements */
    public static final int LONG_TIMEOUT = 30;

    /** Short timeout for quick checks (e.g., element visibility) */
    public static final int SHORT_TIMEOUT = 5;

    /** Page load timeout - max time to wait for full page load */
    public static final int PAGE_LOAD_TIMEOUT = 60;

    // ========================================================================
    // URL CONSTANTS
    // ========================================================================

    /** Login page relative path */
    public static final String LOGIN_URL = "/login";

    /** Registration page relative path */
    public static final String REGISTER_URL = "/register";

    /** Shopping cart page relative path */
    public static final String CART_URL = "/cart";

    /** Checkout page relative path */
    public static final String CHECKOUT_URL = "/checkout";

    /** Order history page relative path */
    public static final String ORDER_HISTORY_URL = "/customer/orders";

    // ========================================================================
    // TEST DATA CONSTANTS
    // ========================================================================

    /** Default valid password used in test scenarios */
    public static final String DEFAULT_PASSWORD = "Test@123";

    /** Default first name used in registration tests */
    public static final String DEFAULT_FIRST_NAME = "John";

    /** Default last name used in registration tests */
    public static final String DEFAULT_LAST_NAME = "Doe";

    /** Default search keyword used in search tests */
    public static final String DEFAULT_SEARCH_KEYWORD = "laptop";

    /** Default quantity used in cart tests */
    public static final int DEFAULT_QUANTITY = 1;

    // ========================================================================
    // MESSAGE CONSTANTS
    // ========================================================================

    /** Success message shown after successful registration */
    public static final String REGISTRATION_SUCCESS_MSG = "Your registration completed";

    /** Error message shown after failed login */
    public static final String LOGIN_ERROR_MSG = "Login was unsuccessful";

    /** Message shown when cart is empty */
    public static final String EMPTY_CART_MSG = "Your Shopping Cart is empty!";

    /** Message shown when no search results found */
    public static final String NO_RESULTS_MSG = "No products were found";

    /** Success message shown after order is placed */
    public static final String ORDER_SUCCESS_MSG = "Your order has been successfully processed";

    // ========================================================================
    // BROWSER CONSTANTS
    // ========================================================================

    /** Chrome browser identifier */
    public static final String CHROME = "chrome";

    /** Firefox browser identifier */
    public static final String FIREFOX = "firefox";

    /** Edge browser identifier */
    public static final String EDGE = "edge";
}
package mostafa.qc.pages; // package declaration in java it tells the compiler that this  class file belongs to the package

import mostafa.qc.constants.Constants; // importing constants class to use its functions
import org.openqa.selenium.WebDriver; // Provide the main interface for controlling the Browser (driver.get(),driver.find element,driver.quit) like driving wheel
import org.openqa.selenium.WebElement; //  Represent element allowing interacting with them (button,input field) like pedal and gear shift in cars
import org.openqa.selenium.support.FindBy; // annotation used in pom to locate elements

/**
 * ============================================================================
 * LoginPage Page Object
 * ============================================================================
 * Description: Represents the nopCommerce login page.
 *              Contains elements and actions for user authentication.
 * Author: Mostafa QC
 * URL: /login
 * ============================================================================
 */
public class LoginPage extends BasePage {

    // ========================================================================
    // WEB ELEMENTS - Located using @FindBy annotations (PageFactory)
    // ========================================================================

    /** Email input field */
    @FindBy(id = "Email")
    private WebElement emailField;

    /** Password input field */
    @FindBy(id = "Password")
    private WebElement passwordField;

    /** Login submit button */
    @FindBy(css = "button.button-1.login-button")
    private WebElement loginButton;

    /** Error message container shown on failed login */
    @FindBy(css = ".message-error.validation-summary-errors")
    private WebElement errorMessage;

    /** Error message container for the email */
    @FindBy(id = "Email-error")
    private WebElement emailError;

    /** "Forgot password" link */
    @FindBy(css = "a.forgot-password")
    private WebElement forgotPasswordLink;

    /** "Remember me" checkbox */
    @FindBy(id = "RememberMe")
    private WebElement rememberMeCheckbox;


    // ========================================================================
    // CONSTRUCTOR
    // ========================================================================

    /**
     * Initializes LoginPage with WebDriver and PageFactory.
     *
     * @param driver WebDriver instance from DriverFactory
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // ========================================================================
    // PAGE ACTIONS
    // ========================================================================

    /**
     * Navigates directly to the login page.
     */
    public void open() {
        navigateTo(Constants.LOGIN_URL);
    }

    /**
     * Enters email address in the email field.
     *
     * @param email Email address to enter
     */
    public void enterEmail(String email) {
        type(emailField, email);
    }

    /**
     * Enters password in the password field.
     *
     * @param password Password to enter
     */
    public void enterPassword(String password) {
        type(passwordField, password);
    }

    /**
     * Clicks the login button to submit the form.
     */
    public void clickLogin() {
        click(loginButton);
    }

    /**
     * Performs a complete login action in one method.
     * Convenience method combining email, password, and click.
     *
     * @param email    User email address
     * @param password User password
     */
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }

    /**
     * Clicks the "Forgot Password" link.
     */
    public void clickForgotPassword() {
        click(forgotPasswordLink);
    }

    // ========================================================================
    // PAGE VERIFICATIONS
    // ========================================================================

    /**
     * Checks if the login error message is displayed.
     *
     * @return true if error message is visible
     */
    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorMessage);
    }

    /**
     * Gets the text of the login error message.
     *
     * @return Error message text
     */
    public String getErrorMessage() {
        return getText(errorMessage);
    }

    /**
     * Gets all validation error messages on the page.
     *
     * @return Validation error text
     */
    public String getValidationErrors() {
        return getText(emailError);
    }
}
package mostafa.qc.pages;

import mostafa.qc.constants.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * ============================================================================
 * RegistrationPage Page Object
 * ============================================================================
 * Description: Represents the nopCommerce registration page.
 *              Contains elements and actions for new user account creation.
 * Author: Mostafa QC
 * URL: /register
 * ============================================================================
 */
public class RegistrationPage extends BasePage {

    // ========================================================================
    // WEB ELEMENTS - Located using @FindBy annotations (PageFactory)
    // ========================================================================

    /** Male gender radio button */
    @FindBy(id = "gender-male")
    private WebElement maleRadioButton;

    /** Female gender radio button */
    @FindBy(id = "gender-female")
    private WebElement femaleRadioButton;

    /** First name input field */
    @FindBy(id = "FirstName")
    private WebElement firstNameField;

    /** Last name input field */
    @FindBy(id = "LastName")
    private WebElement lastNameField;

    /** Email input field */
    @FindBy(id = "Email")
    private WebElement emailField;

    /** Password input field */
    @FindBy(id = "Password")
    private WebElement passwordField;

    /** Confirm password input field */
    @FindBy(id = "ConfirmPassword")
    private WebElement confirmPasswordField;

    /** Register submit button */
    @FindBy(id = "register-button")
    private WebElement registerButton;

    /** Success message shown after successful registration */
    @FindBy(css = ".result")
    private WebElement successMessage;

    /** Validation error summary container */
    private final By validationErrors = By.cssSelector("div.message-error.validation-summary-errors li, div.message-error.validation-summary-errors, span.field-validation-error, .field-validation-error");

    // ========================================================================
    // CONSTRUCTOR
    // ========================================================================

    /**
     * Initializes RegistrationPage with WebDriver and PageFactory.
     *
     * @param driver WebDriver instance from DriverFactory
     */
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    // ========================================================================
    // PAGE ACTIONS
    // ========================================================================

    /**
     * Navigates directly to the registration page.
     */
    public void open() {
        navigateTo(Constants.REGISTER_URL);
    }

    /**
     * Selects gender radio button.
     *
     * @param gender "Male" or "Female"
     */
    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("Male")) {
            click(maleRadioButton);
        } else if (gender.equalsIgnoreCase("Female")) {
            click(femaleRadioButton);
        }
    }

    /**
     * Enters first name in the registration form.
     *
     * @param firstName First name to enter
     */
    public void enterFirstName(String firstName) {
        type(firstNameField, firstName);
    }

    /**
     * Enters last name in the registration form.
     *
     * @param lastName Last name to enter
     */
    public void enterLastName(String lastName) {
        type(lastNameField, lastName);
    }

    /**
     * Enters email address in the registration form.
     *
     * @param email Email address to enter
     */
    public void enterEmail(String email) {
        type(emailField, email);
    }

    /**
     * Enters password in the registration form.
     *
     * @param password Password to enter
     */
    public void enterPassword(String password) {
        type(passwordField, password);
    }

    /**
     * Enters confirm password in the registration form.
     *
     * @param confirmPassword Confirm password to enter
     */
    public void enterConfirmPassword(String confirmPassword) {
        type(confirmPasswordField, confirmPassword);
    }

    /**
     * Clicks the register button to submit the form.
     */
    public void clickRegister() {
        click(registerButton);
    }

    // ========================================================================
    // PAGE VERIFICATIONS
    // ========================================================================

    /**
     * Checks if registration was successful.
     * Determined by the presence of the success message.
     *
     * @return true if success message is displayed
     */
    public boolean isRegistrationSuccessful() {
        return isDisplayed(successMessage);
    }

    /**
     * Gets the registration success message text.
     *
     * @return Success message text
     */
    public String getSuccessMessage() {
        return getText(successMessage);
    }

    /**
     * Gets all validation error messages on the page.
     *
     * @return Validation errors text
     */
    public String getValidationErrors() {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(validationErrors));

        List<WebElement> errors = driver.findElements(validationErrors);
        StringBuilder errorText = new StringBuilder();

        for (WebElement error : errors) {
            if (error.isDisplayed() && !error.getText().trim().isEmpty()) {
                errorText.append(error.getText().trim()).append(" ");
            }
        }

        return errorText.toString().trim();
    }
}
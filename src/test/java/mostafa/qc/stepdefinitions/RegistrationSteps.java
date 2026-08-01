package mostafa.qc.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import mostafa.qc.pages.RegistrationPage;
import mostafa.qc.utils.DriverFactory;
import org.openqa.selenium.WebDriver;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ============================================================================
 * Registration Step Definitions
 * ============================================================================
 * Description: Step definitions for user registration feature scenarios.
 * Maps Gherkin steps to Java automation code.
 * Author: Mostafa QC
 * ============================================================================
 */
public class RegistrationSteps {

    // ========================================================================
    // INSTANCE VARIABLES
    // ========================================================================

    private final WebDriver driver;
    private final RegistrationPage registrationPage;

    /** Stores generated unique email for verification */
    private String generatedEmail;

    // ========================================================================
    // CONSTRUCTOR
    // ========================================================================

    /**
     * Initializes page objects with the current WebDriver instance.
     */
    public RegistrationSteps() {
        this.driver = DriverFactory.getDriver();
        this.registrationPage = new RegistrationPage(driver);
    }

    // ========================================================================
    // GIVEN STEPS
    // ========================================================================

    /**
     * Navigates to the registration page.
     */
    @Given("the user is on the registration page")
    public void theUserIsOnTheRegistrationPage() {
        registrationPage.open();
    }

    // ========================================================================
    // WHEN STEPS
    // ========================================================================

    /**
     * Selects gender option.
     *
     * @param gender Gender to select (Male/Female)
     */
    @When("the user selects gender {string}")
    public void theUserSelectsGender(String gender) {
        registrationPage.selectGender(gender);
    }

    /**
     * Enters first name in the registration form.
     *
     * @param firstName First name to enter
     */
    @When("the user enters first name {string}")
    public void theUserEntersFirstName(String firstName) {
        registrationPage.enterFirstName(firstName);
    }

    /**
     * Enters last name in the registration form.
     *
     * @param lastName Last name to enter
     */
    @When("the user enters last name {string}")
    public void theUserEntersLastName(String lastName) {
        registrationPage.enterLastName(lastName);
    }

    /**
     * Generates and enters a unique email address.
     * Uses UUID to ensure uniqueness for each test run.
     */
    @When("the user enters a unique email")
    public void theUserEntersAUniqueEmail() {
        // Generate unique email using UUID
        generatedEmail = "testuser_" + UUID.randomUUID().toString().substring(0, 8) + "@test.com";
        registrationPage.enterEmail(generatedEmail);
    }

    /**
     * Enters an existing email address (for negative testing).
     *
     * @param email Existing email to enter
     */
    @When("the user enters existing email {string}")
    public void theUserEntersExistingEmail(String email) {
        registrationPage.enterEmail(email);
    }

    /**
     * Enters password in the registration form.
     *
     * @param password Password to enter
     */
    @When("the user enters password {string}")
    public void theUserEntersPassword(String password) {
        registrationPage.enterPassword(password);
    }

    /**
     * Enters confirm password in the registration form.
     *
     * @param confirmPassword Confirm password to enter
     */
    @When("the user enters confirm password {string}")
    public void theUserEntersConfirmPassword(String confirmPassword) {
        registrationPage.enterConfirmPassword(confirmPassword);
    }

    /**
     * Clicks the register button.
     */
    @When("the user clicks the register button")
    public void theUserClicksTheRegisterButton() {
        registrationPage.clickRegister();
    }

    // ========================================================================
    // THEN STEPS
    // ========================================================================

    /**
     * Verifies registration success message is displayed.
     *
     * @param expectedMessage Expected success message
     */
    @Then("the user should see registration success message {string}")
    public void theUserShouldSeeRegistrationSuccessMessage(String expectedMessage) {
        assertThat(registrationPage.isRegistrationSuccessful())
                .as("Registration should be successful")
                .isTrue();

        assertThat(registrationPage.getSuccessMessage())
                .as("Success message should contain expected text")
                .containsIgnoringCase(expectedMessage);
    }

    /**
     * Verifies error message is displayed.
     *
     * @param expectedMessage Expected error message
     */
    @Then("the user should see error message {string}")
    public void theUserShouldSeeErrorMessage(String expectedMessage) {

        assertThat(registrationPage.getValidationErrors())
                .as("Error message should contain expected text")
                .containsIgnoringCase(expectedMessage);
    }

    /**
     * Verifies password validation error is displayed.
     */




    @Then("the user should see password validation error")
    public void theUserShouldSeePasswordValidationError() {
        String errors = registrationPage.getValidationErrors();
        assertThat(errors)
                .as("Password validation error should be displayed")
                .containsIgnoringCase("password");
    }



    /**
     * Verifies validation errors for required fields are displayed.
     */
    @Then("the user should see validation errors for required fields")
    public void theUserShouldSeeValidationErrorsForRequiredFields() {
        String errors = registrationPage.getValidationErrors();
        assertThat(errors)
                .as("Validation errors should be displayed")
                .isNotEmpty();
    }
}
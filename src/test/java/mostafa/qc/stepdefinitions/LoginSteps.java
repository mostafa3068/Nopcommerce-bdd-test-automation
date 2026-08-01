package mostafa.qc.stepdefinitions;

import mostafa.qc.pages.BasePage;
import mostafa.qc.utils.WaitUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import mostafa.qc.pages.HomePage;
import mostafa.qc.pages.LoginPage;
import mostafa.qc.utils.ConfigReader;
import mostafa.qc.utils.DriverFactory;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ============================================================================
 * Login Step Definitions
 * ============================================================================
 * Description: Step definitions for login feature scenarios.
 * Maps Gherkin steps to Java automation code.
 * Author: Mostafa QC
 * ============================================================================
 */
public class LoginSteps {

    // ========================================================================
    // INSTANCE VARIABLES
    // ========================================================================

    private final WebDriver driver;
    private final LoginPage loginPage;
    private final HomePage homePage;

    // ========================================================================
    // CONSTRUCTOR
    // ========================================================================

    /**
     * Initializes page objects with the current WebDriver instance.
     */
    public LoginSteps() {
        this.driver = DriverFactory.getDriver();
        this.loginPage = new LoginPage(driver);
        this.homePage = new HomePage(driver);
    }

    // ========================================================================
    // GIVEN STEPS
    // ========================================================================

    /**
     * Navigates to the login page.
     */
    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        loginPage.open();
        System.out.println("Opening login page");
    }



    /**
     * Logs in the user with default credentials from config.
     */
    @Given("the user is logged in")
    public void theUserIsLoggedIn() {
        loginPage.open();
        loginPage.login(
                ConfigReader.get("default.username"),
                ConfigReader.get("default.password")
        );

        // Verify login was successful
        assertThat(homePage.isUserLoggedIn())
                .as("User should be logged in")
                .isTrue();
    }

    // ========================================================================
    // WHEN STEPS
    // ========================================================================

    /**
     * Enters email in the email field.
     *
     * @param email Email address to enter
     */
    @When("the user enters valid email {string}")
    public void theUserEntersValidEmail(String email) {
        loginPage.enterEmail(email);
    }

    /**
     * Enters valid password in the password field.
     *
     * @param password Password to enter
     */
    @When("the user enters valid password {string}")
    public void theUserEntersValidPassword(String password) {
        loginPage.enterPassword(password);
    }

    /**
     * Enters invalid password in the password field.
     *
     * @param password Invalid password to enter
     */
    @When("the user enters invalid password {string}")
    public void theUserEntersInvalidPassword(String password) {
        loginPage.enterPassword(password);
    }

    /**
     * Clicks the login button.
     */

    @When("the user clicks the login button")
    public void theUserClicksTheLoginButton() {
        loginPage.clickLogin();
    }



    // ========================================================================
    // THEN STEPS
    // ========================================================================

    /**
     * Verifies user is redirected to home page after successful login.
     */



    @Then("the user should be redirected to the home page")
    public void the_user_should_be_redirected_to_the_homepage() {
        System.out.println("Checking if the user is redirected to the home page");
        String currentUrl = driver.getCurrentUrl();
        assertThat(currentUrl)
                .as("User should be on home page")
                .contains(ConfigReader.getBaseUrl());
        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Base URL: " + ConfigReader.getBaseUrl());
    }

    /*
    @Then("the user should be redirected to the home page")
    public void theUserShouldBeRedirectedToTheHomePage() {


    }
*/

    /**
     * Verifies logout link is visible (indicates user is logged in).
     */
    @Then("the user should see the logout link")
    public void theUserShouldSeeTheLogoutLink() {
        assertThat(homePage.isUserLoggedIn())
                .as("Logout link should be visible")
                .isTrue();

    }

    /**
     * Verifies login error message is displayed.
     *
     * @param expectedMessage Expected error message text
     */
    @Then("the user should see login error message {string}")
    public void theUserShouldSeeLoginErrorMessage(String expectedMessage) {
        System.out.println("from inside the user error message*****");
        System.out.println(loginPage.getErrorMessage());
        assertThat(loginPage.isErrorMessageDisplayed())
                .as("Error message should be displayed")
                .isTrue();

        assertThat(loginPage.getErrorMessage())
                .as("Error message should contain expected text")
                .containsIgnoringCase(expectedMessage);
    }

    /**
     * Verifies user remains on login page after failed login.
     */
    @Then("the user should remain on the login page")
    public void theUserShouldRemainOnTheLoginPage() {
        System.out.println(driver.getCurrentUrl());
        assertThat(driver.getCurrentUrl())
                .as("User should still be on login page")
                .contains("/login");
    }

    /**
     * Verifies validation error for empty email field.
     */
    @Then("the user should see validation error for empty email")
    public void theUserShouldSeeValidationErrorForEmptyEmail() {
        String errors = loginPage.getValidationErrors();
        System.out.println("DEBUG: Found validation errors: [" + errors + "]");
        assertThat(errors)
                .as("Validation error should mention email")
                .containsIgnoringCase("email");

    }
}
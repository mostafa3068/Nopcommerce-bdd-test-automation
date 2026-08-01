package mostafa.qc.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import mostafa.qc.pages.HomePage;
import mostafa.qc.pages.SearchResultsPage;
import mostafa.qc.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Alert;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ============================================================================
 * Search Step Definitions
 * ============================================================================
 * Description: Step definitions for product search feature scenarios.
 * Maps Gherkin steps to Java automation code.
 * Author: Mostafa QC
 * ============================================================================
 */
public class SearchSteps {

    // ========================================================================
    // INSTANCE VARIABLES
    // ========================================================================

    private final WebDriver driver;
    private final HomePage homePage;
    private final SearchResultsPage searchResultsPage;

    // ========================================================================
    // CONSTRUCTOR
    // ========================================================================

    /**
     * Initializes page objects with the current WebDriver instance.
     */
    public SearchSteps() {
        this.driver = DriverFactory.getDriver();
        this.homePage = new HomePage(driver);
        this.searchResultsPage = new SearchResultsPage(driver);
    }

    // ========================================================================
    // GIVEN STEPS
    // ========================================================================

    /**
     * Navigates to the home page.
     */
    @Given("the user is on the home page")
    public void theUserIsOnTheHomePage() {
        homePage.open();
    }

    // ========================================================================
    // WHEN STEPS
    // ========================================================================

    /**
     * Performs a product search.
     *
     * @param keyword Search keyword
     */
    @When("the user searches for {string}")
    public void theUserSearchesFor(String keyword) {
        homePage.searchFor(keyword);
    }

    /**
     * Types in search box without submitting (for auto-suggest testing).
     *
     * @param keyword Partial search keyword
     */
    @When("the user types {string} in the search box")
    public void theUserTypesInTheSearchBox(String keyword) {
        // This would need a separate method in HomePage that doesn't click search
        // For now, we'll use the search method
        homePage.typeInto(keyword);
    }

    // ========================================================================
    // THEN STEPS
    // ========================================================================

    /**
     * Verifies search results are displayed.
     */
    @Then("the search results should be displayed")
    public void theSearchResultsShouldBeDisplayed() {

        assertThat(searchResultsPage.hasResults())
                .as("Search results should be displayed")
                .isTrue();
//                try {
//                    // Pause for 3000 milliseconds (3 seconds)
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt(); // Restore interrupted status
//                    System.out.println("The delay was interrupted.");
//                }

    }

    /**
     * Verifies search results contain products with specified keyword.
     *
     * @param keyword Expected keyword in product names
     */
    @Then("the search results should contain products with {string}")
    public void theSearchResultsShouldContainProductsWith(String keyword) {

        List<String> productNames = searchResultsPage.getAllProductNames();
//        System.out.println("The search results should contain products with " + productNames.get(0));

        assertThat(productNames)
                .as("At least one product should contain the keyword")
                .anyMatch(name -> name.toLowerCase().contains(keyword.toLowerCase()));
    }

    /**
     * Verifies search results contain products.
     */
    @Then("the search results should contain products")
    public void theSearchResultsShouldContainProducts() {
        assertThat(searchResultsPage.getProductCount())
                .as("Search results should contain at least one product")
                .isGreaterThan(0);

//                try {
//                            // Pause for 3000 milliseconds (3 seconds)
//                            Thread.sleep(3000);
//                        } catch (InterruptedException e) {
//                            Thread.currentThread().interrupt(); // Restore interrupted status
//                            System.out.println("The delay was interrupted.");
//                        }
    }

    /**
     * Verifies no results message is displayed.
     *
     * @param expectedMessage Expected no results message
     */
    @Then("the user should see no results message {string}")
    public void theUserShouldSeeNoResultsMessage(String expectedMessage) {
        assertThat(searchResultsPage.isNoResultMessageDisplayed())
                .as("No results message should be displayed")
                .isTrue();

        //System.out.println(searchResultsPage.getNoResultMessage());
        assertThat(searchResultsPage.getNoResultMessage())
                .as("No results message should contain expected text")
                .containsIgnoringCase(expectedMessage);
    }

    /**
     * Verifies search warning message is displayed (for empty search).
     */
    @Then("the user should see search warning message")
    public void theUserShouldSeeSearchWarningMessage() {

        System.out.println("from inside the user should see search warning message");

        // 1. Switch WebDriver focus to the active alert
        Alert alert = driver.switchTo().alert();

        // 2. Get the text from the alert to verify it is correct
        String alertText = alert.getText();
        System.out.println("Alert says: " + alertText);

        assertThat(alertText)
                .as("Warning message should match the expected text")
                .isEqualTo("Please enter some search keyword");

        // 3. Click "OK" on the alert to close it and unfreeze the page
        alert.accept();

    }

    /**
     * Verifies auto-suggest dropdown appears.
     */
    @Then("the auto-suggest dropdown should appear")
    public void theAutoSuggestDropdownShouldAppear() {
        //System.out.println(searchResultsPage.isAutoSuggestDropdownVisible());
        assertThat(searchResultsPage.isAutoSuggestDropdownVisible())
                .as("Auto-suggest dropdown should be visible after typing")
                .isTrue();
    }

    /**
     * Verifies suggestions contain relevant products.
     */
    @Then("all suggestions should contain the keyword {string}")
    public void allSuggestionsShouldContainTheKeyword(String keyword) {
        // Verify every suggestion in the dropdown contains the searched keyword
        assertThat(searchResultsPage.doAllSuggestionsMatch(keyword))
                .as("All auto-suggest items should contain '" + keyword + "'")
                .isTrue();

        // Also verify the list is not empty
        //System.out.println(searchResultsPage.doAllSuggestionsMatch(keyword));
        assertThat(searchResultsPage.getAutoSuggestTexts())
                .as("Auto-suggest should return at least one result")
                .isNotEmpty();
    }

}
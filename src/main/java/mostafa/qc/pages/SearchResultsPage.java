package mostafa.qc.pages;

import mostafa.qc.utils.WaitUtils;
import mostafa.qc.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ============================================================================
 * SearchResultsPage Page Object
 * ============================================================================
 * Description: Represents the nopCommerce search results page.
 *              Contains elements and actions for interacting with
 *              product search results.
 * Author: Mostafa QC
 * URL: /search?q={keyword}
 * ============================================================================
 */
public class SearchResultsPage extends BasePage {

    // ========================================================================
    // WEB ELEMENTS - Located using @FindBy annotations (PageFactory)
    // ========================================================================

    /** List of all product items in search results */
    @FindBy(css = ".product-item")
    private List<WebElement> productItems;

    /** List of all product title links in search results */
    @FindBy(css = ".product-title a")
    private List<WebElement> productTitles;

    /** "No results" message shown when search returns nothing */
    @FindBy(css = ".no-result")
    private WebElement noResultMessage;

    /** Search results page heading */
    @FindBy(css = ".search-results")
    private WebElement searchResultsContainer;

    /** Warning message for empty search */
    @FindBy(css = ".warning")
    private WebElement warningMessage;

    @FindBy(id = "ui-id-1")
    private WebElement autoSuggestDropdown;

    // this to get the full value for the list that appear to compare it with the input
    @FindBy(css = "#ui-id-1 li")
    private List<WebElement> autoSuggestItems;

    // ========================================================================
    // CONSTRUCTOR
    // ========================================================================

    /**
     * Initializes SearchResultsPage with WebDriver and PageFactory.
     *
     * @param driver WebDriver instance from DriverFactory
     */
    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    // ========================================================================
    // PAGE ACTIONS
    // ========================================================================

    /**
     * Clicks on a product by its index in the search results.
     *
     * @param index Zero-based index of the product to click
     */
    public void clickProductByIndex(int index) {
        if (index < productTitles.size()) {
            click(productTitles.get(index));
        } else {
            throw new IndexOutOfBoundsException(
                    "Product index " + index + " is out of bounds. Total products: " + productTitles.size()
            );
        }
    }

    // ========================================================================
    // PAGE VERIFICATIONS
    // ========================================================================

    /**
     * Checks if search results are displayed.
     *
     * @return true if at least one product is shown
     */
    public boolean hasResults() {
        return !productItems.isEmpty();
    }

    /**
     * Gets the total number of products in search results.
     *
     * @return Number of product items displayed
     */
    public int getProductCount() {
        return productItems.size();
    }

    /**
     * Gets all product names from the search results.
     *
     * @return List of product name strings
     */
    public List<String> getAllProductNames() {
        return productTitles.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    /**
     * Checks if the "no results" message is displayed.
     *
     * @return true if no results message is visible
     */
    public boolean isNoResultMessageDisplayed() {
        return isDisplayed(noResultMessage);
    }

    /**
     * Gets the "no results" message text.
     *
     * @return No results message text
     */
    public String getNoResultMessage() {
        return getText(noResultMessage);
    }

    /**
     * Gets the warning message text (e.g., for empty search).
     *
     * @return Warning message text
     */
    public String getWarningMessage() {
        return getText(warningMessage);
    }


    // why it's here not in the homepage (bec.appears with the search  so make the related search things together )
    /**
     * Checks if the auto-suggest dropdown is visible.
     * Uses explicit wait since the dropdown appears dynamically after typing.
     *
     * @return true if the dropdown is displayed
     */
    public boolean isAutoSuggestDropdownVisible() {
        try {
            WaitUtils.waitForVisibility(driver, autoSuggestDropdown);
            return autoSuggestDropdown.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * Gets all suggestion texts from the auto-suggest dropdown.
     *
     * @return List of suggestion texts
     */
    public List<String> getAutoSuggestTexts() {
        WaitUtils.waitForVisibility(driver, autoSuggestDropdown);
        return autoSuggestItems.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }


    /**
     * Verifies all suggestions contain the searched keyword (case-insensitive).
     * E.g., searching "com" should return suggestions like "computer", "command remote"
     *
     * @param keyword The text typed in the search bar
     * @return true if ALL suggestions contain the keyword
     */
    public boolean doAllSuggestionsMatch(String keyword) {
        List<String> suggestions = getAutoSuggestTexts();
        System.out.println("DEBUG: Search keyword: " + keyword);
        System.out.println("DEBUG: Suggestions found: " + suggestions);

        return suggestions.stream()
                .allMatch(text -> text.toLowerCase().contains(keyword.toLowerCase()));
    }


    }






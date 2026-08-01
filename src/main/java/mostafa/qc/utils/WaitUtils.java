package mostafa.qc.utils;

import mostafa.qc.constants.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * ============================================================================
 * WaitUtils Utility Class
 * ============================================================================
 * Description: Provides reusable explicit wait methods for stable test
 *              execution. Explicit waits are essential for handling dynamic
 *              web elements that @FindBy alone cannot handle.
 *
 * Why needed alongside @FindBy:
 *   - @FindBy provides lazy loading and cleaner element declarations
 *   - WaitUtils handles timing: waiting for elements to be visible,
 *     clickable, or for specific conditions (e.g., text, disappearance)
 *   - Prevents flaky tests caused by timing issues on dynamic pages
 *
 * Author: Mostafa QC
 * ============================================================================
 */
public class WaitUtils {

    // ========================================================================
    // CONSTRUCTOR - Private to prevent instantiation
    // ========================================================================
    private WaitUtils() {
        // Utility class - should not be instantiated
    }

    // ========================================================================
    // WAIT FOR Explicit time
    // ========================================================================

    /**
     * Pauses test execution for a specified number of seconds.
     * Uses internal try-catch to keep step definitions clean.
     * * @param seconds The number of seconds to wait
     */
    public static void waitForSeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            // Restore interrupted status to respect thread management
            Thread.currentThread().interrupt();
        }
    }

    // ========================================================================
    // WAIT FOR ELEMENT STATE
    // ========================================================================

    /**
     * Waits until the element is visible on the page.
     * Use when element exists in DOM but may not be visible yet.
     *
     * @param driver  WebDriver instance
     * @param element WebElement to wait for
     * @return The visible WebElement
     */
    public static WebElement waitForVisibility(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits until the element is visible using a locator (By).
     *
     * @param driver  WebDriver instance
     * @param locator By locator for the element
     * @return The visible WebElement
     */
    public static WebElement waitForVisibility(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits until the element is clickable (visible + enabled).
     * Use before clicking buttons, links, or any interactive elements.
     *
     * @param driver  WebDriver instance
     * @param element WebElement to wait for
     * @return The clickable WebElement
     */
    public static WebElement waitForClickability(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits until the element is clickable using a locator (By).
     *
     * @param driver  WebDriver instance
     * @param locator By locator for the element
     * @return The clickable WebElement
     */
    public static WebElement waitForClickability(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits until the element disappears from the page.
     * Use after actions that remove elements (e.g., loading spinners).
     *
     * @param driver  WebDriver instance
     * @param locator By locator for the element to disappear
     */
    public static void waitForInvisibility(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // ========================================================================
    // WAIT FOR PAGE / URL CONDITIONS
    // ========================================================================

    /**
     * Waits until the page URL contains the specified text.
     * Use after navigation to verify correct page is loaded.
     *
     * @param driver  WebDriver instance
     * @param urlPart Partial URL text to wait for
     */
    public static void waitForUrlContains(WebDriver driver, String urlPart) {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.urlContains(urlPart));
    }

    /**
     * Waits until the page title contains the specified text.
     *
     * @param driver    WebDriver instance
     * @param titlePart Partial title text to wait for
     */
    public static void waitForTitleContains(WebDriver driver, String titlePart) {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.titleContains(titlePart));
    }

    // ========================================================================
    // WAIT FOR TEXT CONDITIONS
    // ========================================================================

    /**
     * Waits until the element contains the specified text.
     * Use for dynamic text that loads after page render.
     *
     * @param driver  WebDriver instance
     * @param element WebElement to check
     * @param text    Expected text to wait for
     */
    public static void waitForTextPresent(WebDriver driver, WebElement element, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    // ========================================================================
    // CUSTOM TIMEOUT WAITS
    // ========================================================================

    /**
     * Waits for element visibility with a custom timeout.
     * Use when default timeout is not appropriate.
     *
     * @param driver         WebDriver instance
     * @param element        WebElement to wait for
     * @param timeoutSeconds Custom timeout in seconds
     * @return The visible WebElement
     */
    public static WebElement waitForVisibility(WebDriver driver, WebElement element, int timeoutSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits for element clickability with a custom timeout.
     *
     * @param driver         WebDriver instance
     * @param element        WebElement to wait for
     * @param timeoutSeconds Custom timeout in seconds
     * @return The clickable WebElement
     */
    public static WebElement waitForClickability(WebDriver driver, WebElement element, int timeoutSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.elementToBeClickable(element));
    }



}
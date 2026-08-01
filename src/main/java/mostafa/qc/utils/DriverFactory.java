package mostafa.qc.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import mostafa.qc.constants.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================================
 * DriverFactory Utility Class
 * ============================================================================
 * Description: Manages WebDriver lifecycle using the Factory Pattern.
 *              Uses ThreadLocal to ensure thread-safe parallel execution -
 *              each thread gets its own WebDriver instance.
 * Author: Mostafa QC
 *
 * Design Pattern: Factory Pattern + ThreadLocal
 * Thread Safety: Yes - via ThreadLocal<WebDriver>
 * ============================================================================
 */
public class DriverFactory {

    // ========================================================================
    // THREAD-LOCAL WEBDRIVER
    // ========================================================================

    /**
     * ThreadLocal ensures each parallel test thread has its own WebDriver.
     * Prevents tests from interfering with each other during parallel runs.
     */
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    // ========================================================================
    // CONSTRUCTOR - Private to prevent instantiation
    // ========================================================================
    private DriverFactory() {
        // Utility class - should not be instantiated
    }

    // ========================================================================
    // PUBLIC METHODS
    // ========================================================================

    /**
     * Initializes a new WebDriver instance for the current thread.
     * Browser type is read from config.properties.
     * Sets implicit wait and maximizes the window.
     */
    public static void initDriver() {
        String browser = ConfigReader.getBrowser().toLowerCase();
        WebDriver driver;

        switch (browser) {
            case Constants.FIREFOX:
                // Setup Firefox driver automatically via WebDriverManager
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case Constants.EDGE:
                // Setup Edge driver automatically via WebDriverManager
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            case Constants.CHROME:
            default:
                // Setup Chrome driver automatically via WebDriverManager
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("autofill.profile_enabled", false);
                prefs.put("autofill.credit_card_enabled", false);
                prefs.put("profile.default_content_setting_values.notifications", 2);
                chromeOptions.setExperimentalOption("prefs", prefs);
                chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-save-password-bubble");
                chromeOptions.addArguments("--disable-features=AutofillAddressSavePrompt,AutofillServerCommunication,PasswordManagerOnboarding,PasswordLeakDetection");

                driver = new ChromeDriver(chromeOptions);
                break;
        }

        // Maximize browser window for consistent element visibility
        driver.manage().window().maximize();

        // Set implicit wait as a baseline (explicit waits in WaitUtils take priority)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.DEFAULT_TIMEOUT));

        // Set page load timeout
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Constants.PAGE_LOAD_TIMEOUT));

        // Store driver in ThreadLocal for this thread
        driverThreadLocal.set(driver);

        System.out.println("WebDriver initialized: " + browser + " | Thread: " + Thread.currentThread().getId());
    }

    /**
     * Returns the WebDriver instance for the current thread.
     *
     * @return WebDriver instance
     * @throws RuntimeException if driver has not been initialized
     */
    public static WebDriver getDriver() {
        WebDriver driver = driverThreadLocal.get();

        if (driver == null) {
            throw new RuntimeException("WebDriver not initialized. Call initDriver() first.");
        }

        return driver;
    }

    /**
     * Quits the WebDriver and removes it from ThreadLocal.
     * Must be called after each test to prevent memory leaks.
     */
    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();

        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove(); // Prevent memory leak
            System.out.println("WebDriver quit | Thread: " + Thread.currentThread().getId());
        }
    }
}
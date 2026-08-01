package mostafa.qc.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import mostafa.qc.utils.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/**
 * ============================================================================
 * Hooks Class
 * ============================================================================
 * Description: Contains Cucumber hooks that run before and after scenarios.
 * Handles WebDriver lifecycle and screenshot capture on failure.
 * Author: Mostafa QC
 * ============================================================================
 */
public class Hooks {

    // ========================================================================
    // BEFORE HOOKS
    // ========================================================================

    /**
     * Runs before each scenario.
     * Initializes the WebDriver instance.
     *
     * @param scenario Current scenario being executed
     */
    @Before
    public void setUp(Scenario scenario) {
        System.out.println("========================================");
        System.out.println("STARTING SCENARIO: " + scenario.getName());
        System.out.println("TAGS: " + scenario.getSourceTagNames());
        System.out.println("========================================");

        // Initialize WebDriver
        DriverFactory.initDriver();
    }

    // ========================================================================
    // AFTER HOOKS
    // ========================================================================

    /**
     * Runs after each scenario.
     * Captures screenshot on failure and quits the WebDriver.
     *
     * @param scenario Current scenario that was executed
     */
    @After
    public void tearDown(Scenario scenario) {
        try {
            // Capture screenshot if scenario failed
            if (scenario.isFailed()) {
                captureScreenshot(scenario);
            }

            System.out.println("========================================");
            System.out.println("FINISHED SCENARIO: " + scenario.getName());
            System.out.println("STATUS: " + scenario.getStatus());
            System.out.println("========================================\n");

        } finally {
            // Always quit the driver
            DriverFactory.quitDriver();
        }
    }

    // ========================================================================
    // HELPER METHODS
    // ========================================================================

    /**
     * Captures screenshot and attaches it to the Cucumber report.
     *
     * @param scenario Current scenario for attaching screenshot
     */
    private void captureScreenshot(Scenario scenario) {
        try {
            TakesScreenshot ts = (TakesScreenshot) DriverFactory.getDriver();
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot on Failure");
            System.out.println("Screenshot captured for failed scenario: " + scenario.getName());
        } catch (Exception e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
    }
}
package mostafa.qc.runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * ============================================================================
 * Main Test Runner
 * ============================================================================
 * Description: Primary Cucumber-TestNG runner that executes all test scenarios.
 * Supports parallel execution and generates multiple report formats.
 * Author: Mostafa QC
 *
 * Usage:
 *   - Run all tests: mvn test -Dtest=TestRunner
 *   - Run with tags: mvn test -Dcucumber.filter.tags="@smoke"
 * ============================================================================
 */
@CucumberOptions(
        // ========================================================================
        // FEATURE FILES LOCATION
        // ========================================================================
        features = "src/test/resources/features",
        // ========================================================================
        // STEP DEFINITIONS PACKAGE
        // ========================================================================
        glue = {"mostafa.qc.stepdefinitions"},

        // ========================================================================
        // PLUGINS FOR REPORTING
        // ========================================================================
        plugin = {
                // Console output with colors
                "pretty",

                // HTML report
                "html:target/cucumber-reports/cucumber.html",

                // JSON report (for CI/CD integration)
                "json:target/cucumber-reports/cucumber.json",

                // JUnit XML report (for Jenkins)
                "junit:target/cucumber-reports/cucumber.xml",

                // Timeline report (for parallel execution analysis)
                "timeline:target/cucumber-reports/timeline",

                // Rerun failed scenarios file
                "rerun:target/cucumber-reports/rerun.txt"
        },

        // ========================================================================
        // ADDITIONAL OPTIONS
        // ========================================================================

        // Show snippets for undefined steps
        snippets = CucumberOptions.SnippetType.CAMELCASE,

        // Don't run - just check if all steps are defined
        dryRun = false,

        // Strict mode - fail on undefined/pending steps
        publish = false,

        // Enable monochrome output (cleaner logs)
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

    /**
     * Enables parallel execution of scenarios.
     * Each scenario runs in its own thread.
     *
     * @return Object[][] of scenarios for parallel execution
     */
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
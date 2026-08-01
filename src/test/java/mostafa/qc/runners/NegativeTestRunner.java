package mostafa.qc.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * ============================================================================
 * Negative Test Runner
 * ============================================================================
 * Description: Runs only negative/edge case tests tagged with @negative.
 * Use for validating error handling and edge cases.
 * Author: Mostafa QC
 *
 * Usage:
 *   - mvn test -Dtest=NegativeTestRunner
 * ============================================================================
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"mostafa.qc.stepdefinitions"},

        // ========================================================================
        // NEGATIVE TAG FILTER
        // ========================================================================
        tags = "@negative",

        plugin = {
                "pretty",
                "html:target/cucumber-reports/negative/cucumber.html",
                "json:target/cucumber-reports/negative/cucumber.json"
        },

        monochrome = true
)
public class NegativeTestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
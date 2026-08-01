package mostafa.qc.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * ============================================================================
 * Smoke Test Runner
 * ============================================================================
 * Description: Runs only smoke tests tagged with @smoke.
 * Use for quick validation of critical functionality.
 * Author: Mostafa QC
 *
 * Usage:
 *   - mvn test -Dtest=SmokeTestRunner
 *   - Typically runs: 5-10 critical scenarios
 *   - Expected duration: ~5 minutes
 * ============================================================================
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"mostafa.qc.stepdefinitions"},

        // ========================================================================
        // SMOKE TAG FILTER
        // ========================================================================
        tags = "@smoke",

        plugin = {
                "pretty",
                "html:target/cucumber-reports/smoke/cucumber.html",
                "json:target/cucumber-reports/smoke/cucumber.json",
                "junit:target/cucumber-reports/smoke/cucumber.xml"
        },

        monochrome = true
)
public class SmokeTestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
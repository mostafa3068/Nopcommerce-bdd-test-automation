package mostafa.qc.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * ============================================================================
 * Regression Test Runner
 * ============================================================================
 * Description: Runs full regression suite tagged with @regression.
 * Use for comprehensive testing before releases.
 * Author: Mostafa QC
 *
 * Usage:
 *   - mvn test -Dtest=RegressionTestRunner
 *   - Typically runs: All scenarios (~25-30)
 *   - Expected duration: ~30-45 minutes
 * ============================================================================
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"mostafa.qc.stepdefinitions"},

        // ========================================================================
        // REGRESSION TAG FILTER
        // ========================================================================
        tags = "@regression",

        plugin = {
                "pretty",
                "html:target/cucumber-reports/regression/cucumber.html",
                "json:target/cucumber-reports/regression/cucumber.json",
                "junit:target/cucumber-reports/regression/cucumber.xml",
                "timeline:target/cucumber-reports/regression/timeline",
                "rerun:target/cucumber-reports/regression/rerun.txt"
        },

        monochrome = true
)
public class RegressionTestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
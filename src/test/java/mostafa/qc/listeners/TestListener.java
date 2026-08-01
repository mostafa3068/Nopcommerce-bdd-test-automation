package mostafa.qc.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * ============================================================================
 * TestNG Test Listener
 * ============================================================================
 * Description: Custom listener for test execution events.
 * Provides logging and can be extended for custom reporting.
 * Author: Mostafa QC
 * ============================================================================
 */
public class TestListener implements ITestListener {

    // ========================================================================
    // SUITE LEVEL EVENTS
    // ========================================================================

    @Override
    public void onStart(ITestContext context) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("STARTING TEST SUITE: " + context.getName());
        System.out.println("=".repeat(60) + "\n");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("FINISHED TEST SUITE: " + context.getName());
        System.out.println("-".repeat(60));
        System.out.println("PASSED:  " + context.getPassedTests().size());
        System.out.println("FAILED:  " + context.getFailedTests().size());
        System.out.println("SKIPPED: " + context.getSkippedTests().size());
        System.out.println("=".repeat(60) + "\n");
    }

    // ========================================================================
    // TEST LEVEL EVENTS
    // ========================================================================

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("\n[RUNNING] " + getTestName(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("[PASSED]  " + getTestName(result) +
                " (" + getExecutionTime(result) + "ms)");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("[FAILED]  " + getTestName(result));
        System.out.println("          Error: " + result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("[SKIPPED] " + getTestName(result));
    }

    // ========================================================================
    // HELPER METHODS
    // ========================================================================

    /**
     * Gets formatted test name.
     */
    private String getTestName(ITestResult result) {
        return result.getMethod().getMethodName();
    }

    /**
     * Calculates test execution time in milliseconds.
     */
    private long getExecutionTime(ITestResult result) {
        return result.getEndMillis() - result.getStartMillis();
    }
}
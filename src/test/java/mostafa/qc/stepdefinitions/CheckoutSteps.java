package mostafa.qc.stepdefinitions;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import mostafa.qc.pages.CheckoutPage;
import mostafa.qc.utils.DriverFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ============================================================================
 * Checkout Step Definitions
 * ============================================================================
 * Description: Step definitions for checkout process feature scenarios.
 * Maps Gherkin steps to Java automation code.
 * Author: Mostafa QC
 * ============================================================================
 */
public class CheckoutSteps {

    // ========================================================================
    // INSTANCE VARIABLES
    // ========================================================================

    private final WebDriver driver;
    private final CheckoutPage checkoutPage;

    // ========================================================================
    // CONSTRUCTOR
    // ========================================================================

    /**
     * Initializes page objects with the current WebDriver instance.
     */
    public CheckoutSteps() {
        this.driver = DriverFactory.getDriver();
        this.checkoutPage = new CheckoutPage(driver);
    }

    // ========================================================================
    // WHEN STEPS - BILLING ADDRESS
    // ========================================================================

    /**
     * Fills billing address with valid test data.
     */
    @When("the user fills billing address with valid data")
    public void theUserFillsBillingAddressWithValidData() {
        checkoutPage.fillBillingAddress(
                "John",                    // firstName
                "Doe",                     // lastName
                "john.doe@test.com",       // email
                "Egypt",           // country
                "Cairo",                // Province
                "Cairo",                // city
                "123 Test Street",         // address
                "10001",                   // zip
                "1234567890"               // phone
        );
    }

    /**
     * Leaves billing address fields empty (for negative testing).
     */
    @When("the user leaves billing address fields empty")
    public void theUserLeavesBillingAddressFieldsEmpty() {

       checkoutPage.leaveBillingAddress();

    }

    /**
     * Continues from billing address step.
     */
    @When("the user continues to shipping method choose to stay on default and click continue")
    public void theUserContinuesToShippingMethod() {

        // the user chooses to stay on the default option which is the ground shipment

        // then press continue
        checkoutPage.continueBilling();
    }

    /**
     * Tries to continue (may fail for validation errors).
     */
    @When("the user tries to continue")
    public void theUserTriesToContinue() {
        try {
            checkoutPage.continueBilling();
        } catch (Exception e) {
            // Expected for validation errors
        }
    }

    // ========================================================================
    // WHEN STEPS - SHIPPING & PAYMENT
    // ========================================================================

    /**
     * Continues from shipping method step.
     */
    @When("the user continues to payment method choose to stay on default and click continue")
    public void theUserContinuesToPaymentMethod() {
        // the user choose to stay on the default way which is pay by check

        // then press continue
        checkoutPage.continueShippingMethod();
    }

    /**
     * Selects cash on delivery payment method.
     */
    @When("the user selects cash on delivery")
    public void theUserSelectsCashOnDelivery() {
        checkoutPage.selectCashOnDelivery();
    }

    /**
     * Selects credit card payment method.
     */
    @When("the user selects credit card payment")
    public void theUserSelectsCreditCardPayment() {

        checkoutPage.continueShippingMethod();
        checkoutPage.selectCreditCard();

    }

    /**
     * Enters valid credit card details.
     */
    @When("the user enters valid card details")
    public void theUserEntersValidCardDetails() {
        checkoutPage.continuePaymentMethod();
        checkoutPage.fillCreditCardDetails(
                "John Doe",            // holder
                "4111111111111111",    // number (test card)
                "12",                  // month
                "2027",                // year
                "123"                  // cvv
        );
    }

    /**
     * Enters invalid credit card details (for negative testing).
     */
    @When("the user enters invalid card details")
    public void theUserEntersInvalidCardDetails() {
        checkoutPage.continuePaymentMethod();
        checkoutPage.fillCreditCardDetails(
                "John Doe",            // holder
                "4000",    // number (declined test card)
                "12",                  // month
                "2027",                // year
                "123"                  // cvv
        );
    }

    /**
     * Continues from payment info step.
     */
    @When("the user continues to payment info then continue")
    public void theUserContinuesToPaymentInfo() {

        // the user confirm his data then press continue
        checkoutPage.continuePaymentMethod();
        checkoutPage.continuePaymentInfo();
    }

    @When("the user continues to payment info expecting payment error")
    public void theUserContinuesToPaymentInfoExpectingPaymentError() {
        checkoutPage.continuePaymentInfoExpectingError();
    }

    /**
     * Confirms the order.
     */
    @When("the user confirms the order")
    public void theUserConfirmsTheOrder() {
        //checkoutPage.continuePaymentInfo();
        checkoutPage.confirmOrder();
    }

    /**
     * check the box for the term of services .
     */
    @When("check the check box of the term of the services")
    public void theUserChecksTheTermOfServices() {
        checkoutPage.check_the_check_box_for_the_term_of_the_services();
    }






    // ========================================================================
    // THEN STEPS
    // ========================================================================

    /**
     * Verifies order was placed successfully.
     */
    @Then("the order should be placed successfully")
    public void theOrderShouldBePlacedSuccessfully() {
        System.out.println("order is placed successfully:" + checkoutPage.isOrderConfirmed());
        assertThat(checkoutPage.isOrderConfirmed())
                .as("Order should be confirmed")
                .isTrue();
    }

    /**
     * Verifies order confirmation with order number is displayed.
     */
    @Then("the user should see order confirmation with order number")
    public void theUserShouldSeeOrderConfirmationWithOrderNumber() {
//        assertThat(checkoutPage.isOrderConfirmed())
//                .as("Order confirmation should be displayed")
//                .isTrue();

        String orderNumber = checkoutPage.getOrderNumber();
        assertThat(orderNumber)
                .as("Order number should be displayed")
                .isNotEmpty();

        System.out.println("Order placed successfully. Order Number: " + orderNumber);
    }

    /**
     * Verifies payment error message is displayed.
     */
    @Then("the user should see payment error message")
    public void theUserShouldSeePaymentErrorMessage() {
        // Verify we're not on confirmation page (payment failed)
        assertThat(checkoutPage.isOrderConfirmed())
                .as("Order should NOT be confirmed due to payment error")
                .isFalse();
    }

    /**
     * Verifies billing validation errors are displayed.
     */
    @Then("the user should see billing validation errors")
    public void theUserShouldSeeBillingValidationErrors() {


        assertThat(driver.getCurrentUrl())
                .as("User should still be on checkout page with validation errors")
                .contains("checkout");

        System.out.println("From inside the billing error message "+driver.getCurrentUrl());


//        // Verify we're still on billing step (validation failed)
//
//        // Switch to alert
//        Alert alert = driver.switchTo().alert();
//        // Get alert message text
//        String alertMessage = alert.getText();
//        // Accept (click OK)
//        alert.accept();
    }


    /**
     * Error credit number info should appear .
     */
    @Then("the user should see wrong credit number massage")
    public void the_user_should_see_wrong_credit_number_massage() {

        Assert.assertTrue(checkoutPage.CreditError()
                .toLowerCase()
                .contains("wrong"));

        System.out.println(checkoutPage.CreditError());


    }



}
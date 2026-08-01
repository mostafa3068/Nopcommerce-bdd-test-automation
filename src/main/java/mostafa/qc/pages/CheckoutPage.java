package mostafa.qc.pages;

import mostafa.qc.constants.Constants;
import mostafa.qc.utils.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * ============================================================================
 * CheckoutPage Page Object
 * ============================================================================
 * Description: Represents the nopCommerce checkout page.
 *              Handles the multi-step checkout process:
 *              Billing → Shipping → Payment Method → Payment Info → Confirm
 * Author: Mostafa QC
 * URL: /checkout
 * ============================================================================
 */
public class CheckoutPage extends BasePage {

    // ========================================================================
    // WEB ELEMENTS - BILLING ADDRESS
    // ========================================================================

    /**
     * First name field in billing address
     */
    @FindBy(id = "BillingNewAddress_FirstName")
    private WebElement billingFirstName;

    /**
     * Last name field in billing address
     */
    @FindBy(id = "BillingNewAddress_LastName")
    private WebElement billingLastName;

    /**
     * Email field in billing address
     */
    @FindBy(id = "BillingNewAddress_Email")
    private WebElement billingEmail;

    /**
     * Country dropdown in billing address
     */
    @FindBy(id = "BillingNewAddress_CountryId")
    private WebElement billingCountry;

    /**
     * Province dropdown in billing address
     */
    @FindBy(id = "BillingNewAddress_StateProvinceId")
    private WebElement province;

    /**
     * City field in billing address
     */
    @FindBy(id = "BillingNewAddress_City")
    private WebElement billingCity;

    /**
     * Street address field in billing address
     */
    @FindBy(id = "BillingNewAddress_Address1")
    private WebElement billingAddress;

    /**
     * Zip/postal code field in billing address
     */
    @FindBy(id = "BillingNewAddress_ZipPostalCode")
    private WebElement billingZip;

    /**
     * Phone number field in billing address
     */
    @FindBy(id = "BillingNewAddress_PhoneNumber")
    private WebElement billingPhone;

    /**
     * Continue button on billing step
     */
    @FindBy(css = "#billing-buttons-container .new-address-next-step-button")
    private WebElement billingContinueButton;


    /**
     * DELETE button
     */
    @FindBy(id = "delete-billing-address-button")
    WebElement deleteAddressButton;   // *************************************



    // ========================================================================
    // WEB ELEMENTS - SHIPPING METHOD
    // ========================================================================

    /**
     * Continue button on shipping method step
     */
    @FindBy(css = "#shipping-method-buttons-container .button-1.shipping-method-next-step-button")
    private WebElement shippingMethodContinueButton;

    // ========================================================================
    // WEB ELEMENTS - PAYMENT METHOD
    // ========================================================================

    /**
     * Cash on delivery radio button
     */
    @FindBy(css = "input[value='Payments.CashOnDelivery']")
    private WebElement cashOnDeliveryRadio;

    /**
     * Credit card radio button
     */
    @FindBy(css = "input[value='Payments.Manual']")
    private WebElement creditCardRadio;

    /**
     * Continue button on payment method step
     */
    @FindBy(css = "#payment-method-buttons-container .button-1.payment-method-next-step-button")
    private WebElement paymentMethodContinueButton;

    // ========================================================================
    // WEB ELEMENTS - PAYMENT INFO (Credit Card)
    // ========================================================================

    /**
     * Credit card holder name field
     */
    @FindBy(id = "CardholderName")
    private WebElement cardHolderName;

    /**
     * Credit card number field
     */
    @FindBy(id = "CardNumber")
    private WebElement cardNumber;

    /**
     * Card expiry month dropdown
     */
    @FindBy(id = "ExpireMonth")
    private WebElement cardExpiryMonth;

    /**
     * Card expiry year dropdown
     */
    @FindBy(id = "ExpireYear")
    private WebElement cardExpiryYear;

    /**
     * Card CVV/security code field
     */
    @FindBy(id = "CardCode")
    private WebElement cardCvv;

    /**
     * Continue button on payment info step
     */
    @FindBy(css = "#payment-info-buttons-container button.button-1")
    private WebElement paymentInfoContinueButton;

    // ========================================================================
    // WEB ELEMENTS - ORDER CONFIRMATION
    // ========================================================================

    /**
     * Confirm order button on the final review step
     */
    @FindBy(css = "button.button-1.confirm-order-next-step-button")
    private WebElement confirmOrderButton;

    /**
     * Order completion title shown after successful order
     */
    @FindBy(css = "h2.title")
    private WebElement orderCompletedTitle;

    /**
     * Order number shown on confirmation page
     */
    @FindBy(css = "div.order-number strong")
    private WebElement orderNumber;

    /**
     * check box of the term of usage
     */
    @FindBy(id = "termsofservice")
    private WebElement termsCheckbox;

    @FindBy(id = "billing-address-select")
    private WebElement billingAddressSelect;

    @FindBy(css = "#billing-new-address-form")
    private WebElement billingNewAddressForm;

    // ========================================================================
    // WEB ELEMENTS - Credit card wrong info
    // ========================================================================

    /**
     * check box of the term of usage
     */
    @FindBy(css = "div.message-error.validation-summary-errors")
    WebElement errorMessageDiv;




    // ========================================================================
    // CONSTRUCTOR
    // ========================================================================

    /**
     * Initializes CheckoutPage with WebDriver and PageFactory.
     *
     * @param driver WebDriver instance from DriverFactory
     */
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    // ========================================================================
    // BILLING ADDRESS ACTIONS
    // ========================================================================

    /**
     * Fills in the complete billing address form.
     *
     * @param firstName First name
     * @param lastName  Last name
     * @param email     Email address
     * @param country   Country name
     * @param city      City name
     * @param address   Street address
     * @param zip       Zip/postal code
     * @param phone     Phone number
     */
    public void fillBillingAddress(String firstName, String lastName, String email,
                                   String country,String Province, String city, String address,
                                   String zip, String phone) {
        waitForBillingStep();
        selectNewBillingAddressIfAvailable();
        if (isDisplayed(billingFirstName)) {
            type(billingFirstName, firstName);
            type(billingLastName, lastName);
            type(billingEmail, email);

            billingCountry.click();
            Select select = new Select(billingCountry);
            select.selectByVisibleText(country);

            WaitUtils.waitForVisibility(driver, province);
            province.click();
            Select select2 = new Select(province);
            select2.selectByVisibleText(Province);

            type(billingCity, city);
            type(billingAddress, address);
            type(billingZip, zip);
            type(billingPhone, phone);
        }
        click(billingContinueButton);
    }


    /**
     * leave and billing address empty and click continue.
     */
    public void leaveBillingAddress(){
        waitForBillingStep();
        selectNewBillingAddressIfAvailable();
        click(billingContinueButton);

    }

    private void waitForBillingStep() {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.or(
                        ExpectedConditions.visibilityOf(billingFirstName),
                        ExpectedConditions.visibilityOf(billingAddressSelect),
                        ExpectedConditions.elementToBeClickable(billingContinueButton)
                ));
    }

    private void selectNewBillingAddressIfAvailable() {
        if (!isDisplayed(billingFirstName) && isDisplayed(billingAddressSelect)) {
            Select select = new Select(billingAddressSelect);
            for (WebElement option : select.getOptions()) {
                if (option.getText().toLowerCase().contains("new address")) {
                    select.selectByVisibleText(option.getText());
                    WaitUtils.waitForVisibility(driver, billingNewAddressForm);
                    return;
                }
            }
        }
    }



    /**
     * Clicks continue on the billing address step.
     */
    public void continueBilling() {
        if (isCheckoutStepReady(shippingMethodContinueButton) || isCheckoutStepReady(paymentMethodContinueButton)
                || isCheckoutStepReady(paymentInfoContinueButton) || isCheckoutStepReady(confirmOrderButton)) {
            return;
        }

        if (isCheckoutStepReady(billingContinueButton)) {
            billingContinueButton.click();
        }

        waitForCheckoutStep(shippingMethodContinueButton, paymentMethodContinueButton,
                paymentInfoContinueButton, confirmOrderButton);
    }

    // ========================================================================
    // SHIPPING METHOD ACTIONS
    // ========================================================================

    /**
     * Clicks continue on the shipping method step.
     * Uses default/first shipping method selected.
     */
    public void continueShippingMethod() {
        if (isCheckoutStepReady(paymentMethodContinueButton) || isCheckoutStepReady(paymentInfoContinueButton)
                || isCheckoutStepReady(confirmOrderButton)) {
            return;
        }

        WaitUtils.waitForClickability(driver, shippingMethodContinueButton);
        shippingMethodContinueButton.click();

        waitForCheckoutStep(paymentMethodContinueButton, paymentInfoContinueButton, confirmOrderButton);
    }

    // ========================================================================
    // PAYMENT METHOD ACTIONS
    // ========================================================================

    /**
     * Selects Cash on Delivery as the payment method.
     */
    public void selectCashOnDelivery() {
        click(cashOnDeliveryRadio);
    }

    /**
     * Selects Credit Card as the payment method.
     */
    public void selectCreditCard() {
        click(creditCardRadio);
    }

    /**
     * Clicks continue on the payment method step.
     */
    public void continuePaymentMethod() {
        if (isCheckoutStepReady(paymentInfoContinueButton) || isCheckoutStepReady(confirmOrderButton)) {
            return;
        }

        WaitUtils.waitForClickability(driver, paymentMethodContinueButton);
        paymentMethodContinueButton.click();

        waitForCheckoutStep(paymentInfoContinueButton, confirmOrderButton);
    }

    private boolean isCheckoutStepReady(WebElement element) {
        try {
            return element.isDisplayed() && element.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    private void waitForCheckoutStep(WebElement... elements) {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_TIMEOUT))
                .until(webDriver -> {
                    for (WebElement element : elements) {
                        if (isCheckoutStepReady(element)) {
                            return true;
                        }
                    }
                    return false;
                });
    }

    // ========================================================================
    // PAYMENT INFO ACTIONS
    // ========================================================================

    /**
     * Fills in credit card payment details.
     *
     * @param holder Card holder name
     * @param number Card number
     * @param month  Expiry month (e.g., "12")
     * @param year   Expiry year (e.g., "2027")
     * @param cvv    Card security code
     */
    public void fillCreditCardDetails(String holder, String number,
                                      String month, String year, String cvv) {
        type(cardHolderName, holder);
        type(cardNumber, number);

        // Select expiry month and year from dropdowns
        new Select(cardExpiryMonth).selectByValue(month);
        new Select(cardExpiryYear).selectByValue(year);

        type(cardCvv, cvv);
    }

    /**
     * Clicks continue on the payment info step.
     */
    public void continuePaymentInfo() {
        click(paymentInfoContinueButton);
        WaitUtils.waitForVisibility(driver, confirmOrderButton);
    }

    public void continuePaymentInfoExpectingError() {
        click(paymentInfoContinueButton);
        WaitUtils.waitForVisibility(driver, errorMessageDiv);
    }

    // ========================================================================
    // ORDER CONFIRMATION ACTIONS
    // ========================================================================

    /**
     * Clicks the confirm order button to place the order.
     */
    public void confirmOrder() {
        for (int attempt = 0; attempt < 3; attempt++) {
            click(confirmOrderButton);

            if (acceptRecentOrderAlertIfPresent()) {
                WaitUtils.waitForSeconds(Constants.SHORT_TIMEOUT);
                continue;
            }

            WaitUtils.waitForVisibility(driver, orderCompletedTitle);
            return;
        }

        click(confirmOrderButton);
        WaitUtils.waitForVisibility(driver, orderCompletedTitle);
    }

    private boolean acceptRecentOrderAlertIfPresent() {
        try {
            Alert alert = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            alert.accept();
            return alertText.toLowerCase().contains("wait several seconds");
        } catch (Exception e) {
            return false;
        }
    }

    // ========================================================================
    // PAGE VERIFICATIONS
    // ========================================================================

    /**
     * Checks if the order was placed successfully.
     * Determined by the presence of the order completed title.
     *
     * @return true if order confirmation is displayed
     */
    public boolean isOrderConfirmed() {
        return isDisplayed(orderCompletedTitle);
    }

    /**
     * Gets the order number from the confirmation page.
     *
     * @return Order number text
     */
    public String getOrderNumber() {
        return getText(orderNumber);
    }

// ========================================================================
// Check boxes check
// ========================================================================

    /**
     * Check the box of the term of using .
     *
     */

    public void check_the_check_box_for_the_term_of_the_services() {
        // Only click it if it is not already checked
        if (!termsCheckbox.isSelected()) {
            termsCheckbox.click();
        }

    }
 /*  //we are using the click method because it how the customer interact with the website
     //it's slower, but it's real rather than js

    if (!termsCheckbox.isSelected()) {
            try {
                termsCheckbox.click(); // Try normal click first
            } catch (ElementNotInteractableException e) {
                // Fallback to JS click if hidden/overlapped
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", termsCheckbox);
            }
        }
*/


// ========================================================================
// Credit card error massage
// ========================================================================

    /**
     * get the text of the error message .
     *
     * @return credit error text
     */
    public String CreditError() {
        return getText(errorMessageDiv);
    }


    }




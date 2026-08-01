# ============================================================================
# Feature: Checkout Process
# Description: Tests for checkout functionality on nopCommerce
# Author: Mostafa QC
# Tags: @checkout, @smoke, @regression, @negative
# ============================================================================

@checkout
Feature: Checkout Process
  As a customer
  I want to complete the checkout process
  So that I can purchase products

  # --------------------------------------------------------------------------
  # Background: Common setup - user must have items in cart
  # --------------------------------------------------------------------------
  Background:
    Given the user is logged in
    And the user has a product in the cart

  # --------------------------------------------------------------------------
  # Positive Scenarios
  # --------------------------------------------------------------------------

  @smoke @regression @TC013
  Scenario: Successful checkout with cash on delivery
  """
    Verify that logged-in user can complete checkout using cash on delivery.
    Priority: High | Severity: Critical
    """
    When the user opens the cart page
    And check the check box of the term of the services
    And the user proceeds to checkout
    And the user fills billing address with valid data
    And the user continues to shipping method choose to stay on default and click continue
    And the user continues to payment method choose to stay on default and click continue
    # And the user selects cash on delivery
    And the user continues to payment info then continue
    And the user confirms the order
    Then the order should be placed successfully
    And the user should see order confirmation with order number

  @regression @TC013
  Scenario: Successful checkout with credit card
  """
    Verify that user can complete checkout using credit card payment.
    Priority: High | Severity: Critical
    """
    When the user opens the cart page
    And check the check box of the term of the services
    And the user proceeds to checkout
    And the user fills billing address with valid data
    And the user continues to shipping method choose to stay on default and click continue
    And the user selects credit card payment
    And the user enters valid card details
    And the user continues to payment info then continue
    And the user confirms the order
    Then the order should be placed successfully
    And the user should see order confirmation with order number


  # --------------------------------------------------------------------------
  # Negative Scenarios
  # --------------------------------------------------------------------------

  @regression @negative @TC014
  Scenario: Checkout fails with invalid card details
  """
    Verify that checkout fails when user enters invalid card details.
    Priority: High | Severity: Critical
    """
    When the user opens the cart page
    And check the check box of the term of the services
    And the user proceeds to checkout
    And the user fills billing address with valid data
    And the user continues to shipping method choose to stay on default and click continue
    And the user selects credit card payment
    And the user enters invalid card details
    And the user continues to payment info expecting payment error
    Then the user should see wrong credit number massage



  @regression @negative
  Scenario: Checkout fails with incomplete billing address
  """
    Verify that checkout fails when billing address is incomplete.
    Priority: Medium | Severity: Major
    """
    When the user opens the cart page
    And check the check box of the term of the services
    And the user proceeds to checkout
    And the user leaves billing address fields empty
    Then the user should see billing validation errors
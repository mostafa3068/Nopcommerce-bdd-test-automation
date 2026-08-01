# ============================================================================
# Feature: Promotions and Coupons
# Description: Tests for coupon/discount functionality on nopCommerce
# Author: Mostafa QC
# Tags: @coupon, @promotions, @regression, @negative
# ============================================================================

@coupon @promotions
Feature: Promotions and Coupons
  As a customer
  I want to apply discount coupons
  So that I can get discounts on my purchases

  # --------------------------------------------------------------------------
  # Background: User must have items in cart to apply coupon
  # --------------------------------------------------------------------------
  Background:
    Given the user has a product in the cart
    And the user is on the cart page

  # --------------------------------------------------------------------------
  # Positive Scenarios
  # --------------------------------------------------------------------------

  @regression @TC016
  Scenario: Apply valid coupon successfully
  """
    Verify that user can apply a valid coupon and discount is applied.
    Priority: High | Severity: Major
    """
    When the user enters coupon code "456"
    And the user clicks apply coupon button
    Then the coupon should be applied successfully
    And the cart total should reflect the discount

  @regression
  Scenario: Remove applied coupon
  """
    Verify that user can remove an applied coupon.
    Priority: Medium | Severity: Minor
    """
    Given the user has applied coupon "456"
    When the user removes the coupon
    Then the coupon should be removed
    And the cart total should return to original price

  # --------------------------------------------------------------------------
  # Negative Scenarios
  # --------------------------------------------------------------------------

  @regression @negative @TC017
  Scenario: Apply expired coupon fails
  """
    Verify that applying an expired coupon shows error message.
    Priority: Low | Severity: Minor
    """
    When the user enters coupon code "326"
    And the user clicks apply coupon button
    Then the user should see coupon error message "cannot"
    # it treats the invalid as it doesn't exist

  @regression @negative
  Scenario: Apply invalid coupon code fails
  """
    Verify that applying non-existent coupon shows error message.
    Priority: Low | Severity: Minor
    """
    When the user enters coupon code "INVALIDCODE123"
    And the user clicks apply coupon button
    Then the user should see coupon error message "cannot"


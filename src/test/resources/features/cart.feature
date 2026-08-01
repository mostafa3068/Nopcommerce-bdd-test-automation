# ============================================================================
# Feature: Shopping Cart
# Description: Tests for cart functionality on nopCommerce
# Author: Mostafa QC
# Tags: @cart, @smoke, @regression, @negative
# ============================================================================

@cart
Feature: Shopping Cart
  As a customer
  I want to manage items in my cart
  So that I can purchase products I want

  # --------------------------------------------------------------------------
  # Positive Scenarios
  # --------------------------------------------------------------------------

  @smoke @regression @TC011
  Scenario: Add product to cart successfully
  """
    Verify that user can add a product to the shopping cart.
    Priority: High | Severity: Major
    """
    Given the user is on the home page
    When the user searches for "laptop"
    And the user clicks on the first product
    And the user clicks add to cart button
    Then the user should see success notification "product has been added"
    And the cart should contain 1 item

  @regression @TC011
  Scenario: Remove product from cart
  """
    Verify that user can remove a product from the cart.
    Priority: High | Severity: Major
    """
    Given the user has a product in the cart
    When the user opens the cart page
    And the user removes the first item from cart
    Then the cart should be empty

  @regression @TC012
  Scenario: Update product quantity in cart
  """
    Verify that user can update product quantity and totals update correctly.
    Priority: Medium | Severity: Major
    """
    Given the user has a product in the cart
    When the user opens the cart page
    And the user updates the quantity to 3
    Then the cart totals should be updated

  @regression
  Scenario: Cart persists after page refresh
  """
    Verify that cart items persist after refreshing the page.
    Priority: Medium | Severity: Minor
    """
    Given the user has a product in the cart
    Given the user is on the cart page
    When the user refreshes the page
    Then the cart should still contain the product

  # --------------------------------------------------------------------------
  # Negative Scenarios
  # --------------------------------------------------------------------------

  @regression @negative @TC024
  Scenario: Cannot checkout with empty cart
  """
    Verify that user cannot proceed to checkout with empty cart.
    Priority: Medium | Severity: Major
    """
    Given the user is on the cart page
    And the cart is empty
    When the user tries to proceed to checkout
    Then the user should see empty cart message
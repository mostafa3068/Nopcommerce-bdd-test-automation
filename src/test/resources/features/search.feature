# ============================================================================
# Feature: Product Search
# Description: Tests for search functionality on nopCommerce
# Author: Mostafa QC
# Tags: @search, @smoke, @regression, @negative
# ============================================================================

@search
Feature: Product Search
  As a customer
  I want to search for products
  So that I can find items I want to purchase

  # --------------------------------------------------------------------------
  # Background: Common setup for all search scenarios
  # --------------------------------------------------------------------------
  Background:
    Given the user is on the home page

  # --------------------------------------------------------------------------
  # Positive Scenarios
  # --------------------------------------------------------------------------

  @smoke @regression @TC009
  Scenario: Search with exact product name returns results
  """
    Verify that searching with exact product name shows matching products.
    Priority: High | Severity: Major
    """
    When the user searches for "laptop"
    Then the search results should be displayed
    And the search results should contain products with "laptop"

  @regression @TC009
  Scenario: Search with partial product name returns results
  """
    Verify that partial search terms return relevant products.
    Priority: Medium | Severity: Major
    """
    When the user searches for "lap"
    Then the search results should be displayed
    And the search results should contain products

  @regression @TC025
  Scenario: Search auto-suggest shows relevant suggestions
  """
    Verify that typing in search box shows suggestions dropdown.
    Priority: Low | Severity: Minor
    """
    When the user types "com" in the search box
    Then the auto-suggest dropdown should appear
    And all suggestions should contain the keyword "com"

  # --------------------------------------------------------------------------
  # Negative Scenarios
  # --------------------------------------------------------------------------

  @regression @negative @TC010
  Scenario: Search with no matching results shows message
  """
    Verify that searching for non-existent product shows appropriate message.
    Priority: Low | Severity: Minor
    """
    When the user searches for "xyznonexistent123"
    Then the user should see no results message "No products were found"

  @regression @negative
  Scenario: Search with empty query
  """
    Verify behavior when user searches with empty search box.
    Priority: Low | Severity: Minor
    """
    When the user searches for ""
    Then the user should see search warning message
# ============================================================================
# Feature: Login Functionality
# Description: Tests for user authentication on nopCommerce
# Author: Mostafa QC
# Tags: @login, @smoke, @regression, @negative
# ============================================================================

@login
Feature: User Login
  As a registered customer
  I want to login to my account
  So that I can access my profile and make purchases

  # --------------------------------------------------------------------------
  # Background: Common setup for all login scenarios
  # --------------------------------------------------------------------------
  Background:
    Given the user is on the login page


  # --------------------------------------------------------------------------
  # Positive Scenarios
  # --------------------------------------------------------------------------

  @smoke @regression @TC001
  Scenario: Successful login with valid credentials
  """
    Verify that a registered user can login successfully
    with correct email and password combination.
    Priority: High | Severity: Critical
    """
    When the user enters valid email "standard_user@test.com"
    And the user enters valid password "Test@1234"
    And the user clicks the login button
    Then the user should be redirected to the home page
    And the user should see the logout link

  @regression @TC001
  Scenario Outline: Successful login with multiple valid users
  """
    Data-driven test to verify login works for different valid users.
    Priority: High | Severity: Critical
    """
    When the user enters valid email "<email>"
    And the user enters valid password "<password>"
    And the user clicks the login button
    Then the user should be redirected to the home page
    And the user should see the logout link

    Examples:
      | email                  | password |
      | standard_user@test.com | Test@123 |
      | premium_user@test.com  | Test@456 |

  # --------------------------------------------------------------------------
  # Negative Scenarios
  # --------------------------------------------------------------------------

  @regression @negative @TC002
  Scenario: Login fails with invalid password
  """
    Verify that login fails when user enters wrong password.
    Priority: Medium | Severity: Major
    """
    When the user enters valid email "standard_user@test.com"
    And the user enters invalid password "WrongPassword"
    And the user clicks the login button
    Then the user should see login error message "Login was unsuccessful"
    And the user should remain on the login page

  @regression @negative @TC002
  Scenario: Login fails with invalid email
  """
    Verify that login fails when user enters unregistered email.
    Priority: Medium | Severity: Major
    """
    When the user enters valid email "nonexistent@test.com"
    And the user enters valid password "Test@123"
    And the user clicks the login button
    Then the user should see login error message "Login was unsuccessful"

  @regression @negative
  Scenario: Login fails with empty credentials
  """
    Verify that login fails when both fields are empty.
    Priority: Low | Severity: Minor
    """
    When the user clicks the login button
    Then the user should see validation error for empty email

  @regression @negative
  Scenario: Login fails with empty password
  """
    Verify that login fails when password field is empty.
    Priority: Low | Severity: Minor
    """
    When the user enters valid email "standard_user@test.com"
    And the user clicks the login button
    Then the user should see login error message "Login was unsuccessful"
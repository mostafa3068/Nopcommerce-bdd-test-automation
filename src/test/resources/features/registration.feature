# ============================================================================
# Feature: User Registration
# Description: Tests for new user account creation on nopCommerce
# Author: Mostafa QC
# Tags: @registration, @smoke, @regression, @negative
# ============================================================================

@registration
Feature: User Registration
  As a new visitor
  I want to create an account
  So that I can shop and track my orders

  # --------------------------------------------------------------------------
  # Background: Common setup for all registration scenarios
  # --------------------------------------------------------------------------
  Background:
    Given the user is on the registration page

  # --------------------------------------------------------------------------
  # Positive Scenarios
  # --------------------------------------------------------------------------

  @smoke @regression @TC003
  Scenario: Successful registration with valid data
  """
    Verify that a new user can register successfully
    with all required valid information.
    Priority: High | Severity: Major
    """
    When the user enters first name "John"
    And the user enters last name "Doe"
    And the user enters a unique email
    And the user enters password "Test@123"
    And the user enters confirm password "Test@123"
    And the user clicks the register button
    Then the user should see registration success message "Your registration completed"

  @regression @TC003
  Scenario: Successful registration with all fields
  """
    Verify registration with all optional fields filled.
    Priority: Medium | Severity: Minor
    """
    When the user selects gender "Male"
    And the user enters first name "John"
    And the user enters last name "Doe"
    And the user enters a unique email
    And the user enters password "Test@123"
    And the user enters confirm password "Test@123"
    And the user clicks the register button
    Then the user should see registration success message "Your registration completed"

  # --------------------------------------------------------------------------
  # Negative Scenarios
  # --------------------------------------------------------------------------

  @regression @negative @TC004
  Scenario: Registration fails with existing email
  """
    Verify that registration fails when using an already registered email.
    Priority: Medium | Severity: Major
    """
    When the user enters first name "John"
    And the user enters last name "Doe"
    And the user enters existing email "standard_user@test.com"
    And the user enters password "Test@123"
    And the user enters confirm password "Test@123"
    And the user clicks the register button
    Then the user should see error message "exists"

  @regression @negative
  Scenario: Registration fails with mismatched passwords
  """
    Verify that registration fails when passwords don't match.
    Priority: Medium | Severity: Major
    """
    When the user enters first name "John"
    And the user enters last name "Doe"
    And the user enters a unique email
    And the user enters password "Test@123"
    And the user enters confirm password "DifferentPassword"
    And the user clicks the register button
    Then the user should see error message "The password and confirmation password do not match."

  @regression @negative
  Scenario: Registration fails with weak password
  """
    Verify that registration fails when password doesn't meet requirements.
    Priority: Low | Severity: Minor
    """
    When the user enters first name "John"
    And the user enters last name "Doe"
    And the user enters a unique email
    And the user enters password "123"
    And the user enters confirm password "123"
    And the user clicks the register button
    Then the user should see password validation error

  @regression @negative
  Scenario: Registration fails with empty required fields
  """
    Verify that registration fails when required fields are empty.
    Priority: Low | Severity: Minor
    """
    When the user clicks the register button
    Then the user should see validation errors for required fields
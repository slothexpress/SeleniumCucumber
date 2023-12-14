Feature: Create User Account
  Scenario: Create Account happy path
    Given I have filled in all mandatory fields
    When I click Submit
    Then I create an account

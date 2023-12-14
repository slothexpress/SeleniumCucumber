Feature: Create User Account

  Scenario: Happy path
    --Given I am using browser "Edge"
    Given I am using browser "Chrome"
    And I have filled in birthdate "05/12/2000"
    And I have filled in first name "Sami"
    And I have filled in last name "Hu"
    And I have filled in email "hej@svej.com"
    And I have filled in emailConfirmation "hej@svej.com"
    And I have filled in password "HejSvej"
    And I have filled in passwordConfirmation "HejSvej"
    And I have checked the checkbox for Terms and Conditions
    And I have checked the checkbox for Age over eighteen
    And I have checked the checkbox for Ethics and Conduct
    When I click Submit
    Then I create an account and get "THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND"

  Scenario: Missing surname
  --Given I am using browser "Edge"
    Given I am using browser "Chrome"
    And I have filled in birthdate "05/12/2000"
    And I have filled in first name "Sami"
    But I have filled in last name ""
    And I have filled in email "hej@svej.com"
    And I have filled in emailConfirmation "hej@svej.com"
    And I have filled in password "HejSvej"
    And I have filled in passwordConfirmation "HejSvej"
    And I have checked the checkbox for Terms and Conditions
    And I have checked the checkbox for Age over eighteen
    And I have checked the checkbox for Ethics and Conduct
    When I click Submit
    Then I should see an error message "Last Name is required"


  Scenario: Passwords do not match
  --Given I am using browser "Edge"
    Given I am using browser "Chrome"
    And I have filled in birthdate "05/12/2000"
    And I have filled in first name "Sami"
    And I have filled in last name "Hu"
    And I have filled in email "hej@svej.com"
    And I have filled in emailConfirmation "hej@svej.com"
    And I have filled in password "HejSvej"
    But I have filled in passwordConfirmation "HejHejHej"
    And I have checked the checkbox for Terms and Conditions
    And I have checked the checkbox for Age over eighteen
    And I have checked the checkbox for Ethics and Conduct
    When I click Submit
    Then I should see an error message "Password did not match"

  Scenario: Terms and conditions are not accepted
  --Given I am using browser "Edge"
    Given I am using browser "Chrome"
    And I have filled in birthdate "05/12/2000"
    And I have filled in first name "Sami"
    And I have filled in last name "Hu"
    And I have filled in email "hej@svej.com"
    And I have filled in emailConfirmation "hej@svej.com"
    And I have filled in password "HejSvej"
    And I have filled in passwordConfirmation "HejSvej"
    But I have unchecked the checkbox for Terms and Conditions
    And I have checked the checkbox for Age over eighteen
    And I have checked the checkbox for Ethics and Conduct
    When I click Submit
    Then I should see an error message "You must confirm that you have read and accepted our Terms and Conditions"
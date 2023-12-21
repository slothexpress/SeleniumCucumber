Feature: Create User Account

  Scenario Outline: Create an account
    Given I am using browser "<browser>"
    And I have filled in birthdate "05/12/2000"
    And I have filled in first name "Sami"
    And I have filled in last name "<lastName>"
    And I have filled in email "my@email.com"
    And I have filled in emailConfirmation "<emailConfirmation>"
    And I have filled in password "Password"
    And I have filled in passwordConfirmation "<passwordConfirmation>"
    And I have checked the checkbox for Terms and Conditions "<T&C>"
    And I have checked the checkbox for Age over eighteen "<Over18>"
    And I have checked the checkbox for Ethics and Conduct "<E&C>"
    When I click Submit
    Then I verify status <status> and get message "<message>"

    Examples:
      | status  | browser | lastName | emailConfirmation | passwordConfirmation | T&C   | Over18 | E&C  |  | message                                                                   |  |
      | success | Edge    | Huynh    | my@email.com      | Password             | true  | true   | true |  | THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND                 |  |
      | error   | Chrome  |          | my@email.com      | Password             | true  | true   | true |  | Last Name is required                                                     |  |
      | error   | Edge    | Huynh    | my@email.com      | anotherPassword      | true  | true   | true |  | Password did not match                                                    |  |
      | error   | Chrome  | Huynh    | my@email.com      | Password             | false | true   | true |  | You must confirm that you have read and accepted our Terms and Conditions |  |

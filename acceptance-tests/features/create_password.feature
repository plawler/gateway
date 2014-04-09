@wip
Feature:
  Create password
Background:
  Given I have registered as an app provider

 Scenario: A user clicks on the link in their verification e-mail
   When I click on the verification link from the e-mail
   Then I should be on the so and so page

 Scenario: A user enters their new password on the validation page
   Given I am on the validation page
    When I enter a valid password
     And I confirm that password
     And I click the submit button
    Then I should see a welcome message

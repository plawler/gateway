Feature:
  As an Application Provider
  I want to validate my account
  So that I can get credentials and use the development environment within inBloom

  Background:
    Given I have a JSON representation of an appProvider
    And I have already registered as an app provider
    And I have JSON representation of an account validation

  Scenario: An application provider validates his account
    When I POST to the verifications resource with a valid token
    Then the response status should be 200 OK
    And the response contains a representation of the verification
    And the response contains a location header for the verification



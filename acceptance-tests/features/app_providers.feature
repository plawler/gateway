Feature:
  As an application provider
  I need to register for an account at inBloom
  and validate my email address
  so that I can start creating apps.

Background:
  Given I have a JSON representation of an appProvider

Scenario: An Appplicaion Provider registers for an account
  When I POST to the applicationProviders resource
  Then the response status should be 201 Created
   And the response contains a representation of the app provider
   And the response contains a location header for the app provider
   And the app provider receives an email with a verification link

Scenario: An App Provider cannot register twice
  Given I have already registered as an app provider
   When I POST to the applicationProviders resource
   Then the response status should be 400 Bad Request

Scenario Outline: An App Provider leaves off required attributes
   When I POST to the applicationProviders resource without <field>
   Then the response status should be 400 Bad Request

  Examples:
   | field     |
   | firstName |
   | lastName  |
   | email     |


Feature:
  As an application provider
  I need to register for an account at inBloom
  and validate my email address
  so that I can start creating apps.


Background:
  Given I have a JSON representation of an appProvider


Scenario: An App Provider registers for an account
  When I POST to the applicationProviders resource
  Then the response status should be 201 Created
  And the response contains an appProvider
 #And the appProvider received an email with a verification link


Scenario: An App Provider cannot register twice
  When I POST to the applicationProviders resource
  Then the response status should be 201 Created
  And I POST to the applicationProviders resource
  Then the response status should be 400 Conflict
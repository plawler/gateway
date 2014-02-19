Feature:
  As an application provider
  In order to integrate with the inBloom Secure Data Service
  I need to register my application

Scenario: Application providers are not allowed to get a list of applications
  When I use the applications resource
  Then a GET on the resource should not be allowed

Scenario: Application providers can create new applications
  Given I use the applications resource
   When I POST a new application
   Then I can GET that application
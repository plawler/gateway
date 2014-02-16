Feature:
  As an application
  In order to allow the end-user to select a realm
  I need to get the list of realms from the gateway

Scenario: An application calls the Gateway API to get the list of realms
  When I GET the realms
  Then the response contains a list of realms
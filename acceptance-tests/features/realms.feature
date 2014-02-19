Feature:
  As an application
  In order to allow the end-user to select a realm
  I need to get the list of realms from the gateway

Scenario: An application calls the Gateway API to get the list of realms
  When I use the realms resource
  Then a GET on the resource should contain a list of realms
   And each realm has an "identifier" and a "name"
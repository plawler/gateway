Feature:
  As a developer
  In order to verify my setup
  I want to execute a simple cucumber feature

Scenario: Verify testing components
  Given I want to use Capybara
    And I want to use Rest-Client
    And I want to use WebMock
   When I run this feature
   Then all is green
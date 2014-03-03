Feature:
  As an an inBloom admin
  I would like to register new operators with the inBloom Gateway
  So that these operators can serve new customers on their instance of the inBloom SDS.

Background:
  Given I have a JSON representation of an operator

Scenario: inBloom admin registers an operator
  When I POST to the operators resource
  Then the response status should be 201 Created
    And the operator has an identifier
  
Scenario: inBloom admin can retrieve an operator that has been registered
  Given I POST to the operators resource
  When I GET that operator resource
  Then the response status should be 200 OK

Scenario: inBloom admin can modify an operator that has been registered
  Given I POST to the operators resource
    And I GET that operator resource
    And I modify that resource
  When I PUT that operator resource
  Then the response status should be 204 No Content
    And the operator should be modified

Scenario: Gateway will reject an invalid operator
  Given I have an invalid JSON representation of an operator
  When I POST to the operators resource
  Then the response status should be 400 Bad Request


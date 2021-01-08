@accountLog
Feature: The activities done create,update,get based on account is trackable in account Log

  Scenario: Fetch All Account Comment List method with valid consumer
    Given As a user i am passing valid ConsumerId to AccountLog
      | consumerId | 5 |
    Then I should get List of collections as a returntype
      | accountLogId | 249 |

  Scenario: Fetch All Account Comment List method with invalid consumer
    Given As a user i am passing invalid ConsumerId for accoutLog
      | consumerId | 0 |
    Then I should get List of collections as a returntype with invalid consumer
      | accountLogId | 249 |

  Scenario: Create the new Account Log details with flag S
    Given As a user i am passing valid Account Log details with flag S
      | accountnumber |      32567 |
      | clientId      |         25 |
      | consumerId    |         54 |
      | statusCode    |          5 |
      | newStatusCode |         12 |
      | unitId        |         24 |
      | newUnit       |         26 |
      | nextActyDate  | 2017-09-27 |
      | newJackDate   | 2016-09-12 |
    And As a user i am passing character flag S
      | flag | S |
    Then Create a new account log

  Scenario: Create the new Account Log details with flag U
    Given As a user i am passing valid Account Log details with flag U
      | accountnumber |      32567 |
      | clientId      |         25 |
      | consumerId    |         54 |
      | statusCode    |          5 |
      | newStatusCode |         12 |
      | unitId        |         24 |
      | newUnit       |         26 |
      | nextActyDate  | 2017-09-27 |
      | newJackDate   | 2016-09-12 |
    And As a user i am passing character flag U
      | flag | U |

  Scenario: Create the new Account Log details with flag J
    Given As a user i am passing valid Account Log details with flag J
      | accountnumber |      32567 |
      | clientId      |         25 |
      | consumerId    |         54 |
      | statusCode    |          5 |
      | newStatusCode |         12 |
      | unitId        |         24 |
      | newUnit       |         26 |
      | nextActyDate  | 2017-09-27 |
      | newJackDate   | 2016-09-12 |
    And As a user i am passing character flag J
      | flag | J |

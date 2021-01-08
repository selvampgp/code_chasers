@LincenseFileService

Feature: To check all the methods of premium calculation service with valid and invalid unit testing data

@CalculatePremium
Scenario: To calculate premium amount with valid data

Given a person details 
When we need to calculate premium amount for a person
Then it should return the calculated premium amount

@CalculatePremiumWithInvalidData
Scenario Outline: To calculate premium amount with invalid data

Given a person details 
When we need to calculate premium amount for a person with <age> and <gender>
Then it should thrown an exception on calculating premium amount

Examples:
         |   age   |     gender   |
         |         |     FEMALE   |
         |     1   |              |
         |         |              |
         
@TestApplyHabitsBasedCalculations
Scenario Outline: Get premium calculation based on habit scenarios

Given an calculated premium amount <premiumAmount>
When we need to calculate premium amount for a person based on habit scenarios
Then it should return the calculated premium amount using Habits 

Examples:
               |   premiumAmount  |
               |        234       |
               |         24       |
               |         33       |
               |         46       |
               
@TestApplyHealthConditionBasedCalculations
Scenario Outline: Get premium calculation based on health scenarios

Given an calculated premium amount <premiumAmount>
When we need to calculate premium amount for a person based on habit scenarios
Then it should return the calculated premium amount using health 

Examples:
               |   premiumAmount  |
               |        234       |
               |        124       |
               |        333       |
               |        946       |
               
@TestApplyGenderBasedCalculations
Scenario Outline: Get premium calculation based on gender scenarios

Given an calculated premium amount <premiumAmount>
When we need to calculate premium amount for a person based on habit scenarios
Then it should return the calculated premium amount using gender 

Examples:
               |   premiumAmount   |
               |        2347       |
               |        8747       |
               |        1338       |
               |        7461       |
               
@TestApplyAgeBasedCalculations
Scenario Outline: Get premium calculation based on age scenarios

Given an calculated premium amount <premiumAmount>
When we need to calculate premium amount for a person based on habit scenarios
Then it should return the calculated premium amount using age 

Examples:
               |   premiumAmount  |
               |        6234      |
               |        5674     |


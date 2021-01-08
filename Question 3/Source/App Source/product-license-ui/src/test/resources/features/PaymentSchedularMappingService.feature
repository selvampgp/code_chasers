@PaymentSchedularMappingService 
Feature: To check all the methods of payment schedular mapping process with valid and invalid unit test data 

@CreateFromList 
Scenario: Create a List of PaymentSchedularMapping details 
	Given As a user i am passing payment schedular mapping details 
	Then I should get the list of payment schedular mapping as a return type 
	
@GetAccountIdsByCon 
Scenario: Fetch list of accountIds by valid consumerId 
	Given As a user i am passing a valid consumerId to get accountId 
		|consumerId|20024|
	Then I should get the list of accountIds 
	
@GetPasDetailsByTranactionId 
Scenario: Fetch payment schedular mapping details by transactionId 
	Given As a user i am passing a valid transactionId 
		|transId|13260|
	Then I should get the payment schedular mapping details 
	
@DeleteSchedularMappingByPas 
Scenario: Delete Payment Scheduler Mapping details by payment scheduleId 
	Given As a user i am passing a valid payment scheduleId to delete scheduler mapping 
		|pasId|154156|
	Then I should check deleted payment scheduler mapper details 
		|pasId|154156|
		
@RemoveSelAccByTrnsId 
Scenario: Remove selected payment schedular mapping account by transactionId 
	Given As a user i am passing accountId and transactionId to remove 
		|transId|13260|
		|accountId|65489|
	Then I should check the deleted accounts 
		|accountId|65489|
		
@GetSchDetialsByTransId 
Scenario: Fetch payment scheduler and mapping details by mps transactionId 
	Given As a user i am passing mps transactionId 
	Then I should get payment scheduler and mapping details 
	
@GetListOfPsmBYTransId 
Scenario: Fetch list of payment schedular mapping details by transaction Id 
	Given As a user i am passing transaction Id to getlist of mapping details 
		|transId|13260|
	Then I should get the list of payment schedular mapping details 
	
@schedularCancelComment 
Scenario: Add schedular cancel comment for given payment schedular Id 
	Given As a user i am passing payment schedular Id and consumer details to add comment 
		|comment|Recurring Payment plan canceled as MPS altered the plan details with ref scheduler Id :# 154156 |
	Then I should get the schedular cancel comment 
		|comment|Recurring Payment plan canceled as MPS altered the plan details with ref scheduler Id :# 154156 |
		
@schedularCancelComment 
Scenario: Add schedular cancel comment for given payment schedular Id 
	Given As a user i am passing payment schedular Id and invalid consumer details to add comment 
	Then I should get the error message to cancel invalid comment 
		|errorMessage|java.lang.NullPointerException|
		
@DeletePrpRecordAndUpdatePas 
Scenario: Delete repayment plan details and update payment scheduler 
	Given As a user i am passing payment schedular Id and consumer details 
		|pasId|154156|
	Then I should delete and update the payment details 
	
@RemoveSelAccByTrnsId 
Scenario: Remove selected payment schedular mapping account by transaction and stage Id 
	Given As a user i am passing transaction and stage Id to remove 
		|transId|13260|
		|deleteStageId|1017|
	Then I should check the removed accounts from schedular 
		|transId|13260|

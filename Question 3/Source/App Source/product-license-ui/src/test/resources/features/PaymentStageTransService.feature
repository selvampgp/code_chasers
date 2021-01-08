@PaymentStagingTransaction
Feature: To check all the methods of payment transaction service with valid and invalid data

Scenario: Allocation of payment staging transaction with valid data

	Given i am passing payment id and accoutid with valid datatype
		|payId    |121  |
		|accountId|15789|		
	Then I should get the payment transaction model
	
Scenario: Allocation of payment staging transaction with invalid datatype for payment id

	Given i am passing payment id with invalid datatype and accoutid of valid datatype
		|payId    |testdata|
		|accountId|15789   |
	Then I should get the payment transaction model as null
	
Scenario: Allocation of payment staging transaction with invalid datatype for account id

	Given i am passing payment id with valid datatype and accoutid with invalid datatype
		|payId    |9654    |
		|accountId|testdata|
	Then I should get the payment transaction model as null

Scenario: Allocation of payment staging transaction with invalid data for payment id

	Given i am passing payment id and accoutid with valid datatype
		|payId    |155663563234|
		|accountId|112356343434|
	Then I should get the payment transaction model
	

Scenario: Allocation of payment transaction with valid data

	Given i am passing payment id and paydate with valid datatype
		|payId  |121       |
		|payDate|2018/02/14|		
	Then I should get the list of map object

Scenario: Allocation of payment transaction with valid data for paydate and invalid data for payid

	Given i am passing invalid payment id and paydate with valid datatype
		|payId  |testdata  |
		|payDate|2018/02/14|	
		|payAmount|23.00|	
	Then I should get the list of map object as null

Scenario: Allocation of payment transaction with invalid data for paydate and valid data for payid

	Given i am passing valid payment id and paydate with invalid datatype
		|payId  |123     |
		|payDate|testdata|		
	Then I should get the list of map object as empty
	
Scenario: payment staging transaction with valid data

	Given i am passing payment id with valid datatype
		|payId  |121  |				
	Then I should get the list of paymentstagingtransaction
	
Scenario: payment staging transaction with invalid data

	Given i am passing payment id with invalid datatype
		|payId  |testdata  |				
	Then I should get the list of paymentstagingtransaction as null		
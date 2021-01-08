@MakePaymentNew 
Feature: To check all the methods of make payment new service with valid and invalid test data 

@consumerAndPaymentSchedulerDetails 
Scenario: Get consumer details and payment schedular details with given consumer id 
	Given As a user i am passing valid consumer id
		|consumerId|10|
	Then I should get consumer details and payment schedular details
		|note|country		|zipCode	|staId	|firstname	|address	|city	|address2|ctyId|state	  	|lastname	|addressId	|
		|test|United States	|836056331	|555	|ERIC DEAN	|PO BOX 3604|MARICAO|LOT 265 |28726|PUERTO RICO	|MECHAM		|823236		|

@allocatedPayment 
Scenario: Get allocated payment details with given payment details 
	Given As a user i am passing payment id payment date consumer id account ids payment type and override status code flag 
		|payId|100|
		|paymentDate|24-05-2018|
		|consumerId|10|
		|paymentType|253|
		|accountIds|1,2|
		|overrideStatusCode|N|
	Then I should get the allocated payment details 
		|account|clientname	|statuscod	|dor		|dateofservice	|acoCommOnPrincipleBalance	|aco_principle_balance	|accumulatedFlag|CLIENT	|allocationPriority	|tbl	|
		| 62388	|ERIC		|	A12		|09/19/2017	|09/19/2017		|			0.4				|			3.0			|		Y		|	1	|			1		|opened	|	
		
@fetchGeneralLedgerDetailsForPayment
Scenario: Fetch general ledger details for payments
	Given As a user i am fetching general ledger details for payment
	
@loadClosedAccounts 
Scenario: Fetch closed accounts with given consumer id 
	Given As a user i am passing a valid consumer id 
		|consumerId|10|
	Then I should get the closed accounts for given id 
		|account|clientname	|statuscod	|dor		|dateofservice	|dlp	 |
		|62349	|ERIC		|	PIF		|09/14/ Y	|09/14/ Y		|09/14/ Y|
		
@adjustmentAccounts 
Scenario: Fetch adjustment accounts with given payment details 
	Given As a user i am passing accound id consumer id payment date and payment staging id 
		|paymentStagingId|100|
		|consumerId|10|
		|account|105465|
	Then I should get the adjustment account details 
		|clientname	|dor		|dateofservice	|dlp		|balance|aco_principle_balance	|aco_accumulated_interest	|CLIENT|
		|ERIC		|06/06/2017	|06/06/2017		|11/29/2017	|1100867|782653.37				|83212.29					|	374|
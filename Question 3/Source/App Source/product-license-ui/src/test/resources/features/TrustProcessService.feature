@TrustProcess 
Feature: To check all the methods of Trust process with valid and invalid unit test data 

@FindAllById 
Scenario: Fetch trust process details by valid trust batch number 
	Given As a user i am passing a valid trust batch number 
		|trpBatchNumber|2342|
	Then I should get the trust collection as a return type 
	
@GetDate 
Scenario: Fetch the list of trust view date by valid clientId 
	Given As a user i am passing a valid clientId 
		|clientId|20786|
	Then I should get the list of trust view dates 
		|trpViewDate|Fri Jun 01 05:30:00 IST 2018|
		
@GetAllByClientId 
Scenario: Fetch the list of trust process details by valid trust details 
	Given As a user i am passing a valid trust details 
		|trustDate|2018-05-31|
		|amount|40.00|
		|overViewStatus|Y|
		|clientId|20682|
		|trustFormulaId|44|
		|batch|2329|
		|dpFlag|N|
		|name|V35EC|
		|trustViewDate|2018-05-31|
	Then I should get the list of trust process details 
	
@FindByIdFromTrust 
Scenario: Fetch client details from trust process by valid trust batch number 
	Given As a user i am passing a valid trust batch number to get trust client details 
		|trpBatchNumber|2342|
		|clientSpecificglcode|1280|
	Then I should get the client details from trust process 
	
@UpdateOverViewStatus 
Scenario: Update trust overview status by valid trust process details 
	Given As a user i am passing a valid trust process details 
		|trpBatchNumber|2342|
	Then I should update the trust overview status 
		|trpBatchNumber|2342|
		|overViewStatus|Y|
		|publishFlag|Y|
		
@GetTransactionDetailsForTrust 
Scenario: Fetch the list of transaction details for trust process by valid clientId 
	Given As a user i am passing a valid clientId to get transaction details 
		|date|2018-05-31|
		|clientId|11|
		|commissionOnotherChargesInclusiveFLag|N|
		|patId|1328047|
		|interestcommPerc|13.0|
		|paymentType|2712|
		|commOnOtherCharges|-1.3|
		|principalBalance|885.0|
		|consumerId|10468119|
		|trustFormula|44|
		|accountId|67079|
		|attorneycommPerc|13.0|
		|principal|-10.0|
		|totalAmount|-10.0|
	Then I should get the list of transaction details for trust process 
	
@GetTransactionDetailsUsingTrustBatchNoProcessedScreen 
Scenario: Fetch the list of transaction details for trust process by valid trust batch number 
	Given As a user i am passing a valid trust batch number to get transaction details 
		|trpBatchNumber|2342|
		|trustTempFlag|true|
		|clientId|11|
		|commissionOnotherChargesInclusiveFLag|N|
		|patId|1328047|
		|interestcommPerc|13.0|
		|paymentType|2712|
		|commOnOtherCharges|-1.3|
		|principalBalance|885.0|
		|consumerId|10468119|
		|trustFormula|44|
		|accountId|67079|
		|attorneycommPerc|13.0|
		|principal|-10.0|
		|totalAmount|-10.0|
	Then I should get the list of transaction details for trust process 
	
@CancelTrustAction 
Scenario: Revert the transaction details which is removed from trust process by valid trust batch number 
	Given As a user i am passing a valid trust batch number to revert the transaction details 
		|trpBatchNumber|2342|
		|patId|226588|
	Then I should the reverted transaction details 
		|trpBatchNumber|2342|
		
@TransactionAddOrRemoveFromTrust 
Scenario: Add or remove transaction details from trust process by trustBatchNumber and paymentTransactionId 
	Given As a user i am passing a trustBatchNumber and paymentTransactionId 
		|trpBatchNumber|2342|
		|patId|226588|
		|adjustType|true|
		|payType|254|
		|commOnPenalty|-1.30|
		|clientId|11|
		|principal|-10.0|
		|trustFormula|44|
		|interest|-10.00|
		|commOnOtherCharges|-1.30|
		|commission|-1.30|
		|OtherCharges|-10.00|
	Then I should add or remove transaction details from trust process 
		|trustAmount|7.800000000000001|
		|invoiceAmount|0.0|
		
@InsertIntoTrustProcessNew 
Scenario: Insert new trust process details by valid client, payment, transaction details 
	Given As a user i am passing a valid client, payment, transaction details 
		|clientId|11|
		|date|2018-05-31|
		|nextValue|2342|
		|type|Gross Remit|
		|TrustCommissionDP|0.0|
		|TrustCommission|-4.9|
		|PaymentIdNonDp|26077116|
		|TrustAmountDP|-40.0|
		|TrustAmount|40.0|
		|PaymentIdDp|26077116|
		|trpBatchNumber|2342|
		|trpTrustAmount|0.0|
		|clientSpecificglcode|1100014451073|
	Then I should insert the trust data and list of string as a return type 
		|message|Trust Processed Successfully, 44.9|
		
@InsertIntoTrustProcessNew 
Scenario: Error while calculating trust amount by invalid trust formula id
    Given Process the trust with invalid trust formula id
    	|clientId|11|
		|date|2018-05-31|
		|nextValue|2342|
		|type|Gross Remit|
		|TrustCommissionDP|0.0|
		|TrustCommission|-4.9|
		|PaymentIdNonDp|26077116|
		|TrustAmountDP|-40.0|
		|TrustAmount|40.0|
    Then I should get the error message while processing the trust
      | remarks       | Trust Formula is not assigned for this Client |
      
@InsertIntoTrustProcessNew 
Scenario: Error while calculating trust amount by invalid trust balance id
    Given Process the trust with invalid trust balance id
    	|clientId|11|
		|date|2018-05-31|
		|nextValue|2342|
		|type|Gross Remit|
		|TrustCommissionDP|0.0|
		|TrustCommission|-4.9|
		|PaymentIdNonDp|26077116|
		|TrustAmountDP|-40.0|
		|TrustAmount|40.0|
		|lookupKey|43|
		|lookupValue|NET Remit|
		|trustBalanceKey|0|
    Then I should get the error message while processing the trust
      | remarks       | Trust Balance is not assigned for this Client |

@InsertIntoTrustProcessNew 
Scenario: Error while posting ledger for negative trust process
	Given Process the negative trust for ledger posting with invalid data
		|clientId|11|
		|date|2018-05-31|
		|nextValue|2342|
		|type|Gross Remit|
		|TrustCommissionDP|0.0|
		|TrustCommission|-4.9|
		|PaymentIdNonDp|26077116|
		|TrustAmountDP|-40.0|
		|TrustAmount|40.0|
		|PaymentIdDp|26077116|
		|trpBatchNumber|2342|
		|trpTrustAmount|0.0|
		|clientSpecificglcode|1100014451073|
	Then I should get the error message while processing the trust
      | remarks       | java.lang.NullPointerException|
      
@GetTransactionDetailsUsingTrustBatchNoProcessedScreen
Scenario: Fetch the list of transaction details for trust process by invalid trust batch number 
	Given As a user i am passing a invalid trust batch number to get transaction details 
		|trpBatchNumber|0|
		|trustTempFlag|true|
		|paidAmount|1.0|
	Then I should get the error message while processing the trust
      | remarks       | Validation to the trust restricted! partially/fully invoice already paid|
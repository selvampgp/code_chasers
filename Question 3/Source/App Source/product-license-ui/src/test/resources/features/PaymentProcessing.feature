@PaymentProcessing 
Feature: To check all the methods of payment processing service with valid and invalid test data 

@checkForPaymentAmt 
Scenario: Check the payment amount while making payments
	Given As a user i am passing payment details account details and interest rate 
		|interestRate|10.0|
		|scheduledPayAmount|98275.2|
	Then I should get the due details 
		|dueWithInterest|19899.095449496068|
		|zeroInterest|true|
		
@createAndPostPayment 
Scenario: Create and post all type of payments
	Given As a user i am passing payment details auto-approve override flag and allow overpay flag
		|seqId|11|
		|batchNo|10|
		|updatedData|1|
		|autoApproveOverride|true|
		|allowOverPay|false|
	Then I should get the allocation result 
		|remarks|CH Payment Processed Successfully|
		|payType|CH|
		
#@createAndPostOrPlanConnectingMPS 
#Scenario: Create and post payment for MPS 
#	Given As a user i am passing payment details for MPS 
#	|scheduledPayAmount|98275.2|
#	Then I should get the allocation result for MPS 
#		|remarks|Connection failure with MPS|
		
@validatingAccountBeforePayment 
Scenario: Validate account details while making payment 
	Given As a user i am passing transaction details account details consumer id payment type and date
	Then I should get validated status 
		|status|Y|
		
@repayPlanGenerator 
Scenario: Generate payment plan for scheduled payments
	Given As a user i am passing payment schedular details total due payment amount type start date second date and run until 
	Then I should get hashmap of plan details 
		|iteration|1|
		|lastPay|6/8/2018|
		
@updatePaymentScheduleCreationTypeAsNew 
Scenario: Create payment scheduler with repayment plan creation type as new 
	Given As a user i am passing payment details and account details of a consumer 
		|transactionId| 4047|
		|dueWithInterest | 196550.4|
		|zeroInterest  |N|
		|deleteStageId|1017|
		|rePayPlan|new|
	Then I should see the payment plan creation 
		|planId|154156|
		|remark|Repay plan Created|
		
@updatePaymentScheduleCreationTypeAsAppend 
Scenario: Update payment schedule with single transaction id and repayment plan creation type as append 
	Given As a user i am passing payment details and account details of a consumer 
		|transactionId| 4047|
		|dueWithInterest | 196550.4|
		|zeroInterest  |N|
		|deleteStageId|1017|
		|rePayPlan|append|
	Then I should see the payment plan creation 
		|planId|154156|
		|remark|Repay plan Updated|
		
@updatePaymentScheduleCreationTypeAsRemove 
Scenario: Update payment schedule with single transaction id and repayment plan creation type as Remove 
	Given As a user i am passing payment details and account details of a consumer 
		|transactionId| 4047|
		|dueWithInterest | 196550.4|
		|zeroInterest  |N|
		|deleteStageId|1017|
		|rePayPlan|remove|
		|tranId|15678|
	Then I should see the payment plan creation 
		|planId|154156|
		|remark|Repay plan updated|
		
@allocatePaymentByRuleSetupAndReturn 
Scenario: Allocate Payments based on client rulesetup 
	Given As a user i am passing payment transaction details 
	Then I should get the allocation details 
		|statusBoolean|true|
		|status|Y|
		
#@reversePaymentOrAdjUsingPaymentId 
#Scenario: Reverse payment or adjustment using payment id 
#	Given As a user i am passing payment and transaction details and nsf stsatus 
#	Then I should get the reversal status for given payment 
	
@reversePaymentbyID 
Scenario: Reverse payment using payment id 
	Given As a user i am passing payment details and nsf status 
	Then I should get the reversal status 
	
@createReversalEntries 
Scenario: Create reversal payment entries 
	Given As a user i am passing payment details and account details and nsf status 
	Then I should get the reversal data for the given payment 
		|payAmount|-200.0|
		
@createReversePaymentEntries 
Scenario: Create reversal payment entries 
	Given As a user i am passing payment details and user id 
	Then I should get the reversal data for the payment 
		|payAmount|-200.0|
		
@setPaymentDetails 
Scenario: Set payment details with given data 
	Given As a user i am passing payment and account details 
	Then I should get payment transaction details 
		|accountId|65482|
		|consumerId|20022|
		
@setPaymentDetailsWithAcoDetails
Scenario: Set payment details with given data 
	Given As a user i am passing payment details and account details 
	Then I should get payment transaction details 
		|accountId|65482|
		|consumerId|20022|
		
#@loadselectiveaccounddetails @private 
#Scenario: Load account details with payment id 
#	Given As a user i am passing payment id 
#		|paymentId|31|
#	Then I should get the account details for given payment id 
	
	
	

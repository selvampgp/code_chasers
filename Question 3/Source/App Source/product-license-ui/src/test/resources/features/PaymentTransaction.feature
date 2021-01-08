@PaymentTransaction 
Feature: To check complete process of all payment transaction processed in ARTrail system 
	"This feature will cover all payment transactions performed via ARTrail system."
  "Each scenarios will handle both positive and negative test cases and checks all cases written in service class."

@fetchAllPaymentsWithFlagConsumer 
Scenario: Fetch all payment transactions to collector screen for the specified consumer and accounts 
	When User pass valid consumer and comaker id with flag CONSUMER 
		| consumerId        | 10031504 |
		| coMakerAccountIds |    64715 |
		| flag              | CONSUMER |
	Then Should return all payment transaction details with corresponding consumer and comaker 
	
@fetchAllPaymentsWithoutFlagConsumer 
Scenario: Fetch all payment transactions to collector screen for the specified accounts 
	When User pass valid consumer and comaker id 
		| coMakerAccountIds |   64715 |
		| flag              | ACCOUNT |
	Then Should return all payment transaction details with corresponding comaker 
	
@DoNotFetchAllPaymentsWithInvalidData 
Scenario: Fetch all payment transactions to collector screen for the specified accounts 
	When User pass valid consumer and comaker id 
		| coMakerAccountIds |   64715 |
		| flag              | ACCOUNT |
	Then Should return all payment transaction details with corresponding comaker 
	
@updatePaymentTransactionTrustConsiderFlag 
Scenario: Reverse payment transaction by updating trust consider flag with valid payment id 
	When User pass valid payment id need to reverse 
		| paymentID         | 26077053 |
		| trustConsiderFlag | N        |
	Then Trust consider flag will get update for the specified payments 
		| trustConsiderFlag | N |
		
@createPatPaymentTransaction 
Scenario: Create new payment transaction detail 
	"Data will get from JSON file"

	When User post a payment from ARTrail system 
	Then Posted payments will inserts in payment transaction 
	
@getallForPaymentReports 
Scenario: Get all type of payments details for payment report 
	When User choose two different dates 
		| AuthorizeDate | 2018-03-10,2018-03-12 |
	Then View payment transaction details between selected dates 
	
@getById 
Scenario: Get transaction id for reversal process 
	When User given a single transaction id 
		| paymentUniqueId | 226586 |
	Then Should return payment transaction for corresponding id 
	
@loadTransactionDetail 
Scenario: Load payment transactional details for reverse payment including adjustments 
	When User give list of payment id 
		| paymentIds | 26077053,20023 |
	Then Should return list of transaction details 
	
@updateTrustBatchNoChanges 
Scenario: Inserts trust batch details to corresponding payment transactions with valid data 
	When Trust team process trust for valid payments 
		| clientId         |      20673 |
		| authDate         | 2018-04-12 |
		| trustBatchNumber |     145858 |
		| paymentType      |         35 |
		| trustGroup       |       2721 |
		| transType        |       2723 |
		| remitType        |       2721 |
	Then Corressponding payment transaction trust details will get update 
	
@updateTrustBatchNoChanges 
Scenario: Inserts trust batch details to corresponding payment transactions with invalid data 
	When Trust team process trust for invalid payments 
		| clientId         |      48578 |
		| authDate         | 2018-04-17 |
		| trustBatchNumber |     145858 |
		| paymentType      |         35 |
		| trustGroup       |        254 |
		| transType        |       2723 |
		| remitType        |       2721 |
	Then Corressponding payment transaction trust details will not get update 
	
@updateTrustBatchNoForReverse 
Scenario: Update trust batch number as null when trust team reverse processed trust 
	When Trust team reversed selected payments 
		| trustBatchNumber | 142359 |
	Then transaction details will update trust batch number as null for the specified transaction 
	
@getpaymentTransactionbypaymetId 
Scenario: Load selected payment transaction details for trust process and get comment from MPS 
	When User search valid payment transactions 
		| paymentID | 26077053 |
	Then Should return transaction details for the equivalent payments 
	
@loadPaymentApprovalPendingWithvalidInput 
Scenario: Load payment transactions details for bulk approval of payments and update payment date and total balance to client request tab 
	"In this process total balance and payment date will get update for selected payments with valid input types"

	When User search transaction details between two dates and valid payment type 
		| paymentDate | 2018-02-11,2018-04-12 |
		| paymentType | POS-CASH              |
	Then method should return payment details for approval 
	
@loadPaymentApprovalPendingWithInvalidInput 
Scenario: Load empty payment transaction to client request tab 
	"In this process total balance and payment date will not get update for selected payments due to invalid inputs"

	When User search transaction details between two dates and invalid payment type 
		| paymentDate | 2018-02-11,2018-04-12 |
		| paymentType | POS-MO                |
	Then Method should return empty payment details 
	
@getUnApprovedPayments 
Scenario: List un approved payment details for selected payment id 
	"Fetch all payment details having payment approvalflag 'N'"

	When Search list of un approved payment ids 
		| paymentIds   | 20022,20023 |
		| approvalFlag | N           |
	Then User will get list of un approved payments 
		| UnApprovedPaymentIds | 20023 |
		
@getPaymentonly 
Scenario: Fetch payment details only from transaction for equivalent consumer 
	When Collector selects consumer to view only Payment details 
		|	  flag	   | CONSUMER |
		|	consumerId | 1354748  |
		|transactionId |  1327871 |
		|	payments   | 26077171 |
		|commOnPenality|	300.0 |
		|	paymentType| POS-CASH |
		|paymentamount |	500.0 |
		| PrincipalBal |	500.0 |
		|	ClientId   |	 378  |
		| approvalFlag |	  Y	  |
		|  paymentDate |2018-04-25|
		|	penality   |  500.0   |
	Then Collector should view payments details only 
		|	consumerId | 1354748  |
		|transactionId |  1327871 |
		|	payments   | 26077171 |
		|commOnPenality|	300.0 |
		|	paymentType| POS-CASH |
		|paymentamount |	500.0 |
		| PrincipalBal |	500.0 |
		|	ClientId   |	 378  |
		| approvalFlag |	  Y	  |
		|  paymentDate |2018-04-25|
		|	penality   |  500.0   |
		
@getPaymentReversals 
Scenario: Fetch reversal payment details for the loaded consumer from collector screen 
	When Collector selects consumer to view reversal transactions 
		|	  flag	   | CONSUMER |
		|	consumerId | 1354748  |
		|transactionId | 1327679  |
		|	payments   | 26077050 |
		|commOnPenality|   -600.0 |
		|	paymentType|  POS-MO  |
		|paymentamount |  -1000.0 |
		| PrincipalBal | -48925.0 |
		|	ClientId   |	 378  |
		| approvalFlag |	  Y	  |
		|  paymentDate |2018-04-16|
		|	penality   | -1000.0  |
	Then Collector should view reversal payment entries 
		|	consumerId | 1354748  |
		|transactionId |  1327679 |
		|	payments   | 26077050 |
		|commOnPenality|   -600.0 |
		|	paymentType| POS-MO   |
		|paymentamount |  -1000.0 |
		| PrincipalBal | -48925.0 |
		|	ClientId   |	 378  |
		| approvalFlag |	  Y	  |
		|  paymentDate |2018-04-16|
		|	penality   | -1000.0  |
		
@getPaymentAdjusments 
Scenario: Fetch adjustment payment details for the loaded consumer from collector screen 
	When Collector selects consumer to view adjustment transactions 
		|	  flag	   | CONSUMER |
		|	consumerId | 1354748  |
		|transactionId | 1327679  |
		|	payments   | 26077050 |
		|commOnPenality|   -600.0 |
		|	paymentType|  POS-MO  |
		|paymentamount |  -1000.0 |
		| PrincipalBal | -48925.0 |
		|	ClientId   |	 378  |
		| approvalFlag |	  Y	  |
		|  paymentDate |2018-04-16|
		|	penality   | -1000.0  |
	Then Collector should view adjustment payment entries 
		|	consumerId | 1354748  |
		|transactionId | 1327679  |
		|	payments   | 26077050 |
		|commOnPenality|   -600.0 |
		|	paymentType|  POS-MO  |
		|paymentamount |  -1000.0 |
		| PrincipalBal | -48925.0 |
		|	ClientId   |	 378  |
		| approvalFlag |	  Y	  |
		|  paymentDate |2018-04-16|
		|	penality   | -1000.0  |
		
@getTransVerfiedAccountlistbypaymentid 
Scenario: Get transaction approved payments for reverse credit report during payment reversal process 
	When User give particular payment id 
		|paymentId  |	25631855 |
		|accountList|62825, 62825|
	Then Approved transaction need to return 
	
@getTransVerfiedAccountlistbypaymentidWithInvalidPaymentId 
Scenario: Get empty transaction payments during payment reversal process 
	When User give wrong payment id 
		|paymentId | 014356 |
	Then Empty object will return 
	
@insertDataFromModalH2WithValidData 
Scenario: Insert payment details to H2 database during reallocation of payments 
	When Transaction details need to insert with valid Object 
		|paymentId|20022|
	Then H2 will insert given data 
		|paymentId|20022|
		
@insertDataFromModalH2WithInvalidObject 
Scenario: Insert payment details to H2 database during reallocation of payments 
	When Transaction details need to insert with invalid Object 
		|paymentId|0|
	Then H2 will throw error 
		|paymentId|0|
		
@deleteAllocatedData 
Scenario: Delete previous allocated data in H2 database for equivalent payments 
	When User save payment for specific account 
		|paymentId|20022|
	Then previous allocated payments will deleted from H2 database 
		|paymentId|20022|
		
@getAllForPaymentDetails 
Scenario: Fetch all payments processed in between two dates and payment type 
	"This method is used to show payment details in subgrid of payment report"

	When User load subgrid of payment report 
	Then payment details will show in subgrid of payment report 
	
@getAllForPaymentDetailsforSplitup 
Scenario: Fetch payment details for payment report generation 
	"This method provides sum of payment details between payment approved date to generate payment report"

	When User generate payment report between two dates 
	Then payment details will show in subgrid of payment report 
	
@updateBalAftReversal 
Scenario: After reversal process for specified accounts, transaction details will get update with non sufficient charges(NSF) status 
	"Non sufficient charge status will be active only when reverse process for payments."

	When User reverse a particular payment for specified account 
		|	accountId	|  65482 |
		|paymentUniqueId|26079848|
		|	balance		|  92357 |
	Then Transaction details update for the payment 
		|	balance		|  92357 | 
		
@updateBalAftReversalWithIncorrectPaymentDetails 
Scenario: After reversal process for specified accounts, transaction details will not get update with invalid data 
	When User reverse a particular payment for specified account with invalid data 
		|	accountId	|  0     |
		|paymentUniqueId|26079848|
		|	balance		|   0    | 
	Then throw error for the specified payment 
		|	balance		|   0    | 
		
@getpaymentTransactionAndAccountInfobypaymetId 
Scenario: Fetch payment transaction details except rejected 
	When User reverse payment transactions for specific payment id 
		|			  patId				 | 226588 |
		|		    paymentId 		     |26077116|
		|			interest			 |	0.15  |
		|			courtCost			 |	0.14  |
		|			accountnumber		 |	67065 |
		|		expectedCommission		 |	0.14  |
		|		commissionOnCourtCost	 |  0.2   |
		|expectedCommissionOnOtherCharges|	0.41  |
		|  expectedCommissionOnInterest  |	0.79  |
		|	commissionOnAttorneyFees	 |	0.02  |
	Then Transaction details will be inserts into system log 
		|			  patId				 | 226588 | 
		|			interest			 |	0.15  |
		|			courtCost			 |	0.14  |
		|			accountnumber		 |	67065 |
		|		expectedCommission		 |	0.14  |
		|		commissionOnCourtCost	 |  0.2   |
		|expectedCommissionOnOtherCharges|	0.41  |
		|  expectedCommissionOnInterest  |	0.79  |
		|	commissionOnAttorneyFees	 |	0.02  |
		
@updateSpecialTrustAllocation 
Scenario: Update payment transaction trust allocation status during special trust allocation 
	When User performs special trust allocation for equivalent payments 
		|clientId|20673,20674|
		|payDate | 2018-04-25|
		|trustAllocation|success|
	Then transaction trust allocation status and transaction verified status will get update 
		|trustAllocation|success|
		
		#@getPaymentsDetailsByConsumerId 
		#Scenario: Fetch payment details by consumer id 
		#	"This method executed via procedure and used for webservices"
		#
		#	When User search transaction with consumer id 
		#	Then Procedure will return transaction details 
		#	
		#@getPaymentsDetailsByConsumerIdandPaymentId
		#Scenario: Fetch payment details by consumer id and payment id 
		#	"This method executed via procedure and used for webservices"
		#
		#	When User search transaction with payment id and consumer id 
		#	Then Procedure will return transaction details 
		#	
		#@getPaymentsDetailsByConsumerIdandPaymentIdRecipt 
		#Scenario: Fetch payment receipt details by consumer id and payment id 
		#	"This method executed via procedure and used for webservices"
		#
		#	When User search transaction with payment id and consumer id 
		#	Then Procedure will return payment receipt details 
		
@getTransactionDetailsForNegativeTrust 
Scenario: Get payment transactional details for negative trust with entry status "R" 
	When User search transaction with payment id and client id 
		|paymentId|26077053|
		|clientId |   378  |
	Then Return negative transaction details for ledger inserts 
		|entryStatus|R| 
		
@updateTrustBatchNumber 
Scenario: Update payment trust batch number during cancellation of trust or new trust process 
	When User cancel or add transaction from trut by passing valid transactionId 
		|trustBatchNumber|142111|
		|		patId	 |226585|
	Then Trust batch number will get update for specified transaction 
		|trustBatchNumber|142111|
		
@getLatestPaymentDateByAccount 
Scenario: Get approved payment last date with except reversed payments 
	When User reverse payment or adjustment using payment id 
		|accountId|		67065			|
		|	date  |2017-12-19 00:00:00.0|
	Then payment transaction will return last payment date of spcified payment id 
		|	date  |2017-12-19 00:00:00.0|
		
@bulkupdateTransaction 
Scenario: Update multiple transaction details by posting multiple payments 
	"Posting multiple payments from payment staging or other steps"

	When User post pamyent transaction details for update 
		|reason|Direct payment auto approval|
		| flag |	 ALLOCATIONAPPROVE	     |
		|batchNumber|4054|
		|userId|1238|
		|userName|premseth|
		|paymentSourceString|DAP|
		|paidForCon|-544.28|
		|paymentDate|2018-02-26|
		|paymentTypeRef|254|
		|paymentType|OT|
		|paymentId|26077116|
		|accountCount|0|
		|overPay|0.00|
		|trustBalIdRef|5|
	Then System should return pojo class contains transction approval details 
		|paymentId|26077116|
		
@bulkupdateTransaction 
Scenario: Throw processing request error for paid in full status 
	When There is no global settings found for paid in full status while updating transaction details 
		|reason|Direct payment auto approval|
		| flag |	 ALLOCATIONAPPROVE	     |
		|sysGlbSettingStr|PaidInFullStatus|
	Then System should throw error message 
		|errMessage|There Is A Technical Error In Processing Request- PaidInFullStatus not mapped in sysgen|
		
@bulkupdateTransaction 
Scenario: Throw processing request error for settle in full status 
	When There is no global settings found for settle in full status while updating transaction details 
		|reason|Direct payment auto approval|
		| flag |	 ALLOCATIONAPPROVE	     |
		|sysGlbSettingStr|SettleInFullStatus|
	Then System should throw error message 
		|errMessage|There Is A Technical Error In Processing Request- SettleInFullStatus not mapped in sysgen|
		
@bulkupdateTransaction 
Scenario: Return empty pojo class for invalid input 
	When User post pamyent transaction details for update with empty payments 
		|reason|Direct payment auto approval|
		| flag |	 ALLOCATIONAPPROVE	     |
	Then should get empty pojo class 
	
@bulkupdateTransaction
Scenario: Throw processing request error with invalid payment details 
	When Updating payment schedular entries with invalid payment details 
		| reason|Direct payment auto approval|
		| flag  |	 ALLOCATIONAPPROVE	     |
		|paymentDate|2018-02-26|
		|paymentId|26077116|
		|accountCount|0|
		|userName|premseth|
		|overPay|0.00|
		|batchNumber|4054|
		|userId|1238|		
	Then System should throw error message 
		|errMessage|Exception while updating payment schedular entries|
		
@bulkupdateTransaction 
Scenario: Throw processing request error with invalid credit report details 
	When Updating payment transaction with invalid credit report details
		|reason|Direct payment auto approval|
		| flag |	 ALLOCATIONAPPROVE	     |
		|batchNumber|4054|
		|userId|1238|
		|userName|premseth|
		|paymentSourceString|DAP|
		|paidForCon|-544.28|
		|paymentDate|2018-02-26|
		|paymentTypeRef|254|
		|paymentType|OT|
		|paymentId|26077116|
		|accountCount|0|
		|overPay|0.00|
		|trustBalIdRef|5|
	Then System should throw error message 
		|errMessage|Exception credit reporting trigger|

@getAllForTrust 
Scenario: Fetch payment details to run trust process 
	When User Fetch payment details to run trust process 
		|trustDate|2018-03-11 00:00:00|
	Then I should get the payment details 
	
@findActualPaymentByID 
Scenario: Fetch payment transaction details by valid payment id 
	When As a user i am passing a valid payment id 
		|paymentId|20022|
	Then I should get the list of collection as a return type 
		|paymentId|20022|
		
@findActualPaymentByInvalidID 
Scenario: Fetch payment transaction details by invalid payment id 
	When As a user i am passing a invalid payment id 
		|paymentId|0|
	Then I should get the list of collection as a return type 
		|paymentId|0|
		
@validateAccountDetailsWithAllocatedEntries 
Scenario: Validate account details by list of transaction details 
	When User should validate account details by list of transaction details 
		|paymentDate|2018-02-26|
		|accountCount|0|
		|overPay|0.00|
		|reason|Direct payment auto approval|
		|paidFullStatus|PIF|
		|settleFullStatus|SIF|
	Then I should get the collection as return type 
	
@createTransactionEntriesForAdjustment 
Scenario: Create the transaction entries for adjustment to get account details 
	When User Should create the transaction entries for adjustment 
		|previousDue|33088.17|
		|schemeCode |3|
		|principleBalance|0.00|
	Then I should get the account collection as return type
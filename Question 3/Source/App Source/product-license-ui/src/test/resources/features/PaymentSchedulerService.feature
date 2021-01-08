@PaymentSchedulerService 
Feature: To check all the methods of payment schedular process with valid and invalid unit test data 

@CreatePaymentSchedular 
Scenario: Create new payment schedular details 
	Given As a user i am passing payment schedular details 
	Then I should get the collection as a return type 
	
@UpdateSchedular 
Scenario: Update payment schedule with invalid transactionId and valid consumerId and delete stageId 
	Given As a user i am passing invalid transactionId and valid consumerId and delete stageId to update 
		|transId|13260|
		|deleteStageId|1017|
	Then I should get error message 
		|errorMessage|java.lang.NullPointerException|
		
@UpdateSchedular 
Scenario: Update payment schedule with invalid consumerId and valid transactionId and delete stageId 
	Given As a user i am passing invalid consumerId and valid transactionId and delete stageId to update 
		|transId|13260|
		|deleteStageId|1017|
	Then I should get error message 
		|errorMessage|java.lang.NullPointerException|
		
@UpdateSchedular 
Scenario: Update payment schedule with valid transactionId, consumerId and delete stageId 
	Given As a user i am passing valid transactionId, consumerId and delete stageId to update 
		|transId|13260|
		|deleteStageId|1017|
	Then I should get payment scheduler id 
		|pasId|154156|
		
@updateSchedularWithConsumerByTransId 
Scenario: Update payment schedule with valid transactionId, comment and delete stageId 
	Given As a user i am passing valid transactionId, comment and delete stageId to update 
		|comment|Recurring Payment plan deleted with ref Transaction Id:# 13260 , schedule Id :# 154156|
		|deleteStageId|1017|
		|accountId|65489|
		|transId|13260|
	Then I should get payment scheduler details 
	
@updateSchedularWithConsumerByTransId 
Scenario: Update payment schedule with transactionId, delete stageId and invalid comment details 
	Given As a user i am passing valid transactionId, delete stageId and invalid comment details 
		|comment|Recurring Payment plan deleted with ref Transaction Id:# 13260 , schedule Id :# 154156|
		|deleteStageId|1017|
		|accountId|65489|
		|transId|13260|
	Then I should get error message 
		|errorMessage|java.lang.NullPointerException|
		
@cancelRemainingPlan 
Scenario: Cancel the pending payment plan details and insert into comments for log purposes 
	Given As a user i am passing a valid payment schedule and consumer details 
		|pasId|154156|
		|accountId|65489|
	Then I should get payment scheduler details 
	
@cancelRemainingPlan 
Scenario: Cancel the pending payment plan details and insert into comments for log purposes 
	Given As a user i am passing a invalid payment schedule and consumer details 
		|pasId|154156|
		|accountId|65489|
	Then I should get error message 
		|errorMessage|java.lang.NullPointerException|
		
@fetchPaymntScheduleByConId 
Scenario: Fetch payment schedule details by consumerId 
	Given As a user i am passing a valid consumer Id 
		|consumerId|20024|
		|accountId|65489|
	Then I should get payment scheduler details 
	
@getSchedularDetailsById 
Scenario: Fetch payment scheduler details by payment scheduleId 
	Given As a user i am passing a valid payment scheduleId 
		|pasId|154156|
	Then I should get the collection as a return type 
	
@updatePlanByConsumer 
Scenario: Update repayment plan by account and consumer details 
	Given As a user i am passing a valid account and consumer details to update 
		|paymentSourceString|DAP|
		|paidForCon|-544.28|
		|paymentDate|2018-02-26|
		|accountId|65489|
		|paymentType|RP|
		|transId|13260|
		|pasId|154156|
	Then I should check the updated details 
	
@reverseRepayPlan 
Scenario: Revert payment status from fully paid to pending pay 
	Given As a user i am passing a valid account and consumer details to revert 
		|paymentSourceString|DAP|
		|paidForCon|-544.28|
		|paymentDate|2018-02-26|
		|accountId|65489|
		|paymentType|RP|
		|transId|13260|
	Then I should check the updated details 
	
@loadloadCouponBook 
Scenario: Load coupon book details by valid consumer Id 
	Given As a user i am passing a valid consumer Id to load coupon details 
		|endDate|06/10/2018|
		|itr|11|
	Then I should get the list of coupon details 
	
@deleteSchedularEntries 
Scenario: Delete Payment Scheduler details by payment scheduleId 
	Given As a user i am passing a valid payment scheduleId to delete 
		|pasId|154156|
	Then I should check deleted payment scheduler details 
	
@createPaymentSchedule 
Scenario: Create Payment Scheduler details 
	Given As a user i am passing payment scheduler details to create 
		|updatePlan|1|
	Then I should get the collection as a return type 
	
@getSchedularIdByPpdId 
Scenario: Fetch payment scheduler details by transactionId 
	Given As a user i am passing a valid transactionId to get scheduler details 
		|transId|13260|
		|pasId|154156|
	Then I should get the collection as a return type 
	
@paymentPlanDeletionData 
Scenario: Change payment plan flag details using workflow by valid account and delete stage Id 
	Given As a user i am passing a valid account and delete stage Id 
		|deleteStageId|1017|
		|accountId|65489|
	Then Check payment plan changes
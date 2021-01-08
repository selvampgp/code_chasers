@RepaymentPlanService
Feature: To check all the methods of make repayment plan service with valid and invalid test data

@repaymentCreate
Scenario: Create a list of repayment plans
	Given As a user i verify creation of repayment plan by passing valid data
	
@fetchBySchedularId
Scenario: Fetch data by schedular id
	Given As a user i am passing valid schedular id
		|pasId|10|
	Then i should get the details of repayment plan for the given id
		|paymentAmount	|paidamount	|consumerId	|payeeName		|scheduledAmt	|
		| 28351.15		|	500.0	|	20024	|Aiden,Anderson	|	285511.53	|
		
@getrepaymentplanbydate
Scenario: Generate repayment plan by date
	Given As a user i am passing valid date and consumer id
		|consumerId	|20022|
	Then I should get repayment plan for given id and date
		|startDate	|date		|pay	|iteration	|paymentType|frequency		| 
		|2017-08-25	|2017-09-05	|291.01	|	9		|	502		|paymentStatus	|
		
	
@getNextPaymentDate
Scenario: Get next payment date
	Given As a user i am passing valid schedular id and date
		|pasId	|	755		|	
	Then I should get the next payment date for given schedular id
		|id		|PaymentSchedulerId	|paymentDate|paymentAmount	|paymentStatus	|advanceAmount	|createTime|paidamount	|
		|755175	|		755			|2018-06-05	|	100.0		|		461		|	0.00		|2017-07-05|	0.00	|

@getRegECount
Scenario: Get number of schedulars between two dates
	Given As a user i am passing valid payment schedular id
		|input|1|
	Then I should get the count of payment schedulars
		|aDate|2018-06-01|
		|COUNT|		19	|
		
@getConsumerNameByDate
Scenario: Get consumer name by date
	Given As a user i am passing valid payment date
		|paymentDate|2018-05-24|
	Then I should get the consumer name
		|firstname	|lastname	|
		|Aiden		|Anderson	|
		|Alexander	|Smith		|

@deleteRepaymentEntriesByPas
Scenario: Remove payment schedular entries with given schedular id
	Given As a user i am verifying deletion of repayment plan with valid schedular id
		|pasId|200|
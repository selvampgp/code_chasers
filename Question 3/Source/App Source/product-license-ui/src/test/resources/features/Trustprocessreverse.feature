@TrustProcessReverse
Feature: To check all the methods of trust process reverse service with valid and invalid test data

@insertFromTrustForDp
Scenario: Insert trust reverse details from trust process with payment type DP and post to voucher
	Given As a user i am passing valid trust batch id for direct payment
		|batchId|10|
		|revId|11|
		|clientId|419|
	Then I should get the trust batch id for trust reverse for direct payment
		|batchId|10|
		|principal| 0|
		|interst| 0|
		|accumulatedInterest| 0|
		|otherCharges| 0|
		|penality| 0|
		|attorneyFee| 0|
		|courtCost| 0|
		|commision| -10|
		|commisssionOnInterest| 0|
		|commissionAccumulatedInterest| 0|
		|commOnotherCharges| 0|
		|commOnPenality| 0|
		|commOnAttorney| 0|
		|commOnCourtCost|0|
	
@insertFromTrustForApCommExclusive
Scenario: Insert trust reverse details from trust process with payment type other than DP and post to voucher with commission inclusive flag N
	Given As a user i am passing valid trust batch id for agency payment with commission inclusive flag N
		|batchId|10|
		|revId|11|
		|clientId|419|
	Then I should get the trust batch id for trust reverse for agency payment commission exclusive
		|batchId|10|
		|principal| -100|
		|interst| 0|
		|accumulatedInterest| 0|
		|otherCharges| 0|
		|penality| 0|
		|attorneyFee| 0|
		|courtCost| 0|
		|commision| 0|
		|commisssionOnInterest| 0|
		|commissionAccumulatedInterest| 0|
		|commOnotherCharges| 0|
		|commOnPenality| 0|
		|commOnAttorney| 0|
		|commOnCourtCost|0|
		
@insertFromTrustForApCommInclusive
Scenario: Insert trust reverse details from trust process with payment type other than DP and post to voucher with commission inclusive flag Y
	Given As a user i am passing valid trust batch id for agency payment with commission inclusive flag Y
		|batchId|10|
		|revId|11|
		|clientId|419|
	Then I should get the trust batch id for trust reverse for agency payment with commission inclusive
		|batchId|10|
		|principal| -100|
		|interst| 0|
		|accumulatedInterest| 0|
		|otherCharges| 0|
		|penality| 0|
		|attorneyFee| 0|
		|courtCost| 0|
		|commision| 0|
		|commisssionOnInterest| 0|
		|commissionAccumulatedInterest| 0|
		|commOnotherCharges| 0|
		|commOnPenality| 0|
		|commOnAttorney| 0|
		|commOnCourtCost|0|
	
@insertFromTrust
Scenario: Insert trust reverse details from trust process with payment type null and post to voucher
	Given As a user i am passing valid trust batch id
		|batchId|10|
		|revId|11|
		|clientId|419|
	Then I should get the trust batch id for trust reverse
		|batchId|10|
	

@getReceiveAmount
Scenario: Get received amount details from trust reverse process
	Given As a user i am passing valid trust id
		|trustId|1491|
	Then I should get sum of paid amount for the given id
		|paidAmount|2076.0|
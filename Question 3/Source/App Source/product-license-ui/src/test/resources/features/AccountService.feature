@Domka
Feature: To check all the methods of account service with valid and invalid unit testing data

  Background: Creation of accounts for account splitup
    Given Creation of accounts for spliting accounts with consumer and unit id
      | accountnumber | accountFor | accumlatedInterest | amountReferred | attorneyFee | clientcode | unit | unit5 | cancelledDate | chargeDate | accountInvoiceId | collectorId | courtCost | deliquencyDate | documentCode | expectedCommission | expectedCommissionOnInterest | hasPay | insuranceId | interest | interestStartDate | lastActyDate | lettetSent | medicalId | nextActyDate | otherCharges | overPay | principleBalance | referredDate | score | serviceEndDate | serviceStartDate | serviceType | cipRawMappingDetails | table | totalBalance | penalty | workFlowIndicator | paymentMade | lastPaymentDate | consumerID | clientId | agencyCharges | accuredInterest |
      |         12334 | accountFor |             132.23 |       78945.45 |        0.00 |        121 |    1 |   338 | 09/05/2017    | 09/05/2017 | accountInvoiceId |          32 |      1.11 | 09/05/2017     | documentCode |               1.11 |                         0.00 | hasPay |         123 |    16.00 | 09/05/2017        | 09/05/2017   | lettetSent |       132 | 09/05/2017   |       123.00 |    0.00 |           100.00 | 09/05/2017   |   100 | 09/05/2017     | 09/05/2017       | ServiceType | 1303-8-5             |    83 |      8888.01 |    10.0 | workFlowIndicator | Y           | 09/05/2017      |          5 |      121 |          10.0 |           39.73 |

	Given Creation of accounts for spliting accounts with consumer and unit id
		|accountnumber|accountFor		|accumlatedInterest|amountReferred|attorneyFee|clientcode|unit|unit5|cancelledDate|chargeDate|accountInvoiceId|collectorId|courtCost|deliquencyDate|documentCode|expectedCommission|expectedCommissionOnInterest|hasPay|insuranceId|interest|interestStartDate|lastActyDate   |lettetSent|medicalId|nextActyDate|otherCharges|overPay|principleBalance|referredDate|score|serviceEndDate|serviceStartDate|serviceType|cipRawMappingDetails|table|totalBalance|penalty|workFlowIndicator|paymentMade|lastPaymentDate|consumerID|clientId|agencyCharges|accuredInterest|
		|   12334     |accountFor		|     132.23   	   | 	78945.45  | 0.00      | 121      | 1  |338    |09/05/2017  |09/05/2017|accountInvoiceId|    32     |  1.11   |  09/05/2017  |documentCode|    1.11          |                     0.00   |hasPay|  123      |  16.00 |   09/05/2017    | 09/05/2017    |lettetSent| 132     | 09/05/2017 |   123.00   | 0.00  |     100.00     | 09/05/2017 |100  |  09/05/2017  |  09/05/2017     |ServiceType|1303-8-5 						| 83  |    8888.01 |  10.0 |workFlowIndicator|    Y      |  09/05/2017   |   5      | 121    |  10.0       |     39.73     |
	Scenario: Split all accounts using fnSplitAllAccounts method with valid consumer
   		Given As a user i am passing valid ConsumerId
    	  | ConsumerId | 5 |
      	| UnitId     | 0 |
    	Then I should get Map collection as a returntype

  Scenario: Split all accounts using fnSplitAllAccounts method with invalid consumerid
    Given As a user i am passing invalid ConsumerId
      | ConsumerId | 1123 |
      | UnitId     |    0 |
    Then I should not get Map collection as a returntype

  Scenario: Split all accounts using fnSplitAllAccounts method with invalid consumerid
    Given As a user i am passing invalid ConsumerId
      | ConsumerId | -123 |
      | UnitId     |    0 |
    Then I should not get Map collection as a returntype

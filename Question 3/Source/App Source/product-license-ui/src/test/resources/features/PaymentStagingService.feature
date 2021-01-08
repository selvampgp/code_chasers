@PaymentStagingService
Feature: To validate all the possible cases with corressponding methods handled in the ARTrail payment staging process.
  "Payment activity is a process of payment from domestic and external systems.This process handled in payment staging process and handled with various payment type."

  Background: Creation of payment staging details
    Given Create a list of payment staging details for testing

  @CreatePaymentUsingMPSAndRoBoDX @create
  Scenario: Create payment staging details from MADRONE PAYMENT SYSTEM(MPS) or RoBoDX
    When User posts payment details via staging
    Then New file will generate in payment posting file hopper

  @ValidateAdjustmentAccounts @checkForAdjustment
  Scenario: Validate payment posted accounts are eligible for adjustment process with valid staging details
    When User posts payment stage details to check adjustments with adjustment split flag N
      | PayStageId      | 454788 |
      | adjustmentSplit | Y      |
    Then Adjustment components are listed

  @ValidateAdjustmentAccountsWithEmptyReturn @checkForAdjustment
  Scenario: Validate payment posted accounts are eligible for adjustment process with valid staging details
    When User posts payment stage details to check adjustments with adjustment split flag Y
      | PayStageId      | 454789 |
      | adjustmentSplit | Y      |
    Then Adjustment components are listed as empty

  @ValidateAdjustmentWithAdjustmentSplit @checkForAdjustment
  Scenario: Validate payment posted accounts are eligible for adjustment process with payment adjustment split flag N
    Given User posts payment stage details with adjustment split flag N
      | PayStageId      | 454789 |
      | adjustmentSplit | N      |
    Then Adjustment components are listed as empty

  @UpdatePaymentAdjustmentDetails @adjustmentUpdate
  Scenario: Process and update approved adjustment for selected accounts from payment staging
    When User posts payment stage details having adjustments via staging
      | PayStageId  | 454789 |
      | PaymentType | ADJ    |
      | lmsCharges  | false  |
    Then Adjustment payment will get update and insert into voucher then return result status as true

  @DoNotUpdatePaymentAdjustmentWithNullAccounts @adjustmentUpdate
  Scenario: Do not update adjustment details with null accounts
    When User posts payment stage details with null accounts
      | PayStageId  | 454789 |
      | PaymentType | ADJ    |
      | lmsCharges  | false  |
      | isAccount   | false  |
    Then Method will notify there is no account for selected consumer
      | result | Account Not Available |
      | type   | false                 |

  @UpdateAdjustmentForLegalCharges @adjustmentUpdate
  Scenario: Update payment adjustments for legal process
    When User posts payment stage details having lms charges
      | PayStageId    |     454789 |
      | PaymentType   | ADJ        |
      | lmsCharges    | true       |
      | paymentSource | LMS_MANUAL |
    Then Adjustment gets updated for legal accounts

  @UpdateAdjustmentWithoutAdjSplit @adjustmentUpdate
  Scenario: Update adjustment with adjustment split flag 'N'
    When User posts payment stage details with no adjustment split
      | PayStageId      | 454789 |
      | PaymentType     | ADJ    |
      | lmsCharges      | false  |
      | adjustmentSplit | N      |
    Then Paid adjustment adjustment will get update with principle balance and total balance for selected accounts

  @UpdatenegativeAdjustmentWithoutAdjSplit @adjustmentUpdate
  Scenario: Update negative adjustment with adjustment split flag 'N'
    When User posts payment stage details with negative adjustment and adjusment split flag N
      | PayStageId       | 454789 |
      | PaymentType      | ADJ    |
      | lmsCharges       | false  |
      | adjustmentSplit  | N      |
      | adjustmentAmount |   -200 |
    Then Paid negative adjustment will get update with principle balance and total balance for selected accounts

  @ReverseAdjustmentForOpenAccount @adjustmentReversal
  Scenario: Reverse payment adjustment amount processed during adjustment process for open accounts
    When User posts payment stage details and reverse adjustment for open account
      | adjustment | 250.00 |
      | payStageId | 454788 |
      | account    |  62335 |
    Then Adjustment reversed for open accounts
      | adjustmentStatus | Y                  |
      | remarks          | Adjustment Success |
      | accountId        |              62335 |
      | payStageId       |             454788 |

  @ReverseClosedAccountAdjustmentWithStatusCode @adjustmentReversal
  Scenario: Adjustment reversal is inefficacious due to closed account status codes are already in paid in full or settle in full
    When User posts payment stage details and reverse adjustment for closed account
      | adjustment | 250.00 |
      | payStageId | 454788 |
      | account    | 785643 |
      | isAccount  | closed |

  @ReverseWithNullAccounts @adjustmentReversal
  Scenario: Notify message with adjustment reversal for zero adjustment amount cannot be done
    When User posts payment stage details and adjustment reversal details with null accounts
      | adjustment | 120.00 |
      | payStageId | 523422 |
      | account    | 123423 |
      | isAccount  | false  |
    Then return message contains there is no account present
      | adjustmentStatus | N                                                                                        |
      | remarks          | Adjustment Amount larger than available Account_Id not found in Open and Closed accounts |
      | accountId        |                                                                                        0 |
      | payStageId       |                                                                                   523422 |

  @ReverseWithZeroAdjustment @adjustmentReversal
  Scenario: Notify message with adjustment reversal for zero adjustment amount cannot be done
    When User posts payment stage details and adjustment reversal details with zero adjustment
      | adjustment |   0.00 |
      | payStageId | 454788 |
      | account    |  62335 |
    Then Return message contains adjustment amount is greater than available balance
      | adjustmentStatus | N                                 |
      | remarks          | Adjustment Amount Should Not Be 0 |
      | accountId        |                                 0 |
      | payStageId       |                            454788 |

  @ReverseClosedAccountAdjustmentWithOutStatusCode @adjustmentReversal
  Scenario: Adjustment reversal is inefficacious due to closed account status codes are already not in paid in full or settle in full
    When User posts payment stage details and reverse adjustment for closed account having CAN status code
      | adjustment | 120.00 |
      | payStageId | 454788 |
      | account    | 785643 |
      | isAccount  | false  |
      | statusCode | CAN    |
    Then Return message contains account status code not in paid in full or settle in full
      | adjustmentStatus | N                                                                                  |
      | remarks          | Adjustment Amount larger than available Account status code other than PIF and SIF |
      | accountId        |                                                                                  0 |
      | payStageId       |                                                                             454788 |

  @ReverseAdjustmentWithZeroTotalBalance @adjustmentReversal
  Scenario: Ignore adjustment reversal update when an account total balance is zero
    When User posts payment stage details and adjustment reversal details having account total balance is zero
      | adjustment   | 120.00 |
      | payStageId   | 454788 |
      | account      | 785643 |
      | totalBalance |   0.00 |
    Then Return message contains account total due is zero
      | adjustmentStatus | N                                                      |
      | remarks          | Adjustment Amount larger than available Total Due is 0 |
      | accountId        |                                                      0 |
      | payStageId       |                                                 454788 |

  @DoNotUpdateAdjustment @adjustmentReversal
  Scenario: Do not update adjustment details with invalid details
    Given User posts invalid adjustment details
      | adjustment  | 250.00 |
      | payStageId  | 454788 |
      | account     |  62335 |
      | isAdjUpdate | false  |
    Then adjustment details will not get update
      | adjustmentStatus | N      |
      | remarks          | N      |
      | accountId        |      0 |
      | payStageId       | 454788 |

  @SaveAdjustmentFromRoBoDX @saveDataFromCipAdjustment
  Scenario: Create new entry in payment staging during adjustment process from RoBoDX
    When User posts adjustment payments data from RoBoDx then details will save into ARTrail
      | art_account_id   |                                          62335 |
      | art_consumer_id  |                                        1354448 |
      | clientDebtor     |                                         234744 |
      | clientAcctNumber |                                          32156 |
      | adj              |                                         367.19 |
      | adj_principal    |                                       35689.74 |
      | adj_interest     |                                         232.15 |
      | adj_penalty      |                                         105.36 |
      | adj_othercharges |                                         150.32 |
      | adj_accumulated  |                                           1532 |
      | adj_attorneyfee  |                                            124 |
      | adj_courtcost    |                                            102 |
      | PaymentType      | ADJ                                            |
      | clientcode       |                                            454 |
      | agencyCommission |                                         236.15 |
      | paydate          | 05/29/2018                                     |
      | filename         | CIPAdjustment.txt                              |
      | PaymentKey       | 100518A43-405E59AD-77D8-42F6-9DF0-A96E1AD3F3FD |
      | cipRouteId       |                                            588 |

  @ThrowErrorWithAdjustmentSave @saveDataFromCipAdjustment
  Scenario: Throw exception while saving adjustment from RoBoDx
    When User posts adjustment payments data from RoBoDx then error will throw
      | art_account_id   |                                          62335 |
      | art_consumer_id  |                                        1354448 |
      | clientDebtor     |                                         234744 |
      | clientAcctNumber |                                          32156 |
      | adj              |                                         367.19 |
      | adj_principal    |                                       35689.74 |
      | adj_interest     |                                         232.15 |
      | adj_penalty      |                                         105.36 |
      | adj_othercharges |                                         150.32 |
      | adj_accumulated  |                                           1532 |
      | adj_attorneyfee  |                                            124 |
      | adj_courtcost    |                                            102 |
      | PaymentType      | ADJ                                            |
      | clientcode       |                                            454 |
      | agencyCommission |                                         236.15 |
      | paydate          | 05/29/2018                                     |
      | filename         | CIPAdjustment.txt                              |
      | PaymentKey       | 100518A43-405E59AD-77D8-42F6-9DF0-A96E1AD3F3FD |
      | cipRouteId       |                                            588 |

  @CheckPreviousPaymentStatusBeforeReversal @checkStagingStatus
  Scenario: Check payment approved status with valid payment stagind id before getting it reversed
    Given User pass a valid payment staging id to check staging status
      | payStagingId | 454786 |
    Then return staging status details
      | approvedStatus | payAmount | agencyCommision |
      | P              |      1200 |               0 |

  @UpdatePaymentStageStatus @updateStageDetails
  Scenario: Update payment staging approved status, do not post status and approved date with valid staging id
    Given User pass a valid payment staging id to update staging status then staging details will get update
      | payStagingId   | 454786 |
      | approvedStatus | A      |
      | dnpStatus      | A      |
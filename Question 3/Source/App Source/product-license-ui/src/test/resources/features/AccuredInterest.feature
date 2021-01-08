Feature: Accured Interest Calculation with Balance Range,with Default,with Date Range,With Status Code,With multiple Status Code

  Background: Creation of accounts for calculating accured interest
    Given Get all accounts where interest start date not equal to current date
      | accountnumber | accountFor | accumlatedInterest | amountReferred | attorneyFee | clientcode | unit | cancelledDate | chargeDate | accountInvoiceId | collectorId | courtCost | deliquencyDate | documentCode | expectedCommission | expectedCommissionOnInterest | hasPay | insuranceId | interest | interestStartDate | lastActyDate | lettetSent | medicalId | nextActyDate | otherCharges | overPay | principleBalance | referredDate | score | serviceEndDate | serviceStartDate | serviceType | cipRawMappingDetails | table | totalBalance | penalty | workFlowIndicator | paymentMade | lastPaymentDate | consumerID | clientId | agencyCharges | accuredInterest |
      |         12334 | accountFor |             132.23 |       78945.45 |        0.00 |        121 |    1 | 09/05/2017    | 09/05/2017 | accountInvoiceId |          32 |      1.11 | 09/05/2017     | documentCode |               1.11 |                         0.00 | hasPay |         123 |    16.00 | 09/05/2017        | 09/05/2017   | lettetSent |       132 | 09/05/2017   |       123.00 |    0.00 |           100.00 | 09/05/2017   |   100 | 09/05/2017     | 09/05/2017       | ServiceType | 1303-8-5             |    83 |      8888.01 |    10.0 | workFlowIndicator | Y           | 09/05/2017      |          5 |      121 |          10.0 |           39.73 |

  @AcrBalanceRange
  Scenario: Calculate accured interest from client interest setting with Balance Range
    When Client charge interest is set to flag Y and interest type is DOR
      | InterestType         | DOR |
      | ClientChargeInterest | Y   |
    And Calculate the accured interest based on client settings with balance
      | ClientId        |                12 |
      | Component       | Principal Balance |
      | InterestPercent |                 8 |
      | InterestMethod  | Balance           |
      | From            |                 0 |
      | To              |              1000 |
    Then I should see the calculated value
      | accuredInterest | 0.07 |

  @AcrDefIntValue
  Scenario: Calculate accured interest from client interest setting with Default interest value
    When Client charge interest is set to flag Y and interest type is DOR
      | InterestType         | DOR |
      | ClientChargeInterest | Y   |
    And Calculate with Interest Method and Interest Rate with default
      | InterestMethod  | Default |
      | InterestPercent |       5 |
    Then I should see the calculated value
      | accuredInterest | 0.04 |

  @AcrDateRange
  Scenario: Calculate accured interest from client interest setting with Date Range
    When Client charge interest is set to flag Y and interest type is DOR
      | InterestType         | DOR |
      | ClientChargeInterest | Y   |
    And Calculate the accured interest based on client settings with date range
      | ClientId        |                12 |
      | Component       | Principal Balance |
      | InterestPercent |              6.48 |
      | InterestMethod  | Date Range        |
      | From            |                 0 |
      | To              |                20 |
    Then I should see the calculated value
      | accuredInterest | 0.05 |

  @AcrStatCode
  Scenario: Calculate accured interest from client interest setting with status code
    When Client charge interest is set to flag Y and interest type is DOR
      | InterestType         | DOR |
      | ClientChargeInterest | Y   |
    And Calculate the accured interest based on client settings with statusCode
      | ClientId        |                12 |
      | Component       | Principal Balance |
      | InterestPercent |                12 |
      | InterestMethod  | Status Code       |
      | StatusCode      | AUT               |
    Then I should see the calculated value
      | accuredInterest | 0.1 |

  @AcrMulStatCode
  Scenario: Calculate accured interest from client interest setting with multiple status code
    When Client charge interest is set to flag Y and interest type is DOR
      | InterestType         | DOR |
      | ClientChargeInterest | Y   |
    And Calculate the accured interest based on client settings with multiple statusCode
      | ClientId        |                12 |
      | Component       | Principal Balance |
      | InterestPercent |                 8 |
      | InterestMethod  | Status Code       |
      | StatusCode      | AUT,ACH,PVP,PDR   |
    Then I should see the calculated value
      | accuredInterest | 0.07 |

  @AcrStatCodPercent
  Scenario: Calculate accured interest from client interest setting with status code percentage
    When Client charge interest is set to flag Y and interest type is DOR
      | InterestType         | DOR |
      | ClientChargeInterest | Y   |
    And Calculate the accured interest based on client settings with statusCode starts with
      | ClientId        |                12 |
      | Component       | Principal Balance |
      | InterestPercent |                 8 |
      | InterestMethod  | Status Code       |
      | StatusCode      | A%                |
    Then I should see the calculated value
      | accuredInterest | 0.07 |

  @AcrFrmBalRange
  Scenario: Calculate accured interest from client interest setting with Balance Range by setting only from value
    When Client charge interest is set to flag Y and interest type is DOS
      | InterestType         | DOS |
      | ClientChargeInterest | Y   |
    And Calculate the accured interest based on client settings
      | ClientId        |            12 |
      | Component       | Other Charges |
      | InterestPercent |             8 |
      | InterestMethod  | Balance       |
      | From            |           100 |
      | To              |               |
    Then I should see the calculated value
      | accuredInterest | 0.07 |

  @AcrClntIntSet
  Scenario: Calculate accured interest from client interest setting
    Given Make all accounts interest start date to current date
      | InterestStartDate | 09-05-2017 |
    When Client charge interest is set to flag Y and interest type is DOR
      | InterestType         | DOR |
      | ClientChargeInterest | Y   |
    Then I should not see the calculated value
      | accuredInterest | 0.00 |

  @AcrClntCharge
  Scenario: Calculate accured interest from client interest setting with client charge flag
    When Client charge interest is set to flag N
      | ClientChargeInterest | N |
    Then I should not see the calculated value
      | accuredInterest | 0.00 |

  @AcrWrongStatusCode
  Scenario: Calculate accured interest from client interest setting with wrong status code
    When Client charge interest is set to flag Y and interest type is DOR
      | InterestType         | DOR |
      | ClientChargeInterest | Y   |
    And Calculate the accured interest based on client settings with wrong statusCode
      | ClientId        |                12 |
      | Component       | Principal Balance |
      | InterestPercent |                 8 |
      | InterestMethod  | Status Code       |
      | StatusCode      | SSS               |
    Then I should not see the calculated value
      | accuredInterest | 0.00 |

  #New Scenarios
  Scenario: Calculate accured interest from client interest setting with Date Range
    When Client charge interest is set to flag Y and interest type is DOS
      | InterestType         | DOS |
      | ClientChargeInterest | Y   |
    And Calculate the accured interest based on client settings
      | ClientId        |                12 |
      | Component       | Principal Balance |
      | InterestPercent |                 8 |
      | InterestMethod  | Balance           |
      | From            |                 0 |
      | To              |                50 |
    But If the principal balance doesnt fall on balance interest method
      | ClientId        |                12 |
      | Component       | Principal Balance |
      | InterestPercent |                10 |
      | InterestMethod  | Date range        |
      | From            |                 1 |
      | To              |               100 |
    Then System should calculate the value
      | accuredInterest |  |

  Scenario: Calculate accured interest from client interest setting with Status Code
    When Client charge interest is set to flag Y and interest type is DOS
      | InterestType         | DOS |
      | ClientChargeInterest | Y   |
    And Calculate the accured interest based on client settings
      | ClientId        |                12 |
      | Component       | Principal Balance |
      | InterestPercent |                 8 |
      | InterestMethod  | Balance           |
      | From            |                 0 |
      | To              |                50 |
    Then If the principal balance doesnt fall on balance interest method
      | ClientId        |                12 |
      | Component       | Principal Balance |
      | InterestPercent |                10 |
      | InterestMethod  | Date range        |
      | From            |                 1 |
      | To              |                10 |
    Then If the principal balance doesnt fall on date range method
      | ClientId        |                12 |
      | Component       | Principal Balance |
      | InterestPercent |                10 |
      | InterestMethod  | Status Code       |
      | StatusCode      | A%                |
    Then System should calculate the value
      | accuredInterest |  |

  #negative
  Scenario: Calculate accured interest from client interest setting with Default value
    When Client charge interest is set to flag Y and interest type is DOS
      | InterestType         | DOS |
      | ClientChargeInterest | Y   |
    And Calculate the accured interest based on client settings
      | ClientId        |                12 |
      | Component       | Principal Balance |
      | InterestPercent |                 8 |
      | InterestMethod  | Balance           |
      | From            |                 0 |
      | To              |                50 |
    Then If the principal balance doesnt fall on balance interest method
      | ClientId        |                12 |
      | Component       | Principal Balance |
      | InterestPercent |                10 |
      | InterestMethod  | Date range        |
      | From            |                 1 |
      | To              |                10 |
    Then If the principal balance doesnt fall on date range method
      | ClientId        |                12 |
      | Component       | Principal Balance |
      | InterestPercent |                12 |
      | InterestMethod  | Status Code       |
      | StatusCode      | A%                |
    Then If the principal balance doesnt fall by status code
      | ClientId        |                12 |
      | Component       | Principal Balance |
      | InterestPercent |              13.5 |
      | InterestMethod  | Default           |
    Then System should calculate the value
      | accuredInterest |  |

  Scenario: Calculate accured interest from client interest setting with Method is NULL
    When Client charge interest is set to flag Y and interest type is DOS
      | InterestType         | DOS  |
      | ClientChargeInterest | Y    |
      | Method               | NULL |
    Then System should not calculate the value
      | accuredInterest |  |

  Scenario: Calculate accured interest from client interest setting with Status Code Priority
    When Client charge interest is set to flag Y and interest type is DOS
      | InterestType         | DOS    |
      | ClientChargeInterest | Y      |
      | Method               | Simple |
    And Calculate the accured interest based on client settings
      | ClientId        |                12 |
      | Component       | Principal Balance |
      | InterestPercent |                 8 |
      | InterestMethod  | Balance           |
      | From            |                 0 |
      | To              |                50 |
    Then Calculate the accured interest based on client settings with component as principal balance
      | ClientId        |                12 |
      | Component       | Principal Balance |
      | InterestPercent |                10 |
      | InterestMethod  | Status Code       |
      | StatusCode      | AUT               |
    And Calculate the accured interest based on client settings with component as principal balance and status code starts with
      | ClientId        |                12 |
      | Component       | Principal Balance |
      | InterestPercent |                12 |
      | InterestMethod  | Status Code       |
      | StatusCode      | A%                |
    Then System should calculate the value based on priority ten percent
      | accuredInterest |  |

  Scenario: Calculate accured interest from client interest setting with Simple Method
    When Client charge interest is set to flag Y and interest type is DOS
      | InterestType         | DOS    |
      | ClientChargeInterest | Y      |
      | Method               | Simple |
    And Calculate the accured interest based on client settings with component as principal balance
      | ClientId        |                12 |
      | Component       | Principal Balance |
      | InterestPercent |                 8 |
      | InterestMethod  | Balance           |
      | From            |                 0 |
      | To              |                50 |
    And Calculate the accured interest based on client settings with component as interest
      | ClientId        |       12 |
      | Component       | Interest |
      | InterestPercent |       10 |
      | InterestMethod  | Balance  |
      | From            |        0 |
      | To              |       30 |
    Then System should calculate the value for both the charges
      | accuredInterest |  |

  # negative
  Scenario: Calculate accured interest from client interest setting with Zero Prinipal Balance
    When Client charge interest is set to flag Y and interest type is DOS
      | InterestType         | DOS    |
      | ClientChargeInterest | Y      |
      | Method               | Simple |
    And Calculate the accured interest based on client settings
      | ClientId        |                12 |
      | Component       | Principal Balance |
      | InterestPercent |                 8 |
      | InterestMethod  | Balance           |
      | From            |                 0 |
      | To              |                50 |
    Then System should not calculate the value
      | accuredInterest |  |

  # negative
  Scenario: Calculate accured interest from client interest setting with Default value even condition satisfies
    When Client charge interest is set to flag Y and interest type is DOS
      | InterestType         | DOS    |
      | ClientChargeInterest | Y      |
      | Method               | Simple |
    And Calculate the accured interest based on client settings
      | ClientId        |                12 |
      | Component       | Principal Balance |
      | InterestPercent |                 8 |
      | InterestMethod  | Balance           |
      | From            |                 0 |
      | To              |                50 |
    Then System moved to default method
      | ClientId        |                12 |
      | Component       | Principal Balance |
      | InterestPercent |                 8 |
      | InterestMethod  | Default           |
    Then System should calculate the Default value
      | accuredInterest |  |

  Scenario: Calculate accured interest from client interest setting
    When Client charge interest is set to flag Y and interest type is DOS
      | InterestType             | DOS    |
      | ClientChargeInterest     | Y      |
      | Method                   | Simple |
    And Calculate the accured interest based on client settings
      | ClientId        |                12 |
      | Component       | Principal Balance |
      | InterestPercent |                 8 |
      | InterestMethod  | Balance           |
      | From            |                 0 |
      | To              |                50 |
    Then System should calculate the value Based on formula
      | accuredInterest |  |

  Scenario: Calculate accured interest from client interest setting with AccountLevelComponentInterest is N
    When Client charge interest is set to flag Y and interest type is DOS
      | ClientChargeInterest          | Y      |
      | InterestType                  | DOS    |
      | Method                        | Simple |
      | Accrued Interest Formula      | SQL    |
      | AccountLevelComponentInterest | N      |
      | Account Interest Percentage   |     10 |
    And Calculate the accured interest based on client settings with component as principal balance
      | ClientId        |                12 |
      | Component       | Principal Balance |
      | InterestPercent |                 8 |
      | InterestMethod  | Balance           |
      | From            |                 0 |
      | To              |                50 |
    And Calculate the accured interest based on client settings with component as other charges
      | ClientId        |            12 |
      | Component       | Other Charges |
      | InterestPercent |            10 |
      | InterestMethod  | Balance       |
      | From            |             0 |
      | To              |            50 |
    Then System should calculate the Account Interest Percentage value Only for Principal Balance
      | accuredInterest |  |

  Scenario: Calculate accured interest from client interest setting with AccountLevelComponentInterest is Y
    When Client charge interest is set to flag Y and interest type is DOS
      | ClientChargeInterest          | Y      |
      | InterestType                  | DOS    |
      | Method                        | Simple |
      | Accrued Interest Formula      | SQL    |
      | AccountLevelComponentInterest | Y      |
      | Account Interest Percentage   |     10 |
    And Calculate the accured interest based on client settings with component as principal balance
      | ClientId        |                12 |
      | Component       | Principal Balance |
      | InterestPercent |                 8 |
      | InterestMethod  | Balance           |
      | From            |                 0 |
      | To              |                50 |
    And Calculate the accured interest based on client settings with component as other charges
      | ClientId        |            12 |
      | Component       | Other Charges |
      | InterestPercent |            10 |
      | InterestMethod  | Balance       |
      | From            |             0 |
      | To              |            50 |
    Then System should calculate the Account Interest Percentage value for Both components
      | accuredInterest |  |

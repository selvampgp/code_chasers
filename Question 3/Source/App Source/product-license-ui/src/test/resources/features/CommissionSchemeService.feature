@CommissionScheme
Feature: To check all commission scheme for all type of payments.
  This feature will cover all possible and non-possible cases for each method written in commission scheme service.

  Background: Create new commission scheme details with valid scheme details
    Given create a list of commission scheme details

  @extractSchemeCodeViaAccount @extractSchemeCode
  Scenario Outline: Get commission scheme code from account to calculate commission
    When user posts account details for calculate commission with accountnumber <accountnumber> and schemecode as <schemeCodeRef> and client scheme code as <clientSchemeCodeRef>
    Then return scheme code <expectedSchemeCode> for calculate commission

    Examples: 
      | accountnumber | schemeCodeRef | clientSchemeCodeRef | expectedSchemeCode |
      |          8661 |            42 | null                |                 42 |
      |          8662 | null          |                  42 |                 42 |
      |          8664 | null          | null                |               1208 |
      |          8665 |             0 |                   0 |                  0 |

  @CalculateCommissionForAccount @calculateCommission
  Scenario: Calculate commission for account with commission inclusive check true
    When user calculate commission for account creation then calculated commission for each component will be updated
      | calcType   | account     |
      | accountIds | 62335,62336 |

  @CalculateCommissionForPayment @calculateCommission
  Scenario: Calculate commission for payment with commission inclusive check true
    When user calculate commission for payment then calculated commission for each component will be updated
      | calcType   | payment |
      | accountIds |  226586 |

  @CalculateCommissionFromAccountWithInclusiveAndWorkListFilters @CalculateCommissionFromAccount @CalculateCommissionFromAccountWithOverride
  Scenario: Calculate commission for accounts were loaded from account staging with commission inclusive true and to calulate based on override scheme
    When user posts account details to calculate commission after creation an account from account staging with commission inclusive and priority order true and override scheme true
      | calcType             | account |
      | accountId            |   77073 |
      | commission_inclusive | true    |
      | priorityOrder        |    1208 |
    Then commission attributes for corressponding account will be updated
      | totalBalance | expectedCommission | expectedCommissionOnInterest | commissionOnAccumulatedInterest | expectedCommissionOnOtherCharges | commissionOnAttorneyFees | commissionOnCourtCost | commOnPenality | commissionOnPrincipalInclusiveFlag | commissionOnInterestInclusiveFlag | commissionOnAccumulatedInterestInclusiveFlag | commissionOnOtherChargesInclusiveFlag | commissionOnAttorneyInclusiveFlag | commissionOnCourtCostInclusiveFlag | commissionOnPenaltyInclusiveFlag |
      |     82553.99 |            9684.02 |                       237.56 |                          143.81 |                           108.03 |                   242.61 |                161.75 |         148.77 | Y                                  | N                                 | Y                                            | Y                                     | Y                                 | Y                                  | Y                                |

  @CalculateCommissionFromAccountWithExclusiveAndWorkListFilter @CalculateCommissionFromAccount @CalculateCommissionFromAccountWithOverride
  Scenario: Calculate commission for accounts were loaded from account staging with commission exclusive false and to calulate based on override scheme
    When user posts account details to calculate commission after creation an account from account staging with commission exclusive and priority order false and override scheme true
      | calcType             | account |
      | accountId            |   77072 |
      | commission_inclusive | false   |
      | priorityOrder        |    1209 |
    Then commission attributes for corressponding account will be updated
      | totalBalance | expectedCommission | expectedCommissionOnInterest | commissionOnAccumulatedInterest | expectedCommissionOnOtherCharges | commissionOnAttorneyFees | commissionOnCourtCost | commOnPenality | commissionOnPrincipalInclusiveFlag | commissionOnInterestInclusiveFlag | commissionOnAccumulatedInterestInclusiveFlag | commissionOnOtherChargesInclusiveFlag | commissionOnAttorneyInclusiveFlag | commissionOnCourtCostInclusiveFlag | commissionOnPenaltyInclusiveFlag |
      |    630689.89 |           97764.89 |                         30.0 |                          273.16 |                           166.62 |                   183.45 |                102.42 |          165.0 | Y                                  | N                                 | N                                            | N                                     | N                                 | N                                  | N                                |

  @CalculateCommissionFromAccountWithInclusive @CalculateCommissionFromAccount @CalculateCommissionFromAccountWithoutOverrides
  Scenario: Calculate commission for accounts were loaded from account staging with commission inclusive true
    When user posts account details to calculate commission after creation an account from account staging with commission inclusive and priority order true and override scheme false
      | calcType             | account |
      | accountId            |   77073 |
      | commission_inclusive | true    |
      | priorityOrder        |    1211 |
    Then commission attributes for corressponding account will be updated
      | totalBalance      | expectedCommission | expectedCommissionOnInterest | commissionOnAccumulatedInterest | expectedCommissionOnOtherCharges | commissionOnAttorneyFees | commissionOnCourtCost | commOnPenality | commissionOnPrincipalInclusiveFlag | commissionOnInterestInclusiveFlag | commissionOnAccumulatedInterestInclusiveFlag | commissionOnOtherChargesInclusiveFlag | commissionOnAttorneyInclusiveFlag | commissionOnCourtCostInclusiveFlag | commissionOnPenaltyInclusiveFlag |
      | 87265.26000000001 |           14365.29 |                         30.0 |                          143.81 |                           108.03 |                   242.61 |                161.75 |         148.77 | Y                                  | Y                                 | Y                                            | Y                                     | Y                                 | Y                                  | Y                                |

  @CalculateCommissionFromAccountWithExclusive @CalculateCommissionFromAccount @CalculateCommissionFromAccountWithoutOverride
  Scenario: Calculate commission for accounts were loaded from account staging with commission exclusive false
    When user posts account details to calculate commission after creation an account from account staging with commission exclusive and priority order false and override scheme false
      | calcType             | account |
      | accountId            |   77072 |
      | commission_inclusive | false   |
      | priorityOrder        |    1212 |
    Then commission attributes for corressponding account will be updated
      | totalBalance | expectedCommission | expectedCommissionOnInterest | commissionOnAccumulatedInterest | expectedCommissionOnOtherCharges | commissionOnAttorneyFees | commissionOnCourtCost | commOnPenality | commissionOnPrincipalInclusiveFlag | commissionOnInterestInclusiveFlag | commissionOnAccumulatedInterestInclusiveFlag | commissionOnOtherChargesInclusiveFlag | commissionOnAttorneyInclusiveFlag | commissionOnCourtCostInclusiveFlag | commissionOnPenaltyInclusiveFlag |
      |     532925.0 |          156413.36 |                         30.0 |                          273.16 |                           166.62 |                   183.45 |                102.42 |          165.0 | N                                  | N                                 | N                                            | N                                     | N                                 | N                                  | N                                |

  @applySchemeAndDeriveCommissionWithIncusiveOverride @applySchemeAndDeriveCommission
  Scenario: Used to apply the configured override commission scheme to the value and calculate commission value
    When user allocate payment details and to calculate override commission with commission inclusive true and commission priority order true
      | schemeCode      |              1208 |
      | componentValue  |          62534.48 |
      | componentType   |               128 |
      | filterComponent | statusCodeRef-AUT |
    Then return derived commission for posted payment
      | filterName               | calculatedCommission | inclusiveFlag | commissionSchemeDetails |
      | accountCommissionScheme1 |   11475.077080000001 | Y             | null                    |

  @applySchemeAndDeriveCommissionWithExclusiveOverride @applySchemeAndDeriveCommission
  Scenario: Used to apply the configured override commission scheme to the value and calculate commission value
    When user allocate payment details and to calculate override commission with commission inclusive false and commission priority order true
      | schemeCode      |              1209 |
      | componentValue  |           4256.87 |
      | componentType   |               128 |
      | filterComponent | statusCodeRef-ACH |
    Then return derived commission for posted payment
      | filterName                         | calculatedCommission | inclusiveFlag | commissionSchemeDetails |
      | accountCommissionSchemeWithFilters |    794.7576290000001 | Y             | null                    |

  @applySchemeAndDeriveCommissionWithIncusive @applySchemeAndDeriveCommission @applySchemeAndDeriveCommissionWithoutOverride
  Scenario: Used to apply the configured commission scheme to the value and calculate commission value
    When user allocate payment details and to calculate commission with commission inclusive true and commission priority order true
      | schemeCode      |              1211 |
      | componentValue  |           2286.73 |
      | componentType   |               129 |
      | filterComponent | statusCodeRef-ACH |
    Then return derived commission for posted payment
      | filterName | calculatedCommission | inclusiveFlag | commissionSchemeDetails |
      | Default    |                 30.0 | Y             |                    1211 |

  @applySchemeAndDeriveCommissionWithExclusive @applySchemeAndDeriveCommission @applySchemeAndDeriveCommissionWithoutOverride
  Scenario: Used to apply the configured commission scheme to the value and calculate commission value
    When user allocate payment details and to calculate commission with commission inclusive false and commission priority order true
      | schemeCode      |              1212 |
      | componentValue  |           2478.27 |
      | componentType   |               130 |
      | filterComponent | statusCodeRef-AUT |
    Then return derived commission for posted payment
      | filterName | calculatedCommission | inclusiveFlag | commissionSchemeDetails |
      | Default    |   336.79689299999995 | N             |                    1212 |

  @calculateCommissionForRuntimeValues
  Scenario: Calculate commission for runtime transactions
    When user post payment details with commission inclusive
      | schemeCode |   1211 |
      | paymentIds | 226589 |
    Then return scheme details with calculated commission
      | schemeCode | CommissionType | commPer | Amount  | Commission | chargeType | schemeName                           | filterName |
      |       1211 |            124 |   29.87 | 3147.89 |     940.28 |        128 | DefaultCommissionSchemeWithExclusive | Default    |
      |       1211 |            124 |    30.0 | 2623.54 |       30.0 |        129 | DefaultCommissionSchemeWithExclusive | Default    |
      |       1211 |            124 |   15.36 | 3684.45 |     565.93 |        130 | DefaultCommissionSchemeWithExclusive | Default    |
      |       1211 |            124 |   10.89 | 4238.43 |     461.57 |        131 | DefaultCommissionSchemeWithExclusive | Default    |
      |       1211 |            124 |    13.0 |     0.0 |        0.0 |        132 | DefaultCommissionSchemeWithExclusive | Default    |
      |       1211 |            124 |    15.0 |     0.0 |        0.0 |        695 | DefaultCommissionSchemeWithExclusive | Default    |
      | null       |            125 |       0 |     0.0 |        0.0 |        521 | null                                 | null       |
      |       1211 |            124 |    8.26 |     0.0 |        0.0 |        396 | DefaultCommissionSchemeWithExclusive | Default    |

  @calculateCommissionForRuntimeValues @calculateCommissionForRuntimeValuesWithCommissionOverride
  Scenario: Calculate commission for runtime transactions
    When user post payment details with commission inclusive and commission override
      | schemeCode |   1208 |
      | paymentIds | 226589 |
    Then return scheme details with calculated commission
      | schemeCode | CommissionType | commPer | Amount  | Commission | chargeType | schemeName                            | filterName               |
      |       1208 |            124 |   20.36 | 3396.62 |     691.55 |        128 | CommissionSchemeWithInclusiveOverride | accountCommissionScheme1 |
      |       1208 |            124 |    30.0 | 2623.54 |       30.0 |        129 | CommissionSchemeWithInclusiveOverride | Default                  |
      |       1208 |            124 |   15.36 | 3684.45 |     565.93 |        130 | CommissionSchemeWithInclusiveOverride | Default                  |
      |       1208 |            124 |   10.89 | 4238.43 |     461.57 |        131 | CommissionSchemeWithInclusiveOverride | Default                  |
      |       1208 |            124 |    13.0 |     0.0 |        0.0 |        132 | CommissionSchemeWithInclusiveOverride | Default                  |
      |       1208 |            124 |    15.0 |     0.0 |        0.0 |        695 | CommissionSchemeWithInclusiveOverride | Default                  |
      | null       |            125 |       0 |     0.0 |        0.0 |        521 | null                                  | null                     |
      |       1208 |            124 |    8.26 |     0.0 |        0.0 |        396 | CommissionSchemeWithInclusiveOverride | Default                  |

  @getCalculatedValueForSpecifiedComponent @getCalculatedValueForAccumlatedCommission
  Scenario Outline: User to calculate commission for specific component with or without commission inclusive/exclusive and with commission override
    When user mention the component name need to calculate commission for the account id <accountNumber> , component name <componentName> , component value <componentValue> and commission inclusive <commissionInclusive>
    Then return calculated commission <commPer> and the commission <commissionAmount> for scheme code <schemeCode> with commission type <commissionType> , charge type <chargeType> , scheme  <schemeName> , filter name <filterName> and remaining balance <balanceAmount>

    Examples: 
      | accountNumber | componentName | componentValue | commissionInclusive | schemeCode | commissionType | commPer | balanceAmount | commissionAmount | chargeType | schemeName                            | filterName               |
      |         77072 |           130 |       42365.19 | true                |       1209 |            125 |   13.59 |      42365.19 |          5068.61 |        130 | CommissionSchemeWithExclusiveOverride | Default                  |
      |         77073 |           128 |       32548.19 | true                |       1208 |            124 |   18.35 |      27501.64 |          5046.55 |        128 | CommissionSchemeWithInclusiveOverride | accountCommissionScheme1 |
      |         77073 |           128 |      -32548.19 | true                |       1208 |            124 |   18.35 |     -27501.64 |         -5046.55 |        128 | CommissionSchemeWithInclusiveOverride | accountCommissionScheme1 |
      |         62336 |           132 |       42365.19 | false               |       1212 |            125 |    14.0 |      42365.19 |          5931.13 |        132 | DefaultCommissionSchemeWithExclusive  | Default                  |
      |         62335 |           130 |              0 | false               |       1211 |            124 |   13.59 |           0.0 |              0.0 |        130 | DefaultCommissionSchemeWithExclusive  | Default                  |

  @getCalculatedValueForAccumlatedCommissionForAccountList
  Scenario: Fetch calculated value only for accumulated from the account list
    When user want to calculate commission for accumulated for mentioned accounts
      | accountIds | 62335,77073 |
    Then calculated commission for accumulated interest returns
      | accountId | calculatedCommission |
      |     77073 |             163.3518 |
      |     62335 |                543.6 |

  @calculateCommissionFromFile
  Scenario Outline: Calculate commission via payment staging file
    When calculate commission from payment staging for mentioned client <clientId> ,commission in file <commissionInFile>, pay amount <payAmount> , commission inclusive <inclusiveFlag>
    Then calculated commission amount will return as list of hashmap <calculatedCommisionpercentage> , commission type as <CommissionType>

    Examples: 
      | clientId | commissionInFile | payAmount | inclusiveFlag | calculatedCommisionpercentage | CommissionType |
      |      454 |           240.24 |   4452.18 | true          |             5.703784954201626 |            124 |
      |      454 |              0.0 |   4452.18 | true          |                           0.0 |            124 |
      |      454 |           -24.26 |   4452.18 | true          |           -0.5419485126573795 |            124 |

  @calculateCommissionForPaymentAllocation
  Scenario: Calculate commission for payment allocation
    When calculate commission during payment allocation
      | clientId      |        454 |
      | accountId     |      62335 |
      | paymentId     |   26077216 |
      | inclusiveFlag | true       |
      | payDate       | 2018-06-15 |
    Then calculated commision amount will return as list of hashmap
      | schemeCode | CommissionType | commPer | Amount   | Commission | chargeType | schemeName                           | filterName |
      |       1211 |            124 |   29.87 | 18432.41 |    5505.76 |        128 | DefaultCommissionSchemeWithExclusive | Default    |
      |       1211 |            124 |    30.0 |     20.0 |       30.0 |        129 | DefaultCommissionSchemeWithExclusive | Default    |
      |       1211 |            124 |   13.59 |  3521.44 |     478.56 |        130 | DefaultCommissionSchemeWithExclusive | Default    |
      |       1211 |            124 |   10.89 |    47.05 |      72.95 |        131 | DefaultCommissionSchemeWithExclusive | Default    |
      |       1211 |            124 |    15.0 |   521.74 |      78.26 |        132 | DefaultCommissionSchemeWithExclusive | Default    |
      |       1211 |            124 |    20.0 |      0.0 |        0.0 |        695 | DefaultCommissionSchemeWithExclusive | Default    |
      | null       |            125 |     0.0 |      0.0 |        0.0 |        521 | null                                 | null       |
      |       1211 |            124 |    8.26 |    36.68 |      33.32 |        396 | DefaultCommissionSchemeWithExclusive | Default    |

  @getType @fetchCommissionType
  Scenario: Fetch commission type by client id
    When user pass client id to get commission type
      | clientId | 20673 |
    Then commission type for particular client will return

  @getTypeByIds
  Scenario: Fetch commission type by client ids
    When user pass client ids to get commission type
      | clientIds | 20673,454 |
    Then commission type for particular clients will return

  @fetchDefaultScheme
  Scenario: Fetch default commissions scheme
    When call fetch default commission scheme service
    Then default commission scheme will return

  @findCommissionSchemeById
  Scenario: Fetch commission scheme by commission id
    When commission scheme id passed to fetch commission scheme
      | commissionId | 1208 |
    Then commission scheme details will return based on commissionId

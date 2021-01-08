@PaymentAllocationProcess
Feature: To check all the methods of payment allocation process with valid and invalid unit test data
  "This feature file covers all rule setup for  Point of Sale-CASH payment and covers payment process remaining all payment types"

  @PriEqAllLifoByAccount @severity=blocker
  Scenario: Allocate POS-CASH payment using entity level is Priority and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Charge and the selection type is Last In First Out and the allocation type is By Account rule
  "This scenario handle Point of Sale-CASH payment"
    Given Allocate payment with payment type as POS-CASH using entity level is Priority and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Charge and the selection type is Last In First Out and the allocation type is By Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   722 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @DisEqAllLifoByAccount @severity=blocker
  Scenario: Allocate POS-CASH payment using entity level is Distribution and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Charge and the selection type is Last In First Out and the allocation type is By Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Distribution and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Charge and the selection type is Last In First Out and the allocation type is By Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   766 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @PriEqAllLifoAcAccount @severity=blocker
  Scenario: Allocate POS-CASH payment using entity level is Priority and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Charge and the selection type is Last In First Out and the allocation type is Across Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Priority and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Charge and the selection type is Last In First Out and the allocation type is Across Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   679 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @DisEqAllLifoAcAccount @severity=blocker
  Scenario: Allocate POS-CASH payment using entity level is Distribution and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Charge and the selection type is Last In First Out and the allocation type is Across Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Distribution and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Charge and the selection type is Last In First Out and the allocation type is Across Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   716 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @PriEqAllFifoByAccount @severity=normal
  Scenario: Allocate POS-CASH payment using entity level is Priority and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Charge and the selection type is First In First Out and the allocation type is By Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Priority and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Charge and the selection type is First In First Out and the allocation type is By Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   680 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @DisEqAllFifoByAccount
  Scenario: Allocate POS-CASH payment using entity level is Distribution and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Charge and the selection type is First In First Out and the allocation type is By Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Distribution and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Charge and the selection type is First In First Out and the allocation type is By Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   765 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @PriEqAllFifoAcAccount
  Scenario: Allocate POS-CASH payment using entity level is Priority and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Charge and the selection type is First In First Out and the allocation type is Across Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Priority and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Charge and the selection type is First In First Out and the allocation type is Across Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   776 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @DisEqAllFifoAcAccount
  Scenario: Allocate POS-CASH payment using entity level is Distribution and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Charge and the selection type is First In First Out and the allocation type is Across Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Distribution and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Charge and the selection type is First In First Out and the allocation type is Across Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   777 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @PriEqAllMinByAccount
  Scenario: Allocate POS-CASH payment using entity level is Priority and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Charge and the selection type is Minimum Balance and the allocation type is By Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Priority and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Charge and the selection type is Minimum Balance and the allocation type is By Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   682 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @DisEqAllMinByAccount
  Scenario: Allocate POS-CASH payment using entity level is Distribution and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Charge and the selection type is Minimum Balance and the allocation type is By Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Distribution and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Charge and the selection type is Minimum Balance and the allocation type is By Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   769 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @PriEqAllMinAcAccount
  Scenario: Allocate POS-CASH payment using entity level is Priority and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Charge and the selection type is Minimum Balance and the allocation type is Across Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Priority and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Charge and the selection type is Minimum Balance and the allocation type is Across Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   681 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @DisEqAllMinAcAccount
  Scenario: Allocate POS-CASH payment using entity level is Distribution and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Charge and the selection type is Minimum Balance and the allocation type is Across Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Distribution and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Charge and the selection type is Minimum Balance and the allocation type is Across Account
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   767 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @PriEqAllMaxByAccount
  Scenario: Allocate POS-CASH payment using entity level is Priority and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Charge and the selection type is Maximum Balance and the allocation type is By Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Priority and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Charge and the selection type is Maximum Balance and the allocation type is By Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   725 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @DisEqAllMaxByAccount
  Scenario: Allocate POS-CASH payment using entity level is Distribution and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Charge and the selection type is Maximum Balance and the allocation type is By Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Distribution and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Charge and the selection type is Maximum Balance and the allocation type is By Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   770 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @PriEqAllMaxAcAccount
  Scenario: Allocate POS-CASH payment using entity level is Priority and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Charge and the selection type is Maximum Balance and the allocation type is Across Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Priority and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Charge and the selection type is Maximum Balance and the allocation type is Across Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   728 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @DisEqAllMaxAcAccount
  Scenario: Allocate POS-CASH payment using entity level is Distribution and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Charge and the selection type is Maximum Balance and the allocation type is Across Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Distribution and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Charge and the selection type is Maximum Balance and the allocation type is Across Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   768 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @PriEqAllAccount
  Scenario: Allocate POS-CASH payment using entity level is Priority and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Priority and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   726 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @DisEqAllAccount
  Scenario: Allocate POS-CASH payment using entity level is Distribution and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Distribution and the allocation based on Distribution and distribution type is Equal Allocation and distribution by Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   723 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @PriLifoByAccount
  Scenario: Allocate POS-CASH payment using entity level is Priority and the allocation based on priority and the selection type is Last In First Out and the allocation type is ByAccount rule
    Given Allocate payment with payment type as POS-CASH using entity level is Priority and the allocation based on priority and the selection type is Last In First Out and the allocation type is ByAccount rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   686 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @DisLifoByAccount
  Scenario: Allocate POS-CASH payment using entity level is Distribution and the allocation based on Priority and the selection type is Last In First Out and the allocation type is By Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Distribution and the allocation based on Priority and the selection type is Last In First Out and the allocation type is By Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   735 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @PriLifoAcAccount
  Scenario: Allocate POS-CASH payment using entity level is Priority and the allocation based on Priority and the selection type is Last In First Out and the allocation type is Across Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Priority and the allocation based on Priority and the selection type is Last In First Out and the allocation type is Across Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   683 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @DisLifoAcAccount
  Scenario: Allocate POS-CASH payment using entity level is Distribution and the allocation based on Priority and the selection type is Last In First Out and the allocation type is Across Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Distribution and the allocation based on Priority and the selection type is Last In First Out and the allocation type is Across Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   771 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @PriFifoByAccount
  Scenario: Allocate POS-CASH payment using entity level is Priority and the allocation based on priority and the selection type is First In First Out and the allocation type is ByAccount rule
    Given Allocate payment with payment type as POS-CASH using entity level is Priority and the allocation based on priority and the selection type is First In First Out and the allocation type is ByAccount rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   711 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @DisFifoByAccount
  Scenario: Allocate POS-CASH payment using entity level is Distribution and the allocation based on Priority and the selection type is First In First Out and the allocation type is By Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Distribution and the allocation based on Priority and the selection type is First In First Out and the allocation type is By Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   774 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @PriFifoAcAccount
  Scenario: Allocate POS-CASH payment using entity level is Priority and the allocation based on Priority and the selection type is First In First Out and the allocation type is Across Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Priority and the allocation based on Priority and the selection type is First In First Out and the allocation type is Across Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   732 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @DisFifoAcAccount
  Scenario: Allocate POS-CASH payment using entity level is Distribution and the allocation based on Priority and the selection type is First In First Out and the allocation type is Across Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Distribution and the allocation based on Priority and the selection type is First In First Out and the allocation type is Across Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   724 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @PriMinByAccount
  Scenario: Allocate POS-CASH payment using entity level is Priority and the allocation based on Priority and the selection type is Minimum Balance and the allocation type is By Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Priority and the allocation based on Priority and the selection type is Minimum Balance and the allocation type is By Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   775 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @DisMinByAccount
  Scenario: Allocate POS-CASH payment using entity level is Distribution and the allocation based on Priority and the selection type is Minimum Balance and the allocation type is By Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Distribution and the allocation based on Priority and the selection type is Minimum Balance and the allocation type is By Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   730 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @PriMinAcAccount
  Scenario: Allocate POS-CASH payment using entity level is Priority and the allocation based on Priority and the selection type is Minimum Balance and the allocation type is Across Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Priority and the allocation based on Priority and the selection type is Minimum Balance and the allocation type is Across Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   727 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @DisMinAcAccount
  Scenario: Allocate POS-CASH payment using entity level is Distribution and the allocation based on Priority and the selection type is Minimum Balance and the allocation type is Across Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Distribution and the allocation based on Priority and the selection type is Minimum Balance and the allocation type is Across Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   772 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @PriMaxByAccount
  Scenario: Allocate POS-CASH payment using entity level is Priority and the allocation based on Priority and the selection type is Maximum Balance and the allocation type is By Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Priority and the allocation based on Priority and the selection type is Maximum Balance and the allocation type is By Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   688 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @DisMaxByAccount
  Scenario: Allocate POS-CASH payment using entity level is Distribution and the allocation based on Priority and the selection type is Maximum Balance and the allocation type is By Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Distribution and the allocation based on Priority and the selection type is Maximum Balance and the allocation type is By Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   731 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @PriMaxAcAccount
  Scenario: Allocate POS-CASH payment using entity level is Priority and the allocation based on Priority and the selection type is Maximum Balance and the allocation type is Across Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Priority and the allocation based on Priority and the selection type is Maximum Balance and the allocation type is Across Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   684 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @DisMaxAcAccount
  Scenario: Allocate POS-CASH payment using entity level is Distribution and the allocation based on Priority and the selection type is Maximum Balance and the allocation type is Across Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Distribution and the allocation based on Priority and the selection type is Maximum Balance and the allocation type is Across Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   773 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @PriProChargeLifoByAccount
  Scenario: Allocate POS-CASH payment using entity level is Priority and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is Last In First Out and the allocation type is By Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Priority and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is Last In First Out and the allocation type is By Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   718 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @DisProChargeLifoByAccount
  Scenario: Allocate POS-CASH payment using entity level is Distribution and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is Last In First Out and the allocation type is By Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Distribution and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is Last In First Out and the allocation type is By Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   710 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @PriProChargeLifoAcAccount
  Scenario: Allocate POS-CASH payment using entity level is Priority and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is Last In First Out and the allocation type is Across Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Priority and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is Last In First Out and the allocation type is Across Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   675 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @DisProChargeLifoAcAccount
  Scenario: Allocate POS-CASH payment using entity level is Distribution and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is Last In First Out and the allocation type is Across Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Distribution and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is Last In First Out and the allocation type is Across Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   708 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @PriProChargeFifoByAccount
  Scenario: Allocate POS-CASH payment using entity level is Priority and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is First In First Out and the allocation type is By Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Priority and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is First In First Out and the allocation type is By Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   709 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @DisProChargeFifoByAccount
  Scenario: Allocate POS-CASH payment using entity level is Distribution and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is First In First Out and the allocation type is By Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Distribution and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is First In First Out and the allocation type is By Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   676 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @PriProChargeFifoAcAccount
  Scenario: Allocate POS-CASH payment using entity level is Priority and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is First In First Out and the allocation type is Across Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Priority and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is First In First Out and the allocation type is Across Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   339 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @DisProChargeFifoAcAccount
  Scenario: Allocate POS-CASH payment using entity level is Distribution and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is First In First Out and the allocation type is Across Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Distribution and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is First In First Out and the allocation type is Across Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   707 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @PriProChargeMaxByAccount
  Scenario: Allocate POS-CASH payment using entity level is Priority and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is Maximum Balance and the allocation type is By Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Priority and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is Maximum Balance and the allocation type is By Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   719 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @DisProChargeMaxByAccount
  Scenario: Allocate POS-CASH payment using entity level is Distribution and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is Maximum Balance and the allocation type is By Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Distribution and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is Maximum Balance and the allocation type is By Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   712 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @PriProChargeMaxAcAccount
  Scenario: Allocate POS-CASH payment using entity level is Priority and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is Maximum Balance and the allocation type is Across Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Priority and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is Maximum Balance and the allocation type is Across Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   720 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @DisProChargeMaxAcAccount
  Scenario: Allocate POS-CASH payment using entity level is Distribution and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is Maximum Balance and the allocation type is Across Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Distribution and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is Maximum Balance and the allocation type is Across Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   714 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @PriProChargeMinByAccount
  Scenario: Allocate POS-CASH payment using entity level is Priority and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is Minimum Balance and the allocation type is By Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Priority and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is Minimum Balance and the allocation type is By Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   677 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @DisProChargeMinByAccount
  Scenario: Allocate POS-CASH payment using entity level is Distribution and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is Minimum Balance and the allocation type is By Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Distribution and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is Minimum Balance and the allocation type is By Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   778 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @PriProChargeMinAcAccount
  Scenario: Allocate POS-CASH payment using entity level is Priority and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is Minimum Balance and the allocation type is Across Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Priority and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is Minimum Balance and the allocation type is Across Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   713 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @DisProChargeMinAcAccount
  Scenario: Allocate POS-CASH payment using entity level is Distribution and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is Minimum Balance and the allocation type is Across Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Distribution and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is Minimum Balance and the allocation type is Across Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   678 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @PriProAccount
  Scenario: Allocate POS-CASH payment using entity level is Priority and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Priority and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   717 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @DisProAccount
  Scenario: Allocate POS-CASH payment using entity level is Distribution and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Account rule
    Given Allocate payment with payment type as POS-CASH using entity level is Distribution and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   702 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @AgencyDefault_PriPropFifoAcAccount
  Scenario: Allocate POS-CASH payment using agency default rule entity level is Priority and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is First In First Out and the allocation type is Across Account rule
    Given Allocate payment with payment type as POS-CASH using agnecy default rule entity level is Priority and the allocation based on Distribution and distribution type is Proportionate adjustment and distribution by Charge and the selection type is First In First Out and the allocation type is Across Account rule
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |     0 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @PriFifoByAccount_CH
  Scenario: Allocate CH payment using rule with entity level Priority and allocation based on priority and selection type First In First Out and allocation type ByAccount
    Given Allocate CH payment using rule with entity level Priority and allocation based on priority and selection type First In First Out and allocation type ByAccount
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   711 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @PriLifoByAccount_POS-MO
  Scenario: Allocate POS-MO payment using rule with entity level Priority and allocation based on priority and selection type Last In First Out and allocation type ByAccount
    Given Allocate POS-MO payment using rule with entity level Priority and allocation based on priority and selection type Last In First Out and allocation type ByAccount
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   686 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @PriFifoByAccount_POS-CHECK
  Scenario: Allocate POS-CHECK payment using rule with entity level Priority and allocation based on priority and selection type First In First Out and allocation type ByAccount
    Given Allocate POS-CHECK payment using rule with entity level Priority and allocation based on priority and selection type First In First Out and allocation type ByAccount
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   711 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @PriLifoByAccount_CA
  Scenario: Allocate CA payment using rule with entity level Priority and allocation based on priority and selection type Last In First Out and allocation type ByAccount
    Given Allocate CA payment using rule with entity level Priority and allocation based on priority and selection type Last In First Out and allocation type ByAccount
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   686 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @PriFifoByAccount_MO
  Scenario: Allocate MO payment using rule with entity level Priority and allocation based on priority and selection type First In First Out and allocation type ByAccount
    Given Allocate MO payment using rule with entity level Priority and allocation based on priority and selection type First In First Out and allocation type ByAccount
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   711 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype
      | statusBoolean | true |

  @PriEqAllChargeLifoByAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Equal Allocation and distribution by Charge and selection type Last In First Out and allocation type ByAccount
    Given Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Equal Allocation and distribution by Charge and selection type Last In First Out and allocation type ByAccount
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   722 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @DisEqAllChargeLifoByAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Equal Allocation and distribution by Charge and selection type Last In First Out and allocation type ByAccount
    Given Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Equal Allocation and distribution by Charge and selection type Last In First Out and allocation type ByAccount
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   766 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @PriEqAllChargeLifoAcAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Equal Allocation and distribution by Charge and selection type Last In First Out and allocation type Across Account
    Given Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Equal Allocation and distribution by Charge and selection type Last In First Out and allocation type Across Account
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   679 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @DisEqAllChargeLifoAcAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Equal Allocation and distribution by Charge and selection type Last In First Out and allocation type Across Account
    Given Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Equal Allocation and distribution by Charge and selection type Last In First Out and allocation type Across Account
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   716 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @PriEqAllChargeFifoByAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Equal Allocation and distribution by Charge and selection type First In First Out and allocation type ByAccount
    Given Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Equal Allocation and distribution by Charge and selection type Last In First Out and allocation type ByAccount
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   680 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @DisEqAllChargeFifoByAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Equal Allocation and distribution by Charge and selection type First In First Out and allocation type ByAccount
    Given Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Equal Allocation and distribution by Charge and selection type First In First Out and allocation type ByAccount
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   765 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @PriEqAllChargeFifoAcAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Equal Allocation and distribution by Charge and selection type First In First Out and allocation type Across Account
    Given Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Equal Allocation and distribution by Charge and selection type First In First Out and allocation type Across Account
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   766 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @DisEqAllChargeFifoAcAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Equal Allocation and distribution by Charge and selection type First In First Out and allocation type Across Account
    Given Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Equal Allocation and distribution by Charge and selection type First In First Out and allocation type ByAccount
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   777 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @PriEqAllChargeMaxByAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Equal Allocation and distribution by Charge and selection type Maximum Balance and allocation type ByAccount
    Given Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Equal Allocation and distribution by Charge and selection type Maximum Balance and allocation type ByAccount
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   725 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @DisEqAllChargeMaxByAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Equal Allocation and distribution by Charge and selection type Maximum Balance and allocation type ByAccount
    Given Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Equal Allocation and distribution by Charge and selection type Maximum Balance and allocation type ByAccount
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   770 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @PriEqAllChargeMaxAcAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Equal Allocation and distribution by Charge and selection type Maximum Balance and allocation type Across Account
    Given Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Equal Allocation and distribution by Charge and selection type Maximum Balance and allocation type Across Account
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   728 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @DisEqAllChargeMaxAcAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Equal Allocation and distribution by Charge and selection type Maximum Balance and allocation type Across Account
    Given Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Equal Allocation and distribution by Charge and selection type Maximum Balance and allocation type Across Account
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   768 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @PriEqAllChargeMinByAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Equal Allocation and distribution by Charge and selection type Minimum Balance and allocation type ByAccount
    Given Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Equal Allocation and distribution by Charge and selection type Minimum Balance and allocation type ByAccount
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   682 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @DisEqAllChargeMinByAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Equal Allocation and distribution by Charge and selection type Minimum Balance and allocation type ByAccount
    Given Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Equal Allocation and distribution by Charge and selection type Minimum Balance and allocation type ByAccount
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   769 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @PriEqAllChargeMinAcAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Equal Allocation and distribution by Charge and selection type Minimum Balance and allocation type ByAccount
    Given Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Equal Allocation and distribution by Charge and selection type Minimum Balance and allocation type ByAccount
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   681 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @DisEqAllChargeMinAcAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Equal Allocation and distribution by Charge and selection type Minimum Balance and allocation type Across Account
    Given Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Equal Allocation and distribution by Charge and selection type Minimum Balance and allocation type Across Account
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   767 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @PriEqAllAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Equal Allocation and distribution by Account
    Given Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Equal Allocation and distribution by Account
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   726 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @DisEqAllAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Equal Allocation and distribution by Account
    Given Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Equal Allocation and distribution by Account
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   723 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @PriProChargeLifoByAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Charge and selection type Last In First Out and allocation type ByAccount
    Given Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Charge and selection type Last In First Out and allocation type ByAccount
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   718 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @DisProChargeLifoByAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Charge and selection type Last In First Out and allocation type ByAccount
    Given Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Charge and selection type Last In First Out and allocation type ByAccount
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   710 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @PriProChargeLifoAcAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Charge and selection type Last In First Out and allocation type Across Account
    Given Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Charge and selection type Last In First Out and allocation type Across Account
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   675 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @DisProChargeLifoAcAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Charge and selection type Last In First Out and allocation type Across Account
    Given Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Charge and selection type Last In First Out and allocation type Across Account
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   708 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @PriProChargeFifoByAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Charge and selection type First In First Out and allocation type ByAccount
    Given Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Charge and selection type First In First Out and allocation type ByAccount
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   709 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @DisProChargeFifoByAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Charge and selection type First In First Out and allocation type ByAccount
    Given Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Charge and selection type First In First Out and allocation type ByAccount
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   676 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @PriProChargeFifoAcAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Charge and selection type First In First Out and allocation type Across Account
    Given Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Charge and selection type First In First Out and allocation type Across Account
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   339 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @DisProChargeFifoAcAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Charge and selection type First In First Out and allocation type Across Account
    Given Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Charge and selection type First In First Out and allocation type Across Account
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   707 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @PriProChargeMaxByAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Charge and selection type Maximum Balance and allocation type ByAccount
    Given Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Charge and selection type Maximum Balance and allocation type ByAccount
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   719 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @DisProChargeMaxByAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Charge and selection type Maximum Balance and allocation type ByAccount
    Given Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Charge and selection type Maximum Balance and allocation type ByAccount
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   712 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @PriProChargeMaxAcAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Charge and selection type Maximum Balance and allocation type Across Account
    Given Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Charge and selection type Maximum Balance and allocation type Across Account
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   720 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @DisProChargeMaxAcAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Charge and selection type Maximum Balance and allocation type Across Account
    Given Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Charge and selection type Maximum Balance and allocation type Across Account
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   714 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @PriProChargeMinByAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Charge and selection type Minimum Balance and allocation type ByAccount
    Given Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Charge and selection type Minimum Balance and allocation type ByAccount
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   677 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @DisProChargeMinByAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Charge and selection type Minimum Balance and allocation type ByAccount
    Given Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Charge and selection type Minimum Balance and allocation type ByAccount
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   778 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @PriProChargeMinAcAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Charge and selection type Minimum Balance and allocation type Across Account
    Given Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Charge and selection type Minimum Balance and allocation type Across Account
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   713 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @DisProChargeMinAcAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Charge and selection type Minimum Balance and allocation type Across Account
    Given Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Charge and selection type Minimum Balance and allocation type Across Account
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   678 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @PriProAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Account
    Given Allocate Direct Payment using rule with entity level Priority and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Account
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   717 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @DisProAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Account
    Given Allocate Direct Payment using rule with entity level Distribution and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Account
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   702 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @PriLifoByAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Priority and allocation based on Priority and selection type Last In First Out and allocation type ByAccount
    Given Allocate Direct Payment using rule with entity level Priority and allocation based on Priority and selection type Last In First Out and allocation type ByAccount
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   686 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @DisLifoByAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Distribution and allocation based on Priority and selection type Last In First Out and allocation type ByAccount
    Given Allocate Direct Payment using rule with entity level Distribution and allocation based on Priority and selection type Last In First Out and allocation type ByAccount
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   735 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @PriLifoAcAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Priority and allocation based on Priority and selection type Last In First Out and allocation type Across Account
    Given Allocate Direct Payment using rule with entity level Priority and allocation based on Priority and selection type Last In First Out and allocation type Across Account
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   683 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @DisLifoAcAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Distribution and allocation based on Priority and selection type Last In First Out and allocation type Across Account
    Given Allocate Direct Payment using rule with entity level Distribution and allocation based on Priority and selection type Last In First Out and allocation type Across Account
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   771 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @PriFifoByAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Priority and allocation based on Priority and selection type First In First Out and allocation type ByAccount
    Given Allocate Direct Payment using rule with entity level Priority and allocation based on Priority and selection type First In First Out and allocation type ByAccount
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   711 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @DisFifoByAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Distribution and allocation based on Priority and selection type First In First Out and allocation type ByAccount
    Given Allocate Direct Payment using rule with entity level Distribution and allocation based on Priority and selection type First In First Out and allocation type ByAccount
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   774 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @PriFifoAcAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Priority and allocation based on Priority and selection type First In First Out and allocation type Across Account
    Given Allocate Direct Payment using rule with entity level Priority and allocation based on Priority and selection type First In First Out and allocation type Across Account
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   732 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @DisFifoAcAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Distribution and allocation based on Priority and selection type First In First Out and allocation type Across Account
    Given Allocate Direct Payment using rule with entity level Distribution and allocation based on Priority and selection type First In First Out and allocation type Across Account
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   724 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @PriMinByAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Priority and allocation based on Priority and selection type Minimum Balance and allocation type ByAccount
    Given Allocate Direct Payment using rule with entity level Priority and allocation based on Priority and selection type Minimum Balance and allocation type ByAccount
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   775 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @DisMinByAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Distribution and allocation based on Priority and selection type Minimum Balance and allocation type ByAccount
    Given Allocate Direct Payment using rule with entity level Distribution and allocation based on Priority and selection type Minimum Balance and allocation type ByAccount
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   730 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @PriMinAcAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Priority and allocation based on Priority and selection type Minimum Balance and allocation type Across Account
    Given Allocate Direct Payment using rule with entity level Priority and allocation based on Priority and selection type Minimum Balance and allocation type Across Account
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   727 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @DisMinAcAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Distribution and allocation based on Priority and selection type Minimum Balance and allocation type Across Account
    Given Allocate Direct Payment using rule with entity level Distribution and allocation based on Priority and selection type Minimum Balance and allocation type Across Account
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   772 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @PriMaxByAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Priority and allocation based on Priority and selection type Maximum Balance and allocation type ByAccount
    Given Allocate Direct Payment using rule with entity level Priority and allocation based on Priority and selection type Maximum Balance and allocation type ByAccount
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   688 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @DisMaxByAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Distribution and allocation based on Priority and selection type Maximum Balance and allocation type ByAccount
    Given Allocate Direct Payment using rule with entity level Distribution and allocation based on Priority and selection type Maximum Balance and allocation type ByAccount
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   713 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @PriMaxAcAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Priority and allocation based on Priority and selection type Maximum Balance and allocation type Across Account
    Given Allocate Direct Payment using rule with entity level Priority and allocation based on Priority and selection type Maximum Balance and allocation type Across Account
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   684 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @DisMaxAcAccount_DirectPayment
  Scenario: Allocate Direct Payment using rule with entity level Distribution and allocation based on Priority and selection type Maximum Balance and allocation type Across Account
    Given Allocate Direct Payment using rule with entity level Distribution and allocation based on Priority and selection type Maximum Balance and allocation type Across Account
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |   773 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  @DpDefault_PriProAccount
  Scenario: Allocate Direct Payment using dp default rule with entity level Distribution and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Account
    Given Allocate Direct Payment using dp default rule with entity level Distribution and allocation based on Distribution and distribution type Proportionate Adjustment and distribution by Account
      | overRideStatusFlag   | false |
      | entityType           |   274 |
      | ruleSetUp            |     0 |
      | commissionCalTypeRef |   710 |
    Then I should get the collections as a returntype for DirectPayments
      | statusBoolean | true |

  #@PaymentNotFound
  #Scenario: Allocate the payment with invalid payment data
  #	Given Allocate payment with payment data as null
  #		|overRideStatusFlag|false|
  #	Then I should get the error message
  #		|		remarks	   |PaymentId Not Found:null|
  #		|	statusBoolean  |false|
  @AccNotFoundOrPaymentRestriction
  Scenario: Allocate the payment with invalid PaymentTransaction data
    Given Allocate payment with PaymentTransaction data as null
      | overRideStatusFlag | false |
    Then I should get the error message
      | remarks       | Account details not found with selected accounts or account status code have payment restriction |
      | statusBoolean | false                                                                                            |

  @CommissionSchemeCodeNotAvailable
  Scenario: Allocate the payment with invalid schemecode
    Given Allocate payment with schemecode as null
      | overRideStatusFlag | false |
    Then I should get the error message
      | remarks       | Commission scheme not available in account,client or default |
      | statusBoolean | false                                                        |

  @ClientLevelRestriction
  Scenario: Check client level resctricted payment
    Given Check whether the payment is resctricted at client level
      | overRideStatusFlag | false |
    Then I should get the error message
      | remarks       | 2713 type payment's resctricted at client level |
      | statusBoolean | false                                           |

  @CommissionSchemeDetailsNotAvailable
  Scenario: Allocate the payment with invalid scheme details
    Given Allocate payment with scheme details as null
      | overRideStatusFlag | false |
    Then I should get the error message
      | remarks       | Commisssion Scheme Details Not Available For  : #44 |
      | statusBoolean | false                                               |

  @EntityLevelLogicNotDefined
  Scenario: Allocate Payment with invalid entity type
    Given Allocate payment with entity level logic not defined
      | overRideStatusFlag | false  |
      | entityType         |      0 |
      | calculatedValue    |  150.0 |
      | paymentType        | agency |
    Then I should get the error message
      | remarks       | Allocation Entity Level Logic not defined |
      | statusBoolean | false                                     |

  @RuleNotFoundForClient
  Scenario: Allocate Payment with invalid rule setup details
    Given Allocate payment with rule settings not found for client
      | overRideStatusFlag | false |
      | calculatedValue    | 150.0 |
    Then I should get the error message
      | remarks       | Allocation Rule Settings Not Found For Client:20695 |
      | statusBoolean | false                                               |

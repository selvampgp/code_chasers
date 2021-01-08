@AgencychargesBreakup
Feature: The activities donewhen the consumer made a payment through check and the check bounces

  Background: Creation of agency breakup charges deatils
    Given Get all agency breakup charges deatils
      | agencyId | accountIdRef | paymentIdRef | paymentId | agnecyAmount | chargeTypeRef | creadedDate | status |
      |      683 |      7388046 |      9628955 |   9628955 |        17.08 |           521 | 09/01/2017  | Y      |

  Scenario: Fetch Agency breakup charges by Invalid Id
    When User Need to Fetch Agency breakup charges details by Invalid Id
      | agencyId | 2607 |
    Then I Should be Able to Fetch The Details Of Agency breakup charges details by Invalid Id

  Scenario: Create new Single Agency breakup charges records with Valid data
    When User Need to create a new Single Agency breakup charges records with Valid data
      | accountIdRef | paymentIdRef | paymentId | agnecyAmount | chargeTypeRef | creadedDate | status |
      |      7388046 |      9628955 |  26072640 |        17.08 |           521 | 09/01/2017  | Y      |
    Then I Should be Able to view created Single Agency breakup charges records with Valid data
      | accountIdRef  |    7388046 |
      | paymentIdRef  |    9628955 |
      | paymentId     |   26072640 |
      | agencyAmount  |      17.08 |
      | chargeTypeRef |        521 |
      | creadedDate   | 09/01/2017 |
      | status        | Y          |

  Scenario: Create new Single Agency breakup charges records with InValid data
    When User Need to create a new Single Agency breakup charges records with InValid data
      | agencyId      |    0 |
      | accountIdRef  |    0 |
      | paymentIdRef  |    0 |
      | paymentId     |    0 |
      | agencyAmount  | 0.00 |
      | chargeTypeRef |    0 |
      | creadedDate   |    0 |
      | status        |    0 |
    Then I Should be Able to view created Single Agency breakup charges records with InValid data

  Scenario: Create new List of Agency breakup charges records with Valid data
    When User Need to create a new List of Agency breakup charges records with Valid data
      | accountIdRef | paymentIdRef | paymentId | agnecyAmount | chargeTypeRef | creadedDate | status |
      |      7388046 |      9628955 |   9628955 |        17.08 |           521 | 09/01/2017  | Y      |
      |      7388068 |     16521943 |  16521943 |         1.00 |           521 | 10/17/2017  | Y      |
    Then I Should be Able to view created List of Agency breakup charges records with Valid data
      | accountIdRef | paymentIdRef | paymentId | agnecyAmount | chargeTypeRef | creadedDate | status |
      |      7388046 |      9628955 |   9628955 |        17.08 |           521 | 09/01/2017  | Y      |
      |      7388068 |     16521943 |  16521943 |         1.00 |           521 | 10/17/2017  | Y      |

  Scenario: Create new List of Agency breakup charges records with InValid data
    When User Need to create a new List of Agency breakup charges records with InValid data
      | agencyId      |    0 |
      | accountIdRef  |    0 |
      | paymentIdRef  |    0 |
      | paymentId     |    0 |
      | agencyAmount  | 0.00 |
      | chargeTypeRef |    0 |
      | creadedDate   |    0 |
      | status        |    0 |
    Then I Should be Able to view created List of Agency breakup charges records with InValid data

  Scenario: Update Existing  Agency breakup charges records with InValid accountid and paidAmount data

    When User Need to Update Existing Agency breakup charges records with accountid with InValid Data
      | agencyId | 0 |
    And User Need to Update Existing Agency breakup charges records with PaidAmount with Valid Data
      | PaidAmount | 0.00 |
    Then I Should be Able to view Updated Existing Agency breakup charges records with accountid and PaidAmount with InValid Data

  Scenario: Update Existing  Agency breakup charges Status with Valid AgencyId
    When User Need to Update Existing Agency breakup charges Status with Valid AgencyId
      | agencyId | 683 |
      | status   | Y   |
    Then I Should be Able to view Updated Existing Agency breakup Status records with Valid AgencyId

  Scenario: Update Existing  Agency breakup charges Status with InValid AgencyId
    When User Need to Update Existing Agency breakup charges Status with InValid AgencyId
      | agencyId | 0 |
      | status   | Y |
    Then I Should be Able to view Updated Existing Agency breakup Status records with InValid AgencyId

  Scenario: Fetch List of HashMap contains agency charges details for legal with Valid SuitId

    When User Need to Fetch List of HashMap contains agency charges details for legal with Valid SuitId
      | suitId | 12 |
    Then I should be able to view List of HashMap contains agency charges details with Valid SuitId
      | agencyId     |       1937 |
      | paymentIdRef |   26072640 |
      | paymentId    |   26072640 |
      | agencyAmount |     100.00 |
      | creadedDate  | 11/09/2017 |

  Scenario: Fetch List of HashMap contains agency charges details for legal with InValid SuitId

    When User Need to Fetch List of HashMap contains agency charges details for legal with InValid SuitId
      | suitId | 0 |
    Then I should be able to view List of HashMap contains agency charges details with InValid SuitId

  Scenario: Fetch pre interest details with List of JudgmentIds
    When User Need Fetch pre interest details with List of JudgmentIds
      | judgementId | 21 |
    Then I Should be Able to view pre interest details
      | agencyId | accountIdRef | paymentIdRef | paymentId | agnecyAmount | chargeTypeRef | creadedDate | status |
      |      683 |      7388046 |      9628955 |   9628955 |        17.08 |           521 | 09/01/2017  | Y      |

  Scenario: update agencyCharges status by chargeType and agencyId with valid data
    When User Need to update agencyCharges status by chargeType and agencyId with Valid Data
      | agencyId   | 683,684 |
      | chargeType |     521 |
    Then I Should be Able to update agencyCharges status by chargeType and agencyId with Valid Data

  Scenario: update agencyCharges status by chargeType and agencyId with InValid data

    When User Need to update agencyCharges status by chargeType and agencyId with InValid Data
      | agencyId   | 0 |
      | chargeType | 0 |
    Then I Should be Able to update agencyCharges status by chargeType and agencyId with InValid Data

  Scenario: Fetch all the break up charges applied on Agency control account with Valid data

    When User Need to fetch all the break up charges applied on Agency control account with Valid data
      | suitId | 78 |
    Then I Should be Able to return List of AgencychargesBreakup with valid data
      | agencyId      |        683 |
      | accountIdRef  |    7388046 |
      | paymentIdRef  |    9628955 |
      | paymentId     |    9628955 |
      | agencyAmount  |      17.08 |
      | chargeTypeRef |        521 |
      | creadedDate   | 09/01/2017 |
      | status        | Y          |

  Scenario: Fetch all the break up charges applied on Agency control account with InValid data

    When User Need to fetch all the break up charges applied on Agency control account with InValid data
      | suitId | 0 |
    Then I Should be Able to return List of AgencychargesBreakup with InValid data

  Scenario: Fetch Nsf Details by Using AgencyId with valid data
    When User Need to Fetch Nsf Details by Using AgencyId with Valid data
      | agencyId | 2607 |
    Then I Should be Able to return Nsf Details with valid data
      | accountnumber |   8194 |
      | agencyAmount  | 100.00 |
      | paymentIdRef  |        |
      | consumerID    |        |
      | clientId      |        |

  Scenario: Fetch Nsf Details by Using AgencyId with Invalid data
    When User Need to Fetch Nsf Details by Using AgencyId with Invalid data
      | agencyId | 0 |
    Then I Should be Able to return Nsf Details with Invalid data

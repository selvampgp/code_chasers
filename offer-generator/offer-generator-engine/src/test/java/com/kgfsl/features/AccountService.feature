@AccountService

Feature: To check all the methods of account service with valid and invalid unit testing data

@GetConsumerAllAccounts
Scenario Outline: Get existing account details against consumer id

Given an existing consumer id <consumerId>
When a cosumer need to get their account details
Then consumer should get their account details against consumer id

Examples:
               |   consumerId |
               |      23      |
               |      24      |
               |      33      |
               |      46      |

@GetConsumerPaymentHistory
Scenario Outline: Get consumer payment details against consumer id

Given an existing consumer id <consumerId>
When a cosumer need to get their payment details
Then consumer should get their payment details against account id

Examples:
               |   consumerId |
               |      23      |
               |      24      |
               |      33      |
               |      46      |
               
@GetGroupedAccountDetails
Scenario Outline: Get grouped account details against given account id

Given a account id <accountId>
When a cosumer need to get grouped accounts 
Then consumer should get their group account details against consumer id

Examples:
               |   accountId |
               |     4223    |
               |     5223    |
               |     7422    |
               |     8223    |

@GetLookUpValue
Scenario: Get general lookup value

Given a look up id
       | lookupId | 23  |
When we need to get general lookup value against given lookup id
Then the lookup value should be returned

@GetAccountDetails
Scenario Outline: Get account details against given account id

Given a account id <accountId>
When a cosumer need to get a particular account details
Then consumer should get thw  account details against account id

Examples:
               |   accountId |
               |     4223    |
               |     5223    |
               |     7422    |
               |     8223    |


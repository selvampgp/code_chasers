@LetterProcessService
Feature: To check all the methods in LetterProcessService with valid/invalid data

@ValidateLetter
Scenario Outline: Validate Letter

When user need to validate list of letters by <uniqueId>
Then validation process should be successful

Examples: 
      |  uniqueId  |
      |    435     |
      |    345     |
      
@ProcessExternalLetters
Scenario Outline: Process external letters

When we need to process external letters by giving letter <uniqueId>
Then it should process given ids letter

Examples: 
      |  uniqueId  |
      |    435     |
      |    345     |        
      
@CancelLetterRequest
 Scenario Outline: Cancel letter request
 
When we need to cancel letter by giving letter <uniqueId>
Then it should update cancellation status against given id

Examples: 
      |  uniqueId  |
      |    435     |
      |    345     | 
 
@ReRequestLetter
Scenario Outline: Re-request given letter

When we need to re-request list of letters by giving <id>, <consumerId>, <accountId>, <clientId>, <consumerAddressId>, <ltyLetterId>, <vendorId>, <batchId>, and <doNotPost>
Then it should send new letter request

Examples:
   |  id   |  consumerId   |  accountId  | clientId  |  consumerAddressId  |  ltyLetterId  |  vendorId  |  batchId  |  doNotPost  |
   |   3   |   2344        |    345      |    345    |     2343            |      4        |   44       |     32    |      N      |  
       
 
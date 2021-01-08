@LetterVendorService
Feature: To check all the letter request service with outline data

@CreateNewLetterDetail
Scenario Outline: Create a new letter request 

When user gives a new letter request with set of letter details <consumerId>, <accountId>, <clientId>, <consumerAddressId>, <remark>, <ltyLetterId> and <vendorId>
Then the requested data should be inserted into request table  

Examples:
 | consumerId |  accountId  |  clientId  |  consumerAddressId  |  remark  |  ltyLetterId  |  vendorId  |
 |     3      |    34       |    345     |     45              |   STV    |     45        |     43     |
 |     2      |    5        |    385     |      4              |   EXCEP  |     45        |     43     |
 |     3      |    34       |    345     |     45              |   CAN    |     45        |     42     |
 
@CancelLetter
Scenario Outline: Cancel letter request 

When user tries to cancel set of letter request using <uniqueid> and <remark>
Then the cancellation of letter request should be successful

Examples:
    |   uniqueid   |   remark  |
    |      5       |    CAN    |
    |              |    CAN    |
    
 @UpdateLetterRequest
 Scenario Outline: Update Letter Request
 
 When user gives <uniqueid>, <batchId> and <userId> to update letter details
 Then the given data should get updated against uniqueId
 
 Examples:
      |   uniqueid   |   batchId  |  userId  |
      |      4       |      4     |    32    |
      |      87      |      43    |    32    | 
     
 @FetchBatchId
 Scenario: Fetch max of batch id
 
 When user needs to retrieve max of batch id
 Then user should get batch id
 
 @ViewLetterResponse
 Scenario Outline: Fetch letter response details
 
 When user needs to view letter response by giving <requestId>
 Then response details should be retrieved against request id
 
 Examples:
           |   requestId   |
           |       45      |
           |     34554     |
           
 @UpdateDNSStatus 
 Scenario Outline: Update do not post status
 
 When user enable or disable DNS status by giving <uniqueid> and <status>
 Then the dns status should get updated against uniqueId
 
 Examples: 
        |  uniqueid  |  status  |
        |    345     |    true  |
        |     23     |    false |
        
 @FetchLetterRequest
 Scenario Outline: Fetch letter request details by id    
 
 When we need to get letter details against <uniqueId>
 Then the retrieval letter details should be sucessful
 
  Examples:
           |    uniqueId   |
           |       45      |
           |     34554     |
           
 @FetchPendingLetterIds
 Scenario Outline: Fetch pending letters by id
 
 When we need to fetch pending letter by <uniqueId> 
 Then it should fetch pending letter with STV status against unique id
 
 Examples:
           |    uniqueId   |
           |       45      |
           |     34554     |
 
@FetchInProgressLetters
Scenario Outline: Fetch in-progress letters with condition

When we need to fetch in-progress letters with condition <letterId>, <clientId>, <consumerId>, <statusCode> and <requestDate> 
Then it should retrieve in-progree letter against the given condition

Examples: 
        |  letterId  |  clientId  |  consumerId  |  statusCode  |  requestDate  |
        |       3    |     3      |       5      |      STV     |    2018-06-08 |
        
@FetchCompletedLetters
Scenario Outline: Fetch completed letters with condition

When we need to fetch completed letters with condition <fromDate>, <toDate> and <vendorId> 
Then it should retrieve completed letter against the given condition

Examples: 
         |   fromDate  |    toDate   |  vendorId  |
         | 2018-06-08  |  2018-06-08 |     5      |
         
  
@FetchCompletedLettersByBatch
Scenario Outline: Fetch completed letters by batch with condition

When we need to fetch completed letters by batch with condition <batch>, <processedDate>, <consumerId>, <letterId>, <clientId> and <statusCode>
Then it should retrieve completed letters by batch against the given condition

Examples: 
       | batch  | processedDate  | letterId  |  clientId  |  consumerId  |  statusCode  | 
       |    5   |  2018-06-08    |       3   |     3      |       5      |      STV     |   
        
@FetchExceptionLetters
Scenario Outline: Fetch exception and cancelled letters with condition

When we need to fetch exception and cancelled letters with condition <fromDate>, <toDate>, <letterId>, <clientId> and <statusCode>
Then it should retrieve exception and cancelled letter against the given condition

Examples: 
        |   fromDate  |    toDate   |  letterId  |  clientId  |  statusCode |
        | 2018-06-08  |  2018-06-08 |     5      |      55    |     STV     |
 
 @CreateNewVendor
 Scenario Outline: Create a vendor
 
 When we need to create a vendor by giving <vendorName>, <daysToSendAgain>, <chargesPerHit>, <allowRealTimeRequest>, <allowMultipleResponse>, <email>, <phone>, <address1>, <address2>, <city>, <state>, and <zipCode>
 Then vendor details should be saved 
 
 Examples: 
        |  vendorName  |  daysToSendAgain  |  chargesPerHit  |  allowRealTimeRequest  |  allowMultipleResponse  |      email     |  phone  |  address1  |  address2  |  city  |  state  |  zipCode  |
        |     test     |       3           |        4        |         Y              |          N              | test@mail.com  | 3242344 |    test    |   test     |    1   |    3    |  234545   |
       
 @FetchLetterListByCode
 Scenario Outline: Fetch letter details by its code
 
 When we need to fetch letter list by giving its code <letterCode>
 Then it should fetch letter details against letter code
 
 Examples:  
          | letterCode  |
          |     34      |
          
 @FetchVendorById
 Scenario Outline: Fetch vendor details by its unique id
 
 When we need to retrieve vendor details against <vendorId>
 Then it should retrieve vendor details
 
 Examples: 
          |  vendorId  |
          |    324     |
         
 @RemoveVendorChild
 Scenario Outline: Remove vendors all child entries
 
 When user want to delete vendors child entries by giving <vendorId>
 Then all the entries should be removed from the table
 
 Examples:
          |  vendorId  |
          |    324     |
          
 @FetchAllVendors
 Scenario:
 
 When we need to fetch all vendor details
 Then all the vendor details should be retrieved from the table 
           
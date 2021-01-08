@LetterResponseService
Feature: To check all the letter response service with outline data

@SaveLetterResponse
Scenario Outline: Save list of letter responses 

When user gives response for the letters with <lvpId>, <letterCode>, <address1>, <address2>, <returnReasonCode>, <clientCode>, <originalName>, <ncoaReturnMailAddressLine1>, <ncoaReturnMailAddressLine2>, and <appendedPhone1>
Then its should save response details and status in letter request

Examples: 
    |  lvpId  |  letterCode  |  address1  |  address2  |  returnReasonCode  |  clientCode  |  originalName  |  ncoaReturnMailAddressLine1  |  ncoaReturnMailAddressLine2  |  appendedPhone1  |
    |    43   |      4       |    test    |     test   |     success        |      34      |    test        |     testAddr1                |          testAddr2           |        23423     |
    |    45   |      3       |    test 1  |            |     success        |      34      |    test        |                              |          testAddr2           |        23423     |
    
 @StartLetterResponseWorkFlow
 Scenario: Start letter response workflow
 
 When we need to start letter response workflow
 Then it start the workflow with successful updation 
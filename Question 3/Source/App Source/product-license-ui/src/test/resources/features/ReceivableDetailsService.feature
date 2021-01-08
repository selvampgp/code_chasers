@ReceivableDetails
Feature: To check all the methods of receivable details with valid and invalid unit test data 

@ToBeAdjustInTrustForGross
Scenario: Fetch the list of receivable details by clientId
	Given As a user i am passing a clientId to get receivable details
		|clientId|20682|
	Then I should get the list of receivable details
	
@UpdateReceivableDetails
Scenario: Update the receivable details by valid data
	Given As a user i am passing a valid receivable data to update
	Then i should check the updated receivable details
	
@GetInvoiceToBeProcessedRecord
Scenario: Fetch the list of invoice details to be processed for corporate
	Given Process the invoice details for corporate
		|receivableId|2678|
		|corpName|20682V35EC|
		|receivedAmount|3.25|
		|iscorporatesummaryrequired|N|
		|clientIds|20682|
		|corporateId|20682|
		|corporateName|V35EC|
		|receivedDate|2018-05-31|
	Then i should get the list of invoice details for corporate
		|Fees_1|397|

@GetInvoiceToBeProcessedRecordForNonCorporate
Scenario: Fetch the list of invoice details to be processed for non corporate
	Given Process the invoice details for non corporate
		|receivableId|2678|
		|corpName|20682V35EC|
		|receivedAmount|3.25|
		|iscorporatesummaryrequired|N|
		|clientIds|20682|
		|corporateId|20682|
		|corporateName|V35EC|
		|receivedDate|2018-05-31|
	Then i should get the list of invoice details for non corporate
		|Fees_1|397|

@FindByInvoiceNumber
Scenario: Fetch receivable details by valid invoice and client Id
	Given As a user i am passing a valid invoice and client id
		|clientId|20682|
		|invoiceNumber|796|
	Then I should get the receivable details collection as return type
	
@UpdateStatus
Scenario: Update the status of receivable details and paymentReversalDetails by valid trust batch number
	Given As a user i am passing a valid trust batch number to update
		|trpBatchNumber|2342|
	Then I should update the status of receivable details and paymentReversalDetails
	
@GenerateInvoiceByUser
Scenario: Generate invoice details manually by valid clientId and amount
	Given As a user i am passing a valid clientId and amount
		|clientId|20682|
		|amount|100.0|
		|note|Test|
		|sequenceNumber|797|
		|description||
	Then I should generate the invoice details manually
	
@GenerateInvoice
Scenario: Generate invoice details by valid client and receivable details
	Given As a user i am passing a valid client and receivable details
		|fees402|0|
		|fees401|0|
		|rcvId|2720|
		|invoiceAmount|35.1|
		|fees398|0|
		|fees397|0|
		|fees400|0|
		|groupFlag|N|
		|fees399|0|
		|sequenceNumber|801|
	Then I should generate the invoice details
	
@GetReceivableDate
Scenario: Get the list of received dates by vali clientId
	Given As a user i am passing a valid clientId to get date
		|clientId|20682|
		|receivedDate|2018-05-31|
	Then I should get the list of received dates
	
@GetOutstandingByClientId
Scenario: Fetch the list of outstanding receivable details by valid clientId, date and trust Id
	Given As a user i am passing a valid clientId, date and trust Id to get outstanding amount
		|clientId|20682|
		|receivedDate|2018-05-31|
		|trustId|2342|
	Then I should get the list of receivable details
	
@GetAllAmountsByClientId
Scenario: Fetch the list of receivable details by valid clientId, date and trust Id
	Given As a user i am passing a valid clientId, date and trust Id to get all amount
		|clientId|20682|
		|receivedDate|2018-05-31|
		|trustId|2342|
	Then I should get the list of receivable details

@GetOutstandingDate
Scenario: Fetch the list of outstanding received date for particular client
	Given As a user i am passing a valid clientId to get outstanding date
		|clientId|20682|
		|receivedDate|2018-05-31|
	Then I should get the list of outstanding received dates
	
@FindAllForInvoice
Scenario: Fetch the list of invoice details by valid clientId, date and flag
	Given As a user i am passing a valid clientId, date and flag to get invoice
		|clientId|20682|
		|receivedDate|2018-05-31|
		|allFlag|1|
		|receivableId|2720|
		|dpFlag|N|
		|receivedAmount|150.0|
		|yetToPaid|160.6|
		|invoiceNumber|796|
		|paidAmounts|5.0|
	Then I should get the list of invoice details
	
@UpdateReceivedAmountForReceive
Scenario: Update the received amount in invoice details by valid receivable and clientId
	Given As a user i am passing a valid receivable and clientId to update
		|rcvId|2720|
		|clientId|20682|
		|amount|50.0|
		|type|0|
		|invoiceNumber|796|
		|pmtType|Check|
		|paidAmount|5.0|
		|receivedAmount|50.0|
		|receivedDate|06/06/2018|
	Then I should check the updated received amount in invoice details
		|rcvId|2720|
		
@UpdateStatusForReceive
Scenario: Update invoice status when the amount is fully paid
	Given As a user i am passing clientId, invoiceNumber and status
		|clientId|20682|
		|invoiceNumber|796|
		|status|475|
		|isReceived|Y|
	Then I should check the updated invoice status
		|clientId|20682|
		|invoiceNumber|796|
		|status|475|
		|isReceived|Y|
		
@UpdatePaidAmount
Scenario: Reverse the paid amount and update the amount in invoice details by valid receivable and clientId
	Given As a user i am passing a valid receivable and clientId to update to reverse 
		|rcvId|2720|
		|amount|50.0|
		|clientId|20682|
		|invoiceNumber|796|
		|pmtType|Check|
		|paidAmount|5.0|
		|receivedAmount|50.0|
		|receivedDate|06/06/2018|
	Then I should check the updated amount in invoice details
		|rcvId|2720|
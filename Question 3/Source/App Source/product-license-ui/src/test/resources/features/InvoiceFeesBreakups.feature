@InvoiceFeesBreakups
Feature: To check all the methods of invoice fees brekaup service with valid and invalid test data 

@findById
Scenario: Find invoice fees breakup details by id
	Given As a user i am passing valid id
		|ifbId|286|
	Then I should get the invoice breakup details for given id
		|ifbId	|invoiceNumber	|invoiceAmount	|pmtType	|invoiceNote|receiveBy	|reverseFlag|adjustedTrustId|
		|286	|		273		|	20.00		|	Check	|	test	|Elamaruthu	|		Y	|				|
	 	
@createSingle
Scenario: Create invoice fees breakup details
	Given As a user i verify creation of invoice fees breakup details
		|ifbId	|invoiceNumber	|invoiceAmount	|pmtType	|invoiceNote|receiveBy	|reverseFlag|adjustedTrustId|
		|286	|		273		|	20.00		|	Check	|	test	|Elamaruthu	|		Y	|				|
	 	
@createList
Scenario: Create a list of invoice fees breakup details
	Given As a user i verify creation of list of invoice fees breakup details
		|ifbId	|invoiceNumber	|invoiceAmount	|pmtType	|invoiceNote|receiveBy	|reverseFlag|adjustedTrustId|
		|286	|		273		|	20.00		|	Check	|	test	|Elamaruthu	|		Y	|				|
	 	|287	|		273		|	10.00		|	Check	|	test	|Elamaruthu	|		Y	|				|

@update
Scenario: Update invoice fees breakup details
	Given As a user i verify updating invoice fees breakup details
		|ifbId	|invoiceNumber	|invoiceAmount	|pmtType	|invoiceNote|receiveBy	|reverseFlag|adjustedTrustId|
		|286	|		273		|	20.00		|	Check	|	test	|Elamaruthu	|		Y	|				|

@delete
Scenario: Delete invoice fees breakup details by id
	Given As a user i verify deletion of invoice fees breakup details by id
		|ifbId|286|
		
@getAllByInoviceId
Scenario: Get all invoice fees breakup details by invoice id
	Given As a user i am passing valid invoice id
		|invoiceNumber|273|
	Then I should get the invoice fees breakup details for given id
		|ifbId	|invoiceNumber	|invoiceAmount	|pmtType	|invoiceNote|receiveBy	|reverseFlag|adjustedTrustId|
		|286	|		273		|	20.00		|	Check	|	test	|Elamaruthu	|		Y	|				|
	 	|287	|		273		|	10.00		|	Check	|	test	|Elamaruthu	|		Y	|				|
	
@breakupDetailsForReverse
Scenario: Get invoice details for reversal
	Given As a user i am passing list of invoice ids for reversal
		|invoiceNumber|273|
	Then I should get the details of reversed invoice details
		|ifbId	|invoiceNumber	|invoiceAmount	|pmtType	|invoiceNote|receiveBy	|reverseFlag|adjustedTrustId|
		|286	|		273		|	20.00		|	Check	|	test	|Elamaruthu	|		N	|				|
	 	|287	|		273		|	10.00		|	Check	|	test	|Elamaruthu	|		N	|				|

@getAllByInoviceIdReverse
Scenario: Get all invoice details which are reversed
	Given As a user i am passing valid invoice ids which are reversed
		|invoiceNumber|273|
	Then I should get the reversed invoice details
		|ifbId	|invoiceNumber	|invoiceAmount	|pmtType	|invoiceNote|receiveBy	|reverseFlag|adjustedTrustId|
		|286	|		273		|	20.00		|	Check	|	test	|Elamaruthu	|		N	|				|
	 	|287	|		273		|	10.00		|	Check	|	test	|Elamaruthu	|		N	|				|
	 	
@listOfAdjustedInvoiceDetails
Scenario: Get a list of adjusted invoice details
	Given As a user i am passing valid trust id to get invoice details
		|adjustedTrustId|1339|
	Then I should get the list of adjusted invoice details
		|ifbId	|invoiceNumber	|invoiceAmount	|pmtType	|invoiceNote								|receiveBy	|reverseFlag|adjustedTrustId|
		|381	|		491		|	19.72		|Adjustment	|Amount adjusted in Trust (Trust # : 1339) 	|Elamaruthu	|		N	|	1339		|
	 	|382	|		515		|	57.14		|Adjustment	|Amount adjusted in Trust (Trust # : 1339) 	|Elamaruthu	|		N	|	1339		|
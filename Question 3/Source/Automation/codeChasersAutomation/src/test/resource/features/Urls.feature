@Feature-UserLicense @UserLicense
Feature: Checkin the user license for application


@UL001 @login
Scenario: Login the application with valid username and password
Given Load the application
And Enter the valid username and password
|username|password|
|ela|1234|
When Clicking on submit button
Then System should login sucessfully 
And Clicking on logout button

@UL002 @login
Scenario: Login the application with in-valid username and password
Given Load the application
And Enter the in-valid username and password
|username|password|
|ela|123|
When Clicking on submit button
Then System should not login sucessfully 
	|ValidationMessage|
	|Bad credentials|
	
@UL003 @login
Scenario: While user try to create mutiple session but session count which defined in xml file are less means, 
		  system should not allow to create the mutiple session
Given Load the application
And Enter the valid username and password
|username|password|
|ela|1234|
And Clicking on submit button
And Load the application
And Enter the valid username and password
|username|password|
|ela|1234|
When Clicking on submit button
Then System should not login sucessfully 
	|ValidationMessage|
	|Maximum sessions of 1 for this user exceeded|
	
@UL004
Scenario: Check active user device, IP, OS, browser details
Given Load the application
And Enter the valid username and password
|username|password|
|ela|1234|
And Clicking on submit button
When Clicking on active session tab
Then System shows the user device, IP, OS, browser details
	|ExpectedDevice|ExpectedIP|ExpectedOS|ExpectedBrowser|
	|Computer|10.100.1.124|Windows 10|FIREFOX8 / 83.0|
And Clicking on logout button
	
@UL005
Scenario: Create the new user
Given Load the application
And Enter the valid username and password
|username|password|
|ela|1234|
And Clicking on submit button
And Check on create user button
And Enter the user details
	|FirstName|LastName|UserName|Password|Mail|
	|Test|Test|Test|Test|Test@g.com|
When Click on the save button
Then System create the user successfully
	|Message|
	|User Created Successfully|
And Clicking on logout button
	
@UL006
Scenario: Logout the application
Given Load the application
And Enter the valid username and password
|username|password|
|ela|1234|
And Clicking on submit button
When Clicking on logout button
Then System will navigate to login screen

@UL007
Scenario: While user try to login the application but purchased user count gets reached means,
		system should not allow to login the user
Given Load the application
And Enter the valid username and password
|username|password|
|ela|1234|
And Clicking on submit button
And Load the application
And Enter the valid username and password
|username|password|
|manick|Manick@123|
When Clicking on submit button
Then System should not login sucessfully
	|ValidationMessage|
	|Permissable active user limit reached|
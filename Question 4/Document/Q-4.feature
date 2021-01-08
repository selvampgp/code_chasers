#Feature: Testing chatbot screen 
Scenario: #1 Testing Previous interview status by entering valid details in previous interview suggestion
Given open the chatbot screen
And click on chat icon
When selecting previous interview suggestion
And entering valid phone number/email id
And entering valid DOB
Then system should display the previous interview details of the user

Scenario: #2 Testing Previous interview status by entering invalid details in previous interview suggestion
Given open the chatbot screen
And click on chat icon
When selecting previous interview suggestion
And entering invalid phone number/email id
Then system should validate the user to enter valid phone number/email id

Scenario: #3 Testing Previous interview status by entering invalid DOB in previous interview suggestion
Given open the chatbot screen
And click on chat icon
When selecting previous interview suggestion
And entering valid phone number/email id
And entering invalid DOB
Then system should validate the user to enter valid DOB to continue

Scenario: #4 Testing Upcoming interview details by selecting Upcoming interview details suggestion
Given open the chatbot screen
And click on chat icon
When Upcoming interview details
Then system should display the upcoming scheduled interview details 
And system should display the helpdesk contact information for further queries



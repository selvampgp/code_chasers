#Feature: Testing login screen with captcha
Scenario: #1 Login the screen using valid captcha
Given open the login page
And fill the username field with any alpha numeric values
And fill the password field with any alpha numeric values
When entering the displayed captcha in captcha field
And clicking on login button 
Then system should navigate the user to success page

Scenario: #2 Login the screen using invalid captcha
Given open the login page
And fill the username field with any alpha numeric values
And fill the password field with any alpha numeric values
When entering the invalid captcha in captcha field
And clicking on login button 
Then system should not navigate the user to success page
And display the alert "Invalid captcha"

Scenario: #3 Login the screen without entering captcha
Given open the login page
And fill the username field with any alpha numeric values
And fill the password field with any alpha numeric values
When  clicking on login button without entering captcha
Then system should not navigate the user to success page
And highilght the captcha field with red
And display the alert "Please fill out this field"

Scenario: #4 Login the screen without entering username or password
Given open the login page
When entering the displayed captcha in captcha field
And not entering values in username or password field
And clicking on login button 
Then system should not navigate the user to success page
And highilght the field with red
And display the alert "Please fill out this field"
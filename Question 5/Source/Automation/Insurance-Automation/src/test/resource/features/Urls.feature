@Feature-insurance @insurance
Feature: This feature covers all the possible cases of defining insurance premium

@ins_001
Scenario: ins_001~check premium calculation if person age is below 18
Given open calculator
And enter firname last name and DOB gender and phone number and email
|firstname|lastname|dob			|phone     |email			|gender|
|jacob	  |martin  |12/12/2005  |9788677676|newmail@mail.com|Male  |
When person age is below 18 and cliking on get premium button
Then calculated premium value should be based on age and gender
|expectedresult|
|Your Premium Amount is Rs.5100.00|

@ins_002
Scenario: ins_002~check premium calculation if person age between 18 to 25 and having pre-existing condition and one good habit
Given open calculator
And person age between 18 to 25 and the person gender is male 
|firstname|lastname|dob			|phone     |email			|gender|
|jacob	  |martin  |12/12/1997  |9788677676|newmail@mail.com|Male  |
And preexisting codition are bloodpressure and sugar
And the person having one good habit as daily exercise
When calculating the premium
Then system should calculate the premium based on the sum of value calculated based on age gender preexisting conditions
|expectedresult|
|Your Premium Amount is Rs.5551.00|

@ins_003
Scenario: ins_003~check premium calculation if person age between 25 to 30 and female gender and having pre-existing condition and bad habits
Given open calculator
And person age between 25 to 30 and the person gender is female 
|firstname|lastname|dob			|phone     |email			|gender|
|Jasmine  |martin  |12/12/1993  |9788677656|newmail2@mail.com|Female |
And preexisting codition are bloodpressure and sugar
And the person having one bad habit as drugs and alcohol
When calculating the premium
Then system should calculate the premium based on gender and preexisting condition and bad habits
|expectedresult|
|Your Premium Amount is Rs.6542.00|

@ins_004
Scenario: ins_004check premium calculation if person age between 30 to 35 and preexisting condition is overweight and bad habit is alcohol and drugs habit and good habit is exercise
Given open calculator
And person age between 30 to 35 and the person gender is female 
|firstname|lastname|dob			|phone     |email			|gender|
|Jasmine  |martin  |12/12/1988  |9788677656|newmail2@mail.com|Female |
And preexisting codition is overweight
And the person having one bad habit as drugs and alcohol and good habit is daily exercise
When calculating the premium
Then system should calculate the premium based on gender and preexisting condition and bad habits
|expectedresult|
|Your Premium Amount is Rs.6924.00|

@ins_005
Scenario: ins_005check premium calculation if person age is greater than 40 and no prexisting condition and bad habits
Given open calculator
And person age is greater than 40 and gender is male
|firstname|lastname|dob			|phone     |email			|gender|
|Jasmine  |martin  |12/12/1988  |9788677656|newmail2@mail.com|Male |
And the person heving good habit as daily exercise
When calculating the premium
Then system should calculate the premium based on gender and preexisting condition and bad habits
|expectedresult|
|Your Premium Amount is Rs.6585.00|

@ins_006
Scenario: ins_006check premium calculation if person age is greater than 40 and having all prexisting condition and no bad habits
Given open calculator
And person age is greater than 40 and gender is male
|firstname|lastname|dob			|phone     |email			|gender|
|Jasmine  |martin  |12/12/1988  |9788677656|newmail2@mail.com|Male |
And person having all preexisting coditions
And the person heving good habit as daily exercise
When calculating the premium
Then system should calculate the premium based on gender and preexisting conditions and habits
|expectedresult|
|Your Premium Amount is Rs.6848.00|

@ins_007
Scenario: ins_007check premium calculation if person age is greater than 40 and having all prexisting condition and all good and bad habits
Given open calculator
And person age is greater than 40 and gender is male
|firstname|lastname|dob			|phone     |email			|gender|
|Jasmine  |martin  |12/12/1957  |9788677656|newmail2@mail.com|Male |
And person having all preexisting coditions
And the person having all good habits and bad habits
When calculating the premium
Then system should calculate the premium based on gender and preexisting conditions and habits
|expectedresult|
|Your Premium Amount is Rs.17069.00|

@ins_008
Scenario: ins_005 check if user is able to pay the premium amount
Given open calculator
And person age is greater than 40 and gender is male
|firstname|lastname|dob			|phone     |email			|gender|
|Jasmine  |martin  |12/12/1988  |9788677656|newmail2@mail.com|Male |
And person having all preexisting coditions
And the person having all good habits and bad habits
And calculating the premium
And clicking on buypremium
And enter the card details
|cardnumber|CVV|Month|Year|
|1234567890123456|123|April|2021|
When submitting the payment
Then system should display the success message.
|expectedresult|
|Premium amount has been received successfully. Thank you for taking the health insurance policy.|




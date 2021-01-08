Scenario 1 – Check if system is displaying PAN (permanent account number) details 
Given download the Permanent Account Number image as jpg file
And open the application
And click on the PAN CARD
And upload the PAN image
When clicking on the UPLOAD FILE button
Then system should display the PAN attributes like holder name, Father Name, Date of birth and Permanent account number

Scenario 2 – Check if system is able to see the passport details 
Given download the passport details image as jpg file
And open the application
And click on the PASSPORT
And choose the country
And upload the passport image
When clicking on the UPLOAD FILE button
Then system should display the passport attributes like country of passport, passport number, passport date of issue, passport date of expired, passport place of issue, passport nationality and machine readable zone passport number

Scenario 3 – Check if system is able to display the PAN details without uploading the image
Given open the application
And click on the PAN CARD button
When clicking on the UPLOAD FILE button
Then system should display the validation message as “Select a document to upload”

Scenario 4 – Check if system is able to display the PASSPORT details without uploading the image
Given open the application
And click on the PASSPORT button
When clicking on the PASSPORT button
Then system should display the validation message as “Select a document to upload”

Scenario 5 – Check if system is able to display the PASSPORT details without select the country
Given open the application
And click on the PASSPORT button
And upload the image
When clicking on the PASSPORT button
Then system should display the validation message as “Select a country”

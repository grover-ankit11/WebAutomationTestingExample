# Assignment 1: Web Automation Testing

## Setup
* This framework implementation uses:
  * **Java** as a coding language
  * **TestNg** as testing framework
  * **Selenium Webdriver** framework for accessing browser/website elements and verification
  * **Maven** for Project building (https://maven.apache.org/install.html)
  * **JSON** parsing is used to read test data

## Code Structure:
* Page models and Utilities are in src\main\java\com\qa\application\pages and src\main\java\com\qa\application\util package respectively
* Tests are in src\test\java\com\qa\application\testcases package
* Test data is in src\test\resources\testData package
* Properties file is in src\test\resources\config\config.properties
* Extent report is generated inside reports folder with test Steps logs, Failed Screen shot and System info.
* Logs file is created inside logs folder

## Test Scenario Assumptions
* Testing URL: https://www.maf.com/redeem
* Only valid voucher code is: "VALID"
* Only valid country code is of Dubai which is "+971"
* Any 10 digit mobile number is valid
* Only valid OTP is "123456"
* Successful verification will show a Toast/Alert, while unsuccessful verification will show the error message on the same page
* Mobile number field and its corresponding "Submit" button is only displayed after user has entered a valid/invalid Voucher code and clicked on first "Submit" button
* OPT field and it's corresponding "Verify & Continue" button is only displayed after user has entered a valid/invalid phone number and clicked on the second "Submit" button
* Alert contains the following elements:
  * Message: "Voucher Redeemed Successfully"
  * Button: "Ok"

## TestCases Covered
* Positive Scenario: Valid voucher code, valid phone number and valid OTP
* Negative Scenarios:
  * Invalid Voucher Code
  * Invalid Country Code
  * Invalid Mobile Number
  * Invalid OTP 

> The code contains an extra test class called "AutomationExampleTest". This was created to validate the working on models and utility classes against actual pages and elements as part of the assignment only a dummy flow was given

## How to run
* Run `mvn test` on terminal
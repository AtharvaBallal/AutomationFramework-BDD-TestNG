Feature: Register a User

  @registeruser
  Scenario: Register user

  Given User is on home page
  Then Verify that home page is visible successfully
  And Click on "Signup / Login" button
  Then Verify "New User Signup!" is visible
  Then Enter "name" and "name123456@gmail.com" in name and emailaddress field
  And Click Signup button
  Then Verify that "ENTER ACCOUNT INFORMATION" is visible
#  And Fill details: "Title", "Name", "Email", "Password", "Date of birth"
  And Select checkbox "Sign up for our newsletter!"
  And Select checkbox "Receive special offers from our partners!"
  And Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
  Then Click "Create Account button"
  Then Verify that "ACCOUNT CREATED!" is visible
  And Click Continue button
  Then Verify that "Logged in as username" is visible
  And Click Delete Account button
  Then Verify that "ACCOUNT DELETED!" is visible and click "Continue" button




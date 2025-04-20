Feature: Logout from HRM application

Background:
  Given the user is on the login page

Scenario Outline: logout scenario
  When user enters <username> and <password>
  And User clicks on login button
  Then User should see the dashboard for valid credentials
  When user clicks on logout option
  Then user should be logged out successfully

  Examples:
    | username | password  |
    | Admin    |  admin123 |
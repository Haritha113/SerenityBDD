@smoke
Feature: Login to HRM application

Background:
  Given the user is on the login page

  @login
Scenario Outline: login scenario with valid credentials
  When user enters <username> and <password>
  And User clicks on login button
  Then User should see the dashboard for valid credentials
  Examples:
  | username | password  |
  | Admin    |  admin123 |

    @login
  Scenario Outline: login with invalid credentials
  When user enters <username> and <password>
  And User clicks on login button
  Then user should see prompt with text <errormsg>

  Examples:
    | username | password  | errormsg |
    | hello    |  worlsddf | Invalid credentials |

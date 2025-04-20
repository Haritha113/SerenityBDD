#Add New Users: Assign roles like Admin or ESS (Employee Self Service), link to existing employees, set username & password.
#
#Edit Users: Update user role, status, or credentials.
#
#Search Users: Filter users by role, status, employee name, or username.
#
#Delete Users: Select and delete from the user list.

Feature: user actions on HRM application through Admin option

Background:
  Given the user is on the login page

  Scenario Outline: Add users in Admin tab
    When user logs in with <username> and <password>
    And user navigates to <tabname> tab
    Then user should land on <tabname> successfully
    When User clicks on Add User button
    And User fills details with username password empname for <role> and <status>
    And User clicks on Save button
    Then user should be listed in the records table with <role>
    Examples:
      | username | password | tabname | role | status |
      | Admin | admin123 | ADMIN_TAB | ROLE_ADMIN | STATUS_ENABLED |
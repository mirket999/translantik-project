@login
Feature: Actions on "Forgot Password" menu.
  > User lands on the ‘Forgot Password’ page after clicking on the "Forgot your password?" link
  > Users can change their password with the username after clicking on the "REQUEST" button.

  Background:
    Given user is on the translantik login page

  @TRN-1616
  Scenario: Verify that User lands on the 'Forgot Password' page after clicking on the "Forgot your password?" link

    When user clicks on "Forgot your password?" link
    Then user lands on the "Forgot Password" page

  @TRN-1617
  Scenario Outline: Users can change their password with the username after clicking on the "REQUEST" button
  after landing on the 'Forgot Password' page.

    When user clicks on "Forgot your password?" link
    And user lands on the "Forgot Password" page
    And user enters username "<userName>" and clicks "Request" button
    Then user should change the password and shouldn't get error message "<error_message>"

    Examples: User Names and error messages are listed below:
      | userName        | error_message             |
      | user1           | Unable to send the email. |
      | storemanager51  | Unable to send the email. |
      | salesmanager251 | Unable to send the email. |
@login
Feature: Translantik WebPage Login feature

  Validate all users can log in with valid credentials (We have 3 types of users: sales manager, store manager, truck driver).
  Driver should land on the "Quick Launchpad" page after successful login

  Background:
    Given user is on the translantik login page

  @TRN-1540
  Scenario Outline: User should be able to login with valid credentials
    When user enters "<username>" and "<password>" and logins
    Then user "<user_type>" lands on the "<page_heading>"


    @truckDrivers
    Examples: Valid credentials for users
      | username | password    | page_heading    | user_type    |
      | user1    | UserUser123 | Quick Launchpad | Truck Driver |
      | user2    | UserUser123 | Quick Launchpad | Truck Driver |


    @storeManagers
    Examples: Valid credentials for users
      | username       | password    | page_heading | user_type     |
      | storemanager51 | UserUser123 | Dashboard    | Store Manager |
      | storemanager52 | UserUser123 | Dashboard    | Store Manager |


    @salesManagers
    Examples: Valid credentials for users
      | username        | password    | page_heading | user_type     |
      | salesmanager251 | UserUser123 | Dashboard    | Sales Manager |
      | salesmanager252 | UserUser123 | Dashboard    | Sales Manager |

  @TRN-1554
  Scenario Outline: User shouldn't be able to login with invalid credentials
    When user enters "<username>" and "<password>" and logins
    Then user "<user_type>" doesn't land on the "<page_heading>"


    Examples: Invalid credentials for users
      | username           | password         | page_heading    | user_type     |
      | user1123           | UsergfdgUser123  | Quick Launchpad | Truck Driver  |
      | storemanager5cdvf1 | UserUdfsdser123  | Dashboard       | Store Manager |
      | salesmanager252542 | UserUscvdsfer123 | Dashboard       | Sales Manager |


  @TRN-1555
  Scenario Outline: system shouldn't allow users to access the application without providing credentials
  (for example, copy the URL after login, then log out,
  paste the same URL to the browser and try to skip the authentication step)

    When user enters "<username>" and "<password>" and logins
    And user copies the URL
    And user logouts
    And user pastes the URL to browser and enters login without authentication
    Then user is still on the translantik login page

    Examples: Valid credentials for users
      | username        | password    |
      | user1           | UserUser123 |
      | storemanager51  | UserUser123 |
      | salesmanager110 | UserUser123 |


  @TRN-1556
  Scenario Outline: Validate Logging into the Application, closing the Browser without logging out,
  and opening the application in the new Browser/TAB again(optional).
  After logging into the APP, copy the URL, close the browser, and then open a new browser.
  After pasting the URL we should NOT navigate to the Dashboard Page.
  (Because of the cookies, it is possible to navigate to Dashboard Page while manual testing)

    When user enters "<username>" and "<password>" and logins
    And user copies the URL
    And user closes the browser
    And user opens a new browser and pasts copied URL
    Then user is still on the translantik login page

    Examples: Valid credentials for users
      | username        | password    |
      | user1           | UserUser123 |
      | storemanager51  | UserUser123 |
      | salesmanager251 | UserUser123 |

  @TRN-1557
  Scenario Outline: Validate Logging into the Application, closing the Browser without logging out,
  and opening the application in the new Browser/TAB again(optional).
  After logging into the App, copy the URL, open a new TAB,
  close the previous TAB and then paste the URL.
  This time we should see the Dashboard Page.

    When user enters "<username>" and "<password>" and logins
    And user copies the URL
    And user opens a new tab
    And user closes the previous tab
    And user pastes the URL
    Then user "<user_type>" lands on the "<page_heading>"

    Examples: Valid credentials for users
      | username        | password    | page_heading    | user_type     |
      | user1           | UserUser123 | Quick Launchpad | Truck Driver  |
      | storemanager51  | UserUser123 | Dashboard       | Store Manager |
      | salesmanager252 | UserUser123 | Dashboard       | Sales Manager |


  @TRN-1558
  Scenario Outline: Validate whether the leading and trailing
  spaces entered into the Username field are trimmed.

    When user enters "     user1     " and "UserUser123" and logins
    Then user "<user_type>" lands on the "<page_heading>"

    Examples: Valid credentials for users
      | page_heading    | user_type    |
      | Quick Launchpad | Truck Driver |


  @TRN-1573
  Scenario: Validate all the fields in the Login page have the proper ** placeholders **
  (Username or Email and Password) (optional)

    Then proper placeholder is displayed for username and password fields

  @TRN-1574
  Scenario Outline: "Invalid username or password." message should be displayed
  for invalid credentials
    When user enters "<invalid_username>" and "<invalid_password>" and logins
    Then user gets "<expected_warningMessage>"

    Examples: Invalid credentials and warning message:
      | invalid_username | invalid_password | expected_warningMessage        |
      | useruser1515     | 54786232         | Invalid user name or password. |


  @TRN-1575
  Scenario Outline: "Please fill out this field." message
  should be displayed for any empty field

    When user enters username "<username>" and logins
    Then user gets "<expected_validationMessage>" for username
    And user is on the translantik login page
    When user enters password "<password>" and logins
    Then user gets "<expected_validationMessage>" for password

    Examples: Invalid credentials and warning message:
      | username | password    | expected_validationMessage |
      | user1    | UserUser123 | Lütfen bu alanı doldurun.  |


  @TRN-1576
  Scenario: Validate the Password text entered into the
  'Password' field is toggled to hide its visibility

    Then password field is toggled to hide

  @TRN-1578
  Scenario Outline: Validate the Password is not visible in the Page Source (optional)

    When user enters password "<password>"
    Then "<password>" is not visible in Page Source

    Examples: Password
      | password    |
      | UserUser123 |


  @TRN-1615
  Scenario Outline: Validate the copying of the text entered into the Password field
  (It should not be applicable)

    When user enters password "<given_password>"
    And user copies the password and passes to other field
    Then copied password shouldn't equal to given password "<given_password>"

    Examples: Password
      | given_password |
      | UserUser123    |


  @TRN-1620
  Scenario: Validate user can see the "Remember me on this computer" link on the login page
  and it should be clickable.
    Then user sees the "Remember me on this computer" link
    Then "Remember me on this computer" should be clickable

  @TRN-1622
  Scenario Outline: Validate to login by using the Keyboard keys.
  (User click on "Username" input box (enter a valid username), hit "Enter" button,
  then cursor appears on "Password" input box (enter valid password),
  hit "Enter" again, "Login" button gets clicked) .

    When user clicks on username input box
    And user enters a valid username "<valid_username>" and hits ENTER button
    And user enters password "<valid_password>" and hits ENTER button
    Then user "<user_type>" lands on the "<page_heading>"

    Examples: Valid credentials for users
      | valid_username  | valid_password | page_heading    |
      | user1           | UserUser123    | Quick Launchpad |
      | storemanager51  | UserUser123    | Dashboard       |
      | salesmanager252 | UserUser123    | Dashboard       |


  @TRN-1623
  Scenario Outline: Validate to login by using the Keyboard keys.
  (User click on "Username" input box (enter a valid username),
  hit TAB and then cursor appears on "Password" input box (enter valid password),
  hit "Enter" again, "Login" button gets clicked) " ) (optional)

    When user clicks on username input box
    And user enters a valid username "<valid_username>" and hits TAB button
    And user enters password "<valid_password>" and hits ENTER button
    Then user "<user_type>" lands on the "<page_heading>"

    Examples: Valid credentials for users
      | valid_username  | valid_password | page_heading    |
      | user1           | UserUser123    | Quick Launchpad |
      | storemanager51  | UserUser123    | Dashboard       |
      | salesmanager252 | UserUser123    | Dashboard       |

  @TRN-1623

  Scenario Outline: Validate background color  of "LOG IN" ** button
  (hex code #0c84a3) (bonus-optional)

    Then background color of button "<button_name>" is as expected "<expected_hex_code>"

    Examples: buttons and hex codes:
      | button_name | expected_hex_code |
      | login       | #0c84a3  |



















































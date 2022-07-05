Feature: Translantik WebPage Login feature

  Validate all users can log in with valid credentials (We have 3 types of users: sales manager, store manager, truck driver).
  Driver should land on the "Quick Launchpad" page after successful login

@TRN-1540
  Scenario Outline: User should be able to login with valid credentials
    Given user is on the translantik login page
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


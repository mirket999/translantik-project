Feature: Translantik WebPage DashboardPage

  Validate the Breadcrumb, Page Heading, Page URL, Page Title of 'Dashboard Page' Page

@TRN-1541
  Scenario Outline: User should be able to see the Breadcrumb,
  Page Heading, Page URL, Page Title of 'Dashboard Page' Page as expected

    Given user is on the translantik login page
    Given user enters "<username>" and "<password>" and logins
    And user validates the page URL "URL"
    And user validates the page title "title"
   And user validates the Breadcrumb "<breadcrumb>" is displayed

    @truckDrivers
    Examples: Valid credentials for users
      | username | password    | breadcrumb      |
      | user1    | UserUser123 | Quick Launchpad |
      | user2    | UserUser123 | Quick Launchpad |


    @storeManagers
    Examples: Valid credentials for users
      | username       | password    | breadcrumb |
      | storemanager51 | UserUser123 | Dashboard  |
      | storemanager52 | UserUser123 | Dashboard  |


    @salesManagers
    Examples: Valid credentials for users
      | username        | password    | breadcrumb |
      | salesmanager251 | UserUser123 | Dashboard  |
      | salesmanager252 | UserUser123 | Dashboard  |

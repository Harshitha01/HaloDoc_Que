@tag
Feature: Search Functionality

  Scenario: Search for a product on Amazon
    Given I am on the Amazon homepage
    When I search for "iPhone"
    Then I should see search results related to "iPhone"


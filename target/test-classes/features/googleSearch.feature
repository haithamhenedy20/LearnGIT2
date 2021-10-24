@Regression
Feature: Google Search
  I want to use this template for my feature file

# The 'Background' is Prerequisite before running each Scenario below
Background:
  Given I open chrome browser
  When I navigate to google page

  @Smoke @Priority1
  Scenario: Searching Laptop   
    And I enter laptop in the search field
    And I click on search button
    Then I should see search results
    And I validate the first search result Laptop

  @Smoke
  Scenario: Searching Rose   
    And I enter Rose in the search field
    And I click on search button
    Then I should see search results
    And I validate the first search result Rose
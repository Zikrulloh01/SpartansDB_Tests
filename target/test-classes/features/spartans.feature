Feature: Spartans Tests



  Scenario: Add a Spartan
    Given user is on home page
    When user adds a new person
    |name|John|
    |   sex | Male|
    |    phone   | 07742040000    |
    Then Verify num of people match with database





  Scenario: Edit a Spartan
    Given user is on home page
    When User clicks edit button
    And Updates the spartan phone number, It should match with database


  @wip
  Scenario: Delete a spartan
    Given user is on home page
    When user clicks delete button
    Then Verify num of people match with database






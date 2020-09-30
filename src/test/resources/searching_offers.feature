Feature: Searching offers
  Scenario Outline: User inputs search criteria and can find offers
    Given User opens page
    And User inputs key words <key_words> in search field
    And User inputs region <region>
    And User selects type of employment <type_of_employment>
    And User selects level of experience <level_of_experience>
    When User reads all offers by criteria <criteria>
    Then Offers are saved
    Examples:
      | key_words                | region     | type_of_employment | level_of_experience | criteria                                                                                   |
      | "Tester, Selenium, Java" | "Warszawa" | "Pełny etat"       | "Początkujący"      | "tester testowanie automatyczne automatyczny automatyzujący API Java Selenium SQL Postman" |
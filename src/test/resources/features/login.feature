@LoginProfile
Feature: Checkout Smoke Tests

  Scenario: Login and complete order with money order
    Given I go to login page
    And enter my email and password for smoke test
      | email         | password   |
      | test@test.com | test123456 |
    And I go to cart
    And I add a random recommended product to cart
    And I click to 'Alışverişi Tamamla' button on cart
    And I go to payment page
    And I select payment option as "Havale"
    And I check if money order bank list is seen
    And select "Qnb" bank for transfer
    And I go to order summary page
    And click 'Devam Et' button on money order popup
    And I get money order payment total in order summary page
    And I get money order IBAN in order summary page
    And I complete my order
    And I check if order number is seen
    And I check payment type in complete order is "Havale"
    And I check if total paid price matches with summary in completed order page
    And I check if amount paid with money order matches with order summary
    And I check if bank IBAN for mone
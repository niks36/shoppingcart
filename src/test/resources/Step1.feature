Feature: Step 1 - Add item into Shopping Cart

  Scenario: Add item into Shopping Cart
    Given shopping cart is empty
    And below are the available product
      | Name      | Price |
      | Dove Soap | 39.99 |
    When user added 5 "Dove Soap" to Cart
    Then shopping cart should contain 5 "Dove Soap" with price of "39.99"
    And shopping cart total price is "199.95"

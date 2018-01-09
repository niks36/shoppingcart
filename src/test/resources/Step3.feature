Feature: Step 2 - Add item of same type into Shopping Cart

  Scenario: Add item into Shopping Cart
    Given shopping cart is empty
    And below are the available product
      | Name      | Price |
      | Dove Soap | 39.99 |
      | Axe Deo   | 99.99 |
    And Tax rate is "12.5"
    When user added 2 "Dove Soap" to Cart
    And user added 2 "Axe Deo" to Cart
    Then shopping cart should contain 2 "Dove Soap" with price of "39.99"
    And shopping cart should contain 2 "Axe Deo" with price of "99.99"
    And shopping cart tax amount is "35.00"
    And shopping cart total price is "314.96"

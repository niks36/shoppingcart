Feature: Step 5 - applying offer to prodcut

  Scenario: Add item into Shopping Cart
    Given shopping cart is empty
    And below are the available product
      | Name      | Price | Offer     |
      | Dove Soap | 39.99 | BUY2_GET1 |
      | Axe Deo   | 89.99 | NONE      |
    And Tax rate is "12.5"
    When user added 3 "Dove Soap" to Cart
    When user added 2 "Axe Deo" to Cart
    Then shopping cart should contain 3 "Dove Soap" with price of "39.99"
    Then shopping cart should contain 2 "Axe Deo" with price of "89.99"
    And shopping cart total discount is "39.99"
    And shopping cart total price is "292.46"
    And shopping cart tax amount is "32.50"

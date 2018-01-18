Feature: Step 5 - applying offer to prodcut

  Scenario: Add item into Shopping Cart
    Given shopping cart is empty
    And below are the available product
      | Name      | Price | Offer            |
      | Dove Soap | 39.99 | BUY2_GET50OnNext |
    And Tax rate is "12.5"
    When user added 2 "Dove Soap" to Cart
    Then shopping cart should contain 2 "Dove Soap" with price of "39.99"
    And shopping cart total discount is "20.00"
    And shopping cart total price is "67.48"
    And shopping cart tax amount is "7.50"

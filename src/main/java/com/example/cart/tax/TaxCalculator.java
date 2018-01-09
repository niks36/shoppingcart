package com.example.cart.tax;

import java.math.BigDecimal;

public interface TaxCalculator {

    BigDecimal calculateTax(BigDecimal amoount);
}

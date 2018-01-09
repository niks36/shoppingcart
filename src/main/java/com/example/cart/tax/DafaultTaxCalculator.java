package com.example.cart.tax;

import java.math.BigDecimal;

public class DafaultTaxCalculator implements TaxCalculator {


    @Override
    public BigDecimal calculateTax(BigDecimal amoount) {
        return BigDecimal.ZERO;
    }
}

package com.example.cart.tax;

import java.math.BigDecimal;

public class SimpleTaxCalculator implements TaxCalculator {

    BigDecimal rate;

    public SimpleTaxCalculator(BigDecimal rate){
        this.rate = rate;
    }
    @Override
    public BigDecimal calculateTax(BigDecimal amount) {
        return amount.multiply(rate).divide(new BigDecimal(100),2);
    }
}

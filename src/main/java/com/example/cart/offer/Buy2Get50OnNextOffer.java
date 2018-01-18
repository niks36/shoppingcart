package com.example.cart.offer;

import com.example.cart.util.MathRound;

import java.math.BigDecimal;

public class Buy2Get50OnNextOffer extends Offer {
    @Override
    public BigDecimal calcualteDiscount(BigDecimal price, int quantity) {

        return MathRound.roundUpToScale(price.multiply(new BigDecimal(quantity/2)).divide(new BigDecimal(2),2),2);
    }
}

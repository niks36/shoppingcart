package com.example.cart.offer;

import com.example.cart.util.MathRound;

import java.math.BigDecimal;

public class Buy2Get1Offer extends Offer{
    public BigDecimal calcualteDiscount(BigDecimal price, int quantity){
        // TO-Do
        return MathRound.roundUpToScale(price.multiply(new BigDecimal( quantity/3)),2);
    }
}

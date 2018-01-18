package com.example.offer;

import com.example.cart.offer.Buy2Get1Offer;
import com.example.cart.util.MathRound;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class Buy2Get1OfferTest {


    @Test
    public void testOffer(){
        Buy2Get1Offer buy2Get1Offer = new Buy2Get1Offer();
        Assert.assertEquals(MathRound.roundUpToScale(new BigDecimal(40.00),2),buy2Get1Offer.calcualteDiscount(new BigDecimal(40.00),3));
        Assert.assertEquals(MathRound.roundUpToScale(new BigDecimal(40.55),2),buy2Get1Offer.calcualteDiscount(new BigDecimal(40.55),3));
        Assert.assertEquals(MathRound.roundUpToScale(new BigDecimal(0.00),2),buy2Get1Offer.calcualteDiscount(new BigDecimal(40.55),2));
        Assert.assertEquals(MathRound.roundUpToScale(new BigDecimal(40.55),2),buy2Get1Offer.calcualteDiscount(new BigDecimal(40.55),4));
        Assert.assertEquals(MathRound.roundUpToScale(new BigDecimal(81.10),2),buy2Get1Offer.calcualteDiscount(new BigDecimal(40.55),6));
        Assert.assertEquals(MathRound.roundUpToScale(new BigDecimal(20.00),2),buy2Get1Offer.calcualteDiscount(new BigDecimal(39.99),2));
    }
}

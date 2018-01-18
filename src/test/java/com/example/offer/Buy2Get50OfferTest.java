package com.example.offer;

import com.example.cart.offer.Buy2Get50OnNextOffer;
import com.example.cart.util.MathRound;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class Buy2Get50OfferTest {


    @Test
    public void testOffer(){
        Buy2Get50OnNextOffer get50OnNextOffer = new Buy2Get50OnNextOffer();
        Assert.assertEquals(MathRound.roundUpToScale(new BigDecimal(20.00),2),get50OnNextOffer.calcualteDiscount(new BigDecimal(40.00),2));
        Assert.assertEquals(MathRound.roundUpToScale(new BigDecimal(20.27),2),get50OnNextOffer.calcualteDiscount(new BigDecimal(40.55),3));
        Assert.assertEquals(MathRound.roundUpToScale(new BigDecimal(40.55),2),get50OnNextOffer.calcualteDiscount(new BigDecimal(40.55),4));
        Assert.assertEquals(MathRound.roundUpToScale(new BigDecimal(0.00),2),get50OnNextOffer.calcualteDiscount(new BigDecimal(40.55),1));
    }
}

package com.example.cart.util;

import java.math.BigDecimal;

public class MathRound {


    public static BigDecimal roundUpToScale(BigDecimal price, int scale){
        return price.setScale(scale, BigDecimal.ROUND_HALF_EVEN);
    }

}

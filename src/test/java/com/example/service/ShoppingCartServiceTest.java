package com.example.service;

import com.example.cart.repo.ProductRepository;
import com.example.cart.service.ShoppingCartService;
import com.example.cart.util.MathRound;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class ShoppingCartServiceTest {

    private static final String DOVE = "Dove";
    private static final String AXE = "Axe";
    private ShoppingCartService shoppingCartService;

    @Before
    public void initialize() {
        ProductRepository productRepository = new ProductRepository();
        productRepository.add(DOVE, new BigDecimal(39.99));
        productRepository.add(AXE, new BigDecimal(99.99));
        shoppingCartService = new ShoppingCartService(productRepository);
    }

    @Test
    public void testNoProductToCart() {
        Assert.assertTrue(DOVE,shoppingCartService.getProductDetail().isEmpty());
        Assert.assertEquals(BigDecimal.ZERO,shoppingCartService.getTotalAmount());
    }

    @Test
    public void addProductToCart() {
        Assert.assertTrue(shoppingCartService.addProduct(DOVE, 2));
        Assert.assertEquals(DOVE,shoppingCartService.getProductDetail().get(0).getProduct().getProductName());
        Assert.assertEquals(2,shoppingCartService.getProductDetail().get(0).getQuantity());
        Assert.assertEquals(MathRound.roundUpToScale(new BigDecimal(79.98),2),shoppingCartService.getTotalAmount());
    }

    @Test
    public void addNoneExistProductToCart() {
        Assert.assertFalse(shoppingCartService.addProduct("New Dove", 2));
        Assert.assertTrue(DOVE,shoppingCartService.getProductDetail().isEmpty());
        Assert.assertEquals(BigDecimal.ZERO,shoppingCartService.getTotalAmount());
    }


    @Test
    public void addZeroQuantityProductToCart() {
        Assert.assertFalse(shoppingCartService.addProduct(DOVE, 0));
        Assert.assertTrue(DOVE,shoppingCartService.getProductDetail().isEmpty());
        Assert.assertEquals(BigDecimal.ZERO,shoppingCartService.getTotalAmount());
    }

}

package com.example.service;

import com.example.cart.model.ProductDetail;
import com.example.cart.repo.ProductRepository;
import com.example.cart.service.ShoppingCartService;
import com.example.cart.tax.SimpleTaxCalculator;
import com.example.cart.util.MathRound;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Optional;

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

    @Test
    public void addMultipleSameProductToCart() {
        Assert.assertTrue(shoppingCartService.addProduct(DOVE, 2));
        Assert.assertTrue(shoppingCartService.addProduct(DOVE, 3));
        Assert.assertEquals(DOVE,shoppingCartService.getProductDetail().get(0).getProduct().getProductName());
        Assert.assertEquals(5,shoppingCartService.getProductDetail().get(0).getQuantity());
        Assert.assertEquals(MathRound.roundUpToScale(new BigDecimal(199.95),2),shoppingCartService.getTotalAmount());
    }


    @Test
    public void addMultipleDiffProductToCart() {
        Assert.assertTrue(shoppingCartService.addProduct(DOVE, 2));
        Assert.assertTrue(shoppingCartService.addProduct(AXE, 3));

        Optional<ProductDetail> detail = shoppingCartService.getProductDetail().stream().filter(productDetail -> productDetail.getProduct().getProductName().equals(DOVE)).findAny();
        Assert.assertTrue(detail.isPresent());
        Assert.assertEquals(2,detail.get().getQuantity());

        detail = shoppingCartService.getProductDetail().stream().filter(productDetail -> productDetail.getProduct().getProductName().equals(AXE)).findAny();
        Assert.assertTrue(detail.isPresent());
        Assert.assertEquals(3,detail.get().getQuantity());

        Assert.assertEquals(MathRound.roundUpToScale(new BigDecimal(379.95),2),shoppingCartService.getTotalAmount());
    }


    @Test
    public void addMultipleProductToCart() {
        Assert.assertTrue(shoppingCartService.addProduct(DOVE, 2));
        Assert.assertTrue(shoppingCartService.addProduct(DOVE, 3));
        Assert.assertTrue(shoppingCartService.addProduct(AXE, 3));

        Optional<ProductDetail> detail = shoppingCartService.getProductDetail().stream().filter(productDetail -> productDetail.getProduct().getProductName().equals(DOVE)).findAny();
        Assert.assertTrue(detail.isPresent());
        Assert.assertEquals(5,detail.get().getQuantity());

        detail = shoppingCartService.getProductDetail().stream().filter(productDetail -> productDetail.getProduct().getProductName().equals(AXE)).findAny();
        Assert.assertTrue(detail.isPresent());
        Assert.assertEquals(3,detail.get().getQuantity());

        Assert.assertEquals(MathRound.roundUpToScale(new BigDecimal(499.92),2),shoppingCartService.getTotalAmount());
    }


    @Test
    public void addTaxToCart() {
        shoppingCartService.setTaxCalculator(new SimpleTaxCalculator(new BigDecimal(12)));
        Assert.assertTrue(shoppingCartService.addProduct(DOVE, 2));

        Optional<ProductDetail> detail = shoppingCartService.getProductDetail().stream().filter(productDetail -> productDetail.getProduct().getProductName().equals(DOVE)).findAny();
        Assert.assertTrue(detail.isPresent());
        Assert.assertEquals(2,detail.get().getQuantity());

        Assert.assertEquals(MathRound.roundUpToScale(new BigDecimal(9.6),2),shoppingCartService.getTaxAmount());
        Assert.assertEquals(MathRound.roundUpToScale(new BigDecimal(89.58),2),shoppingCartService.getTotalAmount());
    }


    @Test
    public void addZeroTaxToCart() {
        shoppingCartService.setTaxCalculator(new SimpleTaxCalculator(BigDecimal.ZERO));
        Assert.assertTrue(shoppingCartService.addProduct(DOVE, 2));

        Optional<ProductDetail> detail = shoppingCartService.getProductDetail().stream().filter(productDetail -> productDetail.getProduct().getProductName().equals(DOVE)).findAny();
        Assert.assertTrue(detail.isPresent());
        Assert.assertEquals(2,detail.get().getQuantity());

        Assert.assertEquals(MathRound.roundUpToScale(new BigDecimal(0),2),shoppingCartService.getTaxAmount());
        Assert.assertEquals(MathRound.roundUpToScale(new BigDecimal(79.98),2),shoppingCartService.getTotalAmount());
    }


}

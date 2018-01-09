package com.example.repo;

import com.example.cart.model.Product;
import com.example.cart.repo.ProductRepository;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ProductRepositoryTest {


    private static final BigDecimal PRICE_12_34 = new BigDecimal(12.34);
    private static final String DOVE = "Dove";

    @Test
    public void testAddProduct(){
        ProductRepository productRepository = new ProductRepository();
        Assert.assertTrue(productRepository.add(DOVE, PRICE_12_34));
        Product product = productRepository.findByName(DOVE);
        Assert.assertEquals("Dove",product.getProductName());
        Assert.assertEquals(PRICE_12_34,product.getPrice());
    }

    @Test
    public void testFindProductWithOutAdding(){
        ProductRepository productRepository = new ProductRepository();
        Product product = productRepository.findByName(DOVE);
        Assert.assertNull(product);
    }

    @Test
    public void testAddAlreadyAddedProduct(){
        ProductRepository productRepository = new ProductRepository();
        Assert.assertTrue(productRepository.add(DOVE, PRICE_12_34));
        Product product = productRepository.findByName(DOVE);
        Assert.assertEquals(DOVE,product.getProductName());
        Assert.assertEquals(PRICE_12_34,product.getPrice());

        Assert.assertFalse(productRepository.add(DOVE, new BigDecimal(56.78)));
    }

}

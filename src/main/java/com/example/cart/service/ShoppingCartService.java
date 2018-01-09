package com.example.cart.service;

import com.example.cart.model.Product;
import com.example.cart.model.ProductDetail;
import com.example.cart.repo.ProductRepository;
import com.example.cart.tax.DafaultTaxCalculator;
import com.example.cart.tax.TaxCalculator;
import com.example.cart.util.MathRound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class ShoppingCartService {

    private Map<String, ProductDetail> productDetailList = new ConcurrentHashMap<>();

    private BigDecimal totalPrice = BigDecimal.ZERO;

    private BigDecimal totalTax = BigDecimal.ZERO;

    private ProductRepository productRepository;

    private TaxCalculator taxCalculator;

    @Autowired
    public ShoppingCartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.taxCalculator = new DafaultTaxCalculator();
    }

    public void setTaxCalculator(TaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
    }

    public boolean addProduct(String productName, int quantity) {
        if(quantity > 0) {
            Product product = productRepository.findByName(productName);
            if (product != null) {
                ProductDetail productDetail = productDetailList.computeIfAbsent(productName, s -> new ProductDetail(product));
                productDetail.incrementQuantity(quantity);
                updateCartTotalAmount();
                return true;
            }
        }
        return false;
    }

    public List<ProductDetail> getProductDetail() {
        return productDetailList.entrySet().stream().map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    private void updateCartTotalAmount() {
        totalPrice = productDetailList.entrySet().stream()
                .map(Map.Entry::getValue)
                .map(ProductDetail::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        totalTax = MathRound.roundUpToScale(taxCalculator.calculateTax(totalPrice),2);
        totalPrice = MathRound.roundUpToScale(totalPrice.add(totalTax), 2);
    }

    public BigDecimal getTotalAmount() {
        return totalPrice;
    }

    public BigDecimal getTaxAmount() {
        return totalTax;
    }

    public void clearShoppingCart() {
        productDetailList = new ConcurrentHashMap<>();
        totalPrice = BigDecimal.ZERO;
    }

}

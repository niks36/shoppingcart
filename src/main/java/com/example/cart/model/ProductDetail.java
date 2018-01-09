package com.example.cart.model;

import java.math.BigDecimal;

public class ProductDetail {

    private Product product;

    private int quantity;

    public ProductDetail(Product product,int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalAmount(){
        return product.getPrice().multiply(new BigDecimal(quantity));
    }

}

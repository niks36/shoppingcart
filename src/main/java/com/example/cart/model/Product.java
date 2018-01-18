package com.example.cart.model;

import com.example.cart.offer.Offer;

import java.math.BigDecimal;

public class Product {

    String productName;

    BigDecimal price;

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    Offer offer;

    public Product(String productName, BigDecimal price, Offer offer) {
        this.productName = productName;
        this.price = price;
        this.offer = offer;
    }

    public Product(String productName, BigDecimal price) {
        this.productName = productName;
        this.price = price;
        this.offer = new Offer();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }
}

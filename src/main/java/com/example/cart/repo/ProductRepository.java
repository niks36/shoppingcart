package com.example.cart.repo;

import com.example.cart.model.Product;
import com.example.cart.offer.Offer;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ProductRepository {

    private Map<String,Product> productMap = new ConcurrentHashMap<String,Product>();

    public boolean add(String productName, BigDecimal price, Offer offer){

        if(!productMap.containsKey(productName)){
            productMap.put(productName,new Product(productName,price,offer));
            return true;
        }

        return false;
    }
    public boolean add(String productName, BigDecimal price){
        return add(productName,price,new Offer());
    }

    public Product findByName(String name){
        return productMap.get(name);
    }
}

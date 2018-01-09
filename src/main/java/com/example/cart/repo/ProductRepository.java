package com.example.cart.repo;

import com.example.cart.model.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ProductRepository {

    private Map<String,Product> productMap = new ConcurrentHashMap<String,Product>();

    public boolean add(String productName, BigDecimal price){

        if(!productMap.containsKey(productName)){
            productMap.put(productName,new Product(productName,price));
            return true;
        }

        return false;
    }

    public Product findByName(String name){
        return productMap.get(name);
    }
}

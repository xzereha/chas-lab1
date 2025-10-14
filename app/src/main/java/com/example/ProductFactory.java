package com.example;

import java.util.HashMap;
import java.util.Map;

public class ProductFactory {
    // I like singletons, when stuff needs to be unique
    private static ProductFactory singleton = null;

    private final Map<String, Product> productCache;

    // Just to disable creation
    private ProductFactory() {
        productCache = new HashMap<>();
    }

    public static ProductFactory getInstance() {
        if (singleton == null)
            singleton = new ProductFactory();

        return singleton;
    }

    public Product getOrCreateProduct(String name, String category, double price) {
        String key = String.format("%s |%s|", name, category);
        return productCache.computeIfAbsent(key, k -> new Product(name, category, price));
    }
}

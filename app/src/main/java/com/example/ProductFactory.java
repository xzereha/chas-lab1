package com.example;

import java.util.HashMap;
import java.util.Map;

/**
 * This factory ensures that products with the same name and category are
 * unique.
 * This allows getting the same product instance multiple times, for example
 * when comparing
 * products in orders.
 */
public class ProductFactory {
    // I like singletons, when stuff needs to be unique
    private static ProductFactory singleton = null;

    // Hashmap is used to store created products by unique identifier
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

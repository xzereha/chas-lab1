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

    /**
     * Get an existing product or create a new one if it does not exist.
     * The unique identifier is a combination of name and category.
     * 
     * @param name         The name of the product.
     * @param category     The category of the product.
     * @param initialPrice The initial price of the product.
     * @return The existing or newly created product.
     */
    public Product getOrCreateProduct(String name, String category, double initialPrice) {
        String key = String.format("%s |%s|", name, category);
        return productCache.computeIfAbsent(key, k -> new Product(name, category, initialPrice));
    }
}

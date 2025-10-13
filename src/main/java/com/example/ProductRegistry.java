package com.example;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductRegistry {
    private static final Logger logger = LoggerFactory.getLogger(ProductRegistry.class);
    private final Map<String, Product> products;

    public ProductRegistry() {
        // Using HashMap to enable lookup for products by name.
        products = new HashMap<>();
    }

    public void registerProduct(Product product) {
        products.computeIfAbsent(product.getName(), k -> {
            logger.info("Registered product {}", product.getName());
            return product;
        });
    }

    /**
     * Remove the provided product from the manager.
     * 
     * @param product Product to remove.
     * @return true if it was removed.
     */
    public boolean removeProduct(Product product) {
        return products.remove(product.getName()) == null ? false : true;
    }

    /**
     * Remove the provided product from the manager.
     * 
     * @param product Name of the product to remove.
     * @return true if it was removed.
     */
    public boolean removeProduct(String product) {
        return products.remove(product) == null ? false : true;
    }

    public Optional<Product> getProduct(String name) {
        return Optional.ofNullable(products.get(name));
    }

    public Optional<Product> getProduct(long id) {
        return products.values()
                .stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    /**
     * Gets a list of all products sorted by price.
     * 
     * @return A list of all registered products.
     */
    public List<Product> listProducts() {
        return products.values()
                .stream()
                .sorted(Comparator.comparing(Product::getPrice))
                .collect(Collectors.toList());
    }

    /**
     * Gets a list of all products belonging to the provided category.
     * This list will be sorted by price.
     * 
     * @param category Name of the Category to list.
     * @return A list of all matching products, or an empty list if no matches.
     */
    public List<Product> listProducts(String category) {
        // Using stream iterator to first select the correct category, then uses the
        // Comparator API to sort the remaining items by price.
        return products.values()
                .stream()
                .filter(product -> product.getCategory().equals(category))
                .sorted(Comparator.comparing(Product::getPrice))
                .collect(Collectors.toList());
    }
}

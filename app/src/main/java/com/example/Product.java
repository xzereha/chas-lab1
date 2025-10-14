package com.example;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Product {
    private static final Logger logger = LoggerFactory.getLogger(Product.class);

    /** Auto incrementing ID */
    private static long nextID = 0;
    private final long id;
    private final String name;
    private final String category;
    private double price;

    /**
     * Create a new product
     * 
     * IMPORTANT: Don't directly create a Product, to create use the Factory.
     * 
     * @see ProductFactory
     * 
     * @param name     The name of the product, must not be empty.
     * @param category The category the product belongs to, must not be empty.
     * @param price    The price of the product, this mut not be a negative number.
     * @throws IllegalArgumentException if any of the parameters is null or an
     *                                  invalid value.
     */
    public Product(String name, String category, double price) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Name mut be a valid non-empty string");
        if (category == null || category.isEmpty())
            throw new IllegalArgumentException("Category must be a valid non-empty string");
        if (price <= 0.0)
            throw new IllegalArgumentException("Price must not be negative");

        this.id = nextID++;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    /**
     * Updates the price of the Product
     * 
     * @param price New price, will be ignored if set to a negative value.
     */
    public void setPrice(double price) {
        if (price >= 0.0) {
            this.price = price;
            logger.info("Price of product {} updated to ${}", name, price);
        }
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, category);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof Product))
            return false;

        Product p = (Product) other;
        return Long.compare(id, p.id) == 0 &&
                Double.compare(p.price, price) == 0 &&
                name.equals(p.name) &&
                category.equals(p.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, price);
    }
}

package com.example;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {
    /** Auto incrementing ID */
    private static long nextID = 0;
    private final long orderID;
    /** Storing a map of each product and the amount of that product */
    private final Map<Product, Integer> entries;
    private final String customerName;

    /**
     * Create a new order for the specified customer.
     * 
     * @param customerName Name of the customer placing the order, must not be null
     *                     or empty.
     * @throws IllegalArgumentException if customerName is null or empty.
     */
    public Order(String customerName) {
        if (customerName == null)
            throw new IllegalArgumentException("Customer name must not be null");
        if (customerName.trim().isEmpty())
            throw new IllegalArgumentException("Customer name must not be empty");

        this.orderID = nextID++;
        // HashMap is used since the common case is adding or modifying Product entries,
        // and sorting is not needed.
        this.entries = new HashMap<>();
        this.customerName = customerName;
    }

    /**
     * Get the ID representing this order.
     * 
     * @return Unique numeric ID of the order.
     */
    public long getOrderID() {
        return orderID;
    }

    /**
     * Get a list of each product in the order
     * 
     * @return A List of each product type in the order.
     */
    public List<Product> getOrderProducts() {
        // Using stream to return a List instead of a Set.
        return entries
                .keySet()
                .stream()
                .toList();
    }

    /**
     * And a product to the order.
     * If there already is product of that count then the new will be added to that
     * amount.
     * 
     * @param product The type of product to add, must not be null.
     * @param count   The amount of the product, must be positive.
     * @throws IllegalArgumentException if product is null or count is not positive.
     */
    public void addProduct(Product product, int count) {
        if (product == null)
            throw new IllegalArgumentException("Product must not be null");
        if (count <= 0)
            throw new IllegalArgumentException("Count must be positive");

        // Merge the new count with any existing, creating a new entry if needed.
        entries.merge(product, count, Integer::sum);
    }

    /**
     * Get a list of each entry in the order.
     * This contains both the type of product and the amount that is ordered.
     * 
     * @return Read-only Map of Product, Count for the order
     */
    public Map<Product, Integer> getEntries() {
        // Changing to read-only so callers can't modify our internal state.
        return Collections.unmodifiableMap(entries);
    }

    /**
     * Gets the name of the Customer that the order belongs to.
     * 
     * @return Name of the Customer.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Calculates the total cost of the Order
     * 
     * @return the total cost of the order
     */
    public double getCost() {
        // First map to an iterator of the K,V set, then multiply the product with the
        // count and sum the result.
        return entries.entrySet()
                .stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }
}

package com.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderRegistry {
    // Using HashMap to enable lookup of orders.

    /** Mapping customer name to a list of their orders. */
    private final Map<String, List<Order>> orders;

    public OrderRegistry() {
        orders = new HashMap<>();
    }

    public void addOrder(String customerName, Order order) {
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name must not be null or empty");
        }
        if (order == null) {
            throw new IllegalArgumentException("Order must not be null");
        }

        // Append the new order, creating the Order list for the specified customer if
        // missing.
        // ArrayList is used for the per-customer orders since the common case is
        // listing the orders.
        orders.computeIfAbsent(customerName, (k) -> new ArrayList<>()).add(order);
    }

    public Optional<List<Order>> getOrders(String customerName) {
        return Optional.ofNullable(orders.get(customerName));
    }

    public Optional<Double> getCustomerSpending(String customerName) {
        return Optional.ofNullable(this.orders.get(customerName))
                // Change the type of the Optional from List to Double
                .map(list -> list.stream()
                        // And get the double by summing all the costs
                        .mapToDouble(Order::getCost).sum());
    }

    /**
     * Gets the collection of all orders in the system.
     * 
     * @return A Collection containing all orders.
     */
    public Collection<List<Order>> getOrders() {
        return orders.values();
    }
}

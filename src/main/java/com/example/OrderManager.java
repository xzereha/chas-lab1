package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderManager {
    private final Map<String, List<Order>> orders;

    public OrderManager() {
        // Using HashMap to enable lookup of orders.
        orders = new HashMap<>();
    }

    public void addOrder(String costumerName, Order order) {
        // Append the new order, creating the Order list for the specified costumer if
        // missing.
        // ArrayList is used for the per-costumer orders since the common case is
        // listing the orders.
        orders.computeIfAbsent(costumerName, (k) -> new ArrayList<>()).add(order);
    }

    public Optional<Double> getCustomerSpending(String customerName) {
        List<Order> orders = this.orders.get(customerName);
        if (orders == null) {
            return Optional.empty();
        }
        // Use the per Order getCost and sum these together
        double totalCost = orders.stream().mapToDouble(Order::getCost).sum();
        return Optional.of(totalCost);
    }
}

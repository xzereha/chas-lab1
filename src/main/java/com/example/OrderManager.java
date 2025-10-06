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
        return Optional.ofNullable(this.orders.get(customerName))
                // Change the type of the Optional from List to Double
                .map(list -> list.stream()
                        // And get the double by summing all the costs
                        .mapToDouble(Order::getCost).sum());
    }
}

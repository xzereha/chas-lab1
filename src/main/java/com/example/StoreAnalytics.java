package com.example;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class StoreAnalytics {
    private final OrderManager orderManager;

    public StoreAnalytics(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    /**
     * Gets a list of the most popular products in the store.
     * 
     * @return A list of entries containing the product and the number of times it
     *         was ordered, sorted by the number of orders in descending order.
     */
    public List<Entry<Product, Double>> getMostPopularProducts() {
        // We start with remapping all of out orders to a map of Product and how many of
        // them has been orderer.
        Map<Product, Double> products = orderManager.getOrders()
                .stream() // Stream<List<Order>>

                // Flatten the stream to just a stream of Orders
                .flatMap(List::stream) // Stream<Order>

                // Remap from a Stream of Orders to a list of Map<Product, Integer>
                .map(order -> order.getEntries()) // Stream<Map<Product, Integer>>

                // Flatten the stream so it is just a stream of Entries
                .flatMap(p -> p.entrySet().stream()) // Stream<Entry<Product, Integer>>

                // Merge together all entries that share key, adding their values together.
                .collect(Collectors.toMap(Entry::getKey, e -> e.getValue().doubleValue(), Double::sum));

        return products.entrySet()
                .stream()
                // Sort the stream based on the Entry values, since this represents the amount
                .sorted(Entry.<Product, Double>comparingByValue(Comparator.reverseOrder()))
                .toList();
    }

    /**
     * Gets a list of the most popular products in the store.
     * 
     * @param topN The number of products to return.
     * @return A list of entries containing the product and the number of times it
     *         was ordered, sorted by the number of orders in descending order.
     */
    public List<Entry<Product, Double>> getMostPopularProducts(int topN) {
        // Implemented using the method that returns all products.
        return getMostPopularProducts()
                // Return only the top N products, or all if there are less than N.
                .subList(0, Math.min(topN, getMostPopularProducts().size()));

    }

}

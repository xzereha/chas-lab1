package com.example;

import java.util.List;
import java.util.Map.Entry;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.example.exceptions.CustomerNotFoundException;
import com.example.exceptions.ProductNotFoundException;

public class Store {
    private static final Logger logger = LoggerFactory.getLogger(Store.class);
    private final ProductRegistry productManager;
    private final OrderRegistry orderManager;
    private final StoreAnalytics analytics;

    public Store() {
        productManager = new ProductRegistry();
        orderManager = new OrderRegistry();
        analytics = new StoreAnalytics(orderManager);
    }

    public void addProduct(Product newProduct) {
        productManager.registerProduct(newProduct);
    }

    /**
     * Gets a Product by name.
     * 
     * @param name The name to search for.
     * @return The found product.
     * @throws ProductNotFoundException If no product with that name exists.
     */
    public Product getProduct(String name) throws ProductNotFoundException {
        return productManager.getProduct(name)
                // Convert the optional to an exception
                .orElseThrow(() -> {
                    logger.error("Requested product: {} that does not exist", name);
                    return new ProductNotFoundException(name);
                });
    }

    /**
     * Gets a Product by id.
     * 
     * @param id The id to search for.
     * @return The found product.
     * @throws ProductNotFoundException If no product with that id exists.
     */
    public Product getProduct(long id) throws ProductNotFoundException {
        return productManager.getProduct(id)
                // Convert the optional to an exception
                .orElseThrow(() -> {
                    logger.error("Requested product with ID: {} that does not exist", id);
                    return new ProductNotFoundException(String.valueOf(id));
                });
    }

    public List<Product> listProducts(String category) {
        return productManager.listProducts(category);
    }

    public void placeOrder(String customerName, Order order) {
        logger.info("{} placed an order with {} items", customerName, order.getEntries().size());
        try {
            orderManager.addOrder(customerName, order);
        } catch (IllegalArgumentException e) {
            logger.error("Failed to place order for customer {}: {}", customerName, e.getMessage());
            throw e;
        }
    }

    public List<Entry<Product, Integer>> mostPopularProducts(int count) {
        return analytics.getMostPopularProducts(count);
    }

    /**
     * Calculate the total spending for a specified customer.
     * 
     * @param customerName Name of the customer to check.
     * @return The total amount spent by the provided customer.
     * @throws CustomerNotFoundException If the costumer has not placed any orders.
     */
    public double getCustomerSpending(String customerName) throws CustomerNotFoundException {
        return orderManager.getCustomerSpending(customerName)
                // Convert the optional to an exception
                .orElseThrow(() -> {
                    logger.error("Requested customer spending for non existing customer {}", customerName);
                    return new CustomerNotFoundException(customerName);
                });
    }
}

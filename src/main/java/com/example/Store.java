package com.example;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.exceptions.CustomerNotFoundException;
import com.example.exceptions.ProductNotFoundException;

public class Store {
    private static final Logger logger = LoggerFactory.getLogger(Store.class);
    private final ProductManager productManager;
    private final OrderManager orderManager;

    public Store() {
        productManager = new ProductManager();
        orderManager = new OrderManager();
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

    public List<Product> listProducts(String category) {
        return productManager.listProducts(category);
    }

    public void placeOrder(String costumerName, Order order) {
        orderManager.addOrder(costumerName, order);
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

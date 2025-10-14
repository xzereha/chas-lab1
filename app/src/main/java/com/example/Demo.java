package com.example;

import com.example.exceptions.CustomerNotFoundException;
import com.example.exceptions.ProductNotFoundException;

public class Demo {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Demo.class);

    private ProductFactory factory = ProductFactory.getInstance();
    private Store store = new Store();

    public void run() {
        store.addProduct(factory.getOrCreateProduct("Nail", "Hardware", 0.15));
        store.addProduct(factory.getOrCreateProduct("Hammer", "Hardware", 9.99));
        store.addProduct(factory.getOrCreateProduct("Drill", "Hardware", 49.99));
        store.addProduct(factory.getOrCreateProduct("Screwdriver", "Hardware", 4.99));
        store.addProduct(factory.getOrCreateProduct("Apples", "Food", 0.99));
        store.addProduct(factory.getOrCreateProduct("Bananas", "Food", 1.29));
        store.addProduct(factory.getOrCreateProduct("Bread", "Food", 2.49));
        store.addProduct(factory.getOrCreateProduct("Milk", "Food", 3.19));

        store.listProducts("Hardware")
                .forEach((p) -> System.out.println(p.getCategory() + " : " + p.getName() + " $" + p.getPrice()));

        store.listProducts("Food")
                .forEach((p) -> System.out.println(p.getCategory() + " : " + p.getName() + " $" + p.getPrice()));

        Order order1 = new Order("Steven");
        try {
            order1.addProduct(store.getProduct("Nail"), 10);
            order1.addProduct(store.getProduct("Nail"), 5);
            order1.addProduct(store.getProduct("Hammer"), 1);
            order1.addProduct(store.getProduct("Apples"), 4);
            order1.addProduct(store.getProduct("Milk"), 2);
        } catch (Exception e) {
            logger.error("Failed to add product to order", e);
        }
        System.out.println("Order contains:");
        order1.getEntries().forEach((K, V) -> {
            System.out.printf("%d x %s%n", V, K.getName());
        });
        store.placeOrder("Steven", order1);

        double spending = store
                .getCustomerSpending("Steven");

        System.out.println("Steven has spent $" + spending + " at the store");

        Order order2 = new Order("Steven");
        try {
            order2.addProduct(store.getProduct("Drill"), 1);
            order2.addProduct(store.getProduct("Screwdriver"), 1);
            order2.addProduct(store.getProduct("Bread"), 1);
            order2.addProduct(store.getProduct("Bananas"), 6);
        } catch (Exception e) {
            logger.error("Failed to add product to order", e);
        }
        System.out.println("Order contains:");
        order2.getEntries().forEach((K, V) -> {
            System.out.printf("%d x %s%n", V, K.getName());
        });
        store.placeOrder("Steven", order2);

        spending = store
                .getCustomerSpending("Steven");

        System.out.println("Steven has spent $" + spending + " at the store");

        System.out.println("Most popular products:");
        store.mostPopularProducts(3).forEach((e) -> {
            System.out.printf("%s : %d sold%n", e.getKey().getName(), e.getValue().intValue());
        });

        logger.info("Showcasing error handling");
        try {
            store.getProduct("NonExistingProduct");
        } catch (ProductNotFoundException e) {
            logger.error("Caught product exception: {}", e.getMessage());
        }

        try {
            store.getCustomerSpending("NonExistingCustomer");
        } catch (CustomerNotFoundException e) {
            logger.error("Caught customer exception: {}", e.getMessage());
        }
    }
}

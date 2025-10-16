package com.example;

import com.example.exceptions.CustomerNotFoundException;
import com.example.exceptions.ProductNotFoundException;

public class Demo {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Demo.class);

    private ProductFactory factory = ProductFactory.getInstance();
    private Store store = new Store();

    public void run() {
        // Adding some test products
        {
            store.addProduct(factory.getOrCreateProduct("Nail", "Hardware", 0.15));
            store.addProduct(factory.getOrCreateProduct("Hammer", "Hardware", 9.99));
            store.addProduct(factory.getOrCreateProduct("Drill", "Hardware", 49.99));
            store.addProduct(factory.getOrCreateProduct("Screwdriver", "Hardware", 4.99));
            store.addProduct(factory.getOrCreateProduct("Apples", "Food", 0.99));
            store.addProduct(factory.getOrCreateProduct("Bananas", "Food", 1.29));
            store.addProduct(factory.getOrCreateProduct("Bread", "Food", 2.49));
            store.addProduct(factory.getOrCreateProduct("Milk", "Food", 3.19));
        }

        // Listing products by category
        {
            System.out.println("Products in Hardware category:");
            store.listProducts("Hardware")
                    .forEach((p) -> System.out
                            .println("\t" + p.getCategory() + " : " + p.getName() + " $" + p.getPrice()));

            System.out.println("Products in Food category:");
            store.listProducts("Food")
                    .forEach((p) -> System.out
                            .println("\t" + p.getCategory() + " : " + p.getName() + " $" + p.getPrice()));
        }

        // Creating an order for a customer
        {
            System.out.println("Creating orders for customer Steven");
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
            System.out.println("The order contains:");
            order1.getEntries().forEach((K, V) -> {
                System.out.printf("\t%d x %s%n", V, K.getName());
            });
            store.placeOrder("Steven", order1);
        }

        // Spending analytics
        {
            double spending = store
                    .getCustomerSpending("Steven");

            System.out.println("Steven has spent $" + spending + " at the store");
        }

        // Creating another order for the same customer
        {
            Order order2 = new Order("Steven");
            try {
                order2.addProduct(store.getProduct("Drill"), 1);
                order2.addProduct(store.getProduct("Screwdriver"), 1);
                order2.addProduct(store.getProduct("Bread"), 1);
                order2.addProduct(store.getProduct("Bananas"), 6);
            } catch (Exception e) {
                logger.error("Failed to add product to order", e);
            }
            System.out.println("The order contains:");
            order2.getEntries().forEach((K, V) -> {
                System.out.printf("\t%d x %s%n", V, K.getName());
            });
            store.placeOrder("Steven", order2);
        }

        // Spending analytics again to ensure it updates correctly
        {
            double spending = store
                    .getCustomerSpending("Steven");

            System.out.println("Steven has spent $" + spending + " at the store");
        }

        // Listing most popular products
        {
            System.out.println("Most popular products:");
            store.mostPopularProducts(3).forEach((e) -> {
                System.out.printf("\t%s : %d sold%n", e.getKey().getName(), e.getValue().intValue());
            });
        }

        // Showcasing error handling
        {
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
}

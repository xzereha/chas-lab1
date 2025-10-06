package com.example;

import java.util.List;

public class Order {
    /** Auto incrementing ID */
    private static long nextID = 0;
    private final long orderID;
    private final List<Product> products;
    private final String customerName;

    public Order(List<Product> products, String customerName) {
        this.orderID = nextID++;
        this.products = products;
        this.customerName = customerName;
    }

    public long getOrderID() {
        return orderID;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getCustomerName() {
        return customerName;
    }

    /**
     * Calculates the total cost of the Order
     * 
     * @return the total cost of the order
     */
    public double getCost() {
        // First map to an iterator of double then sum those.
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

}

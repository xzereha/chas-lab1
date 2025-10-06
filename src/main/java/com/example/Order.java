package com.example;

import java.util.List;

public class Order {
    private final String orderID;
    private List<Product> products;
    private final String customerName;

    public Order(String orderID, List<Product> products, String customerName) {
        this.orderID = orderID;
        this.products = products;
        this.customerName = customerName;
    }

    public String getOrderID() {
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

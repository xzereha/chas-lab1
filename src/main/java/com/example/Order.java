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

}

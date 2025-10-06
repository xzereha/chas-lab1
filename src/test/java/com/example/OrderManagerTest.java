package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderManagerTest {
    @Test
    void testStartsEmpty() {
        OrderManager mgr = new OrderManager();
        assertTrue(mgr.getOrders("Alice").isEmpty());
    }

    @Test
    void testAddOrder() {
        OrderManager mgr = new OrderManager();
        Order order = new Order("Alice");
        mgr.addOrder("Alice", order);
        assertFalse(mgr.getOrders("Alice").isEmpty());
    }

    @Test
    void testGetOrdersForNonExistentCustomer() {
        OrderManager mgr = new OrderManager();
        assertTrue(mgr.getOrders("Bob").isEmpty());
        mgr.addOrder("Alice", new Order("Alice"));
        assertFalse(mgr.getOrders("Alice").isEmpty());
        assertTrue(mgr.getOrders("Bob").isEmpty());
    }

    @Test
    void testGetCustomerSpendingEmpty() {
        OrderManager mgr = new OrderManager();
        assertTrue(mgr.getCustomerSpending("Alice").isEmpty());
    }

    @Test
    void testGetCustomerSpending() {
        OrderManager mgr = new OrderManager();
        Order order1 = new Order("Alice");
        Order order2 = new Order("Alice");
        order1.addProduct(new Product("Hammer", "Hardware", 10.0), 2); // 20.0
        order2.addProduct(new Product("Nails", "Hardware", 0.1), 100); // 10.0
        mgr.addOrder("Alice", order1);
        mgr.addOrder("Alice", order2);
        assertEquals(30.0, mgr.getCustomerSpending("Alice").orElse(0.0));
    }
}

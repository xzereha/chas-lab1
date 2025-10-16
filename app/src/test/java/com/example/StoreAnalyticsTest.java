package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StoreAnalyticsTest {
    @Test
    void testGetMostPopularProductsEmpty() {
        OrderRegistry mgr = new OrderRegistry();
        StoreAnalytics analytics = new StoreAnalytics(mgr);
        assertTrue(analytics.getMostPopularProducts(5).isEmpty());
    }

    @Test
    void testGetMostPopularProducts() {
        OrderRegistry mgr = new OrderRegistry();
        StoreAnalytics analytics = new StoreAnalytics(mgr);
        Product hammer = new Product("Hammer", "Hardware", 10.0);
        Product nails = new Product("Nails", "Hardware", 0.1);
        Product saw = new Product("Saw", "Hardware", 15.0);
        Order order1 = new Order("Alice");
        order1.addProduct(hammer, 2);
        order1.addProduct(nails, 100);
        Order order2 = new Order("Bob");
        order2.addProduct(hammer, 1);
        order2.addProduct(saw, 1);
        mgr.addOrder("Alice", order1);
        mgr.addOrder("Bob", order2);

        var popular = analytics.getMostPopularProducts();
        assertEquals(3, popular.size());

        assertEquals(nails, popular.get(0).getKey());
        assertEquals(100, popular.get(0).getValue());
        assertEquals(hammer, popular.get(1).getKey());
        assertEquals(3, popular.get(1).getValue());
        assertEquals(saw, popular.get(2).getKey());
        assertEquals(1, popular.get(2).getValue());
    }
}

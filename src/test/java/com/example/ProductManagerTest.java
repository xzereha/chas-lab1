package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ProductManagerTest {

    @Test
    void testStartsEmpty() {
        ProductRegistry mgr = new ProductRegistry();
        assertTrue(mgr.listProducts().isEmpty());
    }

    @Test
    void testRegisterProduct() {
        ProductRegistry mgr = new ProductRegistry();
        mgr.registerProduct(new Product("Hammer", "Hardware", 100.0));
        assertFalse(mgr.listProducts().isEmpty());
    }

    @Test
    void testListProductsOrder() {
        ProductRegistry mgr = new ProductRegistry();
        Product apple = new Product("Apple", "Fruit", 10.0);
        Product banana = new Product("Banana", "Fruit", 5.0);
        mgr.registerProduct(apple);
        mgr.registerProduct(banana);
        List<Product> products = mgr.listProducts();
        assertEquals(products.get(0), banana);
        assertEquals(products.get(1), apple);
    }

    @Test
    void testListProductsByCategory() {
        ProductRegistry mgr = new ProductRegistry();
        Product apple = new Product("Apple", "Fruit", 10.0);
        Product hammer = new Product("Hammer", "Hardware", 5.0);
        mgr.registerProduct(apple);
        mgr.registerProduct(hammer);

        List<Product> products = mgr.listProducts("Fruit");
        assertEquals(products.size(), 1);
        assertEquals(products.get(0), apple);
    }

    @Test
    void testRemoveProduct() {
        ProductRegistry mgr = new ProductRegistry();
        Product apple = new Product("Apple", "Fruit", 10.0);
        mgr.registerProduct(apple);
        assertTrue(mgr.removeProduct(apple));
        assertTrue(mgr.listProducts().isEmpty());
    }

    @Test
    void testRemoveProductByName() {
        ProductRegistry mgr = new ProductRegistry();
        Product apple = new Product("Apple", "Fruit", 10.0);
        mgr.registerProduct(apple);
        assertTrue(mgr.removeProduct("Apple"));
        assertTrue(mgr.listProducts().isEmpty());
    }

    @Test
    void testGetProductEmpty() {
        ProductRegistry mgr = new ProductRegistry();
        assertTrue(mgr.getProduct("Apple").isEmpty());
    }

    @Test
    void testGetProductInvalid() {
        ProductRegistry mgr = new ProductRegistry();
        assertTrue(mgr.getProduct("InvalidProduct").isEmpty());

    }

    @Test
    void testGetProductValid() {
        ProductRegistry mgr = new ProductRegistry();
        Product apple = new Product("Apple", "Fruit", 10.0);
        mgr.registerProduct(apple);
        assertTrue(mgr.getProduct("Apple").isPresent());
    }
}

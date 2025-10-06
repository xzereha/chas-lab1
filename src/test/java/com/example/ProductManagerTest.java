package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ProductManagerTest {

    @Test
    void testStartsEmpty() {
        ProductManager mgr = new ProductManager();
        assertTrue(mgr.listProducts().isEmpty());
    }

    @Test
    void testRegisterProduct() {
        ProductManager mgr = new ProductManager();
        mgr.registerProduct(new Product("Hammer", "Hardware", 100.0));
        assertFalse(mgr.listProducts().isEmpty());
    }

    @Test
    void testListProductsOrder() {
        ProductManager mgr = new ProductManager();
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
        ProductManager mgr = new ProductManager();
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
        ProductManager mgr = new ProductManager();
        Product apple = new Product("Apple", "Fruit", 10.0);
        mgr.registerProduct(apple);
        assertTrue(mgr.removeProduct(apple));
        assertTrue(mgr.listProducts().isEmpty());
    }

    @Test
    void testRemoveProductByName() {
        ProductManager mgr = new ProductManager();
        Product apple = new Product("Apple", "Fruit", 10.0);
        mgr.registerProduct(apple);
        assertTrue(mgr.removeProduct("Apple"));
        assertTrue(mgr.listProducts().isEmpty());
    }

    @Test
    void testGetProductEmpty() {
        ProductManager mgr = new ProductManager();
        assertTrue(mgr.getProduct("Apple").isEmpty());
    }

    @Test
    void testGetProductInvalid() {
        ProductManager mgr = new ProductManager();
        assertTrue(mgr.getProduct("InvalidProduct").isEmpty());

    }

    @Test
    void testGetProductValid() {
        ProductManager mgr = new ProductManager();
        Product apple = new Product("Apple", "Fruit", 10.0);
        mgr.registerProduct(apple);
        assertTrue(mgr.getProduct("Apple").isPresent());
    }
}

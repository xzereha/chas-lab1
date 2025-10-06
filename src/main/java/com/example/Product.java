package com.example;

public class Product {
    /** Auto incrementing ID */
    private static long nextID = 0;
    private final long id;
    private String name;
    private String category;
    private double price;

    /**
     * Create a new product
     * 
     * @param name     The name of the product, must not be empty.
     * @param category The category the product belongs to, must not be empty.
     * @param price    The price of the product, this mut not be a negative number.
     * @throws IllegalArgumentException if any of the parameters is null or an
     *                                  invalid value.
     */
    public Product(String name, String category, double price) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Name mut be a valid non-empty string");
        if (category == null || category.isEmpty())
            throw new IllegalArgumentException("Category must be a valid non-empty string");
        if (price <= 0.0)
            throw new IllegalArgumentException("Price must not be negative");

        this.id = nextID++;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * Change the name of the product.
     * 
     * @param name The new name of the product, if the name is empty this call is
     *             ignored.
     */
    public void setName(String name) {
        if (!name.isEmpty())
            this.name = name;
    }

    public String getCategory() {
        return category;
    }

    /**
     * Change the category of the product.
     * 
     * @param category The new category of the product, if the category is empty
     *                 this call is ignored.
     */
    public void setCategory(String category) {
        if (!category.isEmpty())
            this.category = category;
    }

    public double getPrice() {
        return price;
    }

    /**
     * Updates the price of the Product
     * 
     * @param price New price, will be ignored if set to a negative value.
     */
    public void setPrice(double price) {
        if (price >= 0.0)
            this.price = price;
    }
}

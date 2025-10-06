package com.example;

public class Product {
    private final String id;
    private String name;
    private String category;
    private double price;

    public Product(String id, String name, String category, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * Change the name of the product.
     * 
     * @param name The new name of the product, this must not be empty.
     */
    public void setName(String name) {
        if (!name.isEmpty())
            this.name = name;
        else
            System.out.println("setName requires a non-empty string");
    }

    public String getCategory() {
        return category;
    }

    /**
     * Change the category of the product.
     * 
     * @param category The new category of the product, this must not be empty.
     */
    public void setCategory(String category) {
        if (!category.isEmpty())
            this.category = category;
        else
            System.out.println("setCategory requires a non-empty string");
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price >= 0.0)
            this.price = price;
        else
            System.out.println("setPrice can't have a negative price");
    }
}

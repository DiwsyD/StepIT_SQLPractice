package org.sql.practice.product.entity;

public class Product {

    private int product_id;
    private final String title;
    private final double price;
    private final Category category;

    public Product(String title, double price, Category category) {
        this.title = title;
        this.price = price;
        this.category = category;
    }

    public Product(int product_id, String title, double price, Category category) {
        this.product_id = product_id;
        this.title = title;
        this.price = price;
        this.category = category;
    }

    public int getProduct_id() {
        return product_id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}

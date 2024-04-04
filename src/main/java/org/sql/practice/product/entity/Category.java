package org.sql.practice.product.entity;

public class Category {

    private int category_id;
    private final String title;

    public Category(String title) {
        this.title = title;
    }

    public Category(int category_id, String title) {
        this.category_id = category_id;
        this.title = title;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category_id=" + category_id +
                ", title='" + title + '\'' +
                '}';
    }
}

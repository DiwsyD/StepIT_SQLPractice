package org.sql.practice.product.query;

public enum ProductQueries {
    //TODO(): Write your queries here

    //CREATE
    INSERT_NEW_PRODUCT(""),

    //READ
    SELECT_ALL_PRODUCTS(""),

    SELECT_PRODUCT_BY_ID(""),

    SELECT_PRODUCT_BY_TITLE(""),

    SELECT_PRODUCTS_BY_CATEGORY_ID(""),

    //UPDATE
    UPDATE_PRODUCT_BY_ID(""),
    UPDATE_PRODUCT_BY_TITLE(""),

    //DELETE
    DELETE_PRODUCT_BY_ID(""),
    DELETE_PRODUCT_BY_TITLE(""),

    ;

    private final String query;

    ProductQueries(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}

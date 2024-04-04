package org.sql.practice.user.query;

public enum UserQueries {
    //TODO(): Write your queries here

    //CREATE
    INSERT_NEW_USER(""),

    //READ
    SELECT_ALL_USERS(""),
    SELECT_USER_BY_ID(""),
    SELECT_USER_BY_EMAIL(""),

    //UPDATE
    UPDATE_USER_BY_ID(""),
    UPDATE_USER_BY_EMAIL(""),

    //DELETE
    DELETE_USER_BY_ID(""),
    DELETE_USER_BY_EMAIL(""),

    ;

    private final String query;

    UserQueries(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}

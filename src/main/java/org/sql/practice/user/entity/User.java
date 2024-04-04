package org.sql.practice.user.entity;

public class User {

    private int user_id;
    private final String user_email;
    private final String user_password;

    public User(String user_email, String user_password) {
        this.user_email = user_email;
        this.user_password = user_password;
    }
    public User(int user_id, String user_email, String user_password) {
        this.user_id = user_id;
        this.user_email = user_email;
        this.user_password = user_password;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUser_email() {
        return user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_email='" + user_email + '\'' +
                ", password='" + user_password + '\'' +
                '}';
    }
}

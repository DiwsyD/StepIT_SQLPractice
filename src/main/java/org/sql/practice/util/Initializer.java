package org.sql.practice.util;

import org.sql.practice.product.persistence.ProductRepository;
import org.sql.practice.product.persistence.impl.ProductRepositoryImpl;
import org.sql.practice.user.persistence.UserRepository;
import org.sql.practice.user.persistence.impl.UserRepositoryImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class Initializer {

    private static Connection connection = null;

    public Initializer() {
        if (connection == null)
            connection = initConnection();
    }

    private Connection initConnection() {
        Connection cn;
        Map<String, String> properties = DatabasePropertiesReader.loadProperties();
        String dbIrl = properties.get("url") + properties.get("schema");
        String username = properties.get("username");
        String password = properties.get("password");
        try {
            cn = DriverManager.getConnection(dbIrl, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cn;
    }

    public ProductRepository initProductRepository() {
        return new ProductRepositoryImpl(connection);
    }

    public UserRepository initUserRepository() {
        return new UserRepositoryImpl(connection);
    }

//    public CartRepository initCartRepository() {
//        return new CartRepositoryImpl(connection);
//    }
}

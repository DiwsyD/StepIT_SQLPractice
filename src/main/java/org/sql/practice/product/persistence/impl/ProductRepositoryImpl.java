package org.sql.practice.product.persistence.impl;

import org.sql.practice.product.entity.Category;
import org.sql.practice.product.entity.Product;
import org.sql.practice.product.persistence.ProductRepository;
import org.sql.practice.product.query.ProductQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    private final Connection dbConnection;

    public ProductRepositoryImpl(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void insert(Product object) {
        try {
            String query = ProductQueries.INSERT_NEW_PRODUCT.getQuery();
            PreparedStatement statement = dbConnection.prepareStatement(query);
            setProductStatement(statement, object);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try {
            String query = ProductQueries.SELECT_ALL_PRODUCTS.getQuery();
            PreparedStatement statement = dbConnection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = buildProductByResultSet(resultSet);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product findById(int id) {
        Product product = null;
        try {
            String query = ProductQueries.SELECT_PRODUCT_BY_ID.getQuery();
            PreparedStatement statement = dbConnection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                product = buildProductByResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public void updateById(int id, Product object) {
        try {
            String query = ProductQueries.UPDATE_PRODUCT_BY_ID.getQuery();
            PreparedStatement statement = dbConnection.prepareStatement(query);
            setProductStatement(statement, object);
            statement.setInt(4, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product findByTitle(String title) {
        String query = ProductQueries.SELECT_PRODUCT_BY_TITLE.getQuery();
        Product product = null;
        try {
            PreparedStatement statement = dbConnection.prepareStatement(query);
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                product = buildProductByResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public List<Product> findByCategoryId(int category_id) {
        List<Product> products = new ArrayList<>();
        try {
            String query = ProductQueries.SELECT_PRODUCTS_BY_CATEGORY_ID.getQuery();
            PreparedStatement statement = dbConnection.prepareStatement(query);
            statement.setInt(1, category_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = buildProductByResultSet(resultSet);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void updateByTitle(String title, Product product) {
        try {
            String query = ProductQueries.UPDATE_PRODUCT_BY_TITLE.getQuery();
            PreparedStatement statement = dbConnection.prepareStatement(query);
            setProductStatement(statement, product);
            statement.setString(4, title);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        try {
            String query = ProductQueries.DELETE_PRODUCT_BY_ID.getQuery();
            PreparedStatement statement = dbConnection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByTitle(String title) {
        try {
            String query = ProductQueries.DELETE_PRODUCT_BY_TITLE.getQuery();
            PreparedStatement statement = dbConnection.prepareStatement(query);
            statement.setString(1, title);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Product buildProductByResultSet(ResultSet resultSet) throws SQLException {
        Category category = new Category(
                resultSet.getInt("category_id"),
                resultSet.getString("category_title")
        );
        return new Product(
                resultSet.getInt("product_id"),
                resultSet.getString("title"),
                resultSet.getDouble("price"),
                category
        );
    }

    private void setProductStatement(PreparedStatement statement, Product product) throws SQLException {
        statement.setString(1, product.getTitle());
        statement.setDouble(2, product.getPrice());
        statement.setInt(3, product.getCategory().getCategory_id());
    }
}

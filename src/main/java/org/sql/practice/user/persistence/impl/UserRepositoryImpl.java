package org.sql.practice.user.persistence.impl;

import org.sql.practice.user.entity.User;
import org.sql.practice.user.persistence.UserRepository;
import org.sql.practice.user.query.UserQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final Connection dbConnection;

    public UserRepositoryImpl(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void insert(User object) {
        try {
            String query = UserQueries.INSERT_NEW_USER.getQuery();
            PreparedStatement statement = dbConnection.prepareStatement(query);
            setUserStatement(statement, object);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            String query = UserQueries.SELECT_ALL_USERS.getQuery();
            PreparedStatement statement = dbConnection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = buildUserByResultSet(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findById(int id) {
        User user = null;
        try {
            String query = UserQueries.SELECT_USER_BY_ID.getQuery();
            PreparedStatement statement = dbConnection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = buildUserByResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public User findByEmail(String user_email) {
        String query = UserQueries.SELECT_USER_BY_EMAIL.getQuery();
        User user = null;
        try {
            PreparedStatement statement = dbConnection.prepareStatement(query);
            statement.setString(1, user_email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = buildUserByResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void updateById(int id, User object) {
        try {
            String query = UserQueries.UPDATE_USER_BY_ID.getQuery();
            PreparedStatement statement = dbConnection.prepareStatement(query);
            setUserStatement(statement, object);
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateByEmail(String user_email, User user) {
        try {
            String query = UserQueries.UPDATE_USER_BY_EMAIL.getQuery();
            PreparedStatement statement = dbConnection.prepareStatement(query);
            setUserStatement(statement, user);
            statement.setString(3, user_email);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        try {
            String query = UserQueries.DELETE_USER_BY_ID.getQuery();
            PreparedStatement statement = dbConnection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByEmail(String user_email) {
        try {
            String query = UserQueries.DELETE_USER_BY_EMAIL.getQuery();
            PreparedStatement statement = dbConnection.prepareStatement(query);
            statement.setString(1, user_email);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User buildUserByResultSet(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getInt("user_id"),
                resultSet.getString("user_email"),
                resultSet.getString("user_password")
        );
    }

    private void setUserStatement(PreparedStatement statement, User user) throws SQLException {
        statement.setString(1, user.getUser_email());
        statement.setString(2, user.getUser_password());
    }
}

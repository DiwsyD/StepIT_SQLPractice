package org.sql.practice.user.persistence;


import org.sql.practice.persistance.Repository;
import org.sql.practice.user.entity.User;

public interface UserRepository extends Repository<User> {

    User findByEmail(String user_email);

    void updateByEmail(String user_email, User user);

    void deleteByEmail(String user_email);
}

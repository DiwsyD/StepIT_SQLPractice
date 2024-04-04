package org.sql.practice;

import org.sql.practice.product.persistence.ProductRepository;
import org.sql.practice.user.persistence.UserRepository;
import org.sql.practice.util.Initializer;

public class Application {

    public static void main(String[] args) {
        Initializer initializer = new Initializer();
        ProductRepository productRepository = initializer.initProductRepository();
        UserRepository userRepository = initializer.initUserRepository();

/*      Test your queries here.

        Examples:

        List<Product> all = productRepository.findAll();

        User user = new User("some_email1@email.com", "somePassword1");
        userRepository.insert(user);
*/
    }
}

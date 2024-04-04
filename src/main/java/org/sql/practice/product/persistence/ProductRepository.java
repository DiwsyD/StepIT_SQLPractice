package org.sql.practice.product.persistence;

import org.sql.practice.persistance.Repository;
import org.sql.practice.product.entity.Product;

import java.util.List;

public interface ProductRepository extends Repository<Product> {

    Product findByTitle(String title);

    List<Product> findByCategoryId(int category_id);

    void updateByTitle(String title, Product product);

    void deleteByTitle(String title);
}

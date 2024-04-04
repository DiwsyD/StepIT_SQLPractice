package org.sql.practice.persistance;

import java.util.List;

public interface Repository<T> {

    void insert(T object);

    List<T> findAll();

    T findById(int id);

    void updateById(int id, T object);

    void deleteById(int id);

}

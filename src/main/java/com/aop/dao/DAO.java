package com.aop.dao;

import java.util.List;

public interface DAO<T> {

    T getByID(Long id);
    void save(T t);

    List<T> getAll();

}

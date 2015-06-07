package com.ekvilan.exchangemarket.dao;


import java.util.List;

public interface BasicCrudDao<E> {
    void save(E entity);
    List<E> getAll();
    E get(String property, Object value);
    List<E> getList(String property, Object value);
}

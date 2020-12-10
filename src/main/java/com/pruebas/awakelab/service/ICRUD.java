package com.pruebas.awakelab.service;

import java.util.List;

public interface ICRUD<T> {

    T register(T t);
    T modify(T t);
    boolean delete(Integer id);
    T findById(Integer id);
    List<T> findAll();

}

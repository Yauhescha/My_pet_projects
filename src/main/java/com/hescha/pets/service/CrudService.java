package com.hescha.pets.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<T> {
    List<T> findAll();

    Optional<T> find(Long id);

    T read(Long id);

    T create(T entity);

    T update(T entity);

    void delete(Long id);
}

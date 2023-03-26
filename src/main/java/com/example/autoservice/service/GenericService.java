package com.example.autoservice.service;

public interface GenericService<E> {
    E add(E entity);

    E update(E entity);

    E findById(Long id);
}

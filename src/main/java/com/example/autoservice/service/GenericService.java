package com.example.autoservice.service;

public interface GenericService<E> {
    E save(E entity);

    E findById(Long id);
}

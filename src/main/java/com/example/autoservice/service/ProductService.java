package com.example.autoservice.service;

import com.example.autoservice.model.Product;
import java.util.List;

public interface ProductService extends GenericService<Product> {
    List<Product> findAllById(List<Long> productIds);
}

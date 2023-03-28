package com.example.autoservice.service.impl;

import com.example.autoservice.model.Product;
import com.example.autoservice.repository.ProductRepository;
import com.example.autoservice.service.ProductService;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find product by id " + id));
    }

    @Override
    public List<Product> findAllById(List<Long> productIds) {
        return productRepository.findAllById(productIds);
    }
}

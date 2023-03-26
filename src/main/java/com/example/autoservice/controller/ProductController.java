package com.example.autoservice.controller;

import com.example.autoservice.dto.request.ProductRequestDto;
import com.example.autoservice.dto.response.ProductResponseDto;
import com.example.autoservice.mapper.impl.ProductMapper;
import com.example.autoservice.model.Product;
import com.example.autoservice.service.ProductService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping
    @ApiOperation(value = "Add a new product",
            notes = "Adds a new product with the specified details.")
    public ProductResponseDto add(@RequestBody @Valid ProductRequestDto requestDto) {
        return productMapper.toDto(productService.add(productMapper.toModel(requestDto)));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an existing product",
            notes = "Updates an existing product with the specified ID and details.")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody @Valid ProductRequestDto requestDto) {
        Product product = productMapper.toModel(requestDto);
        product.setId(id);
        return productMapper.toDto(productService.update(product));
    }
}

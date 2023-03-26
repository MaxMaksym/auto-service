package com.example.autoservice.mapper.impl;

import com.example.autoservice.dto.request.ProductRequestDto;
import com.example.autoservice.dto.response.ProductResponseDto;
import com.example.autoservice.mapper.RequestDtoMapper;
import com.example.autoservice.mapper.ResponseDtoMapper;
import com.example.autoservice.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements
        RequestDtoMapper<ProductRequestDto, Product>,
        ResponseDtoMapper<ProductResponseDto, Product> {
    @Override
    public Product toModel(ProductRequestDto requestDto) {
        Product product = new Product();
        product.setName(requestDto.getName());
        product.setPrice(requestDto.getPrice());
        return product;
    }

    @Override
    public ProductResponseDto toDto(Product product) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(product.getId());
        responseDto.setName(product.getName());
        responseDto.setPrice(product.getPrice());
        return responseDto;
    }
}

package com.example.autoservice.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListOfProductsDto {
    private List<Long> productIds;
}
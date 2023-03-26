package com.example.autoservice.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductAndServiceIds {
    private List<Long> productIds;
    private List<Long> serviceIds;
}

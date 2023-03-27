package com.example.autoservice.dto.response;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceResponseDto {
    private Long id;
    private String name;
    private Long mechanicId;
    private BigDecimal price;
    private String status;
}

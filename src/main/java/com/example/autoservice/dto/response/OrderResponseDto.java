package com.example.autoservice.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDto {
    private Long id;
    private Long carId;
    private String description;
    private LocalDate creationDate;
    private BigDecimal totalCost;
    private LocalDate completionDate;
    private String status;
    private List<Long> serviceIds;
    private List<Long> productIds;
}

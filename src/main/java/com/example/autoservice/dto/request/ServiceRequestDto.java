package com.example.autoservice.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceRequestDto {
    @NotBlank
    private String name;
    @NotNull
    private Long mechanicId;
    @Min(0)
    private BigDecimal price;
    @NotNull
    private Boolean wasPaidToMechanic;
}

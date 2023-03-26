package com.example.autoservice.dto.request;

import com.example.autoservice.lib.Year;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarRequestDto {
    @NotBlank
    private String manufacturer;
    @NotBlank
    private String model;
    @Year
    private Integer year;
    @NotBlank
    private String number;
}

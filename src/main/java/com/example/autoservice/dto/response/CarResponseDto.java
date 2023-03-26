package com.example.autoservice.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarResponseDto {
    private Long id;
    private String manufacturer;
    private String model;
    private Integer year;
    private String number;
}

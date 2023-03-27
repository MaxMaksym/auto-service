package com.example.autoservice.dto.request;

import com.example.autoservice.dto.PersonDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MechanicRequestDto extends PersonDto {
    @NotBlank
    private String fullName;
}

package com.example.autoservice.dto.request;

import com.example.autoservice.dto.Person;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MechanicRequestDto extends Person {
    @NotBlank
    private String fullName;
}

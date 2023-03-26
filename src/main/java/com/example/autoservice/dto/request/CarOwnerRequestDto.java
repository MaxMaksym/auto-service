package com.example.autoservice.dto.request;

import com.example.autoservice.dto.Person;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarOwnerRequestDto extends Person {
    @NotBlank
    private String fullName;
    private List<Long> carIds;
}

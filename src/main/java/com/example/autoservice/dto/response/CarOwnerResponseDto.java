package com.example.autoservice.dto.response;

import com.example.autoservice.dto.Person;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarOwnerResponseDto extends Person {
    private Long id;
    private List<Long> carIds;
    private List<Long> orderIds;
}

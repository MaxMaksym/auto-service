package com.example.autoservice.dto.response;

import com.example.autoservice.dto.Person;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MechanicResponseDto extends Person {
    private Long id;
    private List<Long> serviceIds;
}

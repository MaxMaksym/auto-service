package com.example.autoservice.dto.response;

import com.example.autoservice.dto.PersonDto;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarOwnerResponseDto extends PersonDto {
    private Long id;
    private List<Long> carIds;
    private List<Long> orderIds;
}

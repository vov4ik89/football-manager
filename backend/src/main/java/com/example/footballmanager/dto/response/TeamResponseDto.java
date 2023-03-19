package com.example.footballmanager.dto.response;

import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamResponseDto {
    private Long id;
    private String name;
    private String country;
    private int commission;
    private BigDecimal balance;
    private List<Long> playerIds;
}

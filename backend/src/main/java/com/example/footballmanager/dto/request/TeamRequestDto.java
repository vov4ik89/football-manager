package com.example.footballmanager.dto.request;

import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;

@Getter
public class TeamRequestDto {
    private String name;
    private String country;
    private int commission;
    private BigDecimal balance;
    private List<Long> playerIds;
}

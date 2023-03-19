package com.example.footballmanager.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private int experience;
    private Long teamId;
}

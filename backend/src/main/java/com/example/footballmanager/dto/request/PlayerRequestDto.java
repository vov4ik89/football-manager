package com.example.footballmanager.dto.request;

import lombok.Getter;

@Getter
public class PlayerRequestDto {
    private String firstName;
    private String lastName;
    private int age;
    private int experience;
    private Long teamId;
}

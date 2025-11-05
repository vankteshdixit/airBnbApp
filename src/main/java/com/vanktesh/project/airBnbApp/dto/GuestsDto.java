package com.vanktesh.project.airBnbApp.dto;

import com.vanktesh.project.airBnbApp.entity.User;
import com.vanktesh.project.airBnbApp.entity.enums.Gender;
import lombok.Data;

@Data
public class GuestsDto {
    private Long id;
    private User user;
    private String name;
    private Gender gender;
    private Integer age;
}

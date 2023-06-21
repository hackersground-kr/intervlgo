package com.intervlgo.ourfolio.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserDto {
    @NotNull
    private String userId;
    @NotNull
    private String userPassword;

    private String username;

    private String region;

    private String occupation;
}

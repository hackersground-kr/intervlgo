package com.intervlgo.ourfolio.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserIdPasswordDto {
    private String newId;

    @NotNull
    private String userPassword;
    private String newPassword;
}

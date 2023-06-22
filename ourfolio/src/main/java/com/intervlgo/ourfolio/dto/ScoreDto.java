package com.intervlgo.ourfolio.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;

@Getter
@Setter
public class ScoreDto {
    private UserDto user;
    private PortfolioDto portfolio;
    @Max(5)
    private Byte score;
}

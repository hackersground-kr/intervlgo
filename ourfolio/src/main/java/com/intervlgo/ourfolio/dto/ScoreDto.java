package com.intervlgo.ourfolio.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
public class ScoreDto {
    private UserDto user;
    private PortfolioDto portfolio;

    @Max(5)
    @Min(0)
    private Byte score;
}

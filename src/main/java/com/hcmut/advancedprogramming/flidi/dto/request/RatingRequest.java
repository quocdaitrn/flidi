package com.hcmut.advancedprogramming.flidi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RatingRequest {
    @NotNull
    private Long locationId;
    @NotNull
    private Long userId;
    @NotNull
    private Float point;
}

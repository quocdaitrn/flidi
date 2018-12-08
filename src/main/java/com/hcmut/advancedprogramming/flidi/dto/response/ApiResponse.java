package com.hcmut.advancedprogramming.flidi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ApiResponse {
    private Boolean success;
    private String message;
}

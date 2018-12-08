package com.hcmut.advancedprogramming.flidi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
@Setter
public class PasswordUpdateRequest {
    @NotBlank
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank
    @Size(min = 6, max = 20)
    private String oldPassword;

    @NotBlank
    @Size(min = 6, max = 20)
    private String newPassword;
}

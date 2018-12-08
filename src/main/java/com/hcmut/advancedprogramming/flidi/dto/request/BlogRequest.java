package com.hcmut.advancedprogramming.flidi.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class BlogRequest {

    private Long blogId;

    @NotBlank
    @Size(max = 100)
    private String title;

    @NotBlank
    @Size(max = 255)
    private String description;

    @NotBlank
    @Size(max = 255)
    private String image;

    @NotBlank
    private String detail;

    @NotNull
    private Long locationId;

    private String createOrModifyBy;

    private Boolean isCreate;
}

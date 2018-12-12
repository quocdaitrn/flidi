package com.hcmut.advancedprogramming.flidi.dto.request;

import com.hcmut.advancedprogramming.flidi.persistence.enumtype.CommentDomainType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CommentRequest {
    private Long commentId;

    @NotBlank
    private String detail;

    @NotBlank
    private String type;

    private Long mediaId;

    private Long blogId;

    private Long userId;
}

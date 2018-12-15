package com.hcmut.advancedprogramming.flidi.dto.request;

import com.hcmut.advancedprogramming.flidi.persistence.enumtype.LikeDomainType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LikeRequest {
    private Long id;

    @NotNull
    private LikeDomainType type;

    private Long mediaId;

    private Long blogId;

    @NotNull
    private Long userId;
}

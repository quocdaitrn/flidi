package com.hcmut.advancedprogramming.flidi.dto.request;

import com.hcmut.advancedprogramming.flidi.persistence.enumtype.LikeDomainType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LikeRequest {
    private Long id;

    @NotBlank
    private LikeDomainType type;

    private Long mediaId;

    private Long blogId;

    private Long userId;
}

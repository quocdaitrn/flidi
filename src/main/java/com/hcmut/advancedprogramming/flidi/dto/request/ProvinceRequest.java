package com.hcmut.advancedprogramming.flidi.dto.request;

import com.hcmut.advancedprogramming.flidi.persistence.enumtype.ProvinceDomainStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class ProvinceRequest {
    @NotBlank
    private String name;
    private int popular;
    @NotNull
    private Double longitude;
    @NotNull
    private Double latitude;
    @NotNull
    private ProvinceDomainStatus status;
}

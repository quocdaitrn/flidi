package com.hcmut.advancedprogramming.flidi.dto.request;

import com.hcmut.advancedprogramming.flidi.persistence.enumtype.LocationDomainStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class LocationRequest {
    @NotBlank
    private String name;
    private String description;
    @NotBlank
    private String address;
    @NotNull
    private Long provinceId;
    private String image;
    @NotBlank
    private String detail;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
    @NotNull
    private LocationDomainStatus status;
}

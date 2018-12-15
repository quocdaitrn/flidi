package com.hcmut.advancedprogramming.flidi.dto.response;

import com.hcmut.advancedprogramming.flidi.persistence.enumtype.LocationDomainStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LocationResponse {
    private Long id;
    private String name;
    private String description;
    private String address;
    private ProvinceInLocationResponse province;
    private String image;
    private String detail;
    private Double latitude;
    private Double longitude;
    private LocationDomainStatus status;
}

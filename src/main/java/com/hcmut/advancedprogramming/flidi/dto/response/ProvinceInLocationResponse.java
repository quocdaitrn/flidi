package com.hcmut.advancedprogramming.flidi.dto.response;

import com.hcmut.advancedprogramming.flidi.persistence.enumtype.ProvinceDomainStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProvinceInLocationResponse {
    private Long id;
    private String name;
    private int popular;
    private Double longitude;
    private Double latitude;
    private ProvinceDomainStatus status;
}

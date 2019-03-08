package com.hcmut.advancedprogramming.flidi.dto.response;

import com.hcmut.advancedprogramming.flidi.persistence.enumtype.BlogDomainStatus;
import com.hcmut.advancedprogramming.flidi.persistence.model.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BlogResponse {
    private Long id;
    private Instant createAt;
    private Instant updatedAt;
    private int version;
    private String blogTitle;
    private String description;
    private String image;
    private String detail;
    private BlogDomainStatus status;
    private String locationName;
}

package com.hcmut.advancedprogramming.flidi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@Getter
@Setter
public class UserProfile {
    private Long id;
    private String username;
    private String name;
    private Instant joinedAt;
}
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
    private String email;
    private String username;
    private String name;
    private String firstName;
    private String lastName;
    private Instant joinedAt;
}
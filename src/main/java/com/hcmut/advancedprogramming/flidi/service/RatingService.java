package com.hcmut.advancedprogramming.flidi.service;

import com.hcmut.advancedprogramming.flidi.dto.request.RatingRequest;
import com.hcmut.advancedprogramming.flidi.persistence.model.Rating;

public interface RatingService {
    Rating add(RatingRequest request);
}

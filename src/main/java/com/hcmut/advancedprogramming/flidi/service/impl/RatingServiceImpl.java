package com.hcmut.advancedprogramming.flidi.service.impl;

import com.hcmut.advancedprogramming.flidi.dto.request.RatingRequest;
import com.hcmut.advancedprogramming.flidi.exception.ResourceNotFoundException;
import com.hcmut.advancedprogramming.flidi.persistence.model.Location;
import com.hcmut.advancedprogramming.flidi.persistence.model.Rating;
import com.hcmut.advancedprogramming.flidi.persistence.model.User;
import com.hcmut.advancedprogramming.flidi.persistence.repository.LocationRepository;
import com.hcmut.advancedprogramming.flidi.persistence.repository.RatingRepository;
import com.hcmut.advancedprogramming.flidi.persistence.repository.UserRepository;
import com.hcmut.advancedprogramming.flidi.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Rating add(RatingRequest request) {
        Location location = locationRepository.findById(request.getLocationId())
                .orElseThrow(() -> new ResourceNotFoundException("Location", "id", request.getLocationId()));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", request.getUserId()));

        Rating rating = new Rating();
        rating.setLocation(location);
        rating.setUser(user);
        rating.setPoint(request.getPoint());

        location.setRateCount(location.getRateCount() + 1);
        location.setRateTotal(location.getRateTotal() + request.getPoint());
        locationRepository.save(location);

        return ratingRepository.save(rating);
    }
}

package com.hcmut.advancedprogramming.flidi.web.controller;

import com.hcmut.advancedprogramming.flidi.dto.request.RatingRequest;
import com.hcmut.advancedprogramming.flidi.dto.response.ApiResponse;
import com.hcmut.advancedprogramming.flidi.persistence.model.Comment;
import com.hcmut.advancedprogramming.flidi.persistence.model.Rating;
import com.hcmut.advancedprogramming.flidi.security.CurrentUser;
import com.hcmut.advancedprogramming.flidi.security.UserPrincipal;
import com.hcmut.advancedprogramming.flidi.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<?> addRating(@Valid @RequestBody RatingRequest request, @CurrentUser UserPrincipal currentUser) {
        request.setUserId(currentUser.getId());
        Rating rating = ratingService.add(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{ratingId}")
                .buildAndExpand(rating.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Rating added successfully"));
    }
}

package com.hcmut.advancedprogramming.flidi.web.controller;

import com.hcmut.advancedprogramming.flidi.dto.request.LikeRequest;
import com.hcmut.advancedprogramming.flidi.dto.response.ApiResponse;
import com.hcmut.advancedprogramming.flidi.persistence.model.Like;
import com.hcmut.advancedprogramming.flidi.security.CurrentUser;
import com.hcmut.advancedprogramming.flidi.security.UserPrincipal;
import com.hcmut.advancedprogramming.flidi.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @GetMapping("/{id}")
    public Like findLikesById(@PathVariable Long id) {
        return likeService.findById(id);
    }

    @GetMapping("/blog/{id}")
    public List<Like> findLikesByBlog(@PathVariable Long id) {
        return likeService.findByBlog(id);
    }

    @GetMapping("/media/{id}")
    public List<Like> findLikesByMedia(@PathVariable Long id) {
        return likeService.findByMedia(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addLike(@Valid LikeRequest likeRequest, @CurrentUser UserPrincipal currentUser) {
        likeRequest.setUserId(currentUser.getId());
        Like like = likeService.add(likeRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{likeId}")
                .buildAndExpand(like.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Like added successfully"));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteLike(@PathVariable Long id, @CurrentUser UserPrincipal currentUser) {
        likeService.delete(id, currentUser);

        return ResponseEntity.ok()
                .body(new ApiResponse(true, "Like deleted successfully"));
    }
}

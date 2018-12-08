package com.hcmut.advancedprogramming.flidi.service;

import com.hcmut.advancedprogramming.flidi.dto.request.LikeRequest;
import com.hcmut.advancedprogramming.flidi.persistence.model.Like;
import com.hcmut.advancedprogramming.flidi.security.UserPrincipal;

import java.util.List;

public interface LikeService {
    Like findById(Long id);

    List<Like> findByBlog(Long blogId);

    List<Like> findByMedia(Long mediaId);

    Like add(LikeRequest likeRequest);

    void delete(Long id, UserPrincipal currentUser);
}

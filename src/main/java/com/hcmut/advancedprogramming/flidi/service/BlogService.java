package com.hcmut.advancedprogramming.flidi.service;

import com.hcmut.advancedprogramming.flidi.dto.request.BlogRequest;
import com.hcmut.advancedprogramming.flidi.persistence.model.Blog;
import com.hcmut.advancedprogramming.flidi.security.UserPrincipal;

import java.util.List;

public interface BlogService {
    List<Blog> findAll();

    List<Blog> findByLocation(Long locationId);

    Blog findById(Long id);

    Blog create(BlogRequest blogRequest);

    Blog update(BlogRequest blogRequest);

    void delete(Long id, UserPrincipal currentUser);
}

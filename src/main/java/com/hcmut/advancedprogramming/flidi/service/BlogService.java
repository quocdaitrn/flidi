package com.hcmut.advancedprogramming.flidi.service;

import com.hcmut.advancedprogramming.flidi.dto.request.BlogRequest;
import com.hcmut.advancedprogramming.flidi.dto.response.BlogResponse;
import com.hcmut.advancedprogramming.flidi.persistence.model.Blog;
import com.hcmut.advancedprogramming.flidi.security.UserPrincipal;

import java.util.List;

public interface BlogService {
    List<Blog> findAll();

    List<Blog> findByLocation(Long locationId);

    BlogResponse findById(Long id);

    List<Blog> findByUser(Long id);

    Blog create(BlogRequest blogRequest);

    Blog update(BlogRequest blogRequest);

    void delete(Long id, UserPrincipal currentUser);
}

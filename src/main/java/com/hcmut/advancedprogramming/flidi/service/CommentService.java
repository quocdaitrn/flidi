package com.hcmut.advancedprogramming.flidi.service;

import com.hcmut.advancedprogramming.flidi.dto.request.CommentRequest;
import com.hcmut.advancedprogramming.flidi.persistence.model.Comment;
import com.hcmut.advancedprogramming.flidi.security.UserPrincipal;

import java.util.List;

public interface CommentService {
    Comment findById(Long id);

    List<Comment> findByBlog(Long blogId);

    List<Comment> findByMedia(Long mediaId);

    Comment add(CommentRequest commentRequest);

    Comment edit(CommentRequest commentRequest);

    void delete(Long commentId, UserPrincipal currentUser);
}

package com.hcmut.advancedprogramming.flidi.web.controller;

import com.hcmut.advancedprogramming.flidi.dto.request.CommentRequest;
import com.hcmut.advancedprogramming.flidi.dto.response.ApiResponse;
import com.hcmut.advancedprogramming.flidi.exception.UpdateIdMismatchException;
import com.hcmut.advancedprogramming.flidi.persistence.model.Comment;
import com.hcmut.advancedprogramming.flidi.security.CurrentUser;
import com.hcmut.advancedprogramming.flidi.security.UserPrincipal;
import com.hcmut.advancedprogramming.flidi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/{id}")
    public Comment findCommentsById(@PathVariable Long id) {
        return commentService.findById(id);
    }

    @GetMapping("/blog/{id}")
    public List<Comment> findCommentsByBlog(@PathVariable Long id) {
        return commentService.findByBlog(id);
    }

    @GetMapping("/media/{id}")
    public List<Comment> findByCommentsMedia(@PathVariable Long id) {
        return commentService.findByMedia(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addComment(@Valid @RequestBody CommentRequest commentRequest, @CurrentUser UserPrincipal currentUser) {
        commentRequest.setUserId(currentUser.getId());
        Comment comment = commentService.add(commentRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{commentId}")
                .buildAndExpand(comment.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Comment added successfully"));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> editComment(@PathVariable Long id, @Valid @RequestBody CommentRequest commentRequest, @CurrentUser UserPrincipal currentUser) {
        if (id != commentRequest.getCommentId()) {
            throw new UpdateIdMismatchException();
        }

        commentRequest.setUserId(currentUser.getId());
        commentService.edit(commentRequest);

        return ResponseEntity.ok()
                .body(new ApiResponse(true, "Comment edited successfully"));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteComment(@PathVariable Long id, @CurrentUser UserPrincipal currentUser) {
        commentService.delete(id, currentUser);

        return ResponseEntity.ok()
                .body(new ApiResponse(true, "Comment deleted successfully"));
    }

}

package com.hcmut.advancedprogramming.flidi.web.controller;

import com.hcmut.advancedprogramming.flidi.dto.request.BlogRequest;
import com.hcmut.advancedprogramming.flidi.dto.response.ApiResponse;
import com.hcmut.advancedprogramming.flidi.dto.response.BlogResponse;
import com.hcmut.advancedprogramming.flidi.exception.UpdateIdMismatchException;
import com.hcmut.advancedprogramming.flidi.persistence.model.Blog;
import com.hcmut.advancedprogramming.flidi.security.CurrentUser;
import com.hcmut.advancedprogramming.flidi.security.UserPrincipal;
import com.hcmut.advancedprogramming.flidi.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public List<Blog> findAll() {
        return blogService.findAll();
    }

    @GetMapping("/location/{id}")
    public List<Blog> findByLocation(@PathVariable Long id) {
        return blogService.findByLocation(id);
    }

    @GetMapping("/{id}")
    public BlogResponse findById(@PathVariable Long id) {
        return blogService.findById(id);
    }

    @GetMapping("/users/{id}")
    public List<Blog> findByUser(@PathVariable Long id) {
        return blogService.findByUser(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> createBlog(@Valid @RequestBody BlogRequest blogRequest, @CurrentUser UserPrincipal currentUser) {
        blogRequest.setCreateOrModifyBy(currentUser.getUsername());
        blogRequest.setIsCreate(true);
        Blog blog = blogService.create(blogRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{blogId}")
                .buildAndExpand(blog.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Blog created successfully"));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> updateBlog(@Valid @RequestBody BlogRequest blogRequest, @PathVariable Long id,
                                        @CurrentUser UserPrincipal currentUser) {
        if (blogRequest.getBlogId() != id) {
            throw new UpdateIdMismatchException();
        }

        blogRequest.setCreateOrModifyBy(currentUser.getUsername());
        blogRequest.setIsCreate(false);
        blogService.update(blogRequest);

        return ResponseEntity.ok()
                .body(new ApiResponse(true, "Blog updated successfully"));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteBlog(@PathVariable Long id, @CurrentUser UserPrincipal currentUser) {
        blogService.delete(id, currentUser);
        return ResponseEntity.ok()
                .body(new ApiResponse(true, "Blog deleted successfully"));
    }
}

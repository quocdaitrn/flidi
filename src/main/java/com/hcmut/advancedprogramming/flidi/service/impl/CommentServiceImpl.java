package com.hcmut.advancedprogramming.flidi.service.impl;

import com.hcmut.advancedprogramming.flidi.dto.request.CommentRequest;
import com.hcmut.advancedprogramming.flidi.exception.BadRequestException;
import com.hcmut.advancedprogramming.flidi.exception.ResourceNotFoundException;
import com.hcmut.advancedprogramming.flidi.persistence.enumtype.CommentDomainType;
import com.hcmut.advancedprogramming.flidi.persistence.model.Blog;
import com.hcmut.advancedprogramming.flidi.persistence.model.Comment;
import com.hcmut.advancedprogramming.flidi.persistence.model.MediaGallery;
import com.hcmut.advancedprogramming.flidi.persistence.model.User;
import com.hcmut.advancedprogramming.flidi.persistence.repository.BlogRepository;
import com.hcmut.advancedprogramming.flidi.persistence.repository.CommentRepository;
import com.hcmut.advancedprogramming.flidi.persistence.repository.MediaGalleryRepository;
import com.hcmut.advancedprogramming.flidi.persistence.repository.UserRepository;
import com.hcmut.advancedprogramming.flidi.security.UserPrincipal;
import com.hcmut.advancedprogramming.flidi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static org.apache.commons.lang3.Validate.notNull;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private MediaGalleryRepository mediaGalleryRepository;

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id));
    }

    @Override
    public List<Comment> findByBlog(Long blogId) {
        return commentRepository.findByBlog(blogId);
    }

    @Override
    public List<Comment> findByMedia(Long mediaId) {
        return commentRepository.findByMedia(mediaId);
    }

    @Override
    public Comment add(CommentRequest commentRequest) {
        notNull(commentRequest.getUserId(), "User's id must not be null");

        Comment comment = new Comment();
        Blog blog;
        MediaGallery mediaGallery;

        if (commentRequest.getType() == CommentDomainType.BLOG) {
            notNull(commentRequest.getBlogId(), "Blog's id must not be null");
            blog = blogRepository.findById(commentRequest.getBlogId())
                    .orElseThrow(() -> new ResourceNotFoundException("Blog", "id", commentRequest.getBlogId()));
            comment.setBlog(blog);
        } else {
            notNull(commentRequest.getMediaId(), "Media's id must not be null");
            mediaGallery = mediaGalleryRepository.findById(commentRequest.getMediaId())
                    .orElseThrow(() -> new ResourceNotFoundException("MediaGallery", "id", commentRequest.getMediaId()));
            comment.setMedia(mediaGallery);
        }

        comment.setDetail(commentRequest.getDetail());
        comment.setType(commentRequest.getType());

        User user = userRepository.findById(commentRequest.getUserId())
                        .orElseThrow(() -> new ResourceNotFoundException("User", "id", commentRequest.getUserId()));
        comment.setUser(user);

        return commentRepository.save(comment);
    }

    @Override
    public Comment edit(CommentRequest commentRequest) {
        notNull(commentRequest.getCommentId(), "Comment's id must not be null");
        notNull(commentRequest.getUserId(), "User's id must not be null");

        Comment comment = commentRepository.findById(commentRequest.getCommentId())
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentRequest.getCommentId()));

        if (comment.getUser().getId() != commentRequest.getUserId()) {
            throw new BadRequestException("Only owner of comment can modify it");
        }

        comment.setDetail(commentRequest.getDetail());

        return commentRepository.save(comment);
    }

    @Override
    public void delete(Long commentId, UserPrincipal currentUser) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if (comment.getUser().getId() != currentUser.getId()) {
            throw new BadRequestException("Only owner of comment can delete it");
        }

        commentRepository.delete(comment);
    }
}

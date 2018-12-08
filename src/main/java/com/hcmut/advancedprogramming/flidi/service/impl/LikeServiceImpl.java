package com.hcmut.advancedprogramming.flidi.service.impl;

import com.hcmut.advancedprogramming.flidi.dto.request.LikeRequest;
import com.hcmut.advancedprogramming.flidi.exception.BadRequestException;
import com.hcmut.advancedprogramming.flidi.exception.ResourceNotFoundException;
import com.hcmut.advancedprogramming.flidi.persistence.enumtype.LikeDomainType;
import com.hcmut.advancedprogramming.flidi.persistence.model.Blog;
import com.hcmut.advancedprogramming.flidi.persistence.model.Like;
import com.hcmut.advancedprogramming.flidi.persistence.model.MediaGallery;
import com.hcmut.advancedprogramming.flidi.persistence.model.User;
import com.hcmut.advancedprogramming.flidi.persistence.repository.BlogRepository;
import com.hcmut.advancedprogramming.flidi.persistence.repository.LikeRepository;
import com.hcmut.advancedprogramming.flidi.persistence.repository.MediaGalleryRepository;
import com.hcmut.advancedprogramming.flidi.persistence.repository.UserRepository;
import com.hcmut.advancedprogramming.flidi.security.UserPrincipal;
import com.hcmut.advancedprogramming.flidi.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static org.apache.commons.lang3.Validate.notNull;

@Service
@Transactional
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private MediaGalleryRepository mediaGalleryRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Like findById(Long id) {
        return likeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Like", "id", id));
    }

    @Override
    public List<Like> findByBlog(Long blogId) {
        return likeRepository.findByBlog(blogId);
    }

    @Override
    public List<Like> findByMedia(Long mediaId) {
        return likeRepository.findByMedia(mediaId);
    }

    @Override
    public Like add(LikeRequest likeRequest) {
        notNull(likeRequest.getUserId(), "User's id must not be null");

        Like like = new Like();
        Blog blog;
        MediaGallery mediaGallery;

        if (likeRequest.getType() == LikeDomainType.BLOG) {
            notNull(likeRequest.getBlogId(), "Blog's id must not be null");
            blog = blogRepository.findById(likeRequest.getBlogId())
                    .orElseThrow(() -> new ResourceNotFoundException("Blog", "id", likeRequest.getBlogId()));
            like.setBlog(blog);
        } else {
            notNull(likeRequest.getMediaId(), "Media's id must not be null");
            mediaGallery = mediaGalleryRepository.findById(likeRequest.getMediaId())
                    .orElseThrow(() -> new ResourceNotFoundException("MediaGallery", "id", likeRequest.getMediaId()));
            like.setMedia(mediaGallery);
        }

        like.setType(likeRequest.getType());

        User user = userRepository.findById(likeRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", likeRequest.getUserId()));
        like.setUser(user);

        return likeRepository.save(like);
    }

    @Override
    public void delete(Long id, UserPrincipal currentUser) {
        Like like = likeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Like", "id", id));

        if (like.getUser().getId() != currentUser.getId()) {
            throw new BadRequestException("Only owner of like can delete it");
        }

        likeRepository.delete(like);
    }
}

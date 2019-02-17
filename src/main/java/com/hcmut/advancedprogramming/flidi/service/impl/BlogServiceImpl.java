package com.hcmut.advancedprogramming.flidi.service.impl;

import com.hcmut.advancedprogramming.flidi.dto.request.BlogRequest;
import com.hcmut.advancedprogramming.flidi.exception.BadRequestException;
import com.hcmut.advancedprogramming.flidi.exception.ResourceNotFoundException;
import com.hcmut.advancedprogramming.flidi.persistence.enumtype.BlogDomainStatus;
import com.hcmut.advancedprogramming.flidi.persistence.model.Blog;
import com.hcmut.advancedprogramming.flidi.persistence.model.Location;
import com.hcmut.advancedprogramming.flidi.persistence.model.User;
import com.hcmut.advancedprogramming.flidi.persistence.repository.BlogRepository;
import com.hcmut.advancedprogramming.flidi.persistence.repository.LocationRepository;
import com.hcmut.advancedprogramming.flidi.persistence.repository.UserRepository;
import com.hcmut.advancedprogramming.flidi.security.UserPrincipal;
import com.hcmut.advancedprogramming.flidi.service.BlogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static org.apache.commons.lang3.Validate.notNull;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public List<Blog> findByLocation(Long locationId) {
        return blogRepository.findByLocationId(locationId);
    }

    @Override
    public Blog findById(Long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog", "id", id));
    }

    @Override
    public List<Blog> findByUser(Long id) {
        return blogRepository.findByUserId(id);
    }

    @Override
    public Blog create(BlogRequest blogRequest) {
        blogRequest.setBlogId(null);
        Blog blog = createBlog(blogRequest);

        return blogRepository.save(blog);
    }

    @Override
    public Blog update(BlogRequest blogRequest) {
        notNull(blogRequest.getBlogId(), "Blog Id must not be null");
        Blog blog = createBlog(blogRequest);

        return blogRepository.save(blog);
    }

    @Override
    public void delete(Long id, UserPrincipal currentUser) {
        Blog blog = blogRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Blog", "id", id));
        if (!StringUtils.equals(blog.getUser().getUsername(), currentUser.getUsername())) {
            throw new BadRequestException("Only user who created blog can delete one");
        }

        blogRepository.delete(blog);
    }

    private Blog createBlog(BlogRequest blogRequest) {
        notNull(blogRequest.getCreateOrModifyBy(), "User who created or modified must not be null");

        Blog blog;
        User user = userRepository.findByUsername(blogRequest.getCreateOrModifyBy())
                        .orElseThrow(() -> new ResourceNotFoundException("User", "username", blogRequest.getCreateOrModifyBy()));

        if (blogRequest.getIsCreate()) {
            blog = new Blog();
            blog.setUser(user);
            blog.setStatus(BlogDomainStatus.ACTIVE);
        } else {
            blog = blogRepository.findById(blogRequest.getBlogId())
                .orElseThrow(() -> new ResourceNotFoundException("Blog", "id", blogRequest.getBlogId()));
            notNull(blog.getUser());

            if (user.getId() != blog.getUser().getId()) {
                throw new BadRequestException("Only user who created blog can modify one");
            }
        }

        blog.setBlogTitle(blogRequest.getTitle());
        blog.setDescription(blogRequest.getDescription());
        blog.setDetail(blogRequest.getDetail());
        blog.setImage(blogRequest.getImage());

        Location location = locationRepository.findById(blogRequest.getLocationId())
                                .orElseThrow(() -> new ResourceNotFoundException("Location", "id", blogRequest.getLocationId()));
        blog.setLocation(location);

        return blog;
    }
}

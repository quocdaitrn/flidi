package com.hcmut.advancedprogramming.flidi.persistence.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hcmut.advancedprogramming.flidi.persistence.enumtype.BlogDomainStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "blogs")
@AttributeOverride(name = "id", column = @Column(name = "blog_id"))
public class Blog extends AbstractEntity {
    @Column(name = "blog_title", nullable = false)
    private String blogTitle;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "detail", nullable = false)
    private String detail;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    @JsonBackReference
    private Location location;

    @Enumerated
    @Column(name = "status", columnDefinition = "smallint", nullable = false)
    private BlogDomainStatus status;

    public Blog() {
        super();
    }

    public Blog(String blogTitle, String description, String image, String detail, User user,
                Location location, BlogDomainStatus status) {
        this.blogTitle = blogTitle;
        this.description = description;
        this.image = image;
        this.detail = detail;
        this.user = user;
        this.location = location;
        this.status = status;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public BlogDomainStatus getStatus() {
        return status;
    }

    public void setStatus(BlogDomainStatus status) {
        this.status = status;
    }
}

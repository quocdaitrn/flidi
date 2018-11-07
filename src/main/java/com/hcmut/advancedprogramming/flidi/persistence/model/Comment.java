package com.hcmut.advancedprogramming.flidi.persistence.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hcmut.advancedprogramming.flidi.persistence.enumtype.CommentDomainType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
@AttributeOverride(name = "id", column = @Column(name = "comment_id"))
public class Comment extends AbstractEntity {
    @Column(name = "detail", nullable = false)
    private String detail;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private CommentDomainType type;

    @ManyToOne
    @JoinColumn(name = "media_id")
    private MediaGallery media;

    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Comment() {
        super();
    }

    public Comment(String detail, CommentDomainType type, MediaGallery media, Blog blog, User user) {
        this.detail = detail;
        this.type = type;
        this.media = media;
        this.blog = blog;
        this.user = user;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public CommentDomainType getType() {
        return type;
    }

    public void setType(CommentDomainType type) {
        this.type = type;
    }

    public MediaGallery getMedia() {
        return media;
    }

    public void setMedia(MediaGallery media) {
        this.media = media;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

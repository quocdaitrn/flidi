package com.hcmut.advancedprogramming.flidi.persistence.model;

import com.hcmut.advancedprogramming.flidi.persistence.enumtype.LikeDomainType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "likes")
@AttributeOverride(name = "id", column = @Column(name = "like_id"))
public class Like extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private LikeDomainType type;

    @ManyToOne
    @JoinColumn(name = "media_id")
    private MediaGallery media;

    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Like() {
        super();
    }

    public Like(LikeDomainType type, MediaGallery media, Blog blog, User user) {
        this.type = type;
        this.media = media;
        this.blog = blog;
        this.user = user;
    }

    public LikeDomainType getType() {
        return type;
    }

    public void setType(LikeDomainType type) {
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

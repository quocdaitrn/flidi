package com.hcmut.advancedprogramming.flidi.persistence.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "media_gallery")
@AttributeOverride(name = "id", column = @Column(name = "media_id"))
public class MediaGallery extends AbstractEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "gallery_id", nullable = false)
    private Gallery gallery;

    @Column(name = "media_name", nullable = false)
    private String mediaName;

    @Column(name = "description")
    private String description;

    @Column(name = "imgurl", nullable = false)
    private String imgurl;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public MediaGallery() {
        super();
    }

    public MediaGallery(Gallery gallery, String description, String imgurl, User user) {
        this.gallery = gallery;
        this.description = description;
        this.imgurl = imgurl;
        this.user = user;
    }

    public Gallery getGallery() {
        return gallery;
    }

    public void setGallery(Gallery gallery) {
        this.gallery = gallery;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

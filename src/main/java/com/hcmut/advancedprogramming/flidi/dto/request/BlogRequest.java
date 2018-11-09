package com.hcmut.advancedprogramming.flidi.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BlogRequest {

    private Long blogId;

    @NotBlank
    @Size(max = 100)
    private String title;

    @NotBlank
    @Size(max = 255)
    private String description;

    @NotBlank
    @Size(max = 255)
    private String image;

    @NotBlank
    private String detail;

    @NotNull
    private Long locationId;

    private String createOrModifyBy;

    private Boolean isCreate;

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getCreateOrModifyBy() {
        return createOrModifyBy;
    }

    public void setCreateOrModifyBy(String createOrModifyBy) {
        this.createOrModifyBy = createOrModifyBy;
    }

    public Boolean isCreate() {
        return isCreate;
    }

    public void setCreate(Boolean create) {
        isCreate = create;
    }
}

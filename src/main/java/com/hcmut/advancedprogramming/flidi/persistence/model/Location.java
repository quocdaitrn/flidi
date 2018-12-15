package com.hcmut.advancedprogramming.flidi.persistence.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hcmut.advancedprogramming.flidi.persistence.enumtype.LocationDomainStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "locations")
@AttributeOverride(name = "id", column = @Column(name = "location_id"))
public class Location extends AbstractEntity {
    @Column(name = "location_name", nullable = false)
    private String locationName;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "address", nullable = false)
    private String address;

    @ManyToOne(optional = false)
    @JoinColumn(name = "province_id", nullable = false)
    @JsonBackReference
    private Province province;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
    @JsonManagedReference
    private List<Blog> blogs;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "detail", nullable = false)
    private String detail;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Enumerated
    @Column(name = "status", columnDefinition = "smallint", nullable = false)
    private LocationDomainStatus status;

    public Location() {
        super();
    }

    public Location(String locationName, String description, String address, Province province, String image,
                    String detail, Double longitude, Double latitude, LocationDomainStatus status) {
        this.locationName = locationName;
        this.description = description;
        this.address = address;
        this.province = province;
        this.image = image;
        this.detail = detail;
        this.longitude = longitude;
        this.latitude = latitude;
        this.status = status;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
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

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public LocationDomainStatus getStatus() {
        return status;
    }

    public void setStatus(LocationDomainStatus status) {
        this.status = status;
    }
}

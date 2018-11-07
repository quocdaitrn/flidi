package com.hcmut.advancedprogramming.flidi.persistence.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "galleries")
@AttributeOverride(name = "id", column = @Column(name = "gallery_id"))
public class Gallery extends AbstractEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(name = "title", nullable = false)
    private String title;

    public Gallery() {
        super();
    }

    public Gallery(Location location, String title) {
        this.location = location;
        this.title = title;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

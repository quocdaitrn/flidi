package com.hcmut.advancedprogramming.flidi.persistence.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
@AttributeOverride(name = "id", column = @Column(name = "rate_id"))
public class Rating extends AbstractEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    @JsonBackReference
    private Location location;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "point")
    private Float point;

    public Rating() {
        super();
    }

    public Rating(Location location, User user, Float point) {
        this.location = location;
        this.user = user;
        this.point = point;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Float getPoint() {
        return point;
    }

    public void setPoint(Float point) {
        this.point = point;
    }
}

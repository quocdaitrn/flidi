package com.hcmut.advancedprogramming.flidi.persistence.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_meta")
public class UserMeta extends AbstractEntity {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "about")
    private String about;

    @Column(name = "hobby")
    private String hobby;

    @Column(name = "favorite_quote")
    private String favoriteQuote;

    @Column(name = "address")
    private String address;

    @Column(name = "university")
    private String university;

    @Column(name = "job")
    private String job;

    @Column(name = "age")
    private Integer age;

    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(name = "cover_photo")
    private String coverPhoto;

    public UserMeta() {
        super();
    }

    public UserMeta(User user, String about, String hobby, String favoriteQuote, String address, String university,
                    String job, Integer age, Date birthday, String coverPhoto) {
        this.user = user;
        this.about = about;
        this.hobby = hobby;
        this.favoriteQuote = favoriteQuote;
        this.address = address;
        this.university = university;
        this.job = job;
        this.age = age;
        this.birthday = birthday;
        this.coverPhoto = coverPhoto;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getFavoriteQuote() {
        return favoriteQuote;
    }

    public void setFavoriteQuote(String favoriteQuote) {
        this.favoriteQuote = favoriteQuote;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }
}

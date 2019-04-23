package com.group8.dbms.entity;


import javax.persistence.*;

@Entity
@Table(name = "user_")
public class User {

    @Id
    @Column(name = "user_id")
    private String uid;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "review_count")
    private int reviewCount;

    @Column(name = "fans")
    private int fans;

    @Column(name = "yelping_since")
    private String registrationTime;

    @Column(name = "average_stars")
    private Double averageStars;

    @Column(name = "useful")
    private int useful;

    @Column(name = "funny")
    private int funny;

    @Column(name = "cool")
    private int cool;

//    @OneToMany(fetch = FetchType.LAZY,
//            mappedBy = "user",
//            cascade = CascadeType.ALL)
//    private List<Review> reviews;


    public User() {

    }


    public User(String uid, String name, String password, int reviewCount, int fans, String registrationTime,
                Double averageStars, int useful, int funny, int cool) {
        this.uid = uid;
        this.name = name;
        this.password = password;
        this.reviewCount = reviewCount;
        this.fans = fans;
        this.registrationTime = registrationTime;
        this.averageStars = averageStars;
        this.useful = useful;
        this.funny = funny;
        this.cool = cool;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }

    public int getUseful() {
        return useful;
    }

    public void setUseful(int useful) {
        this.useful = useful;
    }

    public int getFunny() {
        return funny;
    }

    public void setFunny(int funny) {
        this.funny = funny;
    }

    public int getCool() {
        return cool;
    }

    public void setCool(int cool) {
        this.cool = cool;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public Double getAverageStars() {
        return averageStars;
    }

    public void setAverageStars(Double averageStars) {
        this.averageStars = averageStars;
    }

//    public List<Review> getReviews() {
//        return reviews;
//    }
//
//    public void setReviews(List<Review> reviews) {
//        this.reviews = reviews;
//    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", reviewCount=" + reviewCount +
                ", fans=" + fans +
                ", registrationTime='" + registrationTime + '\'' +
                ", averageStars=" + averageStars +
                ", useful=" + useful +
                ", funny=" + funny +
                ", cool=" + cool +
                '}';
    }
}

package com.group8.dbms.entity;

import javax.persistence.*;

@Entity
@Table(name = "yuanwang.review")
public class Review {

    @Id
    @Column(name = "review_id")
    private String rid;

    @Column(name = "user_id")
    private String uid;

    @Column(name = "business_id")
    private String bid;

    @Column(name = "text")
    private String text;

    @Column(name = "time")
    private String date;

    @Column(name = "stars")
    private int star;

    @Column(name = "funny")
    private int funny;

    @Column(name = "cool")
    private int cool;

    @Column(name = "useful")
    private int useful;

//    @ManyToOne(fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @ManyToOne(fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    @JoinColumn(name = "business_id")
//    private Business business;

    public Review() {

    }

    public Review(String rid, String uid, String bid, String text, String date, int star, int funny, int cool, int useful) {
        this.rid = rid;
        this.uid = uid;
        this.bid = bid;
        this.text = text;
        this.date = date;
        this.star = star;
        this.funny = funny;
        this.cool = cool;
        this.useful = useful;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
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

    public int getUseful() {
        return useful;
    }

    public void setUseful(int useful) {
        this.useful = useful;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Business getBusiness() {
//        return business;
//    }
//
//    public void setBusiness(Business business) {
//        this.business = business;
//    }

    @Override
    public String toString() {
        return "Review{" +
                "rid='" + rid + '\'' +
                ", text='" + text + '\'' +
                ", date='" + date + '\'' +
                ", star=" + star +
                ", funny=" + funny +
                ", cool=" + cool +
                ", useful=" + useful +
                '}';
    }
}

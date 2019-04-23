package com.group8.dbms.entity;

import javax.persistence.*;

@Entity
@Table(name = "business")
public class Business {

    @Id
    @Column(name = "business_id")
    private String bid;

    @Column(name = "name")
    private String name;

    @Column(name = "categories")
    private String category;

    @Column(name = "password")
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "postal_code")
    private String zip;

    @Column(name = "stars")
    private double star;

    @Column(name = "review_count")
    private int reviewCount;

    @Column(name = "wifi")
    private String wifi;

    @Column(name = "has_tv")
    private String hasTV;

    @Column(name = "price_range")
    private String priceRange;

    @Column(name = "good_for_kids")
    private String goodForKids;

//    @OneToMany(fetch = FetchType.LAZY,
//            mappedBy = "business",
//            cascade = CascadeType.ALL)
//    private List<Review> reviews;

    public Business() {

    }


    public Business(String bid, String name, String category,
                    String password, String address,
                    String city, String state, String zip,
                    double star, int reviewCount, String wifi,
                    String hasTV, String priceRange, String goodForKids) {
        this.bid = bid;
        this.name = name;
        this.category = category;
        this.password = password;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.star = star;
        this.reviewCount = reviewCount;
        this.wifi = wifi;
        this.hasTV = hasTV;
        this.priceRange = priceRange;
        this.goodForKids = goodForKids;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
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

    public double getStar() {
        return star;
    }

    public void setStar(double star) {
        this.star = star;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getHasTV() {
        return hasTV;
    }

    public void setHasTV(String hasTV) {
        this.hasTV = hasTV;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public String getGoodForKids() {
        return goodForKids;
    }

    public void setGoodForKids(String goodForKids) {
        this.goodForKids = goodForKids;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
        return "Business{" +
                "bid='" + bid + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", star=" + star +
                ", reviewCount=" + reviewCount +
                ", wifi='" + wifi + '\'' +
                ", hasTV='" + hasTV + '\'' +
                ", priceRange='" + priceRange + '\'' +
                ", goodForKids='" + goodForKids + '\'' +
                '}';
    }
}


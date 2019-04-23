package com.group8.dbms.service;

import com.group8.dbms.entity.Review;

import java.util.List;

public interface ReviewService {

    Review findById(String rid);

    void save(Review review);

    void deleteById(String rid);

    List<Review> findAllByUid(String uid);

    List<Review> findAllByBid(String bid);

    List<Object[]> reviewNumByYear();

    List<Object[]> reviewNumByMonth(String year);

    List<Object[]> reviewNumByYearAndCategoryAndCity(String city, String state, String category);

    List<Object[]> reviewNumByYearAndCategoryAndState(String state, String category);

    List<Object[]> reviewNumByMonthAndCategoryAndCity(String city, String state, String year, String category);

    List<Object[]> reviewNumByCategoryAndMonthAndState(String state, String year, String category);

    List<Object[]> reviewNumByCategoryAndMonth(String year, String category);

    List<Object[]> reviewNumByYearAndCategory(String category);

    List<Object[]> avgStarsByYearAndCategoryAndCity(String city, String state, String category);

    List<Object[]> avgStarsByYearAndCategoryAndState(String state, String category);

    List<Object[]> avgStarsByMonthAndCategoryAndCity(String city, String state, String year, String category);

    List<Object[]> avgStarsByCategoryAndMonthAndState(String state, String year, String category);

    List<Object[]> avgStarsByCategoryAndMonth(String year, String category);

    List<Object[]> avgStarsByYearAndCategory(String category);

    List<Object[]> activeUsersByYear();

    List<Object[]> activeUsersByMonth(String year);

    int countTotalReviews();


}

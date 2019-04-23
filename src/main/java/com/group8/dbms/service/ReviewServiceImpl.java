package com.group8.dbms.service;

import com.group8.dbms.dao.ReviewRepository;
import com.group8.dbms.entity.Review;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository theReviewRepository) {
        reviewRepository = theReviewRepository;
    }

    @Override
    public Review findById(String rid) {
        Optional<Review> result = reviewRepository.findById(rid);
        Review review = null;
        if (result.isPresent()) {
            review = result.get();
        }
        return review;
    }

    @Override
    public void save(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public void deleteById(String rid) {
        reviewRepository.deleteById(rid);
    }

    @Override
    public List<Review> findAllByUid(String uid) {
        return reviewRepository.findAllByUid(uid);
    }

    @Override
    public List<Review> findAllByBid(String bid) {
        return reviewRepository.findAllByBid(bid);
    }

    @Override
    public List<Object[]> reviewNumByYear() {
        return reviewRepository.reviewNumByYear();
    }

    @Override
    public  List<Object[]> reviewNumByMonth(String year) { return reviewRepository.reviewNumByMonth(year); }

    @Override
    public List<Object[]> reviewNumByYearAndCategoryAndCity(String city, String state,
                                                            String category) {
        return reviewRepository.reviewNumByYearAndCategoryAndCity(city, state, category);
    }

    @Override
    public List<Object[]> reviewNumByYearAndCategoryAndState(String state, String category) {
        return reviewRepository.reviewNumByYearAndCategoryAndState(state, category);
    }

    @Override
    public List<Object[]> reviewNumByMonthAndCategoryAndCity(String city, String state,
                                                             String year, String category) {
        return reviewRepository.reviewNumByMonthAndCategoryAndCity(city, state, year, category);
    }

    @Override
    public List<Object[]> reviewNumByCategoryAndMonthAndState(String state, String year,
                                                              String category) {
        return reviewRepository.reviewNumByCategoryAndMonthAndState(state, year, category);
    }

    @Override
    public List<Object[]> reviewNumByCategoryAndMonth(String year, String category) {
        return reviewRepository.reviewNumByCategoryAndMonth(year, category);
    }

    @Override
    public List<Object[]> reviewNumByYearAndCategory(String category) {
        return reviewRepository.reviewNumByYearAndCategory(category);
    }

    @Override
    public List<Object[]> avgStarsByYearAndCategoryAndCity(String city, String state,
                                                           String category) {
        return reviewRepository.avgStarsByYearAndCategoryAndCity(city, state, category);
    }

    @Override
    public List<Object[]> avgStarsByYearAndCategoryAndState(String state, String category) {
        return reviewRepository.avgStarsByYearAndCategoryAndState(state, category);
    }

    @Override
    public List<Object[]> avgStarsByMonthAndCategoryAndCity(String city, String state,
                                                            String year, String category) {
        return reviewRepository.avgStarsByMonthAndCategoryAndCity(city, state, year, category);
    }

    @Override
    public List<Object[]> avgStarsByCategoryAndMonthAndState(String state,
                                                             String year, String category) {
        return reviewRepository.avgStarsByCategoryAndMonthAndState(state, year, category);
    }

    @Override
    public List<Object[]> avgStarsByCategoryAndMonth(String year, String category) {
        return reviewRepository.avgStarsByCategoryAndMonth(year, category);
    }

    @Override
    public List<Object[]> avgStarsByYearAndCategory(String category) {
        return reviewRepository.avgStarsByYearAndCategory(category);
    }

    @Override
    public List<Object[]> activeUsersByYear() {
        return reviewRepository.activeUsersByYear();
    }

    @Override
    public int countTotalReviews() {
        return reviewRepository.countTotalReviews();
    }

    @Override
    public List<Object[]> activeUsersByMonth(String year) {
        return reviewRepository.activeUsersByMonth(year);
    }
}

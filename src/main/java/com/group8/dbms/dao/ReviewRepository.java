package com.group8.dbms.dao;

import com.group8.dbms.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, String> {

    @Query(value = "select * from yuanwang.review where user_id=?1", nativeQuery = true)
    List<Review> findAllByUid(String uid);

    @Query(value = "select * from yuanwang.review where business_id=?1", nativeQuery = true)
    List<Review> findAllByBid(String bid);

//    @Query(value = "select to_char(time, 'yyyy-mm'), avg(stars) " +
//                    " from yuanwang.review review " +
//                    " where business_id=?1 and to_char(time, 'yyyy')=?2 " +
//                    " group by to_char(time, 'yyyy-mm') " +
//                    " order by to_char(time, 'yyyy-mm')",
//            nativeQuery = true)
//    List<Object[]> avgStarsByMonth(String bid, String year);
//
//    @Query(value = "select to_char(time, 'yyyy'), avg(stars) " +
//                    " from yuanwang.review review " +
//                    " where business_id=?1 " +
//                    " group by to_char(time, 'yyyy') " +
//                    " order by to_char(time, 'yyyy')",
//            nativeQuery = true)
//    List<Object[]> avgStarsByYear(String bid);

    @Query(value = "select to_char(time, 'yyyy'), count(*)" +
                    " from yuanwang.review review " +
                    " group by to_char(time, 'yyyy') " +
                    " order by to_char(time, 'yyyy')",
            nativeQuery = true)
    List<Object[]> reviewNumByYear();

    @Query(value = "select to_char(time, 'yyyy-mm'), count(*) " +
                    " from yuanwang.review review " +
                    " where to_char(time, 'yyyy')=?1 " +
                    " group by to_char(time, 'yyyy-mm') " +
                    " order by to_char(time, 'yyyy-mm')",
            nativeQuery = true)
    List<Object[]> reviewNumByMonth(String year);


    @Query(value = "select to_char(review.time, 'yyyy'), count(*)" +
            " from yuanwang.review review, business " +
            " where business.business_id = review.business_id and " +
            " lower(business.city) = ?1 and " +
            " lower(business.state) = ?2 and " +
            " lower(business.categories) like ?3 " +
            " group by to_char(review.time, 'yyyy') " +
            " order by to_char(review.time, 'yyyy')",
            nativeQuery = true)
    List<Object[]> reviewNumByYearAndCategoryAndCity(String city, String state, String category);

    @Query(value = "select to_char(review.time, 'yyyy'), count(*) " +
            " from yuanwang.review review, business " +
            " where business.business_id = review.business_id and " +
            " lower(business.state) = ?1 and " +
            " lower(business.categories) like ?2 " +
            " group by to_char(review.time, 'yyyy') " +
            " order by to_char(review.time, 'yyyy')",
            nativeQuery = true)
    List<Object[]> reviewNumByYearAndCategoryAndState(String state, String category);

    @Query(value = "select to_char(review.time, 'yyyy-mm'), count(review.review_id) " +
            " from yuanwang.review review, business " +
            " where to_char(review.time, 'yyyy')=?3 and " +
            " business.business_id = review.business_id and " +
            " lower(business.city) = ?1 and " +
            " lower(business.state) = ?2 and " +
            " lower(business.categories) like ?4 " +
            " group by to_char(review.time, 'yyyy-mm') " +
            " order by to_char(review.time, 'yyyy-mm')",
            nativeQuery = true)
    List<Object[]> reviewNumByMonthAndCategoryAndCity(String city, String state, String year, String category);

    @Query(value = "select to_char(review.time, 'yyyy-mm'), count(review.review_id) " +
            " from yuanwang.review review, business " +
            " where to_char(review.time, 'yyyy')=?2 and " +
            " business.business_id = review.business_id and " +
            " lower(business.state) = ?1 and " +
            " lower(business.categories) like ?3 " +
            " group by to_char(review.time, 'yyyy-mm') " +
            " order by to_char(review.time, 'yyyy-mm')",
            nativeQuery = true)
    List<Object[]> reviewNumByCategoryAndMonthAndState(String state, String year, String category);

    @Query(value = "select to_char(review.time, 'yyyy-mm'), count(review.review_id) " +
            " from yuanwang.review review, business " +
            " where to_char(review.time, 'yyyy')=?1 and " +
            " business.business_id = review.business_id and " +
            " lower(business.categories) like ?2 " +
            " group by to_char(review.time, 'yyyy-mm') " +
            " order by to_char(review.time, 'yyyy-mm')",
            nativeQuery = true)
    List<Object[]> reviewNumByCategoryAndMonth(String year, String category);

    @Query(value = "select to_char(review.time, 'yyyy'), count(*)" +
            " from yuanwang.review review, business " +
            " where business.business_id = review.business_id and " +
            " lower(business.categories) like ?1 " +
            " group by to_char(review.time, 'yyyy') " +
            " order by to_char(review.time, 'yyyy')",
            nativeQuery = true)
    List<Object[]> reviewNumByYearAndCategory(String category);


    @Query(value = "select to_char(review.time, 'yyyy'), avg(review.stars)" +
            " from yuanwang.review review, business " +
            " where business.business_id = review.business_id and " +
            " lower(business.city) = ?1 and " +
            " lower(business.state) = ?2 and " +
            " lower(business.categories) like ?3 " +
            " group by to_char(review.time, 'yyyy') " +
            " order by to_char(review.time, 'yyyy')",
            nativeQuery = true)
    List<Object[]> avgStarsByYearAndCategoryAndCity(String city, String state, String category);

    @Query(value = "select to_char(review.time, 'yyyy'), avg(review.stars) " +
            " from yuanwang.review review, business " +
            " where business.business_id = review.business_id and " +
            " lower(business.state) = ?1 and " +
            " lower(business.categories) like ?2 " +
            " group by to_char(review.time, 'yyyy') " +
            " order by to_char(review.time, 'yyyy')",
            nativeQuery = true)
    List<Object[]> avgStarsByYearAndCategoryAndState(String state, String category);

    @Query(value = "select to_char(review.time, 'yyyy-mm'), avg(review.stars) " +
            " from yuanwang.review review, business " +
            " where to_char(review.time, 'yyyy')=?3 and " +
            " business.business_id = review.business_id and " +
            " lower(business.city) = ?1 and " +
            " lower(business.state) = ?2 and " +
            " lower(business.categories) like ?4 " +
            " group by to_char(review.time, 'yyyy-mm') " +
            " order by to_char(review.time, 'yyyy-mm')",
            nativeQuery = true)
    List<Object[]> avgStarsByMonthAndCategoryAndCity(String city, String state, String year, String category);

    @Query(value = "select to_char(review.time, 'yyyy-mm'), avg(review.stars) " +
            " from yuanwang.review review, business " +
            " where to_char(review.time, 'yyyy')=?2 and " +
            " business.business_id = review.business_id and " +
            " lower(business.state) = ?1 and " +
            " lower(business.categories) like ?3 " +
            " group by to_char(review.time, 'yyyy-mm') " +
            " order by to_char(review.time, 'yyyy-mm')",
            nativeQuery = true)
    List<Object[]> avgStarsByCategoryAndMonthAndState(String state, String year, String category);

    @Query(value = "select to_char(review.time, 'yyyy-mm'), avg(review.stars) " +
            " from yuanwang.review review, business " +
            " where to_char(review.time, 'yyyy')=?1 and " +
            " business.business_id = review.business_id and " +
            " lower(business.categories) like ?2 " +
            " group by to_char(review.time, 'yyyy-mm') " +
            " order by to_char(review.time, 'yyyy-mm')",
            nativeQuery = true)
    List<Object[]> avgStarsByCategoryAndMonth(String year, String category);

    @Query(value = "select to_char(review.time, 'yyyy'), avg(review.stars) " +
            "       from yuanwang.review review, business " +
            "       where business.business_id = review.business_id and " +
            "       lower(business.categories) like ?1 " +
            "       group by to_char(review.time, 'yyyy') " +
            "       order by to_char(review.time, 'yyyy')",
            nativeQuery = true)
    List<Object[]> avgStarsByYearAndCategory(String category);

    @Query(value = "select count(review_id) from yuanwang.review", nativeQuery = true)
    int countTotalReviews();

    @Query(value = "select y1, n2/n1*100 from " +
            "           (select  year.y y1, count(user_.user_id) n1 " +
            "           from user_, (select distinct(to_char(user_.yelping_since, 'yyyy')) y " +
            "                        from user_) year " +
            "           where to_char(user_.yelping_since, 'yyyy')<= year.y " +
            "           group by year.y), " +
            "           (select to_char(r.time, 'yyyy') y2, count(distinct(r.user_id)) n2 " +
            "           from yuanwang.review r " +
            "           group by to_char(r.time, 'yyyy')) " +
            "       where y1=y2 " +
            "       order by y1",
            nativeQuery = true)
    List<Object[]> activeUsersByYear();


    @Query(value = "select y1, n2/n1*100 from " +
            "           (select  year.y y1, count(user_.user_id) n1 " +
            "            from user_, (select distinct(to_char(user_.yelping_since, 'yyyy-mm')) y " +
            "                         from user_ " +
            "                         where to_char(user_.yelping_since, 'yyyy')=?1) year " +
            "            where to_char(user_.yelping_since, 'yyyy-mm')<= year.y " +
            "            group by year.y), " +
            "           (select to_char(r.time, 'yyyy-mm') y2, count(distinct(r.user_id)) n2 " +
            "            from yuanwang.review r " +
            "            where to_char(r.time, 'yyyy')=?1 " +
            "            group by to_char(r.time, 'yyyy-mm')) " +
            "       where y1=y2 " +
            "       order by y1",
            nativeQuery = true)
    List<Object[]> activeUsersByMonth(String year);

}

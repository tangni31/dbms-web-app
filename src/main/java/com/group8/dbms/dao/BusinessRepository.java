package com.group8.dbms.dao;

import com.group8.dbms.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BusinessRepository extends JpaRepository<Business, String> {

    @Query(value = "select * from business where lower(name) like ?1 and zip like ?2", nativeQuery = true)
    List<Business> findAllByNameLikeAndZipLike(String name, String zip);

    @Query(value = "select * from business where lower(name) like ?1 and lower(city)=?2 and lower(state)=?3", nativeQuery = true)
    List<Business> findAllByNameLikeAndCityAndState(String name, String city, String state);

    @Query(value = "select * from business where lower(name) like ?1 and zip=?2", nativeQuery = true)
    List<Business> findAllByNameLikeAndZip(String name, String zip);

    @Query(value = "select * from business where lower(categories) like ?1 and lower(city)=?2 and lower(state)=?3", nativeQuery = true)
    List<Business> findAllByCategoryLikeAndCityAndState(String category, String city, String state);

    @Query(value = "select * from business where lower(categories) like ?1 and zip=?2", nativeQuery = true)
    List<Business> findAllByCategoryLikeAndZip(String category, String zip);

    @Query(value = "select * from business where lower(categories) like ?1 and zip like ?2", nativeQuery = true)
    List<Business> findAllByCategoryLikeAndZipLike(String category, String zip);

    @Query(value = "select count(business_id) from business", nativeQuery = true)
    int countTotalBusiness();

}

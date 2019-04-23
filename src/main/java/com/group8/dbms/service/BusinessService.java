package com.group8.dbms.service;

import com.group8.dbms.entity.Business;

import java.util.List;

public interface BusinessService {

    Business findById(String bid);

    void save(Business business);

    void deleteById(String bid);

    List<Business> findAllByNameLikeAndZipLike(String name, String zip);

    List<Business> findAllByNameLikeAndCityAndState(String name, String city, String state);

    List<Business> findAllByNameLikeAndZip(String name, String zip);

    List<Business> findAllByCategoryLikeAndCityAndState(String name, String city, String state);

    List<Business> findAllByCategoryLikeAndZip(String category, String zip);

    List<Business> findAllByCategoryLikeAndZipLike(String category, String zip);

    String login(Business business);

    Business signUp(Business business);

    int countTotalBusiness();
}

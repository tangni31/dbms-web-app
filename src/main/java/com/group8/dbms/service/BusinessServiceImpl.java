package com.group8.dbms.service;

import java.util.List;
import java.util.Optional;

import com.group8.dbms.Tool;
import com.group8.dbms.dao.BusinessRepository;
import com.group8.dbms.entity.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BusinessServiceImpl implements BusinessService {

    private BusinessRepository businessRepository;

    @Autowired
    public BusinessServiceImpl(BusinessRepository theBusinessRepository) {
        businessRepository = theBusinessRepository;
    }

    @Override
    public Business findById(String bid) {
        Optional<Business> result = businessRepository.findById(bid);
        Business business = null;
        if (result.isPresent()) {
            business = result.get();
        }
        if (business != null) {
            if (business.getHasTV().isEmpty() || business.getHasTV().equals("True")) {
                business.setHasTV("Yes");
            } else {
                business.setHasTV("No");
            }
            if (business.getWifi().toLowerCase().contains("free")) {
                business.setWifi("Free");
            } else if (business.getWifi().toLowerCase().contains("paid")) {
                business.setWifi("Paid");
            } else {
                business.setWifi("No");
            }
            if (business.getGoodForKids().equals("True")) {
                business.setGoodForKids("Yes");
            } else {
                business.setGoodForKids("No");
            }
        }
        return business;
    }

    @Override
    public void save(Business business) {
        businessRepository.save(business);
    }

    @Override
    public void deleteById(String bid) {
        businessRepository.deleteById(bid);
    }

    @Override
    public List<Business> findAllByNameLikeAndZipLike(String name, String zip) {
        return businessRepository.findAllByNameLikeAndZipLike("%" + name + "%", zip + "%");
    }

    @Override
    public List<Business> findAllByNameLikeAndCityAndState(String name, String city, String state) {
        return businessRepository.findAllByNameLikeAndCityAndState("%" + name + "%", city, state);
    }

    @Override
    public List<Business> findAllByNameLikeAndZip(String name, String zip) {
        return businessRepository.findAllByNameLikeAndZip("%" + name + "%", zip);
    }

    @Override
    public List<Business> findAllByCategoryLikeAndCityAndState(String category, String city, String state) {
        return businessRepository.findAllByCategoryLikeAndCityAndState("%" + category + "%", city, state);
    }

    @Override
    public List<Business> findAllByCategoryLikeAndZip(String category, String zip) {
        return businessRepository.findAllByCategoryLikeAndZip("%" + category + "%", zip);
    }

    @Override
    public List<Business> findAllByCategoryLikeAndZipLike(String category, String zip) {
        return businessRepository.findAllByCategoryLikeAndZipLike("%" + category + "%", zip);
    }

    @Override
    public String login(Business business) {
        Business res = findById(business.getBid());
        if (res==null) {
            return "user not exist";
        } else if (!res.getPassword().equals(business.getPassword())){
            return "Wrong password";
        } else {
            return "Success";
        }
    }

    @Override
    public Business signUp(Business business) {
        String bid;
        do {
            bid = Tool.generateRandomId(22);
        } while (findById(bid) != null);
        business.setBid(bid);
        save(business);
        return business;
    }

    @Override
    public int countTotalBusiness() { return businessRepository.countTotalBusiness(); }

}

package com.group8.dbms.controller;

import com.group8.dbms.entity.Business;
import com.group8.dbms.entity.Review;
import com.group8.dbms.entity.User;
import com.group8.dbms.service.BusinessService;
import com.group8.dbms.service.ReviewService;
import com.group8.dbms.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.*;


@Controller
@RequestMapping("/business")
public class BusinessController {

    private BusinessService businessService;
    private ReviewService reviewService;
    private UserService userService;


    //constructor injection
    @Autowired
    public BusinessController(BusinessService businessService,
                               ReviewService reviewService, UserService userService) {
        this.businessService = businessService;
        this.reviewService = reviewService;
        this.userService = userService;
    }


    @GetMapping("/")
    public String getByBid(@RequestParam("bid") String bid, Model model) {
        Business business = businessService.findById(bid);
        model.addAttribute("business", business);
        HashMap<User, Review> reviews = getReviews(bid);
        model.addAttribute("reviews", reviews);
        model.addAttribute("isHomepage", false);
        return "business/business-page";
    }

    @GetMapping("/homepage")
    public String getBusinessHomePage(HttpSession session, Model model) {
        Object tmpBusiness = session.getAttribute("user");
        if (tmpBusiness == null) {
            return "redirect:/login";
        }
        Business business = businessService.findById(((Business) tmpBusiness).getBid());
        model.addAttribute("business", business);
        HashMap<User, Review> reviews = getReviews(business.getBid());
        model.addAttribute("reviews", reviews);
        model.addAttribute("isHomepage", true);
        return "business/business-page";
    }

    @GetMapping("/businesslogin")
    public String login(RedirectAttributes attr,
                        @ModelAttribute("business") Business business,
                        HttpSession session) {
        String result = businessService.login(business);
        if (result.equals("Success")) {
            Business realBusiness = businessService.findById(business.getBid());
            session.setAttribute("user", realBusiness);
            session.setAttribute("isBusiness", 1);
            attr.addAttribute("id", business.getBid());
            return "redirect:/business/homepage";
        }
        attr.addFlashAttribute("result", result);
        return "redirect:/login";
    }

    @GetMapping("/signup")
    public String signUp(Model model) {
        Business business = new Business();
        model.addAttribute("business", business);
        return "/business/business-sign-up-page";
    }

    @PostMapping("/signup/save")
    public String saveUser(@ModelAttribute("business") Business tmpBusiness,
                           HttpSession session, RedirectAttributes attr) {
        Business business = businessService.signUp(tmpBusiness);
        session.setAttribute("user", business);
        session.setAttribute("isBusiness", true);
        attr.addAttribute("bid", business.getBid());
        return "redirect:/business/homepage";
    }


    @GetMapping("/search")
    public String getSearchResult(@RequestParam("name") String name,
                                  @RequestParam("address") String address, Model model) {
        List<Business> result;
        name = name.trim().toLowerCase();
        if(address.matches("[0-9]+")) {
            result = searchByZip(name, address);
        } else {
            result = searchByCity(name, address.toLowerCase());
        }
        model.addAttribute("address", address);
        model.addAttribute("name", name.toLowerCase());
        model.addAttribute("businessResults", result);
        return "business/search-result";
    }

    private List<Business> searchByZip(String name, String zip) {
        String zipLike = zip.substring(0,3);
        List<Business> resultByName = businessService.findAllByNameLikeAndZip(name, zip);
        List<Business> resultByCategory = businessService.findAllByCategoryLikeAndZip(name, zip);
        List<Business> resultByNameZipLike = businessService.findAllByNameLikeAndZipLike(name, zipLike);
        List<Business> resultByCategoryZipLike = businessService.findAllByCategoryLikeAndZipLike(name, zipLike);
        resultByCategory.addAll(resultByNameZipLike);
        resultByCategory.addAll(resultByCategoryZipLike);
        for (Business b : resultByCategory) {
            if (!resultByName.contains(b)) {
                resultByName.add(b);
            }
        }
        return resultByName;
    }

    private List<Business> searchByCity(String name, String address) {
        String[] newAddress = address.split(",");
        String city = newAddress[0].trim();
        String state = newAddress[1].trim();
        List<Business> result = businessService.findAllByNameLikeAndCityAndState(name, city, state);
        List<Business> resultByCategory = businessService.findAllByCategoryLikeAndCityAndState(name, city, state);
        for (Business b : resultByCategory) {
            if (!result.contains(b)) {
                result.add(b);
            }
        }
        return result;
    }

    private HashMap<User, Review> getReviews(String bid) {
        List<Review> tmpReviews = reviewService.findAllByBid(bid);
        HashMap<User, Review> reviews = new HashMap<>();
        for(Review r : tmpReviews) {
            User user = userService.findById(r.getUid());
            reviews.put(user, r);
        }
        return reviews;
    }

}

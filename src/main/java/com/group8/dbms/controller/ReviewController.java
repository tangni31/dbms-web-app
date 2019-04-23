package com.group8.dbms.controller;

import com.group8.dbms.Tool;
import com.group8.dbms.entity.Business;
import com.group8.dbms.entity.Review;
import com.group8.dbms.entity.User;
import com.group8.dbms.service.BusinessService;
import com.group8.dbms.service.ReviewService;
import com.group8.dbms.service.UserService;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class ReviewController {

    private UserService userService;
    private BusinessService businessService;
    private ReviewService reviewService;

    @Autowired
    public ReviewController(UserService userService, BusinessService businessService,
                            ReviewService reviewService) {
        this.userService = userService;
        this.businessService = businessService;
        this.reviewService = reviewService;
    }

    @GetMapping("/review")
    public String showWriteReviewPage(@RequestParam("bid") String bid,
                                      HttpSession session, Model model) {
        User user = userService.getUserFromSession(session);
        if (user == null) { //user must login before write a review
            return "redirect:/login";
        }
        Business business = businessService.findById(bid);
        model.addAttribute("business", business);

        Review review = new Review();
        model.addAttribute("review", review);
        return "review/review-page";
    }


    @PostMapping("/business/review/save")
    public String saveReview(RedirectAttributes attr,
                             HttpSession session,
                             @ModelAttribute("review") Review review,
                             @RequestParam("bid") String bid) {
        //generate and set rid
        String rid;
        do {
            rid = Tool.generateRandomId(22);
        } while (reviewService.findById(rid) != null);
        review.setRid(rid);
        //set date
        review.setDate(Tool.getDate());
        //set uid
        User user = userService.getUserFromSession(session);
        //review.setUser(user);
        review.setUid(user.getUid());
        //set bid
        review.setBid(bid);
        //review.setBusiness(businessService.findById(bid));
        attr.addAttribute("bid", bid);
        //save review
        reviewService.save(review);
        return "redirect:/business/";
    }
}

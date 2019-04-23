package com.group8.dbms.controller;

import com.group8.dbms.entity.Business;
import com.group8.dbms.entity.Friend;
import com.group8.dbms.entity.Review;
import com.group8.dbms.entity.User;
import com.group8.dbms.service.BusinessService;
import com.group8.dbms.service.FriendService;
import com.group8.dbms.service.ReviewService;
import com.group8.dbms.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private BusinessService businessService;
    private ReviewService reviewService;
    private FriendService friendService;

    public UserController(BusinessService businessService, ReviewService reviewService,
                          UserService userService, FriendService friendService) {
        this.businessService = businessService;
        this.reviewService = reviewService;
        this.userService = userService;
        this.friendService = friendService;
    }

    @GetMapping("/userlogin")
    public String login(RedirectAttributes attr,
                        @ModelAttribute("user") User user,
                        HttpSession session) {
        String result = userService.login(user);
        if (result.equals("Success")) {
            User realUser = userService.findById(user.getUid());
            session.setAttribute("user", realUser);
            attr.addAttribute("uid", user.getUid());
            session.setAttribute("isBusiness", 0);
            return "redirect:/user/homepage";
        }
        attr.addFlashAttribute("result", result);
        return "redirect:/login";
    }

    @GetMapping("/signup")
    public String signUp(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "/user/user-sign-up-page";
    }

    @PostMapping("/signup/save")
    public String saveUser(@ModelAttribute("user") User tmpUser,
                           HttpSession session,
                           RedirectAttributes attr) {
        //System.out.println(tmpUser.toString());
        User user = userService.signUp(tmpUser);
        //System.out.println(user.toString());
        session.setAttribute("user", user);
        attr.addAttribute("uid", user.getUid());
        return "redirect:/user/homepage";
    }

    @GetMapping("/homepage")
    public String showHomePage(Model model, HttpSession session) {
        User user = userService.getUserFromSession(session);
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        HashMap<Business, Review> reviews = getReviews(user.getUid());
        model.addAttribute("reviews", reviews);
        List<User> friends = getFriends(user.getUid());
        model.addAttribute("friends", friends);
        model.addAttribute("isHomepage", true);
        return "user/user-page";
    }


    @GetMapping("/")
    public String findById(@RequestParam("uid") String uid, Model model) {
        User user = userService.findById(uid);
        model.addAttribute("user", user);
        HashMap<Business, Review> reviews = getReviews(uid);
        model.addAttribute("reviews", reviews);
        List<User> friends = getFriends(uid);
        model.addAttribute("friends", friends);
        model.addAttribute("isHomepage", false);
        return "user/user-page";
    }


    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(HttpSession session, Model model) {
        User user = userService.getUserFromSession(session);
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "user/user-form";
    }


    @PostMapping("/save")
    public String saveUpdate(@ModelAttribute("user") User tmpUser) { //th:object ${employee}
        User user = userService.findById(tmpUser.getUid());
        user.setName(tmpUser.getName());
        //password...
        userService.save(user);

        return "redirect:/user/homepage"; // post/redirect/get pattern
    }


    private HashMap<Business, Review> getReviews(String uid) {
        List<Review> tmpReviews = reviewService.findAllByUid(uid);
        HashMap<Business, Review> reviews = new HashMap<>();
        for (Review r : tmpReviews) {
            //Business business = r.getBusiness();
            Business business = businessService.findById(r.getBid());
            reviews.put(business, r);
        }
        return reviews;
    }


    private List<User> getFriends(String uid) {

        Friend friend = friendService.findById(uid);
        if (friend == null) {
            return null;
        }
        String[] fIds = friend.getFriends().split(",");
        List<User> friends = new LinkedList<>();
        for (String f : fIds) {
            //System.out.println(f);
            User user = userService.findById(f);
            if (user != null) {
                friends.add(user);
            }
        }
        return friends;
    }


}

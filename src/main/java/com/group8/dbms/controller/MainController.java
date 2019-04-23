package com.group8.dbms.controller;

import com.group8.dbms.entity.Admin;
import com.group8.dbms.entity.Business;
import com.group8.dbms.model.LoginMessage;
import com.group8.dbms.entity.User;
import com.group8.dbms.service.UserService;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




@Controller
public class MainController {

    UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping ("/")
    public String getSearchResult(Model model) {
        Business business = new Business();
        model.addAttribute("business", business);
        return "index";
    }

    @GetMapping("/login")
    public String loginPage(Model model){
        LoginMessage loginMessage = new LoginMessage();
        model.addAttribute("message", loginMessage);
        return "login";
    }


    @GetMapping("/signout")
    public String signOut(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

    @PostMapping("/login/redirct")
    public String loginRedict(@ModelAttribute("message") LoginMessage message,
                              RedirectAttributes attr) {
        if(message.getType().equals("user")) {
            User user = new User();
            user.setUid(message.getId());
            user.setPassword(message.getPassword());
            attr.addFlashAttribute("user", user);
            return "redirect:/user/userlogin";

        } else if (message.getType().equals("business")) {
            Business business = new Business();
            business.setBid(message.getId());
            business.setPassword(message.getPassword());
            attr.addFlashAttribute("business", business);
            return "redirect:/business/businesslogin";
        } else {
            Admin admin = new Admin();
            admin.setName(message.getId());
            admin.setPassword(message.getPassword());
            attr.addFlashAttribute("admin", admin);
            return "redirect:/admin/adminlogin";
        }
    }



}

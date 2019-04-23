package com.group8.dbms.controller;

import com.group8.dbms.entity.Admin;
import com.group8.dbms.model.BusinessSearchInfo;
import com.group8.dbms.service.AdminService;
import com.group8.dbms.service.BusinessService;
import com.group8.dbms.service.ReviewService;
import com.group8.dbms.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;
    private UserService userService;
    private ReviewService reviewService;
    private BusinessService businessService;

    @Autowired
    public AdminController(AdminService adminService, UserService userService,
                           ReviewService reviewService, BusinessService businessService) {
        this.adminService = adminService;
        this.userService = userService;
        this.reviewService = reviewService;
        this.businessService = businessService;
    }


    @GetMapping("/adminlogin")
    public String login(RedirectAttributes attr,
                        @ModelAttribute("admin") Admin admin,
                        HttpSession session) {
        String result = adminService.login(admin);
        if (result.equals("Success")) {
            Admin realAdmin = adminService.findByUsername(admin.getName());
            session.setAttribute("user", realAdmin);
            attr.addAttribute("id", admin.getaId());
            session.setAttribute("isBusiness", 2);
            return "redirect:/admin/homepage";
        }
        attr.addFlashAttribute("result", result);
        return "redirect:/login";
    }


    @GetMapping("/homepage")
    public String getBusinessHomePage(HttpSession session, Model model) {
        Object tmpAdmin = session.getAttribute("user");
        if (tmpAdmin == null) {
            return "redirect:/login";
        }
        Admin admin = (Admin) tmpAdmin;
        model.addAttribute("message", "Welcome: " + admin.getName());
        int totalUser = userService.countTotalUser();
        int totalReview = reviewService.countTotalReviews();
        int totalBusiness = businessService.countTotalBusiness();
        model.addAttribute("totalUser", totalUser);
        model.addAttribute("totalBusiness", totalBusiness);
        model.addAttribute("totalReview", totalReview);
        return "admin/admin-home-page";
    }


    @GetMapping ("/business_info")
    public String getSearchResult(@RequestParam("category") String category,
                                  @RequestParam("location") String location,
                                  @RequestParam("year") String year,
                                  @RequestParam("content") String content,
                                  Model model,
                                  HttpSession session) {
        Object tmpAdmin = session.getAttribute("user");
        if (tmpAdmin == null) {
            return "redirect:/login";
        }
        BusinessSearchInfo business = new BusinessSearchInfo();
        String message;
        if (content.equals("review")) {
            message = "Total Review ";
        } else {
            message = "Average Stars ";
        }
        message += "for Business Category: " + category +
                ", In: " + location + ", on Year: " + year;
        model.addAttribute("business", business);
        category = category.toLowerCase();
        location = location.toLowerCase();
        List<Object[]> tmpRes;
        if (category.equals("all")) {
                category = "%";
        } else {
            category = "%" + category + "%";
        }
        if (location.equals("all")) {
            if (year.toLowerCase().equals("all")) {
                if (content.equals("review")) {
                    tmpRes = reviewService.reviewNumByYearAndCategory(category);
                } else {
                    tmpRes =reviewService.avgStarsByYearAndCategory(category);
                }

            } else {
                if (content.equals("review")) {
                    tmpRes = reviewService.reviewNumByCategoryAndMonth(year, category);
                } else {
                    tmpRes = reviewService.avgStarsByCategoryAndMonth(year, category);
                }
            }
        } else if (location.contains(",")) {
            String[] newAddress = location.split(",");
            String city = newAddress[0].trim();
            String state = newAddress[1].trim();
            if (year.toLowerCase().equals("all")) {
                if (content.equals("review")) {
                    tmpRes = reviewService.reviewNumByYearAndCategoryAndCity(city, state, category);
                } else {
                    tmpRes = reviewService.avgStarsByYearAndCategoryAndCity(city, state, category);
                }
            } else {
                if (content.equals("review")) {
                    tmpRes = reviewService.reviewNumByMonthAndCategoryAndCity(city, state, year, category);
                } else {
                    tmpRes = reviewService.avgStarsByMonthAndCategoryAndCity(city, state, year, category);
                }
            }
        } else {
            if (year.toLowerCase().equals("all")) {
                if (content.equals("review")) {
                    tmpRes = reviewService.reviewNumByYearAndCategoryAndState(location, category);
            } else {
                    tmpRes = reviewService.avgStarsByYearAndCategoryAndState(location, category);
                }
            } else {
                if (content.equals("review")) {
                    tmpRes = reviewService.reviewNumByCategoryAndMonthAndState(location, year, category);
                } else {
                    tmpRes = reviewService.avgStarsByCategoryAndMonthAndState(location, year, category);
                }
            }
        }
        List<List> res = getResult(tmpRes);
        System.out.println(res.get(0).toString());
        System.out.println(res.get(1).toString());
        model.addAttribute("message", message);
        model.addAttribute("dataLabel", res.get(0));
        model.addAttribute("dataVal", res.get(1));
        return "admin/business-info-page";
    }


    @GetMapping("/website_info")
    public String getWebUserSiteInfo(@RequestParam("type") String type,
                                     @RequestParam("year") String year,
                                     Model model,
                                     HttpSession session) {
        Object tmpAdmin = session.getAttribute("user");
        if (tmpAdmin == null) {
            return "redirect:/login";
        }
        String message;
        List<Object[]> tmpRes;
        if (type.equals("user")) {
            model.addAttribute("type", "user");
            if (year.equals("all")) {
                message = "Number of " + type + " registered per year";
                tmpRes = userService.registerUserNumByYear();
            } else {
                message = "Number of " + type + " registered per month in " + year;
                tmpRes = userService.registerUserNumByMonth(year);
            }
        } else if (type.equals("active_user")) {
            model.addAttribute("type", "active_user");
            if (year.equals("all")) {
                message = "Percentage of Active User per Year";
                tmpRes = reviewService.activeUsersByYear();
            } else {
                message = "Percentage of Active User per Month in " + year;
                tmpRes = reviewService.activeUsersByMonth(year);
            }
        } else {
            model.addAttribute("type", "review");
            if (year.equals("all")) {
                message = "Number of " + type + " per Year";
                tmpRes = reviewService.reviewNumByYear();
            } else {
                message = "Number of " + type + " per Month in " + year;
                tmpRes = reviewService.reviewNumByMonth(year);
            }
        }
        List<List> res = getResult(tmpRes);
        model.addAttribute("message", message);
        model.addAttribute("dataLabel", res.get(0));
        model.addAttribute("dataVal", res.get(1));
        return "admin/website-info-page";
    }


    private List<List> getResult(List<Object[]> res) {
        List<String> years = new ArrayList<>();
        List<Double> nums = new ArrayList<>();
        List<List> result = new ArrayList<>();
        for (Object[] r : res) {
            String year = (String) r[0];
            double num =  ((BigDecimal)r[1]).doubleValue();
            years.add(year);
            nums.add(num);
        }
        result.add(years);
        result.add(nums);
        return result;
    }
}

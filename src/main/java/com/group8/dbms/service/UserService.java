package com.group8.dbms.service;

import com.group8.dbms.entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {

    User findById(String uid);

    void save(User user);

    void deleteById(String uid);

    String login(User user);

    User getUserFromSession(HttpSession session);

    User signUp(User user);

    List<Object[]> registerUserNumByYear();

    List<Object[]> registerUserNumByMonth(String year);

    int countTotalUser();

}

package com.group8.dbms.service;

import com.group8.dbms.Tool;
import com.group8.dbms.dao.UserRepository;
import com.group8.dbms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository theUserRepository) {
        userRepository = theUserRepository;
    }

    @Override
    public User findById(String uid) {
        Optional<User> result = userRepository.findById(uid);
        User user = null;
        if (result.isPresent()) {
            user = result.get();
        }
        return user;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(String uid) {
        userRepository.deleteById(uid);
    }

    @Override
    public String login(User user) {
        User res = findById(user.getUid());
        System.out.println(user.getPassword());
        if (res==null) {
            return "User not exist";
        } else if (!res.getPassword().equals(user.getPassword())){
            return "Wrong password";
        } else {
            return "Success";
        }
    }

    @Override
    public User getUserFromSession(HttpSession session) {
        Object tmpUser = session.getAttribute("user");
        if (tmpUser == null) {
            return null;
        }
        User user = null;
        try {
             user = findById(((User)tmpUser).getUid());
        } catch (ClassCastException e) {

        }
        return user;
    }

    @Override
    public User signUp(User user) {
        //User user = new User();
        //user.setName(message.getId());
        user.setRegistrationTime(Tool.getDate());
        //user.setPassword(message.getPassword());
        String uid;
        do {
            uid = Tool.generateRandomId(22);
        } while (findById(uid) != null);
        user.setUid(uid);
        save(user);
        return user;
    }

    @Override
    public List<Object[]> registerUserNumByYear() {
        return userRepository.registerUserNumByYear();
    }

    @Override
    public List<Object[]> registerUserNumByMonth(String year) { return userRepository.registerUserNumByMonth(year); }

    @Override
    public int countTotalUser() { return userRepository.countTotalUser(); }
}

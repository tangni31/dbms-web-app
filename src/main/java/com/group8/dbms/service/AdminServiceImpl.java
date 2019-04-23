package com.group8.dbms.service;

import com.group8.dbms.dao.AdminRepository;
import com.group8.dbms.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin findByUsername(String username) {
        return adminRepository.findByName(username);
    }

    @Override
    public String login(Admin admin) {
        Admin res = findByUsername(admin.getName());
        if (res==null) {
            return "user not exist";
        } else if (!res.getPassword().equals(admin.getPassword())){
            return "Wrong password";
        } else {
            return "Success";
        }
    }

}

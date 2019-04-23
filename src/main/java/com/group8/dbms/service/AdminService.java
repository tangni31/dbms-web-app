package com.group8.dbms.service;

import com.group8.dbms.entity.Admin;

public interface AdminService {

    Admin findByUsername(String username);

    String login(Admin admin);
}

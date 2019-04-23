package com.group8.dbms.dao;

import com.group8.dbms.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String> {
    Admin findByName(String name);
}

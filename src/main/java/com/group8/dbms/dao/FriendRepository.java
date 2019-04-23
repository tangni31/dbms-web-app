package com.group8.dbms.dao;

import com.group8.dbms.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FriendRepository extends JpaRepository<Friend, String> {

}

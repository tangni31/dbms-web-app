package com.group8.dbms.service;

import com.group8.dbms.entity.Friend;
import com.group8.dbms.entity.User;

import java.util.List;

public interface FriendService {
    void save(Friend friend);
    Friend findById(String uid);
}

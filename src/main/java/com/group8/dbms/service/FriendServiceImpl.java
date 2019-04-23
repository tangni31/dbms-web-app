package com.group8.dbms.service;

import com.group8.dbms.dao.FriendRepository;
import com.group8.dbms.entity.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FriendServiceImpl implements FriendService {

    private FriendRepository friendRepository;

    @Autowired
    public FriendServiceImpl(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    @Override
    public void save(Friend friend) {
        friendRepository.save(friend);
    }

    @Override
    public Friend findById(String uid) {
        Optional<Friend> result = friendRepository.findById(uid);
        Friend friend = null;
        if (result.isPresent()) {
            friend = result.get();
        }
        return friend;
    }

}

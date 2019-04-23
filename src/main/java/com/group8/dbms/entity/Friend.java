package com.group8.dbms.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "friend")
public class Friend implements Serializable{

    @Id
    @Column(name = "uid_1")
    private String uid1;

    @Column(name = "friends")
    private String friends;

    public Friend() {

    }

    public Friend(String uid1, String friends) {
        this.uid1 = uid1;
        this.friends = friends;
    }

    public String getFriends() {
        return friends;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }

    public String getUid1() {
        return uid1;
    }

    public void setUid1(String uid1) {
        this.uid1 = uid1;
    }




}

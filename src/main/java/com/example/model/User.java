package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.UUID;

@RedisHash("User")
public class User implements Serializable {

    private String userName;

    private String password;

    private String token;

    private  Integer money;

    private static User user;

    public static User getUser(String userName, String password, String token, int money) {

        if (user==null) {

            user=new User(userName,password,token, money);
        }
        return user;
    }

    private User(String userName, String password, String token, int money){
        this.userName = userName;
        this.password = password;
        this.token = token;
        this.money = money;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

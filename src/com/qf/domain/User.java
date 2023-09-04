package com.qf.domain;

import java.io.Serializable;
/* 实现序列化接口： 将对应的字符转化2进制，提高速度*/
public class User implements Serializable {
    private  Integer id;//用户id;
    private  String username;//用户名
    private String password;//密码
    private String avatar;//头像
    private String nickname;//昵称

    public User() {
        super();
    }
    public User(Integer id, String username, String password, String avatar, String nickname) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }



}

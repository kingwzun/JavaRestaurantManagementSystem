package com.qf.service;

import com.qf.domain.User;

public interface UserService  {
    public User findByUserName(String username);

    boolean saveUser(User inserUser);
}

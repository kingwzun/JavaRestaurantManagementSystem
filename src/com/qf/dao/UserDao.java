package com.qf.dao;

import com.qf.domain.User;

/**
 * dao层接口
 */
public interface UserDao {
    //增删改 （int类型的值）
    boolean addUser(User user);

    //根据用户名和密码查询
    User selectByUserNameAndPassword(String username, String password);

    User selectByUserName(String username);


}


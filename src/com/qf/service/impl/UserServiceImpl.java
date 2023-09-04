package com.qf.service.impl;

import com.qf.dao.UserDao;
import com.qf.dao.impl.UserDaoImpl;
import com.qf.domain.User;
import com.qf.service.UserService;
import com.qf.utils.Md5Util;

public class UserServiceImpl implements UserService {
    //准备dao层实例
    UserDao userDao = new UserDaoImpl();

    @Override
    public User findByUserName(String username) {
        //调用dao层方法
        User user = userDao.selectByUserName(username);
        return user;
    }

    @Override
    public boolean saveUser(User insertUser) {
        //先将用户密码加密
        //加密方式 md5(已经被破解,简单)(所以需要 加盐{未实现})
        String md5Pwd = Md5Util.md5(insertUser.getPassword());
        insertUser.setPassword(md5Pwd);
        //将加密的信息存入数据库
        boolean b = userDao.addUser(insertUser);
        return b;
    }


}

package com.qf.dao.impl;

import com.qf.dao.UserDao;
import com.qf.domain.User;
import com.qf.utils.DbUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//用户dao的实现类
public class UserDaoImpl implements UserDao {

    //实现添加用户功能
    public boolean addUser(User user) {

        //向t_user表中插入一条数据
        //1、写SQL语句
        String sql = "insert into t_user values(null,?,?,?,?)";
        //2、获得执行类
        PreparedStatement preparedStatement = DbUtil.getPreparedStatement(sql);
        try {
            //添加信息
            //通过实现类的get和set方法获取和设置对应的数据表中的信息
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, "/images/avatar.png");
            preparedStatement.setString(4, "Admin");
            int result = preparedStatement.executeUpdate();
            //3、返回boolean类型的值用于判断信息是否插入成功
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //实现用户名和密码的查询
    //实现登录信息的验证
    public User selectByUserNameAndPassword(String username, String password) {
        //写查找用户名和对应的密码信息的SQL语句：
        String sql = "select * from t_user where username=? and password=?";
        //获得执行类，同时捕获异常
        PreparedStatement preparedStatement = DbUtil.getPreparedStatement(sql);
        try {
            //设置第一个和第二个?对应的值
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            //执行SQL语句，获得集合类
            ResultSet resultSet = preparedStatement.executeQuery();
            //解决空指针异常问题
            User user = null;
            if (resultSet.next()) {
                user = new User();
                //通过集合类获得t_user中对应的数据
                //向user中设置对应的信息
                user.setId(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setAvatar(resultSet.getString(4));
                user.setNickname(resultSet.getString(5));
            }
            //返回user类，通过user类是否为空判断用户信息是否存在
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //判断注册时从键盘获得的username是否已经存在
    //判断的方法是返回的User类是否为空
    @Override
    public User selectByUserName(String username) {
        //写查找用户名对应的信息的SQL语句：
        String sql = "select * from t_user where username=?";
        //获得执行类
        PreparedStatement preparedStatement = DbUtil.getPreparedStatement(sql);
        try {
            //通过执行类给?设置值
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            //解决空指针异常
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setAvatar(resultSet.getString(4));
                user.setNickname(resultSet.getString(5));
            }
            //返回user类，通过user类是否为空判断用户信息是否存在
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

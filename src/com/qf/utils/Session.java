package com.qf.utils;
import com.qf.domain.User;
/*
* 建立一个共享空间存用户
* */
public class Session {
//    static保证唯一性。 单例
    private static User user;

    public static void setUser(User user) {
        Session.user = user;
    }

    public static User getUser() {
        return user;
    }
}

package com.qf.controller;

import com.qf.MainApplication;
import com.qf.domain.User;
import com.qf.service.UserService;
import com.qf.service.impl.UserServiceImpl;
import com.qf.utils.DialogUtil;
import com.qf.utils.Md5Util;
import com.qf.utils.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//登录控制类
public class LoginController {
    //当前的窗口容器对象;
    private Stage LoginStage;

    public Stage getLoginStage() {
        return LoginStage;
    }

    public void setLoginStage(Stage loginStage) {
        LoginStage = loginStage;
    }

    @FXML
    private TextField usernameInput;//接受用户输入的用户id
    @FXML
    private PasswordField passwordInput;//接受用户输入的密码

    //登录的方法
    //在对于fxml里面调用必须带#
    @FXML
    void login() {
        //1. 获取传的数据
        String username = usernameInput.getText();
        String password = passwordInput.getText();
        //如果数据为空值，弹窗输入不能为空
        if (username.trim().equals("") || password.trim().equals("")) {//去掉空格
            DialogUtil.Dialog(Alert.AlertType.WARNING, "警告", "温馨提示",
                    "用户名及密码不能为空");
            return;
        }
        //2. 验证数据  去service层
        UserService userService = new UserServiceImpl();
        //2.1 调用service层的方法
        User user = userService.findByUserName(username);
        //返回验证结果：
        // 如果存在数据，可以登陆，弹框可以登录（点击确定 进入主页 初始化主页）
        if (user != null) {
            if (user.getPassword().equals(Md5Util.md5(password))) {
                //将当前用户存入Session中
                Session.setUser(user);
                //弹窗提示
                boolean success = DialogUtil.Dialog(Alert.AlertType.INFORMATION, "提示", "温馨提示",
                        "登录成功");
                //打开新的页面
                if (success) {
                    //关闭当前窗口
                    this.LoginStage.close();
                    //打开主页
                    new MainApplication().initHome();
                }
            }   //如果不存在数据，弹窗不能登录
            else {
                DialogUtil.Dialog(Alert.AlertType.WARNING, "警告", "危险动作",
                        "密码错误");
            }
        } else {   //如果不存在数据，弹窗不能登录

            DialogUtil.Dialog(Alert.AlertType.WARNING, "警告", "危险动作",
                    "当前用户不存在");
        }
    }

    //注册的方法
    @FXML
    void register() {
        //关闭当前界面
        this.LoginStage.close();
        //跳转到注册页面.
        new MainApplication().initRegister();
    }
}

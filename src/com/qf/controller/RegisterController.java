package com.qf.controller;

import com.qf.MainApplication;
import com.qf.domain.User;
import com.qf.service.UserService;
import com.qf.service.impl.UserServiceImpl;
import com.qf.utils.DialogUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {
    //设置当前的窗口容器对象;
    private Stage RegisterStage;

    public Stage getRegisterStage() {
        return RegisterStage;
    }

    public void setRegisterStage(Stage RegisterStage) {
        this.RegisterStage = RegisterStage;
    }

    // 接受jfx里面的组件,名字必须和对口的fxml里面的组件一样
    @FXML
    private TextField usernameInput;//接受用户输入的用户名
    @FXML
    private PasswordField passwordInput;//接受用户输入的用户密码
    @FXML
    private PasswordField authorizationCodeInput;//接受用户输入的授权码

    //实现按钮动作，在对于fxml里面调用必须带#
    //登录的方法：转调到登录界面
    @FXML
    void login() {
        //关闭当前页面
        this.RegisterStage.close();
        //跳转到登录页面.
        new MainApplication().initLogin();
    }

    //注册的方法
    @FXML
    void register() {
        //1获取授权码信息
        String authorizationCode = authorizationCodeInput.getText();
        //判断用户是否输入有效信息
        //如果是无效信息进行弹窗警告
        if (authorizationCode.trim().equals("")) {//去掉空格
            DialogUtil.Dialog(Alert.AlertType.WARNING, "警告", "温馨提示",
                    "授权码不能为空");
        } else {
            //如果是有效信息进行判断是否为正确授权码
            //如果错误，弹窗警告
            if (!authorizationCode.equals("123456")) {
                DialogUtil.Dialog(Alert.AlertType.WARNING, "警告", "警告",
                        "授权码错误，请重新输入");
            } else {
                //如果正确判断用户信息。
                //1.获取数据
                String username = usernameInput.getText();
                String password = passwordInput.getText();
                //2.判断是否为空
                if (username.trim().equals("") || password.trim().equals("")) {//去掉空格
                    DialogUtil.Dialog(Alert.AlertType.WARNING, "警告", "警告",
                            "用户名及密码不能为空");
                } else {
                    //3.1  获取当前用户名
                    UserService userService = new UserServiceImpl();
                    User user = userService.findByUserName(username);
                    //3.2 判断是否存在,存在就报错
                    if (user != null) {
                        DialogUtil.Dialog(Alert.AlertType.WARNING, "提示", "提示",
                                "用户名已经存在");
                    } else {
                        User insertUser = new User();
                        insertUser.setUsername(username);
                        insertUser.setPassword(password);
                        //4.1添加用户 访问service 层调用添加的方法
                        boolean result = userService.saveUser(insertUser);
                        //5.1 添加成功,提示添加成功, 转跳到登录
                        if (result) {
                            boolean success = DialogUtil.Dialog(Alert.AlertType.INFORMATION, "OK", "友情提示",
                                    "用户添加成功，点击确定进入登录，点击取消继续注册其他账户");
                            if (success) {
                                //关闭当前窗口
                                // this.RegisterStage.close();
                                //打开登录页面
                                new MainApplication().initLogin();
                            }
                        } else { //5.2 添加失败,提示添加失败
                            DialogUtil.Dialog(Alert.AlertType.ERROR, "错误", "错误信息",
                                    "用户添加失败");
                        }
                    }
                }
            }
        }
    }
}

package com.qf.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CreateAccount {
    @FXML
    private TextField usernameInput;//接受jfx里面的组件,名字必须he对口的fxml里面的组件一样
    @FXML
    private PasswordField passwordInput;//接受jfx里面的组件

    //登录的方法
    //在对于fxml里面调用必须带#
    @FXML
    void login(){
        System.out.println("点击了登录");
        System.out.println(usernameInput.getText());
        System.out.println(passwordInput.getText());

    }

    //注册的方法
    @FXML
    void register(){
        System.out.println("点击了注册");
        System.out.println(usernameInput.getText());
        System.out.println(passwordInput.getText());
    }
}

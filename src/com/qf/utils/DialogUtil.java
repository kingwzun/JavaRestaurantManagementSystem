package com.qf.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import java.util.Optional;
/*
* 生成弹窗的方法
* 弹窗类型
* 标题
* 对话框的头
* 消息
*
* */

public class DialogUtil {
    public static boolean Dialog(Alert.AlertType alertType,String title,String header,String message){
        Alert alert=new Alert(alertType,message,
                new ButtonType("取消", ButtonBar.ButtonData.CANCEL_CLOSE),
                new ButtonType("确认", ButtonBar.ButtonData.YES));
        alert.setTitle(title);
        alert.setHeaderText(header);
        //接受对应的按了什么按钮,返回的是按钮的类型
        Optional<ButtonType> buttonType = alert.showAndWait();
        //获取对按钮类型对应的按钮的buttonData值
        return buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES);
    }
}

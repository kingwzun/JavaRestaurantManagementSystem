package com.qf.utils;

import javafx.scene.control.Alert;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
 * 生成用户输入信息弹窗的方法
 * 弹窗类型
 * 标题
 * 对话框的头
 * 消息
 *
 * */

public class ControlUtil extends JFrame implements ActionListener {
    private int m=-1;

    public int getM() {
        return m;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //用户输入输入框的内容
        String str = JOptionPane.showInputDialog(this, "请输入正整数", "输入对话框", JOptionPane.PLAIN_MESSAGE);
        if (str != null) {
            try {
                int t = Integer.parseInt(str);
                if(t<0){
                    DialogUtil.Dialog(Alert.AlertType.WARNING, "警告", "危险动作",
                            "请输入有效的正整数,请不要输入负数");
                }else
                {
                    this.m=t;
                }
            } catch (Exception e) {
                DialogUtil.Dialog(Alert.AlertType.WARNING, "警告", "危险动作",
                        "请输入有效的正整数");
            }
        }
    }
}




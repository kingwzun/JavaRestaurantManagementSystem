package com.qf.controller;

import com.qf.domain.Record;
import com.qf.service.impl.MenuServiceImpl;
import com.qf.service.impl.PriceServiceImpl;
import com.qf.service.impl.RecordServiceImpl;
import com.qf.service.impl.checkDataRange;
import com.qf.utils.DialogUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;

public class UpdateController {

    //当前的窗口容器对象;
    private Stage AddStage;

    public Stage getUpdateStage() {
        return AddStage;
    }

    public void setUpdateStage(Stage primaryStage) {
        this.AddStage = AddStage;
    }

    @FXML
    private TextField selectIdInput;//接受用户输入查询的id
    @FXML
    private TextField updateIdInput;//接受用户输入更改后的id
    @FXML
    private TextField memoInput; //接受用户输入的备注

    //接受用户勾选的菜品
    @FXML
    private CheckBox checkBox1;
    @FXML
    private CheckBox checkBox2;
    @FXML
    private CheckBox checkBox3;
    @FXML
    private CheckBox checkBox4;
    @FXML
    private CheckBox checkBox5;
    @FXML
    private CheckBox checkBox6;
    @FXML
    private CheckBox checkBox7;
    @FXML
    private CheckBox checkBox8;
    @FXML
    private ComboBox comboBox;

    //初始化信息
    @FXML
    void initInformation() {
        //将用户输入查询的id设置为空
        selectIdInput.setText("");
        //将用户输入更改后的id设置为空
        updateIdInput.setText("");
        //设置选菜默认为空
        comboBox.getSelectionModel().selectFirst();
        checkBox1.setSelected(false);
        checkBox2.setSelected(false);
        checkBox3.setSelected(false);
        checkBox4.setSelected(false);
        checkBox5.setSelected(false);
        checkBox6.setSelected(false);
        checkBox7.setSelected(false);
        checkBox8.setSelected(false);
        //设置备注为空
        memoInput.setText("");
    }

    //查询用户的方法
    @FXML
    void selectById() {
        //1.获取数据，将string类型的输入强制转换成int类型的
        try {
            int tid = Integer.parseInt(selectIdInput.getText());
            RecordServiceImpl recordServiceImpl = new RecordServiceImpl();
            //返回餐桌的信息：情景1：餐桌信息存在 情景2：餐桌信息不存在
            Record tableRecord = recordServiceImpl.findTable(tid);
            //获取数据
            //1.预约还是就座，显示在文本框
            String tState = tableRecord.getState();
            if (tState.equals("预约")) {
                comboBox.getSelectionModel().select(0);
            } else {
                comboBox.getSelectionModel().select(1);
            }
            //2.将选择的菜品封装在集合中
            MenuServiceImpl menuServiceImpl = new MenuServiceImpl();
            ArrayList<Integer> arrayList = menuServiceImpl.exportMenu(tid);

            if (arrayList.get(0) == 1) {
                checkBox1.setSelected(true);
            } else {
                checkBox1.setSelected(false);
            }
            if (arrayList.get(1) == 1) {
                checkBox2.setSelected(true);
            } else {
                checkBox2.setSelected(false);
            }
            if (arrayList.get(2) == 1) {
                checkBox3.setSelected(true);
            } else {
                checkBox3.setSelected(false);
            }
            if (arrayList.get(3) == 1) {
                checkBox4.setSelected(true);
            } else {
                checkBox4.setSelected(false);
            }
            if (arrayList.get(4) == 1) {
                checkBox5.setSelected(true);
            } else {
                checkBox5.setSelected(false);
            }
            if (arrayList.get(5) == 1) {
                checkBox6.setSelected(true);
            } else {
                checkBox6.setSelected(false);
            }
            if (arrayList.get(6) == 1) {
                checkBox7.setSelected(true);
            } else {
                checkBox7.setSelected(false);
            }
            if (arrayList.get(7) == 1) {
                checkBox8.setSelected(true);
            } else {
                checkBox8.setSelected(false);
            }

            //3.获取备注，并显示在文本框
            String memo = tableRecord.getMemo();
            memoInput.setText(memo);

            //获取新的id
            updateIdInput.setText(String.valueOf(tid));
        } catch (Exception e) {
            DialogUtil.Dialog(Alert.AlertType.WARNING, "警告", "温馨提示",
                    "餐桌号不存在");
        }

    }

    //增加餐桌信息的方法
    @FXML
    void sure() {
        try {
            int deleid = Integer.parseInt(selectIdInput.getText());
            int tid = Integer.parseInt(updateIdInput.getText());

            //获得想要添加的tid
            Object selectedItem = comboBox.getSelectionModel().getSelectedItem();
            //选择就餐还是预约
            String state = selectedItem.toString();
            //操作时间
            Date date = new Date();
            //设置备注
            String memo = memoInput.getText();
            //创建新的String类型的ArrayList数组，用来存放选择的菜品
            ArrayList<String> arrayList = new ArrayList<>();

            if (checkBox1.isSelected()) {
                arrayList.add("dish1");
            }
            if (checkBox2.isSelected()) {
                arrayList.add("dish2");
            }
            if (checkBox3.isSelected()) {
                arrayList.add("dish3");
            }
            if (checkBox4.isSelected()) {
                arrayList.add("dish4");
            }
            if (checkBox5.isSelected()) {
                arrayList.add("dish5");
            }
            if (checkBox6.isSelected()) {
                arrayList.add("dish6");
            }
            if (checkBox7.isSelected()) {
                arrayList.add("dish7");
            }
            if (checkBox8.isSelected()) {
                arrayList.add("dish8");
            }

            //调用service层类的方法  实现修改功能
            RecordServiceImpl recordService = new RecordServiceImpl();
            MenuServiceImpl menuService = new MenuServiceImpl();
            PriceServiceImpl priceServiceImpl = new PriceServiceImpl();
            if (new checkDataRange().checkDataRange(tid)) {
                //删除餐桌信息 更新的十分完美
                if (recordService.checkTableId(tid)&&tid!=deleid) {
                    DialogUtil.Dialog(Alert.AlertType.WARNING, "警告", "温馨提示",
                            "餐桌号已存在");
                } else {
                    if (recordService.delTable(deleid) && menuService.deleteMenuById(deleid)) {
                        //获得顾客的消费总金额
                        Double sum = priceServiceImpl.selectPriceTable(arrayList);
                        //获得菜品的总数量
                        int num = arrayList.size();
                        String total = String.valueOf(num);
                        //新创建一个record类，操作record表
                        Record record = new Record(tid, state, total, sum, memo, date);
                        if (recordService.addTable(record)||tid==deleid) {
                            //实现添加menu表中的数据
                            int i = menuService.updateMenuId(arrayList, tid);
                            if (i == 2) {
                                DialogUtil.Dialog(Alert.AlertType.INFORMATION, "提示", "温馨提示",
                                        "修改餐桌信息成功");
                                //调用初始化方法,方便用户多个更新用户信息
                                initInformation();
                            } else if (i == 3) {
                                DialogUtil.Dialog(Alert.AlertType.WARNING, "警告", "温馨提示",
                                        "修改失败，请重新操作");
                            }
                        } else {
                            DialogUtil.Dialog(Alert.AlertType.WARNING, "提示", "温馨提示",
                                    "此餐桌号已经存在");
                        }
                    } else {
                        DialogUtil.Dialog(Alert.AlertType.WARNING, "错误", "温馨提示",
                                "餐桌信息删除失败");
                    }

                }
            } else {
                DialogUtil.Dialog(Alert.AlertType.WARNING, "提示", "温馨提示",
                        "桌号不在1~99范围内，请重新输入");
            }
        } catch (Exception e) {
            DialogUtil.Dialog(Alert.AlertType.WARNING, "提示", "温馨提示",
                    "桌号不在1~99范围内，请重新输入");
        }
    }
}

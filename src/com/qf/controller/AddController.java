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

public class AddController {

    //当前的窗口容器对象;
    private Stage AddStage;

    public Stage getAddStage() {
        return AddStage;
    }

    public void setAddStage(Stage primaryStage) {
        this.AddStage = primaryStage;
    }

    //接受用户输入的id
    @FXML
    private TextField idInput;
    //接受用户输入的备注
    @FXML
    private TextField memoInput;
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

    //界面初始化调用的方法
    @FXML
    public void initialize() {//名字必须这个
        initInformation();
    }

    //初始化信息方法
    @FXML
    void initInformation() {
        //将信息设置为空
        idInput.setText("");
        //设置就餐信息首选项
        comboBox.getSelectionModel().selectFirst();
        //设置选菜默认为空
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

    //用户点击确认按钮的方法
    @FXML
    void sure() {
        try {
            //1.获取数据
            String id = idInput.getText();
            //获得想要添加的tid
            int tid = Integer.parseInt(id);
            Object selectedItem = comboBox.getSelectionModel().getSelectedItem();
            //获取用户就餐还是预约
            String state = selectedItem.toString();
            //操作当前的时间
            Date date = new Date();
            //获取用户输入的备注
            String memo = memoInput.getText();
            //建立用户的选菜信息的List
            ArrayList<String> arrayList = new ArrayList<>();
            //将用户的选择菜加入List
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
            //调用service层实现类
            RecordServiceImpl recordServiceImpl = new RecordServiceImpl();
            MenuServiceImpl menuService = new MenuServiceImpl();
            //消费金额的类
            PriceServiceImpl priceServiceImpl = new PriceServiceImpl();
            //获得顾客的消费总金额
            Double sum = priceServiceImpl.selectPriceTable(arrayList);
            //获得菜品的总数量
            String total = String.valueOf(arrayList.size());
            //判断用户的数据是合法(1~99),如果合法再进行
            if (new checkDataRange().checkDataRange(tid)) {
                //新创建一个record类，操作record表
                Record record = new Record(tid, state, total, sum, memo, date);
                //将信息传入数据库
                if (recordServiceImpl.addTable(record)) {
                    //实现添加menu表中的数据
                    int i = menuService.updateMenuId(arrayList, tid);
                    if (i == 2) {
                        DialogUtil.Dialog(Alert.AlertType.INFORMATION, "提示", "温馨提示",
                                "餐桌创建成功");
                    } else if (i == 3) {
                        DialogUtil.Dialog(Alert.AlertType.WARNING, "警告", "温馨提示",
                                "餐桌创建失败，请重新操作");
                    }
                } else {
                    DialogUtil.Dialog(Alert.AlertType.WARNING, "反馈", "温馨提示",
                            "当前桌号已经存在");
                }
            } else {
                DialogUtil.Dialog(Alert.AlertType.WARNING, "反馈", "温馨提示",
                        "请输入1~99桌号");
            }
            //调用初始化方法,方便用户继续增加
            initInformation();
        } catch (Exception e) {
            DialogUtil.Dialog(Alert.AlertType.WARNING, "警告", "温馨提示",
                    "桌号不在1~99范围内，请重新输入");
        }
    }
}

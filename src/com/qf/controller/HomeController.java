package com.qf.controller;


import com.qf.MainApplication;
import com.qf.domain.Menu;
import com.qf.domain.Record;
import com.qf.service.impl.MenuServiceImpl;
import com.qf.service.impl.MoneyServiceImpl;
import com.qf.service.impl.RecordServiceImpl;
import com.qf.utils.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.util.*;


//主页控制类
public class HomeController {
    //当前的窗口容器对象;
    private Stage HomeStage;

    public void setHomeStage(Stage HomeStage) {
        this.HomeStage = HomeStage;
    }

    @FXML
    public TextField turnoverInput;//用户今天的营业额
    @FXML
    public TextField searchInput;//用户搜索信息
    @FXML
    private ImageView imageViewPicture;//头像
    @FXML
    private Label usernameLabel;//显示的用户名
    //设置第一个表的列数据
    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn stateColumn;
    @FXML
    private TableColumn classificationColumn;
    @FXML
    private TableColumn moneyColumn;
    @FXML
    private TableColumn memoColumn;
    @FXML
    private TableColumn dateColumn;
    //设置第二个表的列数据
    @FXML
    private TableColumn id2Column;
    //第几道菜的数据
    @FXML
    private TableColumn dish1;
    @FXML
    private TableColumn dish2;
    @FXML
    private TableColumn dish3;
    @FXML
    private TableColumn dish4;
    @FXML
    private TableColumn dish5;
    @FXML
    private TableColumn dish6;
    @FXML
    private TableColumn dish7;
    @FXML
    private TableColumn dish8;

    @FXML
    private TableView tableView;
    @FXML
    private TableView tableView2;

    @FXML//初始化调用的方法
    public void initialize() {//名字必须这个
        initUserRecode();//初始化左边数据
        initRecordTableView();//初始化右边数据
    }

    //初始化右边的tableView的方法
    @FXML
    public void initRecordTableView() {
        //查询对应的uid record的内容
        RecordServiceImpl recordService = new RecordServiceImpl();
        List<Record> records = recordService.selectRecordAll();
        //将对应的查询的值显示到tableView上
        ToolsUtil toolsUtil = new ToolsUtil();
        //创建一个数据集对象,并且赋值
        ObservableList<TableColumn> observableList = toolsUtil.getTableViewData(records);
        //传递数据集对象到界面
        toolsUtil.initTableViewData(tableView,
                observableList,
                idColumn,
                stateColumn,
                moneyColumn,
                classificationColumn,
                memoColumn,
                dateColumn);

        //查询对应的menu的内容
        MenuServiceImpl menuService = new MenuServiceImpl();
        ArrayList<Menu> menus = menuService.exportAllMenu();
        //将对应的查询的值显示到tableView上
        //创建一个数据集对象,并且赋值
        ObservableList observableList2 = toolsUtil.getTableViewData2(menus);
        //传递数据集对象2
        toolsUtil.initTableViewData2(tableView2,
                observableList2,
                id2Column,
                dish1,
                dish2,
                dish3,
                dish4,
                dish5,
                dish6,
                dish7,
                dish8);
    }

    //初始化用户信息的方法
    @FXML
    private void initUserRecode() {
        //设置给对应的usernameLabel以及对应的imageViePicture
        usernameLabel.setText(Session.getUser().getNickname());
        //设置图片
        imageViewPicture.setImage(new Image("file:src/com/qf" + Session.getUser().getAvatar()));
        //设置宽高
        imageViewPicture.setFitHeight(45);
        imageViewPicture.setFitWidth(53);
        //设置缓存
        imageViewPicture.setCache(true);
        imageViewPicture.setPreserveRatio(true);
        imageViewPicture.setSmooth(true);
        //获取总的营业额
        MoneyServiceImpl moneyService = new MoneyServiceImpl();
        String s = String.valueOf(moneyService.getTurnover());
        //设置营业额到界面
        turnoverInput.setText(s);
    }

    //查询用户信息的方法
    @FXML
    private void queryInformation() {
        try {
            //tid就是餐桌号
            int tid = Integer.parseInt(searchInput.getText());
            //查询餐桌表
            RecordServiceImpl recordService = new RecordServiceImpl();
            Record record = recordService.findTable(tid);
            //如果查询到的信息为空就是未查询到
            if (record == null) {
                DialogUtil.Dialog(Alert.AlertType.WARNING, "警告", "温馨提示",
                        "没有该餐桌号");
            } else {
                //如果查询到信息,将信息利用get方法,分别获取到,方便排版
                //获取id
                Integer id = record.getId();
                //新建用于显示的字符串ans,并将id加入
                StringBuilder ans = new StringBuilder(id + " 号餐桌的用户信息如下：\n");
                String state = record.getState();
                ans.append("用户就餐状况为：").append(state).append("\n");
                String classification = record.getClassification();
                ans.append("用户菜品数量为：").append(classification).append("\n");
                Double money = record.getMoney();
                ans.append("用户消费金额为：").append(money).append("\n");
                String memo = record.getMemo();
                ans.append("用户备注为：").append(memo).append("\n");
                Date date = record.getTime();
                String time = DateFormatUtil.format(date, "yyyy-MM-dd hh:mm:ss");
                ans.append("用户就餐时间为：").append(time).append("\n");

                ans.append("用户的菜单如下：\n");
                //查询菜单表(service层)
                MenuServiceImpl menuService = new MenuServiceImpl();
                ArrayList<Integer> list = menuService.exportMenu(tid);
                //将查询到的表示有或者无的信息,转化为菜的名字
                String[] str = {"酸菜鱼", "可乐鸡翅", "佛跳墙", "白切鸡", "宫保鸡丁", "九转大肠", "狮子头", "北京烤鸭"};
                LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
                for (int i = 0; i < list.size(); i++) {
                    linkedHashMap.put(str[i], list.get(i));
                }
                //通过map表查询,如果键值对的值为1的话，就输出
                Set<Map.Entry<String, Integer>> entrySet = linkedHashMap.entrySet();
                int i = 1;
                for (Map.Entry<String, Integer> entry : entrySet) {
                    //键值对可以获取
                    if (entry.getValue() == 1) {
                        ans.append(i).append(")").append(entry.getKey()).append("\n");
                        i++;
                    }
                }
                DialogUtil.Dialog(Alert.AlertType.INFORMATION, "温馨提示", "顾客用餐信息一览表：",
                        ans.toString());
            }
        } catch (Exception e) {
            DialogUtil.Dialog(Alert.AlertType.WARNING, "警告", "警告",
                    "请输入整数");
        }
    }

    //删除餐桌信息
    @FXML
    private void del() {
        //调用弹窗界面的帮助类,让用户输入需要删除的餐桌信息.
        ControlUtil controlUtil = new ControlUtil();
        controlUtil.actionPerformed(new ActionEvent(12, 12, "12"));
        //获取用户的输入的需要删除的餐桌号
        int tid = controlUtil.getM();
        //如果餐桌号不为-1(用户输入了数据)再进行删除操作
        if (tid != -1) {
            //查询餐桌信息是否存在(餐桌表和菜单表)
            RecordServiceImpl recordServiceImpl = new RecordServiceImpl();
            MenuServiceImpl menuService = new MenuServiceImpl();
            //如果两个表都删除成功(如果删除失败,则是餐桌号不存在),则提示删除成功
            if (recordServiceImpl.delTable(tid) && menuService.deleteMenuById(tid)) {
                //调用初始化方法
                initialize();
                //进行弹窗提示用户
                DialogUtil.Dialog(Alert.AlertType.WARNING, "提示", "温馨提示",
                        "餐桌信息删除成功");
            } else {
                //调用初始化方法
                initialize();
                //进行弹窗提示用户
                DialogUtil.Dialog(Alert.AlertType.WARNING, "警告", "温馨提示",
                        "餐桌号不存在");
            }
        }

    }

    //增加新的餐桌
    @FXML
    private void add() {
        //打开增加信息的页面进行处理
        new MainApplication().initAdd();
    }

    //修改餐桌信息
    @FXML
    private void update() {
        //打开修改信息的页面进行处理
        new MainApplication().initUpdate();
    }

    //结账代码
    @FXML
    private void initCheckout() {

        //调用弹窗界面的帮助类,让用户输入需要结账的餐桌信息.
        ControlUtil controlUtil = new ControlUtil();
        controlUtil.actionPerformed(new ActionEvent(12, 12, "12"));
        //获取用户输入的id
        int tid = controlUtil.getM();
        //如果餐桌号不为-1(用户输入了数据)再进行删除操作
        if (tid != -1) {
            //查询餐桌信息是否存在(餐桌表和菜单表)
            try {
                MenuServiceImpl menuServiceImpl = new MenuServiceImpl();

                //查找用户信息,存入record
                RecordServiceImpl recordServiceImpl = new RecordServiceImpl();
                Record record = recordServiceImpl.findTable(tid);

                //获取用户本单的结账金额
                Double turnover = record.getMoney();
                System.out.println("2");
                //将金额设置为String类型,方便结账的时候添加信息
                String account = String.valueOf(turnover);
                System.out.println("2");
                //将金额添加到今日营业额
                MoneyServiceImpl moneyServiceImpl = new MoneyServiceImpl();
                moneyServiceImpl.addRecord(turnover);
                System.out.println("2");
                //将record里面的信息获取到字符串ans中,进行排版,
                Integer id = record.getId();
                System.out.println("2");
                String ans = id + " 号餐桌的用户信息如下：\n";
                String classification = record.getClassification();
                ans += "用户菜品数量为：" + classification + "\n";
                String memo = record.getMemo();
                ans += "用户备注为：" + memo + "\n";
                Date date = record.getTime();
                String time = DateFormatUtil.format(date, "yyyy-MM-dd hh:mm:ss");
                ans += "用户就餐时间为：" + time + "\n";
                ans += "用户的菜单如下：\n";
                //查询菜单表
                MenuServiceImpl menuService = new MenuServiceImpl();
                ArrayList<Integer> list = menuService.exportMenu(tid);
                String[] str = {"酸菜鱼", "可乐鸡翅", "佛跳墙", "白切鸡", "宫保鸡丁", "九转大肠", "狮子头", "北京烤鸭"};
                LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<String, Integer>();
                for (int i = 0; i < list.size(); i++) {
                    linkedHashMap.put(str[i], list.get(i));
                }
                //通过map表查询,如果键值对的值为1的话，就输出
                Set<Map.Entry<String, Integer>> entrySet = linkedHashMap.entrySet();
                int i = 1;
                for (Map.Entry<String, Integer> entry : entrySet) {
                    //键值对可以获取
                    if (entry.getValue() == 1) {
                        ans += i + ")" + entry.getKey() + "\n";
                        i++;
                    }
                }
                //如果两个表都删除成功(如果删除失败,则是餐桌号不存在),则提示删除成功
                if (recordServiceImpl.delTable(tid) && menuServiceImpl.deleteMenuById(tid)) {
                    //调用初始化方法
                    initialize();
                    //进行弹窗提示用户
                    DialogUtil.Dialog(Alert.AlertType.INFORMATION, "温馨提示", "欢迎下次光临",
                            ans + "\n结账成功,顾客总消费：" + account + "￥");
                } else {
                    //调用初始化方法
                    initialize();
                    //进行弹窗提示用户
                    DialogUtil.Dialog(Alert.AlertType.WARNING, "警告", "温馨提示",
                            "结账失败,系统错误,请联系技术人员");
                }
            } catch (Exception e) {
                //调用初始化方法
                initialize();
                //进行弹窗提示用户
                DialogUtil.Dialog(Alert.AlertType.WARNING, "提示", "温馨提示",
                        "请输入正确有效的餐桌号");
            }
        }
    }

    //退出登录的方法
    @FXML
    private void signOutUser() {
        //关闭当前界面
        this.HomeStage.close();
        //打开登录界面
        new MainApplication().initLogin();
    }
}

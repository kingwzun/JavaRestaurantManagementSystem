package com.qf;


import com.qf.controller.*;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

//视图页面运行的入口
public class MainApplication extends Application {
    @FXML
    private static Stage primaryStage;//窗口对象
    @FXML
    private Parent root;

    public static void main(String[] args) {
        launch(args);
    }

//设置启动的界面
    @Override
    public void start(Stage primaryStage) {
        MainApplication.primaryStage = primaryStage;
        MainApplication.primaryStage.setTitle("酒家管理系统");
        initLogin();
    }

//设置更改信息的界面
    public Scene initUpdate() {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("views\\UpdateView.fxml"));
            root = fxmlLoader.load();
            Scene scene = new Scene(root);
            //设置节点
            primaryStage.setScene(scene);
            primaryStage.setTitle("酒家管理系统");
            UpdateController controller = fxmlLoader.getController();
            controller.setUpdateStage(primaryStage);
            primaryStage.show();
            return scene;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //设置增加信息的界面
    public Scene initAdd() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("views\\AddView.fxml"));

            root = fxmlLoader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("酒家管理系统");
            //设置节点
            primaryStage.setScene(scene);
            primaryStage.show();
            return scene;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //抽取渲染loginView.fxml的方法
    public Scene initLogin()  {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("views\\LoginView.fxml"));
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        primaryStage.setResizable(false);//可移动大小
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        LoginController controller = fxmlLoader.getController();
        controller.setLoginStage(primaryStage);
        primaryStage.show();
        return scene;
    }

    public Scene initRegister() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("views\\RegisterView.fxml"));
             primaryStage.setResizable(false);//可移动大小
            root = fxmlLoader.load();
            Scene scene = new Scene(root);
            //设置节点
            primaryStage.setScene(scene);

            RegisterController controller = fxmlLoader.getController();
            controller.setRegisterStage(primaryStage);
            primaryStage.show();
            return scene;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Scene initHome() {
        try {
            //1新建fxmlLoad
            FXMLLoader fxmlLoader = new FXMLLoader();
            //2.设置fxml路径
            fxmlLoader.setLocation(getClass().getResource("views\\HomeView.fxml"));
            //5. 获取对应的root
            root = fxmlLoader.load();
            //4. 生成对应的scene
            Scene scene = new Scene(root);
            //6. 设置相关内容
            Stage stage = new Stage();
            primaryStage.setTitle("酒家管理系统");
            stage.setResizable(false);//可移动大小
            stage.setAlwaysOnTop(false);//是不是顶层
            //初始化为应用
            stage.initModality(Modality.APPLICATION_MODAL);
            //覆盖窗口
            stage.initOwner(primaryStage);
            stage.setScene(scene);
            //设置关闭窗口
            HomeController controller = fxmlLoader.getController();
            controller.setHomeStage(stage);
            stage.setTitle("酒家管理系统");
            stage.show();
            //7. 显示
            return scene;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

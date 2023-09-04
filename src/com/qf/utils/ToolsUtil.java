package com.qf.utils;

import com.qf.domain.Menu;
import com.qf.domain.Record;
import com.qf.domain.TableData;
import com.qf.domain.TableData2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class ToolsUtil {
    ObservableList observableList = FXCollections.observableArrayList();
    ObservableList observableList2 = FXCollections.observableArrayList();

    public ObservableList getTableViewData(List<Record> records) {
        for (Record record : records) {
            //创建行数据对象
            TableData tableData = new TableData(
                    String.valueOf(record.getId()),
                    record.getState(),
                    String.valueOf(record.getMoney()),
                    String.valueOf(record.getClassification()),
                    record.getMemo(),
                    //时间需要格式化
                    DateFormatUtil.format(record.getTime(), "yy-MM-dd hh:mm:ss")
            );
            //tableData.setDate("2020-1-1");
            //将行数据加入数据集
            observableList.add(tableData);
        }
        return observableList;
    }

    //获取数据填充到对应的tableView中
    public void initTableViewData(TableView tableView,
                                  ObservableList<TableColumn> data,
                                  TableColumn<TableData, String> idColumn,
                                  TableColumn<TableData, String> typeColumn,
                                  TableColumn<TableData, String> moneyColumn,
                                  TableColumn<TableData, String> classificationColumn,
                                  TableColumn<TableData, String> memoColumn,
                                  TableColumn<TableData, String> dateColumn
    ) {
        //设置各列的数据
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        moneyColumn.setCellValueFactory(cellData -> cellData.getValue().moneyProperty());
        classificationColumn.setCellValueFactory(cellData -> cellData.getValue().classificationProperty());
        memoColumn.setCellValueFactory(cellData -> cellData.getValue().memoProperty());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        //设置列的数据
        tableView.setItems(data);
    }

    public ObservableList getTableViewData2(List<Menu> menus) {


        for (Menu menu : menus) {
            //创建行数据对象
            TableData2 tableData2 = new TableData2();
            tableData2.setId(String.valueOf(menu.getTid()));
            if (menu.getDish1() == 1) {
                tableData2.setDish1("√");
            }
            if (menu.getDish2() == 1) {
                tableData2.setDish2("√");
            }
            if (menu.getDish3() == 1) {
                tableData2.setDish3("√");
            }
            if (menu.getDish4() == 1) {
                tableData2.setDish4("√");
            }
            if (menu.getDish5() == 1) {
                tableData2.setDish5("√");
            }
            if (menu.getDish6() == 1) {
                tableData2.setDish6("√");
            }
            if (menu.getDish7() == 1) {
                tableData2.setDish7("√");
            }
            if (menu.getDish8() == 1) {
                tableData2.setDish8("√");
            }

            //将行数据加入数据集
            observableList2.add(tableData2);
        }
        return observableList2;
    }

    //获取数据填充到对应的tableView中
    public void initTableViewData2(TableView tableView,
                                   ObservableList<TableView> data,
                                   TableColumn<TableData2, String> idColumn,
                                   TableColumn<TableData2, String> dish1,
                                   TableColumn<TableData2, String> dish2,
                                   TableColumn<TableData2, String> dish3,
                                   TableColumn<TableData2, String> dish4,
                                   TableColumn<TableData2, String> dish5,
                                   TableColumn<TableData2, String> dish6,
                                   TableColumn<TableData2, String> dish7,
                                   TableColumn<TableData2, String> dish8
    ) {
        //设置各列的数据
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());

        dish1.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        dish1.setCellValueFactory(cellData -> cellData.getValue().dish1Property());
        dish2.setCellValueFactory(cellData -> cellData.getValue().dish2Property());
        dish3.setCellValueFactory(cellData -> cellData.getValue().dish3Property());
        dish4.setCellValueFactory(cellData -> cellData.getValue().dish4Property());
        dish5.setCellValueFactory(cellData -> cellData.getValue().dish5Property());
        dish6.setCellValueFactory(cellData -> cellData.getValue().dish6Property());
        dish7.setCellValueFactory(cellData -> cellData.getValue().dish7Property());
        dish8.setCellValueFactory(cellData -> cellData.getValue().dish8Property());
        //设置列的数据
        tableView.setItems(data);
    }
}

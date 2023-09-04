package com.qf.domain;

import javafx.beans.property.SimpleStringProperty;

public class TableData {
    private SimpleStringProperty id;//序号
    private SimpleStringProperty type;//分类
    private SimpleStringProperty money;//金额
    private SimpleStringProperty classification;//类型
    private SimpleStringProperty memo;//备注
    private SimpleStringProperty date;//日期


    public TableData(String id, String type, String money, String classification, String memo,String date) {
        this.id = new SimpleStringProperty(id);
        this.type = new SimpleStringProperty(type);
        this.money = new SimpleStringProperty(money);
        this.classification = new SimpleStringProperty(classification);
        this.memo = new SimpleStringProperty(memo);
        this.date = new SimpleStringProperty(date);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setId(String id) {
        this.id.set(id);
    }


    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }



    public SimpleStringProperty typeProperty() {
        return type;
    }



    public SimpleStringProperty moneyProperty() {
        return money;
    }



    public SimpleStringProperty classificationProperty() {
        return classification;
    }



    public SimpleStringProperty memoProperty() {
        return memo;
    }
}


package com.qf.domain;

import java.util.Date;

public class Record {
    private Integer id;
    //餐桌状态类型
    private String state;
    //菜品总数量
    private String classification;
    //总金额
    private Double money;
    //备注
    private String memo;
    //时间
    private Date time;

    public Record() {

    }

    public Record(Integer id, String state, String classification, Double money, String memo, Date time) {
        this.id = id;
        this.state = state;
        this.classification = classification;
        this.money = money;
        this.memo = memo;
        this.time = time;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public Double getMoney() {
        return money;
    }

    public String getClassification() {
        return classification;
    }

    public String getMemo() {
        return memo;
    }

    public Date getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", classification='" + classification + '\'' +
                ", money=" + money +
                ", memo='" + memo + '\'' +
                ", time=" + time +
                '}';
    }
}

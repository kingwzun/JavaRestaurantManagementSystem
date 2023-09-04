package com.qf.service;

public interface MoneyService {
    //获取今日的营销额
    Double getTurnover();

    //判断当前日期数据是否存在
    boolean checkForRecord();

    //将当前日期的金额刷新进入tMoeny表中
    boolean addRecord(Double turnover);
}

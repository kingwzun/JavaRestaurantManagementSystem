package com.qf.dao;

import java.util.ArrayList;

//实现对价格表的操作
public interface PriceDao {
    //查询功能,查询功能中封装着对菜品总价格的计算，返回客人本次用餐的费用
    Double selectPriceTable(ArrayList<String> arrayList);
}

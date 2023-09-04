package com.qf.dao.impl;

import com.qf.dao.PriceDao;
import com.qf.utils.DbUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PriceDaoImpl implements PriceDao {
    //从t_price表中获取数据
    @Override
    public Double selectPriceTable(ArrayList<String> arrayList) {
        //从前台获得菜单的数据表
        //写获得价格的SQL语句
        String sql = "select price from t_price where name = ?";
        //获得执行类方法
        PreparedStatement preparedStatement = DbUtil.getPreparedStatement(sql);
        double sum = 0.00;
        //将存在的信息或的后存入arrayList表中
        for (int i = 0; i < arrayList.size(); i++) {
            try {
                preparedStatement.setString(1, arrayList.get(i));
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    double price = resultSet.getDouble(1);
                    sum += price;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return sum;
    }
}

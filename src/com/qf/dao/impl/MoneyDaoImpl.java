package com.qf.dao.impl;

import com.qf.dao.MoneyDao;
import com.qf.utils.DateFormatUtil;
import com.qf.utils.DbUtil;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class MoneyDaoImpl implements MoneyDao {
    Date time = new Date();
    String date = DateFormatUtil.format(time, "yyyy-MM-dd");

    //读取t_money中今天的营业额 测试成功
    @Override
    public Double getTurnover() {
        String sql = "select turnover from t_money where date = ?";
        PreparedStatement preparedStatement = DbUtil.getPreparedStatement(sql);
        try {
            double sum = 0.00;
            preparedStatement.setString(1, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            //获得money数据累加
            while (resultSet.next()) {
                sum = resultSet.getDouble(1);
            }
            return sum;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.00;
    }

    //判断当前日期是否存在 true是存在 false是不存在
    @Override
    public boolean checkForRecord() {
        String sql = "select * from t_money where date = ?";
        PreparedStatement preparedStatement = DbUtil.getPreparedStatement(sql);
        try {
            preparedStatement.setString(1, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //向表中添加数据
    @Override
    public boolean addRecord(Double turnover) {
        JdbcTemplate template = DbUtil.getJdbcTemplate();
        //再添加的过程中，我应该首先获取原来表中的数据，然后再加上的得到的钱，然后转存到数据库中
        Double turnover1 = getTurnover();
        System.out.println(turnover1);
        Double Money = turnover1 + turnover;
        if (checkForRecord()) {
            String sql = "update t_money set turnover = ? where date = ?";
            int result = template.update(sql, Money, date);
            return result > 0;
        } else {
            String sql = "insert into t_money values(?,?)";
            int result = template.update(sql, date,Money );
            return result > 0;
        }
    }
}

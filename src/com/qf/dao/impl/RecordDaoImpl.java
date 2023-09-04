package com.qf.dao.impl;

import com.qf.dao.Mapper.RecordRowMapper;
import com.qf.dao.RecordDao;
import com.qf.domain.Record;
import com.qf.utils.DateFormatUtil;
import com.qf.utils.DbUtil;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RecordDaoImpl implements RecordDao {
    //通过id查找餐桌记录
    JdbcTemplate template = DbUtil.getJdbcTemplate();

    @Override
    public Record selectRecordById(int tid) {
        String sql = "select * from t_record where tid=?";
        PreparedStatement preparedStatement = DbUtil.getPreparedStatement(sql);
        try {
            preparedStatement.setInt(1, tid);
            ResultSet resultSet = preparedStatement.executeQuery();
            Record record = null;
            while (resultSet.next()) {
                record = new Record(
                        resultSet.getInt(1),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDouble(5),
                        resultSet.getString(6),
                        resultSet.getDate(7)
                );
            }
            return record;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    //添加桌子 重写成功
    //tId tState tClassification tMoney tDate tMemo
    @Override
    public boolean addTable(Record record) {
        if (checkTableId(record.getId())) {
            return false;
        } else {
            String sql = "insert into t_record values(?,?,?,?,?,?)";
            int result = template.update(sql,
                    record.getId(), record.getState(),
                    record.getClassification(),
                    record.getMoney(), record.getMemo(),
                    DateFormatUtil.format(record.getTime(), "yyyy-MM-dd hh:mm:ss"));
            return result > 0;
        }
    }

    //删除餐桌
    @Override
    public boolean delTable(int tid) {
        //判断要删除的餐桌号是否存在
        if (checkTableId(tid)) {
            String sql = "delete from t_record where tid=?";
            int result = template.update(sql, tid);
            return result > 0;
        }
        return false;
    }

    //修改餐桌号信息：满足客人想换餐桌的需求
    @Override
    public boolean updateTable(int tid, int newId) {
        String sql = "update t_record set tid = ? where tid=?";
        if (checkTableId(tid)) {
            return false;
        }
        int result = template.update(sql, tid, newId);
        return result > 0;
    }

    //tId tState tClassification tMoney tDate tMemo
    //根据tid查询对应的餐桌信息，返回对应的record
    @Override
    public Record findTable(int tid) {
        String sql = "select * from t_record where tid = ?";
        /*List<Record> list = template.query(sql, new RecordRowMapper());*/
        PreparedStatement preparedStatement = DbUtil.getPreparedStatement(sql);
        try {
            preparedStatement.setInt(1, tid);
            ResultSet resultSet = preparedStatement.executeQuery();
            Record record = null;
            if (resultSet.next()) {
                record = new Record();
                record.setId(resultSet.getInt(1));
                record.setState(resultSet.getString(2));
                record.setClassification(resultSet.getString(3));
                record.setMoney(resultSet.getDouble(4));
                record.setMemo(resultSet.getString(5));
                //将数据表中的字符串转化成Date格式
                record.setTime(DateFormatUtil.pares(resultSet.getString(6), "yyyy-MM-dd hh:mm:ss"));
            }
            return record;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
//        return list.get(0);
    }

    //餐桌号查重
    @Override
    public boolean checkTableId(int tid) {
        String sql = "select * from t_record where tid = ?";
        PreparedStatement preparedStatement = DbUtil.getPreparedStatement(sql);
        try {
            preparedStatement.setInt(1, tid);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //查询所有的数据信息
    @Override
    public List<Record> selectRecordAll() {
        String sql = "select *from t_record ";
        List<Record> list = template.query(sql, new RecordRowMapper());
        return list;
    }
}

package com.qf.dao.Mapper;

import com.qf.utils.DateFormatUtil;
import com.qf.domain.Record;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class RecordRowMapper implements RowMapper<Record> {
    @Override
    public Record mapRow(ResultSet resultSet, int i) throws SQLException {
        //tId tState tClassification tMoney tDate tMemo
        Integer tid = resultSet.getInt("tid");
        String tState = resultSet.getString("tState");
        String tClassification = resultSet.getString("tClassification");
        Double tMoney = resultSet.getDouble("tMoney");
        String tMemo = resultSet.getString("tMemo");
        //将数据表中的字符串转化成Date格式

        Date tDate = DateFormatUtil.pares(resultSet.getString("tDate"), "yyyy-MM-dd hh:mm:ss");

        Record record = new Record();
        record.setId(tid);
        record.setState(tState);
        record.setClassification(tClassification);
        record.setMoney(tMoney);
        record.setMemo(tMemo);
        record.setTime(tDate);
        return record;
    }
}

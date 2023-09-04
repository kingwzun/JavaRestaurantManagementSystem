package com.qf.service;

import com.qf.domain.Record;

import java.util.List;

public interface RecordService {
    //通过ID查询返回用户的record数据
    Record selectRecordById(int uid);

    //添加餐位信息
    boolean addTable(Record record);

    //删除餐桌
    boolean delTable(int tid);

    //修改餐桌号
    boolean updateTable(int tid, int newId);

    //查询餐桌信息
    Record findTable(int tid);

    //餐桌号查重
    boolean checkTableId(int tid);

    //查询所有餐桌信息
    List<Record> selectRecordAll();

}

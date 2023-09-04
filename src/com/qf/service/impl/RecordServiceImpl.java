package com.qf.service.impl;

import com.qf.dao.impl.RecordDaoImpl;
import com.qf.domain.Record;
import com.qf.service.RecordService;

import java.util.List;

public class RecordServiceImpl implements RecordService {
    @Override
    public Record selectRecordById(int uid) {
        RecordDaoImpl recordDao = new RecordDaoImpl();
        return recordDao.selectRecordById(uid);
    }

    @Override
    public boolean addTable(Record record) {
        RecordDaoImpl recordDao = new RecordDaoImpl();
        return recordDao.addTable(record);
    }

    @Override
    public boolean delTable(int tid) {
        RecordDaoImpl recordDao = new RecordDaoImpl();
        return recordDao.delTable(tid);
    }

    @Override
    public boolean updateTable(int tid, int newId) {
        RecordDaoImpl recordDao = new RecordDaoImpl();
        return recordDao.updateTable(tid,newId);
    }

    @Override
    public Record findTable(int tid) {
        RecordDaoImpl recordDao = new RecordDaoImpl();
        return recordDao.findTable(tid);
    }

    @Override
    public boolean checkTableId(int tid) {
        RecordDaoImpl recordDao = new RecordDaoImpl();
        return recordDao.checkTableId(tid);
    }

    @Override
    public List<Record> selectRecordAll() {
        RecordDaoImpl recordDao = new RecordDaoImpl();
        return recordDao.selectRecordAll();
    }
}

package com.qf.service.impl;

import com.qf.dao.impl.MoneyDaoImpl;
import com.qf.service.MoneyService;

public class MoneyServiceImpl implements MoneyService {
    @Override
    public Double getTurnover() {
        MoneyDaoImpl moneyDao = new MoneyDaoImpl();
        return moneyDao.getTurnover();
    }

    @Override
    public boolean checkForRecord() {
        MoneyDaoImpl moneyDao = new MoneyDaoImpl();
        return moneyDao.checkForRecord();
    }

    @Override
    public boolean addRecord(Double turnover) {
        MoneyDaoImpl moneyDao = new MoneyDaoImpl();
        return moneyDao.addRecord(turnover);
    }
}

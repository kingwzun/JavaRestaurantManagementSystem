package com.qf.service.impl;

import com.qf.dao.impl.PriceDaoImpl;
import com.qf.service.PriceService;

import java.util.ArrayList;

public class PriceServiceImpl implements PriceService {
    @Override
    public Double selectPriceTable(ArrayList<String> arrayList) {
        PriceDaoImpl priceDao = new PriceDaoImpl();
        return priceDao.selectPriceTable(arrayList);
    }
}

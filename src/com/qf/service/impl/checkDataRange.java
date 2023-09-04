package com.qf.service.impl;

import com.qf.service.DataRangeService;
//判断用户的数据是合法(1~99)
public class checkDataRange implements DataRangeService {
    @Override
    public boolean checkDataRange(int tid) {
        if (tid >= 1 && tid <= 99) {
            return true;
        }
        return false;
    }
}

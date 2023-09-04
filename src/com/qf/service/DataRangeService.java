package com.qf.service;
//判断用户的数据是合法(1~99)
public interface DataRangeService {
    boolean checkDataRange(int tid);
}

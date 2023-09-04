package com.qf.dao;

import com.qf.domain.Menu;

import java.util.ArrayList;

//数据表menu的CRUD
public interface MenuDao {
    //餐桌号查重
    boolean checkMenuById(int tid);

    //添加餐桌桌号
    boolean addMenuId(int tid);

    //选菜,输入的是集合,集合中储存dish1,dish2,dish3......
    int updateMenuId(ArrayList<String> arrayList, int tid);

    //通过餐桌号导出菜单集合
    ArrayList<Integer> exportMenu(int tid);

    //修改餐桌号
    boolean changeTableId(int tid, int newId);

    //删除数据表
    boolean deleteMenuById(int tid);

    //查询所有
    public ArrayList<Menu> exportAllMenu();
}

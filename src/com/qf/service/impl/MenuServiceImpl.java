package com.qf.service.impl;

import com.qf.dao.impl.MenuDaoImpl;
import com.qf.domain.Menu;
import com.qf.service.MenuService;

import java.util.ArrayList;

public class MenuServiceImpl implements MenuService {
    @Override
    public boolean checkMenuById(int tid) {
        MenuDaoImpl menuDao = new MenuDaoImpl();
        return menuDao.checkMenuById(tid);
    }

    @Override
    public boolean addMenuId(int tid) {
        MenuDaoImpl menuDao = new MenuDaoImpl();
        return menuDao.addMenuId(tid);

    }

    @Override
    public int updateMenuId(ArrayList<String> arrayList, int tid) {
        MenuDaoImpl menuDao = new MenuDaoImpl();
        return menuDao.updateMenuId(arrayList, tid);

    }

    @Override
    public ArrayList<Integer> exportMenu(int tid) {

        MenuDaoImpl menuDao = new MenuDaoImpl();
        return menuDao.exportMenu(tid);
    }

    @Override
    public boolean changeTableId(int tid, int newId) {
        MenuDaoImpl menuDao = new MenuDaoImpl();
        return menuDao.changeTableId(tid,newId);
    }

    @Override
    public boolean deleteMenuById(int tid) {
        MenuDaoImpl menuDao = new MenuDaoImpl();
        return menuDao.deleteMenuById(tid);
    }

    @Override
    public ArrayList<Menu> exportAllMenu() {
        MenuDaoImpl menuDao = new MenuDaoImpl();
        return menuDao.exportAllMenu();
    }
}

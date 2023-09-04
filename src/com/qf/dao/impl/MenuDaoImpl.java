package com.qf.dao.impl;

import com.qf.dao.Mapper.MenuRowMapper;
import com.qf.dao.MenuDao;
import com.qf.domain.Menu;
import com.qf.utils.DbUtil;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDaoImpl implements MenuDao {

    JdbcTemplate template = DbUtil.getJdbcTemplate();

    //餐桌号查重
    @Override
    public boolean checkMenuById(int tid) {
        Connection connection = DbUtil.getConnection();
        String sql = "select * from t_menu where tid = ?";
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

    //添加餐桌号 success;
    @Override
    public boolean addMenuId(int tid) {
        //餐桌号不存在的话就创建
        if (!checkMenuById(tid)) {
            String sql = "insert into t_menu(tid) values(?)";
            int rs = template.update(sql, tid);
            return rs > 0;
        }
        //餐桌号存在的话,就返回fasle
        return false;
    }

    //修改餐桌号 重写成功
    @Override
    public boolean changeTableId(int tid, int newId) {
        //餐桌号存在才可以修改
        if (checkMenuById(tid)) {
            String sql = "update t_menu set tid = ? where tid = ?";
            int rs = template.update(sql, newId, tid);
            return rs > 0;
        }
        //餐桌号不存在就返回错误
        return false;
    }

    //删除菜单信息 重写成功
    @Override
    public boolean deleteMenuById(int tid) {
        JdbcTemplate template = DbUtil.getJdbcTemplate();
        if (checkMenuById(tid)) {
            String sql = "delete from t_menu where tid = ?";
            int rs = template.update(sql, tid);
            return rs > 0;
        }
        return false;
    }

    //向表中添加顾客选择好的菜品;
    @Override
    public int updateMenuId(ArrayList<String> arrayList, int tid) {
        int flag = 0;
        if (checkMenuById(tid)) {
            //标记餐桌已经存在
            flag = 1;
            return flag;
        } else {
            //不存在这个数据的话就创建对应的餐桌号信息
            addMenuId(tid);
            //如果餐桌创建成功的话，就进行插入数据操作
            for (int i = 0; i < arrayList.size(); i++) {
                try {
                    String sql = "update t_menu set " + arrayList.get(i) + " = 1 where tid = ?";
                    PreparedStatement preparedStatement = DbUtil.getPreparedStatement(sql);
                    preparedStatement.setInt(1, tid);
                    int rs = preparedStatement.executeUpdate();
                    if (rs > 0) {
                        //菜品添加成功
                        flag = 2;
                    } else {
                        //菜品添加失败
                        flag = 3;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return flag;
        }
    }

    ////通过tid导出对应的菜单结构表 success
    @Override
    public ArrayList<Integer> exportMenu(int tid) {
        ArrayList<Integer> arrayList = null;
        //如果对应的菜单表的结构存在的话
        if (checkMenuById(tid)) {
            //这个的意思就是从第2条数据开始选择，一共筛选8条，在条件符合的情况下
            String sql = "select * from t_menu where tid = ?";
            PreparedStatement preparedStatement = DbUtil.getPreparedStatement(sql);
            try {
                preparedStatement.setInt(1, tid);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    arrayList = new ArrayList<>();
                    arrayList.add(resultSet.getInt(2));
                    arrayList.add(resultSet.getInt(3));
                    arrayList.add(resultSet.getInt(4));
                    arrayList.add(resultSet.getInt(5));
                    arrayList.add(resultSet.getInt(6));
                    arrayList.add(resultSet.getInt(7));
                    arrayList.add(resultSet.getInt(8));
                    arrayList.add(resultSet.getInt(9));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            return null;
        }
        return arrayList;
    }

    //导出menu表的全部数据
    @Override
    public ArrayList<Menu> exportAllMenu() {
        String sql = "select * from t_menu";
        try {
            List<Menu> list = template.query(sql, new MenuRowMapper());
            return (ArrayList<Menu>) list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
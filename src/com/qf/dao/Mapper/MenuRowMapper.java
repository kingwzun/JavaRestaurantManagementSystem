package com.qf.dao.Mapper;

import com.qf.domain.Menu;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Integer tid = resultSet.getInt("tid");
        Integer dish1 = resultSet.getInt("dish1");
        Integer dish2 = resultSet.getInt("dish2");
        Integer dish3 = resultSet.getInt("dish3");
        Integer dish4 = resultSet.getInt("dish4");
        Integer dish5 = resultSet.getInt("dish5");
        Integer dish6 = resultSet.getInt("dish6");
        Integer dish7 = resultSet.getInt("dish7");
        Integer dish8 = resultSet.getInt("dish8");

        Menu menu = new Menu();
        menu.setTid(tid);
        menu.setDish1(dish1);
        menu.setDish2(dish2);
        menu.setDish3(dish3);
        menu.setDish4(dish4);
        menu.setDish5(dish5);
        menu.setDish6(dish6);
        menu.setDish7(dish7);
        menu.setDish8(dish8);

        return menu;
    }
}

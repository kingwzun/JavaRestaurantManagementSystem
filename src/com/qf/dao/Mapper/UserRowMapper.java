package com.qf.dao.Mapper;

import com.qf.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        Integer id = resultSet.getInt("id");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String avatar = resultSet.getString("avatar");
        String nickname = resultSet.getString("nickname");

        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setAvatar(avatar);
        user.setNickname(nickname);

        return user;
    }
}

package com.qf.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Properties;

/**
 * 封装sql连接及关闭的相关代码
 */
public class DbUtil {
    private static Connection conn;
    private static PreparedStatement preparedStatement;
    private static DataSource ds;

    static {
        //加载配置文件
        Properties pro = new Properties();
        try {
            pro.load(DbUtil.class.getClassLoader().getResourceAsStream("db.properties"));
            //获取datasource
            ds = DruidDataSourceFactory.createDataSource(pro);
            conn = ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 获取连接
     * */
    public static Connection getConnection() {
        return conn;
    }

    /**
     * 释放资源
     */
    public static void close(Statement stmt, Connection conn) {
        close(null, stmt, conn);
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //获取连接池的方法
    public static DataSource getDataSource() {
        return ds;
    }

    //封装获取JdbcTemplate的静态方法；
    public static JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(ds);
    }

    /**
     * 封装的返回执行对象的方法
     */
    public static PreparedStatement getPreparedStatement(String sql) {

        try {
            preparedStatement = conn.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return preparedStatement;
    }
}

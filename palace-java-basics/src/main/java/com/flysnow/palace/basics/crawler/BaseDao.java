package com.flysnow.palace.basics.crawler;

/**
 * @Package com.flysnow.palace.basics.crawler
 * @Description
 * @Author Fly
 * @Date 2019-11-07 9:50
 * @Version V1.0
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {

    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/poems?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&autoReconnect=true&useSSL=false";
    private static final String name = "root";
    private static final String pwd = "123456";
    Connection conn = null;


    /*public Connection getconn() {
        Connection conn = null;
        Context ctx;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/news");
            conn = ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }*/

    /**
     * 获取连接
     *
     * @return Connection
     */
    protected Connection getconn() {
        conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, name, pwd);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     *
     * @param conn
     * @param ps
     * @param rs
     */
    protected void closeAll(Connection conn, PreparedStatement ps, ResultSet rs) {
        if (rs != null)
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }

    /**
     * 增/删/改/ , 接受 参数为 SQL语句 和 对象数组
     *
     * @param sql
     * @param ob
     * @return int 返回受影响行数
     */
    public int executeUpdate(String sql, Object[] ob) {
        conn = getconn();
        PreparedStatement ps = null;
        try {
            ps = prepareStatement(conn, sql, ob);
            int i = ps.executeUpdate();
            return i;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            //	e.printStackTrace();
            return 0;
        } finally {
            closeAll(conn, ps, null);
        }

    }

    /**
     * 构造SQL语法
     *
     * @param conn
     * @param sql
     * @param ob
     * @return PreparedStatement
     */
    protected PreparedStatement prepareStatement(Connection conn, String sql, Object[] ob) {
        PreparedStatement ps = null;
        try {
            int index = 1;
            ps = conn.prepareStatement(sql);
            if (ps != null && ob != null) {
                for (int i = 0; i < ob.length; i++) {
                    ps.setObject(index, ob[i]);
                    index++;
                }
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return ps;
    }

}

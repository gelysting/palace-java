package com.flysnow.palace.basics.crawler;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * @Package com.flysnow.palace.basics.crawler
 * @Description
 * @Author Fly
 * @Date 2019-11-07 10:09
 * @Version V1.0
 */
public class JDBCUtil {

    static Logger logger = Logger.getLogger("JDBCUtil");

    //JDBC驱动
    private static String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    //数据库用户名
    private static String username = "root";
    //数据库密码
    private static String password = "123456";
    //数据库连接字符串
    private static String connUrl = "jdbc:mysql://localhost:3306/poems?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&autoReconnect=true&useSSL=false";

    private Connection conn = null;

    /**
     * 利用构造函数 自动创建数据库连接，将连接数据库方法改造为私有函数
     */
    public JDBCUtil() {
        try {
            getConnect();
            logger.info("==========>> 构造函数-自动创建-数据库连接");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接数据库
     *
     * @throws Exception
     */
    private Connection getConnect() throws Exception {
        Class.forName(jdbcDriver);
        conn = DriverManager.getConnection(connUrl, username, password);
        return conn;
    }


    /**
     * 关闭数据库，释放内存
     *
     * @throws SQLException
     */
    private void close() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    /**
     * 理应在每个类都创建：自定义构造函数，自定义析构函数【用 destroy() 方法代替，并显式调用，用于销毁对象】
     * 关闭数据库连接（关闭Java对外的访问），释放内存
     */
    public void destroy() {
        try {
            close();
            logger.info("==========>> 显式调用-手动回收-数据库连接");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 利用 Java 的收尾功能-垃圾回收
     * <p>
     * Java 是垃圾自动收集语言，实际上不存在真正的析构函数，还是需要显示调用才能执行回收动作。
     * <p>
     * java 提供 finalize()方法，垃圾回收器准备释放内存的时候，会先调用 finalize()。
     * 当垃圾回收器(garbage colector)决定回收某对象时，就会运行该对象的 finalize()方法
     * 如果在 Applet 或应用程序退出之前垃圾回收器没有释放内存，垃圾回收器将不会调用 finalize()。
     */
    protected void finalize() {
        try {
            close();
            logger.info("==========>> 垃圾回收-自动回收-数据库连接");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            super.finalize();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * 设置PrepareStatement对象中Sql语句中的参数
     *
     * @param sql    sql语句
     * @param params 参数列表
     * @return PreparedStatement 增删改查 参数对象
     * @throws SQLException
     */
    private PreparedStatement setPrepareStatementParams(String sql, Object[] params) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(sql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                pstm.setObject(i + 1, params[i]);
            }
        }
        return pstm;
    }

    /**
     * 执行查询
     *
     * @param sql    sql语句
     * @param params 参数列表
     * @return ResultSet类型的查询结果
     * @throws SQLException
     */
    public ResultSet executeQuery(String sql, Object[] params) throws SQLException {
        return setPrepareStatementParams(sql, params).executeQuery();
    }

    /**
     * 执行更新数据库（增/删/改）
     *
     * @param sql    String   sql语句
     * @param params Object[] 参数列表
     * @return int 执行操作的结果 受影响行数
     * @throws SQLException
     */
    public int executeUpdate(String sql, Object[] params) throws SQLException {
        return setPrepareStatementParams(sql, params).executeUpdate();
    }


    /**
     * 设置CallableStatement对象中Sql语句中的参数
     *
     * @param sql      String sql语句
     * @param inParams Object[] 参数列表
     * @return CallableStatement 存储过程 参数对象
     * @throws SQLException
     */
    private CallableStatement setCallProcedure(String sql, Object[] inParams) throws SQLException {
        CallableStatement cs = conn.prepareCall(sql);
        if (inParams != null) {
            for (int i = 0; i < inParams.length; i++) {
                cs.setObject(i + 1, inParams[i]);
            }
        }
        return cs;
    }

    /**
     * 执行存储过程（只有入参，可以获取存储过程结果集）
     *
     * @param sql      String sql语句
     * @param inParams Object[] 参数列表
     * @return ResultSet类型的查询结果
     * @throws SQLException
     */
    public ResultSet executeProcedure(String sql, Object[] inParams) throws SQLException {
        return setCallProcedure(sql, inParams).executeQuery();
    }

    public static void main(String[] args) {
        System.out.println("Start...");
    }
}
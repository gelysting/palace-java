package cn.knight.fly.test.crawler;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @Package cn.knight.fly.test.crawler
 * @Description
 * @Author Fly
 * @Date 2019-11-08 15:58
 * @Version V1.0
 */
public class TestSQL {

    /**
     * mysql 6 以下版本使用驱动名：com.mysql.jdbc.Driver
     * url = jdbc:mysql://localhost:3306/test?serverTimezone=Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false
     * <p>
     * mysql 6 及以上版本使用驱动名：com.mysql.cj.jdbc.Driver
     * mysql 6 及以上版本必须指定时区
     * url = jdbc:mysql://localhost:3306/test?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false
     * serverTimezone=Asia/Shanghai
     * serverTimezone=Asia/Hongkong
     * <p>
     * MySQL 8.0 以上版本不需要建立 SSL 连接的，需要显示关闭
     * <p>
     * useUnicode=true&characterEncoding=utf8
     * 用于转换字符编码
     */

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/poems?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException cex) {
            cex.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * 调用存储过程方法
     *
     * @param era
     * @param author
     * @param title
     * @param content
     * @return
     */
    public static int executeProcedure(String era, String author, String title, String content) {
        Connection conn = null;
        try {
            String sql = " call addPoems(?,?,?,?,?); ";
            conn = getConnection();
            CallableStatement callableStatement = conn.prepareCall(sql);
            callableStatement.setString(1, era);
            callableStatement.setString(2, author);
            callableStatement.setString(3, title);
            callableStatement.setString(4, content);
            callableStatement.registerOutParameter(5, java.sql.Types.INTEGER); //用于获取 过程中的OUT参数

            //所有调用存储过程都用executeQuery
            ResultSet rs = callableStatement.executeQuery();

            int retrow = callableStatement.getInt(5);
            System.err.println("retrow=" + retrow);

            if (rs.next()) {

                System.err.println("ResultSet : " + rs.getRow());
            }

            rs.close();
            callableStatement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * 增删改方法
     *
     * @param era
     * @param author
     * @param title
     * @param content
     * @return
     */
    public static int executeUpdate(String era, String author, String title, String content) {
        Connection conn = null;
        try {
            String sql = "insert into poems(era,author,title,content) values(?,?,?,?)";
            conn = getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, era);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, title);
            preparedStatement.setString(4, content);

            int updateRowCount = preparedStatement.executeUpdate();

            preparedStatement.close();
            conn.close();

            return updateRowCount;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 普通的查询方法
     *
     * @throws Exception
     */
    public static void executeQuery() throws Exception {

        String driver = "com.mysql.cj.jdbc.Driver";  //需要引入依赖
        Class.forName(driver); //按指定名称加载驱动，并且执行类初始化，可以通过第二个参数控制是否初始化
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver()); // 或者使用此方法

        String url = "jdbc:mysql://localhost:3306/poems?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&autoReconnect=true&useSSL=false";
        String user = "root";
        String password = "123456";
        Connection conn = DriverManager.getConnection(url, user, password); //创建链接

        if (!conn.isClosed()) System.out.println("Succeded connecting to the MySQL Database !");

        String executeSQL = "select book_id,name,number from book where book_id = ?"; // 用英文标点符号"?"可以传递参数 , 1000
        PreparedStatement ps = conn.prepareStatement(executeSQL);
        ps.setInt(1, 1000);  //用set方法加载SQL的参数

        ResultSet resultSet; //创建结果集以便获取执行SQL的查询结果

        resultSet = ps.executeQuery(); // 默认执行最后一个设置的SQL语句

        executeSQL = "Select book_id,name,number from book";
        resultSet = ps.executeQuery(executeSQL);

        System.out.println("加载的SQL语句：" + ps.toString());
        System.out.println("执行的SQL语句：" + executeSQL + "\n");

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData(); //获取对象列信息
        //ResultSetMetaData resultSetMetaData1 = ps.getMetaData(); // 与上一行等价，因为PreparedStatement对象被预编译，所以不必执行就知道它将返回的Result对象。
        int columnCount = resultSetMetaData.getColumnCount(); //用于获取总列数


        boolean isTitle = false;
        int rowCount = 0; //用于获取总行数
        while (resultSet.next()) {
            rowCount++;

            if (!isTitle) {
                for (int j = 1; j <= columnCount; j++) {
                    System.out.print(resultSetMetaData.getColumnName(j) + "\t");
                }
                System.out.println("\n-----------------------");
                isTitle = true;
            }

            System.out.print("" + resultSet.getInt("book_id"));
            System.out.print("," + resultSet.getString("name"));
            System.out.println("," + resultSet.getInt("book_id"));
        }
        System.out.println("-----------------------");
        System.out.println("输出总行数：" + rowCount);
        System.out.println("输出总列数：" + columnCount);

        resultSet.close();
        ps.close();
        conn.close(); //关闭链接
    }

    public static void main(String[] args) {


        /**
         * 通过DatabaseMetaData 接口获取连接的数据库的整体综合信息。
         */
        Connection connection = null;
        try {
            connection = getConnection();
            try {
                DatabaseMetaData databaseMetaData = connection.getMetaData();
                int majVersion = databaseMetaData.getDatabaseMajorVersion(); //获取底层数据库的主版本号。
                int minVersion = databaseMetaData.getDatabaseMinorVersion(); //底层数据库的次版本号。
                String proName = databaseMetaData.getDatabaseProductName(); //获取此数据库产品的名称。
                String proVersion = databaseMetaData.getDatabaseProductVersion(); //获取此数据库产品的版本号。
                //...
                System.out.println("majVersion = " + majVersion + ", minVersion = " + minVersion + ", proName = " + proName + ", proVersion = " + proVersion);
                System.out.println("DriverName：" + databaseMetaData.getDriverName() + ",DriverVersion：" + databaseMetaData.getDriverVersion());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        //try {
        //    executeQuery();
        //} catch (Exception ex) {
        //    ex.printStackTrace();
        //}
        //try {
        //    int ret = 0;
        //    String era = "未来";
        //    String author = "future";
        //    String title = "无题-2";
        //    String content = "未来深不可测，未来有多深，深深的黝黑，深深的沟渠";
        //
        //    ret = executeUpdate(era,author,title,content);  // 普通的增删改方法
        //
        //    ret = executeProcedure(era,author,title,content); // 调用存储过程的方法
        //
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}

    }
}

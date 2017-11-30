package utils;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * <p>
 * 封装Jdbc的加载驱动,创建连接,关闭资源等操作
 * </p>
 *
 * @author sqm
 * @version 1.0
 */
public class JdbcUtils {
    //私有静态常量,和注册驱动有关,和创建连接对象有关
    private static String DRIVERCLASS;
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;
    private static Connection connection;

    //静态代码块,用于加载此类时直接初始化常量
    static {
        //从properties配置文件中获取值
        DRIVERCLASS = ResourceBundle.getBundle("jdbc").getString("driverclass");
        URL = ResourceBundle.getBundle("jdbc").getString("url");
        USERNAME = ResourceBundle.getBundle("jdbc").getString("username");
        PASSWORD = ResourceBundle.getBundle("jdbc").getString("password");

        //静态初始化加载驱动
        try {
            Class.forName(DRIVERCLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //静态初始化创建连接对象
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     *<p>
     *获得对应数据库的连接对象
     *</p>
     *@return   对应数据库的连接对象
     */
    public static Connection getConnection() throws SQLException {
        return connection;
    }

    /**
     *<p>
     *关闭资源的方法
     *</p>
     *@param    connection  要关闭的连接对象
     *@param    statement  要关闭的sql声明对象
     *@param    resultSet   要关闭的结果集对象
     *@return
     */
    public static void close(Connection connection, Statement statement, ResultSet resultSet) throws  SQLException{
        if (connection != null) {
            connection.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (resultSet != null) {
            resultSet.close();
        }
    }
}

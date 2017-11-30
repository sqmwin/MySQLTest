package dao;

import domain.User;
import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>
 * 实现对数据库的操作
 * </p>
 *
 * @author sqm
 * @version 1.0
 */
public class UserDaoImpl implements UserDao {
    /**
     *<p>
     *检索用户信息是否在数据库中
     *</p>
     *@param    user    要检索的user对象
     *@return   若返回新的user对象,则是数据库中检索到了此数据,若返回null则代表无此user
     */
    @Override
    public User retrieveUser(User user) throws Exception {
        //要执行的sql语句
        String sql = "SELECT * FROM user WHERE username=? AND password=?";

        //定义要使用的资源对象
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        //资源对象必须要关闭,无论程序是否执行,所以要把关闭方法放入finally中
        try {
            //使用JdbcUtils获得对数据库的连接
            connection = JdbcUtils.getConnection();
            //对SQL语句进行预编译
            preparedStatement = connection.prepareStatement(sql);

            //将user中的信息插入语句中
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());

            //获取sql声明的执行结果
            resultSet = preparedStatement.executeQuery();
            //判断声明返回结果中是否有数据,判断游标是否可以继续
            if (resultSet.next()) {
                //如果有数据则把返回的数据封装入新的User对象中
                User u = new User();
                u.setUsername(resultSet.getString("username"));
                u.setPassword(resultSet.getString("password"));
                return u;
            }
        } finally {
            try {
                //资源对象必须要关闭,无论程序是否执行,所以要把关闭方法放入finally中
                JdbcUtils.close(connection, preparedStatement, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}

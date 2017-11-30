package dao;

import domain.User;

/**
 * <p>
 * 提供对数据库的增删改查方法
 * </p>
 *
 * @author sqm
 * @version 1.0
 */
public interface UserDao {
    /**
     *<p>
     *检索用户信息是否在数据库中
     *</p>
     *@param    user    要检索的user对象
     *@return   若存在则返回此用户,若不存在则返回null
     */
    public User retrieveUser(User user) throws Exception;
}

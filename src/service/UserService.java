package service;

import dao.UserDaoImpl;
import domain.User;
import exception.LoginException;

/**
 * <p>
 * 提供用户登录,注册等方法
 * </p>
 *
 * @author sqm
 * @version 1.0
 */
public class UserService {
    /**
     *<p>
     *登录功能
     *</p>
     *@param    user    要登陆的user对象
     *@return   若返回存在的user对象,则登陆成功,若返回null,则登陆失败
     */
    public User login(User user) throws LoginException {
        User existUser = null;
        try {
            existUser = new UserDaoImpl().retrieveUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new LoginException("登陆失败");
        }
        return existUser;
    }
}

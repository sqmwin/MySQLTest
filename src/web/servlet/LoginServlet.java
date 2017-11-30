package web.servlet;

import domain.User;
import exception.LoginException;
import service.UserService;

import java.io.IOException;

/**
 * <p>
 * 登陆验证
 * </p>
 *
 * @author sqm
 * @version 1.0
 */
@javax.servlet.annotation.WebServlet(name = "LoginServlet", urlPatterns = "/loginservlet")
public class LoginServlet extends javax.servlet.http.HttpServlet {

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        /*
            验证登陆用户名和密码是否正确
                正确则重定向至登陆成功页面
                错误则转发回登陆页面
         */
        //取得输入的验证码
        String code = request.getParameter("code");
        //取得保存在session中的实际验证码字符串
        String trueCode = (String) request.getSession().getAttribute("verificationCode");
        //对比验证码是否正确(将输入的验证码转换成小写后对比)
        if (code.toLowerCase().equals(trueCode)) {
            //验证码正确则比较user的用户名和密码
            //封装属性至TO对象
            User user = new User();
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            user.setUsername(username);
            user.setPassword(password);

            //调用service中的登陆方法
            UserService userService = new UserService();
            try {
                User existUser = userService.login(user);
                //判断existUser是否为null
                if (existUser == null) {
                    //登陆失败,用户名和密码错误
                    request.setAttribute("login.message", "用户名或密码错误");
                    //转发回原登陆页面,地址是服务器内地址
                    request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
                } else {
                    //登陆成功,带着existUser对象重定向至登陆成功页面
                    request.getSession().setAttribute("user", existUser);
                    response.sendRedirect(request.getContextPath() + "/jsp/success.jsp");
                }
            } catch (LoginException e) {
                //登陆产生异常
                request.setAttribute("login.message", e.getMessage());
                //带着异常转发回原登陆页面
                request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
                e.printStackTrace();
            }

        } else {
            //验证码输入错误
            //如果输入的验证码错误或者为null,则转发回原页面并提示"验证码输入错误"
            //为什么用转发不用重定向?因为转发是一次请求一次响应可以携带信息回原页面
            request.setAttribute("login.message", "验证码输入错误");
            //转发的路径是服务器内的路径
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }



    }

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //doGet方法和doPost方法相同时使用
        this.doGet(request, response);


    }


}

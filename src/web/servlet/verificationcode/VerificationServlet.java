package web.servlet.verificationcode;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * <p>
 * 生成验证码,在登陆界面生成验证码图像,在session中生成相应的验证码文字
 * </p>
 *
 * @author sqm
 * @version 1.0
 */
@WebServlet(name = "VerificationServlet", urlPatterns = "/verificationservlet")
public class VerificationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //设置此Servlet为图片
        response.setContentType("image/jpeg");                                           
        //先确保此验证码没有缓存,则验证码可刷新
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        //获得验证码生成器对象(自建的)
        VerificationCode code = VerificationCode.getCodeInstance();
        //生成验证码
        String codeText = code.generateCodeText(4);

        //获取session对象
        HttpSession session = request.getSession();

        //删除存储在session中的上次的验证码
        session.removeAttribute("verificationCode");
        //在session中存入这次验证码,转换成小写
        session.setAttribute("verificationCode", codeText.toLowerCase());

        //生成验证码图片
        BufferedImage codeImage = code.outputImage(150, 60, codeText);
        //把验证码输出到响应
        OutputStream output = response.getOutputStream();
        ImageIO.write(codeImage, "jpg", output);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //doGet方法和doPost方法相同时使用
        this.doGet(request, response);


    }


}

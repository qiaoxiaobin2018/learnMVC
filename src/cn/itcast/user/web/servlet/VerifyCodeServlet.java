package cn.itcast.user.web.servlet;

import cn.itcast.vcode.utils.VerifyCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet(name = "VerifyCodeServlet",urlPatterns = "/verifycodeServlet")
public class VerifyCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//请求编码（POST）
        response.setContentType("text/html;charset=utf-8");//响应编码

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
         * 获取图片验证码
         * */
        VerifyCode verifyCode = new VerifyCode();
        BufferedImage image = verifyCode.getImage();
        //向Session中存储验证码
        request.getSession().setAttribute("verifycode",verifyCode.getText());
        //返回验证码图片
        verifyCode.output(image,response.getOutputStream());


    }
}

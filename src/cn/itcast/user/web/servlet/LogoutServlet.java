package cn.itcast.user.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogoutServlet",urlPatterns = "/logoutServlet")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//请求编码（POST）
        response.setContentType("text/html;charset=utf-8");//响应编码

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");//响应编码
            request.getSession().removeAttribute("sessionUser");
            /*
            * 重定向到登录页面
            * */
            response.sendRedirect(request.getContextPath()+"/user/login.jsp");
    }
}

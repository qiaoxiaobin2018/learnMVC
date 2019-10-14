package cn.itcast.user.web.servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.user.domain.User;
import cn.itcast.user.service.UserException;
import cn.itcast.user.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet",urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//请求编码（POST）
        response.setContentType("text/html;charset=utf-8");//响应编码

        //依赖UserService
        UserService userService = new UserService();
        /**
         * 1.封装表单数据到User form 中
         * 2.调用Service的login方法 ，得到返回的User对象
         *  > 如果抛出异常：获取异常信息，保存到reqest域中，再保存form，转发到login.jsp中
         *  > 弱国没有异常：保存返回值到session中，重定向到welcome.jsp中
         * */
        User form = CommonUtils.toBean(request.getParameterMap(), User.class);
        try {
            User user = userService.login(form);
            /*
            * 登录成功
            * 1.将user保存到session中
            * 2.重定向至首页 welcome.jsp
            * */
            request.getSession().setAttribute("sessionUser",user);
            response.sendRedirect(request.getContextPath()+"/user/welcome.jsp");
        } catch (UserException e) {
            /*
            * 登录失败
            * 1. 返回错误信息！
            * 2. 保存登录信息，用于回显
            * 3. 继续转发至login.jsp中
            * */
            request.setAttribute("msg",e.getMessage());
            request.setAttribute("user",form);
            request.getRequestDispatcher("/user/login.jsp").forward(request,response);

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");//响应编码

    }
}

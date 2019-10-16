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
import java.util.HashMap;

@WebServlet(name = "RegistServlet",urlPatterns = "/registerServlet")
public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//请求编码（POST）
        response.setContentType("text/html;charset=utf-8");//响应编码

        //依赖UserService
        UserService userService = new UserService();
        /*
        * 1.封装表单数据（封装到User对象中）
        * */
        User form = CommonUtils.toBean(request.getParameterMap(),User.class);
        System.out.println("****用户信息****");
        System.out.println(form);
        /**
         * 添加新任务（表单校验）；
         * 1.创建一个Map，用来装载所有的表单错误信息
         *      在校验过程中，如果失败，向Map添加错误信息，其中 key为表单字段名称
         * 2.校验之后，查看Map长度是否大于0，如果大于0，说明有错误信息，就是有错误！
         *  > 保存map到request中，保存form到request中，转发到regist.jsp中
         * 3.如果map为空，说明没有错误信息，向下执行！
         * */
        HashMap<String, String> map = new HashMap<>();
        //校验用户名
        String username = form.getUsername();
        if(username == null || username.trim().isEmpty()){//去除空格
               map.put("username","用户名不能为空！");
        }else if(username.length() < 3 || username.length()>15){
            map.put("username","用户名长度必须在3~15之间！");
        }
        //校验密码
        String password = form.getPassword();
        if(password == null || password.trim().isEmpty()){//去除空格
            map.put("password","密码不能为空！");
        }else if(password.length() < 3 || password.length()>15){
            map.put("password","密码长度必须在3~15之间！");
        }
        //校验年龄
        String age = form.getAge();
        int ageNum = Integer.parseInt(age);
        if(age == null || age.trim().isEmpty()){//去除空格
            map.put("age","年龄不能为空！");
        }else if(ageNum>200 || ageNum<0){
            map.put("age","年龄不真实！");
        }
        //校验性别
        String gender = form.getGender();
        if(gender == null || gender.trim().isEmpty()){//去除空格
            map.put("gender","性别不能为空！");
        }
        //校验验证码
        String verifycode = form.getVerifycode();
        String  Trueverifycode = (String)request.getSession().getAttribute("verifycode");
        if(verifycode == null || verifycode.trim().isEmpty()){//去除空格
            map.put("verifycode","验证码不能为空！");
        }else if(verifycode.length() != 4){
            map.put("verifycode","验证码长度必须为4！");
        }else if (!verifycode.equalsIgnoreCase(Trueverifycode)){
            map.put("verifycode","验证码错误！");

        }

        if(map != null && map.size()>0){
            /*
            * 有错误
            * 1.保存map到request域中；
            * 2.保存form到request中，为了回显；
            * 3.转发到regist.jsp中
            * */
            request.setAttribute("errors",map);
            request.setAttribute("user",form);
            request.getRequestDispatcher("/user/regist.jsp").forward(request,response);
            //及时返回
            return;
        }
        /*
        * 2.调用UserService的regist方法，传递form过去
        * 3.得到异常：获取异常信息，保存到request域中，转发到regist.jsp中显示
        * 4.没有异常：显示注册成功
        * */
        try {
            userService.Regist(form);
            //（****JSP中必须加上项目名****）
            response.getWriter().print("<p>注册成功！</p><a href='"+ request.getContextPath()+"/user/login.jsp'>去登录</a>");
        } catch (UserException e) {
            //获取异常信息，保存到request域中
            request.setAttribute("msg",e.getMessage());
            //在表单中保存用户的输入信息
            request.setAttribute("user",form);
            //转发到regist.jsp中（****java中不用添加项目路径，只用给出相对地址****）
            request.getRequestDispatcher("/user/regist.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");//响应编码

    }
}

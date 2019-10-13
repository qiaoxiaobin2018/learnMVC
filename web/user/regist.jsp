<%--
  Created by IntelliJ IDEA.
  User: JOE
  Date: 2019/10/13
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String contextPath = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath + "/";
%>
<html>
<head>
    <title>Regist</title>
    <base href="<%=basePath%>">
    <script type="text/javascript">
        function _change() {
            /*
            * 1.得到image元素
            * 2.修改其src为/verifycodeservlet
            * */
            var imageele = document.getElementById("image1");
            imageele.src = "<c:url value="/verifycodeServlet?a="/>"+ new Date().getTime();
        }
    </script>
</head>
<body>
<h3>Regist</h3>
<form action="<c:url value="/registerServlet"/>" method="post">
    <%--requestScope.user.username--%>
    账 号：<input type="text" name="username" value="${param.username}"/><font color="#ff7f50">* ${errors.username}</font> <br/>
    密 码：<input type="password" name="password" value="${param.password}"><font color="#ff7f50">* ${errors.password}</font><br/>
    验 证：<input type="text" name="verifycode" value="" size="3"/>
        <img id="image1" src="<c:url value="/verifycodeServlet"/>" border="2"/>
        <a href="javascript:_change()">换一张</a><font color="#ff7f50">* ${errors.verifycode}</font><br/>
    <input type="submit" value="注册"/>
</form>
</body>
</html>

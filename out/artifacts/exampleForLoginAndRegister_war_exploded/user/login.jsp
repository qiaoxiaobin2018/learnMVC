<%--
  Created by IntelliJ IDEA.
  User: JOE
  Date: 2019/10/13
  Time: 10:46
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
    <title>Login</title>
    <base href="<%=basePath%>">
</head>
<body>
<h3>Login</h3>
<font color="#ff8c00">${msg}</font>
<form action="<c:url value="/loginServlet"/>" method="post">
    <%--requestScope.user.username--%>
    账 号：<input type="text" name="username" value="${param.username}"/><br/>
    密 码：<input type="password" name="password" value="${param.password}"><br/>
    <input type="submit" value="登录"/>
</form>
</body>
</html>

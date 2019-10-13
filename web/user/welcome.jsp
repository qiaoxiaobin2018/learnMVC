<%--
  Created by IntelliJ IDEA.
  User: JOE
  Date: 2019/10/13
  Time: 10:49
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
    <title>Welcome</title>
    <base href="<%=basePath%>">
</head>
<body>
<h3>Welcome</h3>
</body>
</html>

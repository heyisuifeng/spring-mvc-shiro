<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2017/3/16
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="/login" method="post">
    <input type="text" name="username" id="inp-username"/>
    <input type="password" name="password" id="inp-password"/>
    <input type="submit" value="login"/>
    <p><c:out value="${message}"/></p>
</form>
</body>
</html>

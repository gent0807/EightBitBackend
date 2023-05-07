<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-05-07
  Time: 오전 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" import="java.util.*"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h4><c:out value="${exception.getMessage()}"></c:out></h4>

<ul>
    <c:forEach items="${exception.getStackTrace()}" var="stack">
    <li><c:out value="${stack}"></c:out></li>
    </c:forEach>
</body>
</html>

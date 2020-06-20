<%--
  Created by IntelliJ IDEA.
  User: valerie_brave
  Date: 03.06.2020
  Time: 1:34
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>

<html>
<head>
    <title>Title</title>
</head>

<body>
<jsp:include page="templates/header.jsp"/>
<%
    if(request.getParameter("message") != null) response.getWriter().println("<b>"+request.getParameter("message")+"</b>");
%>
<div style="height: 80%; " >
    <form action="/war/registerservlet" method="post" style="margin: auto;">

        <p>Username:</p>
        <input type="text" name="username"/>
        <p>Password:</p>

        <input type="text" name="password"/>
        <input type="submit" value="Sign in">
    </form>

</div>

</body>
<jsp:include page="templates/footer.jsp"/>
</html>

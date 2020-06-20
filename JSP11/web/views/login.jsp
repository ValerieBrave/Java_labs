<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "cus" uri = "/tdl/customlib.tld"%>
<html>
  <head>
    <title>Log in page</title>
  </head>
  <body>
  <jsp:include page="templates/header.jsp"/>
  <%
    if(request.getParameter("message") != null) response.getWriter().println("<b>"+request.getParameter("message")+"</b>");
  %>
  <div style="height: 80%; " >
<%--    <cus:svvLabledTextField label="hello there"/>--%>
    <jsp:useBean id="now" class="java.util.Date" />
    <fmt:setLocale value="en-EN"/>
    Вывод даты в формате English<br/>
    Сегодня: <fmt:formatDate value="${now}" /><br/>
    <fmt:setLocale value="ru-RU"/>
    Вывод даты в формате Russian<br/>
    Сегодня: <fmt:formatDate value="${now}" /><br/>

    <form action="/war/userservlet" method="post" style="margin: auto;">
<%--      <p>Username:</p>--%>
<%--      <input type="text" name="username"/>--%>
        <cus:svvLabledTextField label="Username:" parname="username"/>
<%--      <p>Password:</p>--%>
<%--      <input type="text" name="password"/>--%>
        <cus:svvLabledTextField label="Password:" parname="password"/>
        <cus:svvSubmit/>
<%--      <input type="submit" value="Log in">--%>
    </form>
<%--    <form action="/war/userservlet" method="get">--%>
<%--      <input type="submit" value="Sign in">--%>
<%--    </form>--%>

  </div>

  </body>
  <jsp:include page="templates/footer.jsp"/>
</html>
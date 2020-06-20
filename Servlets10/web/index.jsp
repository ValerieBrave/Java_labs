<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
  <form action="infoServletTest" method="get">
    <input type="submit" value="Задание 2">
  </form>
<hr/>
  <div style="display: flex; flex-direction: row;">
    <form action="userservice" method="post" style="width: 25%;">
      <div>Username</div>
      <input type="text" name="username"/>
      <div>Password</div>
      <input type="text" name="password"/>
      <input type="submit" value="Войти">
    </form>
  </div>
  <form action="callservlet" method="get">
    <input type="submit" value="Call servlet with GET" name="call_first_servlet"/>
  </form>
  <form action="callservlet" method="post">
    <input type="submit" value="Call servlet with POST -> forward to jsp"/>
  </form>
  </body>
</html>
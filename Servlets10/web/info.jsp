<%--
  Created by IntelliJ IDEA.
  User: valerie_brave
  Date: 19.05.2020
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Задание 2а+ 2б</title>
</head>
<body>

<div>Текущее время : ${time}</div>
<div>Версия протокола : ${version}</div>
<div>IP : ${IP}</div>
<div>Имя клиента : ${name}</div>
<div>Метод : ${meth}</div>
<form action="/showinfo" method="get">
    <input value="Заголовки запроса" type="submit"/>
</form>
</body>
</html>

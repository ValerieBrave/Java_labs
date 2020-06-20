<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "cus" uri = "/tld/customlib.tld" %>
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

    <form action="/war/userservlet" method="post" style="margin: auto;">

        <cus:svvLabledTextField label="Username:" parname="username"/>

        <cus:svvLabledTextField label="Password:" parname="password"/>
        <cus:svvSubmit/>

    </form>


</div>

</body>
<jsp:include page="templates/footer.jsp"/>
</html>
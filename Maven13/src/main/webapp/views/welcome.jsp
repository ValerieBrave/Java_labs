<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@ page session= "true" %>
<%@ page errorPage="/views/errorPage.jsp" %>
<%@ taglib prefix = "cus" uri = "/tld/customlib.tld"%>
<html>
<head>
    <title>Title</title>
</head>
<jsp:include page="templates/header.jsp"/>
<body>
<sql:setDataSource var = "winecart" driver = "org.sqlite.JDBC"
                   url = "jdbc:sqlite:D://JavaLabs/JSP11/finewine.sqlite3"/>
<sql:query dataSource = "${winecart}" var = "result">
    SELECT * from Winecart;
</sql:query>
<table border = "1" width = "100%" style="width: 75%; margin: auto; border-collapse: collapse;">
    <tr>
        <th>Wine</th>
        <th>Year</th>
        <th>Type</th>
    </tr>
    <c:forEach var = "row" items = "${result.rows}">
        <tr>
            <td><c:out value = "${row.Wine}"/></td>
            <td><c:out value = "${row.Year}"/></td>
            <td><c:out value = "${row.Type}"/></td>
        </tr>
    </c:forEach>
</table>
<div style="height: 50%;">
    <div>
        <form action="${pageContext.servletContext.contextPath}/controller?command=add_wine" method="post">
            <div style="display: flex; flex-direction: row; justify-content: center;">
                <div style="display: flex; flex-direction: column;">
                    <p>Wine</p>
                    <input type="text" name="wine"/>
                </div>
                <div style="display: flex; flex-direction: column;">
                    <p>Year</p>
                    <input type="number" name="year"/>
                </div>
                <div style="display: flex; flex-direction: column;">
                    <p>Type</p>
                    <input type="text" name="type"/>
                </div>
            </div>
            <div style="width: 50%; margin: auto;">
                <input type="submit" value="Add" style="margin: auto;">
            </div>
        </form>
    </div>
    <div style="width: 50%; margin: auto;">
        <form action="${pageContext.servletContext.contextPath}/controller?command=log_out" method="post">
            <input type="submit" value="Log out" style="margin: auto;"/>
        </form>
    </div>
    <c:set var="ses" value="<%=request.getSession().getAttribute("jsp11user")%>"/>
    <cus:svvTable rows="2">${ses}</cus:svvTable>
</div>

</body>
<jsp:include page="templates/footer.jsp"/>
</html>

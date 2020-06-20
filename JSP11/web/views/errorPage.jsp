
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<jsp:include page="templates/header.jsp"/>
<body>
<div style="height: 80%;">
    <jsp:text> Request to ${pageContext.errorData.requestURI} failed((((</jsp:text>
</div>
</body>
<jsp:include page="templates/footer.jsp"/>
</html>

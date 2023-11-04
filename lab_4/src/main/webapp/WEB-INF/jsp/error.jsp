<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<div>
    <h1>Error Page</h1>
    <p>Failed URL: ${url}
        Exception: ${exception.getMessage()}
        <c:forEach items="${exception.getStackTrace()}" var="ste">
            ${ste}
        </c:forEach>
    </p>
</div>
</body>
</html>
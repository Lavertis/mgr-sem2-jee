<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<% String previousPageURL = request.getHeader("referer"); %>
<html>
<head>
    <title>Kalkulator rat (Bean) - błąd</title>
</head>
<body>
<div>
    <h2>Wprowadzono błędne dane!</h2>
    <p>Pojawił się następujący błąd: <%= exception.getMessage() %>
    </p>
    <a href="<%= previousPageURL  %>">Wróć do poprzedniej strony</a>
</div>
</body>
</html>

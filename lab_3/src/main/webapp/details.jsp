<%@ page import="com.pollub.lab_3.Beans.CountryBean" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    List<CountryBean> countries = (List<CountryBean>) session.getAttribute("countries");
    int index = Integer.parseInt(request.getParameter("index"));
    CountryBean country = countries.get(index);
%>
<html>
<head>
    <title><%= country.getName() %>
    </title>
</head>
<body>
<div>
    <h1>
        <%= country.getName() %>
    </h1>
    <p>
        Code: <%= country.getCode() %>
    </p>
    <p>
        Population: <%= country.getPopulation() %>
    </p>
    <p>
        Surface area: <%= country.getSurfaceArea() %>
    </p>
    <a href="countries.jsp">Back to List</a>
</div>
</body>
</html>

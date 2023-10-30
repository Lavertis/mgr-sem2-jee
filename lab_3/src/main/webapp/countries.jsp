<%@ page import="com.pollub.lab_3.Beans.CountryBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Kraje Europy</title>
    <style>
        table {
            border-collapse: collapse;
        }

        table, th, td {
            border: 1px solid black;
            padding: 5px;
        }
    </style>
</head>
<body>
<div>
    <h1>Kraje Europy</h1>
    <table style="border: 1px solid black">
        <tr>
            <th>Country</th>
            <th>Code</th>
            <th>Population</th>
        </tr>
        <%
            List<CountryBean> countries = (List<CountryBean>) session.getAttribute("countries");
            if (countries == null) countries = new ArrayList<>();
            for (CountryBean country : countries) {
        %>
        <tr>
            <td><%= country.getName() %>
            </td>
            <td><%= country.getCode() %>
            </td>
            <td><%= country.getPopulation() %>
            </td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>

<%@ page import="com.pollub.lab_3.Beans.CountryBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Kraje Europy</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }

        td {
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
            <th>Details</th>
        </tr>
        <%
            List<CountryBean> countries = (List<CountryBean>) session.getAttribute("countries");
            if (countries == null) countries = new ArrayList<>();
            for (int i = 0; i < countries.size(); i++) {
        %>
        <tr>
            <td>
                <%= countries.get(i).getName() %>
            </td>
            <td>

                <%= countries.get(i).getCode() %>
            </td>
            <td>

                <%= countries.get(i).getPopulation() %>
            </td>
            <td>
                <a href="details.jsp?index=<%= i %>">Details</a>
            </td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>

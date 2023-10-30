package com.pollub.lab_3;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "listServlet", value = "/list-servlet")
public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            processRequest(resp);
        } catch (SQLException | ClassNotFoundException | IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    protected void processRequest(HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException {
        //pobranie sterownika do MySQL:
        Class.forName("com.mysql.cj.jdbc.Driver");

        //utworzenie obiektu połączenia do bazy danych MySQL:
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?serverTimezone=UTC", "root", "");

        //utworzenie obiektu do wykonywania zapytań do bd:
        Statement st = conn.createStatement();
        String query = "SELECT * FROM Country WHERE Continent = 'Europe'";

        //wykonanie zapytania SQL:
        ResultSet rs = st.executeQuery(query);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Countries</h1>");
        out.println("<p>Query: " + query + "</p>");
        out.println("<table border=\"1\">");
        out.println("<tr><th>Country</th><th>Code</th><th>Population</th></tr>");

        while (rs.next()) {
            var country = rs.getString("Name");
            var code = rs.getString("Code");
            var population = rs.getInt("Population");
            out.println("<tr><td>" + country + "</td><td>" + code + "</td><td>" + population + "</td></tr>");
        }

        out.println("</table>");
        out.println("</body></html>");
    }
}

package com.pollub.lab_3.Servlets;

import com.pollub.lab_3.Beans.CountryBean;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "listServlet2", value = "/list-servlet2")
public class ListServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            processRequest(req, resp);
        } catch (SQLException | ClassNotFoundException | IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException {
        // pobranie sterownika do MySQL:
        Class.forName("com.mysql.cj.jdbc.Driver");

        // utworzenie obiektu połączenia do bazy danych MySQL:
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?serverTimezone=UTC", "root", "");

        // utworzenie obiektu do wykonywania zapytań do bd:
        Statement st = conn.createStatement();
        String query = "SELECT * FROM Country WHERE Continent = 'Europe'";

        // wykonanie zapytania SQL:
        ResultSet rs = st.executeQuery(query);
        List<CountryBean> countries = new ArrayList<>();
        while (rs.next()) {
            CountryBean country = new CountryBean();
            country.setCode(rs.getString("Code"));
            country.setName(rs.getString("Name"));
            country.setPopulation(rs.getInt("Population"));
            country.setSurfaceArea(rs.getFloat("SurfaceArea"));
            countries.add(country);
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("countries", countries);
        response.sendRedirect("countries.jsp");

        rs.close();
        st.close();
        conn.close();
    }
}

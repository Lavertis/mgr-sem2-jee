package com.pollub.lab_1;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    private Date date1;
    private DateFormat dateFormat;

    public void init() {
        message = "Hello World!";
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date1 = new Date();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<h2>Dane serwera</h2>");
        out.println("<p>request.getServerName(): " + request.getServerName() + "</p>");
        out.println("<p>request.getServerPort(): " + request.getServerPort() + "</p>");
        out.println("<p>request.getRemoteHost(): " + request.getRemoteHost() + "</p>");
        out.println("<p>request.getRemoteAddr(): " + request.getRemoteAddr() + "</p>");
        out.println("<h2>Szczegóły żądania</h2>");
        out.println("<p>request.getMethod(): " + request.getMethod() + " </p>");
        out.println("<p>request.getQueryString(): " + request.getQueryString() + "</p>");
        out.println("<p>data zainicjalizowana w metodzie init(): " + date1 + "</p>");
        out.println("<p>data z processRequest: " + new Date() + "</p>");
        out.println("<p>data z processRequest sformatowana: " + dateFormat.format(new Date()) + "</p>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}
package com.pollub.lab_1;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Optional;

@SuppressWarnings("unchecked")
@WebServlet(name = "calcServlet", value = "/calc-servlet")
public class CalcServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        if (getParameterValue(req, "clearHistory").isPresent()) {
            session.setAttribute("history", null);
            req.removeAttribute("clearHistory");
        }
        resp.sendRedirect("calc.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(true);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + getWelcomeMessage(req, resp) + "</h1>");
        printLinks(out);

        var paramA = getParameterValue(req, "a");
        var paramB = getParameterValue(req, "b");
        var paramOperation = getParameterValue(req, "operation");
        String resultString;
        var someParamIsMissing = paramA.isEmpty() || paramB.isEmpty() || paramOperation.isEmpty();
        if (someParamIsMissing) {
            resultString = "Błędne dane";
        } else {
            try {
                var a = Double.parseDouble(paramA.get());
                var b = Double.parseDouble(paramB.get());
                var operation = paramOperation.get();
                var result = calculate(a, b, operation);
                resultString = a + " " + operation + " " + b + " = " + result;
            } catch (NumberFormatException e) {
                resultString = "Błędne dane";
            }
        }

        out.println("<h1>Wynik obliczeń</h1>");
        out.println("<p>" + resultString + "</p>");

        printCalculatorHistory(req, out, session);
        saveCalculationToHistory(req, session, resultString);

        out.println("</body></html>");
    }

    private String getWelcomeMessage(HttpServletRequest req, HttpServletResponse resp) {
        String nazwaCookie = "oldUser";
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals(nazwaCookie))
                    return "Witaj ponownie";
            }
        }
        Cookie cookie = new Cookie(nazwaCookie, "1");
        cookie.setMaxAge(60 * 60 * 24 * 365);
        resp.addCookie(cookie);
        return "Witaj po raz pierwszy";
    }

    private void printLinks(PrintWriter out) {
        out.println("<a href=\"calc-servlet\">Powrót do formularza</a>&nbsp;&nbsp;");
        out.println("<a href=\"calc-servlet?clearHistory=true\">Wyczyść historię sesji</a>");
    }

    private void saveCalculationToHistory(HttpServletRequest req, HttpSession session, String result) {
        var history = (ArrayList<String>) req.getSession().getAttribute("history");
        if (history == null)
            history = new ArrayList<>();
        history.add(result);
        session.setAttribute("history", history);
    }

    private void printCalculatorHistory(HttpServletRequest req, PrintWriter out, HttpSession session) {
        var history = (ArrayList<String>) req.getSession().getAttribute("history");
        if (history == null)
            history = new ArrayList<>();
        out.println("<h1>Historia obliczeń z sesji</h1>");
        out.println("<ul>");
        for (var result : history) {
            out.println("<li>" + result + "</li>");
        }
        out.println("</ul>");
    }

    private Optional<String> getParameterValue(HttpServletRequest req, String name) {
        String value;
        try {
            value = req.getParameter(name);
        } catch (NullPointerException ignored) {
            value = null;
        }
        if (value == null || value.isBlank()) {
            return Optional.empty();
        }
        return Optional.of(value);
    }

    private String calculate(double a, double b, String operation) {
        if (operation.equals("/") && b == 0) {
            return "Nie można dzielić przez 0";
        }
        return switch (operation) {
            case "+" -> String.valueOf(a + b);
            case "-" -> String.valueOf(a - b);
            case "*" -> String.valueOf(a * b);
            case "/" -> String.valueOf(a / b);
            default -> throw new IllegalStateException("Unexpected operation value: " + operation);
        };
    }
}

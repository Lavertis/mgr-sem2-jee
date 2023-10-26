<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Kalkulator rat</title>
</head>
<body>
<div>
    <%
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String date = dateFormat.format(now);
    %>
    <%= date %>
    <%
        var amount = request.getParameter("amount") == null ? "" : request.getParameter("amount");
        var interestRate = request.getParameter("interestRate") == null ? "" : request.getParameter("interestRate");
        var period = request.getParameter("period") == null ? "" : request.getParameter("period");
    %>
    <div>
        <h1>Kalkulator rat</h1>
        <form method="post">
            <label>
                Kwota pożyczki:
                <input type="number" name="amount" min="0" step="0.01" value="<%= amount %>">
            </label>
            <br/><br/>
            <label>
                Procent roczny:
                <input type="number" name="interestRate" min="0" step="0.01" value="<%= interestRate %>">
            </label>
            <br/><br/>
            <label>
                Liczba rat
                <input type="number" name="period" min="1" value="<%= period %>">
            </label>
            <br/><br/>
            <input type="submit" name="calculate" value="Oblicz">
        </form>
        <%
            String res = "";
            if (request.getParameter("calculate") != null) {
                try {
                    var k = Double.parseDouble(amount);
                    var pr = Double.parseDouble(interestRate);
                    var n = Integer.parseInt(period);

                    var monthlyInterestPayment = k * (pr / 100) / 12;
                    var monthlyInterestFactor = Math.pow(1 + (pr / 100) / 12, n);
                    var m = monthlyInterestPayment * monthlyInterestFactor / (monthlyInterestFactor - 1);

                    DecimalFormat df = new DecimalFormat("#.00");
                    res = "Rata miesięczna: " + df.format(m) + " zł";
                } catch (Exception ex) {
                    res = "Błędne dane";
                }
            }
        %>
        <%= res %>
    </div>
</div>
</body>
</html>

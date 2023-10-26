<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page errorPage="errorPage.jsp" %>
<jsp:useBean id="loan" class="com.pollub.lab_2.LoanBean" scope="session"/>
<jsp:setProperty name="loan" property="*"/>
<html>
<head>
    <title>Kalkulator rat (Bean)</title>
</head>
<body>
<div>
    <%
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String date = dateFormat.format(now);
    %>
    <%= date %>
    <div>
        <h1>Kalkulator rat (Bean)</h1>
        <form method="post">
            <label>
                Kwota pożyczki:
                <input type="number" name="amount" min="0" step="0.01" value="<%= loan.getAmount() %>">
            </label>
            <br/><br/>
            <label>
                Procent roczny:
                <input type="number" name="interestRate" min="0" step="0.01" value="<%= loan.getInterestRate() %>">
            </label>
            <br/><br/>
            <label>
                Liczba rat
                <input type="text" name="periodInMonths" min="1" value="<%= loan.getPeriodInMonths() %>">
            </label>
            <br/><br/>
            <input type="submit" name="calculate" value="Oblicz">
        </form>
        <%
            String res = "";
            if (request.getParameter("calculate") != null) {
                DecimalFormat df = new DecimalFormat("#.00");
                res = "Rata miesięczna: " + df.format(loan.getMonthlyPayment()) + "zł";
            }
        %>
        <%= res %>
    </div>
</div>
</body>
</html>

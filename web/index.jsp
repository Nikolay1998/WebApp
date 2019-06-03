<%@ page import="com.kraynov.calculator.validation.CalculatorValidator" %>
<%@ page import="com.kraynov.calculator.Util" %>
<%@ page import="com.kraynov.calculator.calc.PowerCalculationTask" %>
<%@ page import="static com.kraynov.calculator.servlet.ResultHtmlServlet.TASK_SESSION_ATTRIBUTE_NAME" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Калькулятор</title>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <!--===============================================================================================-->
</head>
<body>
<h2>Возведение в степень в JSP</h2>
<%!
    private final static String actionParameterName = "action";
    private final static String baseParameterName = "base";
    private final static String powerParameterName = "power";
    private final static String redirectUrl = "result";

    public void validateAndRedirect() {

    }
%>

<%
    CalculatorValidator calcValidator = new CalculatorValidator();

    String action = request.getParameter(actionParameterName);
    String base   = Util.standartize(request.getParameter(baseParameterName));
    String power  = Util.standartize(request.getParameter(powerParameterName));

    if (!Util.isEmpty(action)) {
        calcValidator.runValidation(base, power);
        if (calcValidator.isValid()) {
            PowerCalculationTask task = new PowerCalculationTask(calcValidator.getBase(), calcValidator.getPower());
            request.getSession().setAttribute(TASK_SESSION_ATTRIBUTE_NAME, task);
            response.sendRedirect(redirectUrl);
        }
    }
%>

<form action="index.jsp">
    <table>
        <tr>
            <td>Основание</td>
            <td>
                <input title="Введите число возводимое в степень" name="base" type="text" value="<%=base%>"/>
            </td>
            <td class="validationMessage">
                <%=calcValidator.getBaseValidationMessage()%>
            </td>
        </tr>
        <tr>
            <td>Показатель</td>
            <td>
                <input title="Введите степень, в которую нужно возвести число" name="power" type="text" value="<%=power%>"/>
            </td>
            <td class="validationMessage">
                <%=calcValidator.getPowerValidationMessage()%>
            </td>
        </tr>
    </table>
    <input type="hidden" name="action" value="calculate"/>
    <input type="submit" value="Вычислить"/>
</form>
</body>
</html>

<%@ page import="static com.kraynov.calculator.servlet.ResultHtmlServlet.TASK_SESSION_ATTRIBUTE_NAME" %>
<%@ page import="com.kraynov.calculator.calc.PowerCalculationTask" %>
<%@ page import="java.io.IOException" %>
<%@ page import="com.kraynov.calculator.Util" %>
<%@ page import="com.kraynov.calculator.xml.TaskSerializer" %>
<%@ page import="java.io.File" %>
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
<%!
    private static final int REDIRECTION_DELAY_IN_MILISECONDS = 5000;
    private static final String REDIRECT_URL = "index.jsp";
    private static final String TASK_OBJECT_IS_NULL_ERROR_MESSAGE = "Объект задания на вычисление не найден в сессии.";
    private static final String TASK_OBJECT_IS_NOT_SUPPORTED_ERROR_MESSAGE = "Данное вычисление не поддерживается.";
    private static final String CALCULATION_ERROR_MESSAGE = "Ошибка во время вычислений: ";
    private static final String REDIRECTION_ERROR_MESSAGE = " Вы будете перенаправлены на начальную страницу в течение 5 секунд. " +
     "Если этого не произошло перейдите по <a href=\"index.jsp\">ссылке</a>";
%>

<%
    Object taskObject = request.getSession().getAttribute(TASK_SESSION_ATTRIBUTE_NAME);
    String errorMessage = "";
    if (taskObject == null) {
        errorMessage += TASK_OBJECT_IS_NULL_ERROR_MESSAGE;
    } else {
        if (!(taskObject instanceof PowerCalculationTask)) {
            errorMessage += TASK_OBJECT_IS_NOT_SUPPORTED_ERROR_MESSAGE;
        } else {
            PowerCalculationTask task = (PowerCalculationTask) taskObject;
            if (!Util.isEmpty(task.getErrorMessage())) {
                errorMessage = errorMessage + CALCULATION_ERROR_MESSAGE + task.getErrorMessage();
            }
        }
    }

    if ("".equals(errorMessage)) {
        PowerCalculationTask task = (PowerCalculationTask) taskObject;
%>
    <h2>Результат вычисления</h2>
    <form>
        <table>
            <tr>
                <td>Основание</td>
                <td >
                    <%=task.getBase()%>
                </td>
            </tr>
            <tr>
                <td>Показатель</td>
                <td>
                   <%=task.getPower()%>
                </td>
            </tr>
            <tr>
                <td>Результат</td>
                <td>
                    <%=task.getResult()%>
                </td>
            </tr>
            <tr>
                <td>
                    <a href="index.jsp">Вернуться обратно</a></td>
                <td>
                    <a href="resultXml">Результат</a>
                </td>
            </tr>
        </table>
    </form>
<%
} else {
    out.println(errorMessage+REDIRECTION_ERROR_MESSAGE);
%>
<script>
    setTimeout(function() {
        document.location = "<%=REDIRECT_URL%>";
    }, <%=REDIRECTION_DELAY_IN_MILISECONDS%>); // <-- this is the delay in milliseconds
</script>
<%
    }
%>
</body>
</html>

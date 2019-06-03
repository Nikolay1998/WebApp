package com.kraynov.calculator.servlet;

import com.kraynov.calculator.calc.PowerCalculationTask;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "resultServlet", urlPatterns = {"/result"})
public class ResultHtmlServlet extends HttpServlet {
    public static final String TASK_SESSION_ATTRIBUTE_NAME = "Powerer Task";
    public static final String VIEW_NAME = "result.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Object attributeValue = request.getSession().getAttribute(TASK_SESSION_ATTRIBUTE_NAME);
        if (attributeValue instanceof PowerCalculationTask) {
            ((PowerCalculationTask) attributeValue).run();
        }
        //request.getSession().setAttribute(TASK_SESSION_ATTRIBUTE_NAME, attributeValue);
        response.sendRedirect(VIEW_NAME);
    }
}

package com.kraynov.calculator.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.kraynov.calculator.servlet.ResultHtmlServlet.TASK_SESSION_ATTRIBUTE_NAME;

@WebServlet(name = "clearSessionServlet", urlPatterns = {"/clear"})
public class SessionResetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().removeAttribute(TASK_SESSION_ATTRIBUTE_NAME);
        resp.sendRedirect("index.jsp");
    }
}

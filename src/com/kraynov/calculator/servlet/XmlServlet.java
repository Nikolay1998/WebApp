package com.kraynov.calculator.servlet;

import com.kraynov.calculator.calc.PowerCalculationTask;
import com.kraynov.calculator.xml.TaskSerializer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.kraynov.calculator.servlet.ResultHtmlServlet.TASK_SESSION_ATTRIBUTE_NAME;

@WebServlet(name = "xmlServlet", urlPatterns = {"/resultXml"})
public class XmlServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/xml");
        PowerCalculationTask task = (PowerCalculationTask) req.getSession().getAttribute(TASK_SESSION_ATTRIBUTE_NAME);
        TaskSerializer<PowerCalculationTask> serializer = new TaskSerializer<>(PowerCalculationTask.class);
        serializer.writeToStream(task,resp.getOutputStream());
    }
}

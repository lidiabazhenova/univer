package com.lidiabazhenova.webapp.service;

import com.lidiabazhenova.webapp.model.Todo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delete-service.do")
public class DeleteTodoServlet extends HttpServlet {
    private TodoService todoService = new TodoService();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        todoService.deleteTodo(new Todo(request.getParameter("service"), request.getParameter("product")));
        response.sendRedirect("/list-todos.do");
    }
}



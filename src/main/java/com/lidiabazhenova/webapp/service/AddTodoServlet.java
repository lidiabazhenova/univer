package com.lidiabazhenova.webapp.service;

import com.lidiabazhenova.webapp.model.Todo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/add-todo.do")
public class AddTodoServlet extends HttpServlet {
    private TodoService todoService = new TodoService();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/add-todo.jsp").forward(
                request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String newTodo = request.getParameter("url");
        String product = request.getParameter("product");
        todoService.addTodo(new Todo(newTodo, product));
        request.setAttribute("todos", todoService.retrieveTodos());
        response.sendRedirect("/list-todos.do");
    }
}



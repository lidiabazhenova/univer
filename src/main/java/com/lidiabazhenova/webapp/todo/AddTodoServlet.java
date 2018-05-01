package com.lidiabazhenova.webapp.todo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/add-todo.do")
public class AddTodoServlet extends HttpServlet {
    private TodoService todoService = new TodoService();

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String newTodo = request.getParameter("todo");
        todoService.addTodo(new Todo(newTodo));
        request.setAttribute("todos", todoService.retrieveTodos());
        response.sendRedirect("/todo.do");
    }
}



package com.lidiabazhenova.webapp.controllers.login;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {
    private LoginValidation userValidation = new LoginValidation();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        request.setAttribute("username", name);
        RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
        dis.forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        boolean isValidUser = userValidation.validateUser(name, password);

        if (isValidUser) {
            request.getSession().setAttribute("username", name);
            response.sendRedirect("/list-products.do");
        } else {
            request.setAttribute("errorMessage", "Invalid Credentials!!");
            RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
            dis.forward(request, response);
        }
    }
}


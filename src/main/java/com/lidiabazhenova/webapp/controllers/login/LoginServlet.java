package com.lidiabazhenova.webapp.controllers.login;

import com.lidiabazhenova.webapp.exception.DataSourceException;
import com.lidiabazhenova.webapp.dao.LoginDao;
import com.lidiabazhenova.webapp.service.LoginService;
import com.lidiabazhenova.webapp.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {

    public LoginServlet() {
    }

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
        boolean isValidUser;

        try {
            isValidUser = LoginService.getInstance().userValidation(name, password);
            if (isValidUser) {
                request.getSession().setAttribute("username", name);
                response.sendRedirect("/list-orders.do");
            } else {
                request.setAttribute("errorMessage", "Заполните форму Правильно!!");
                RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
                dis.forward(request, response);
            }
        } catch (DataSourceException e) {
            e.printStackTrace();
        }
    }
}



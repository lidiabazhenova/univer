package com.lidiabazhenova.webapp.controllers.user;

import com.lidiabazhenova.webapp.exception.DataSourceException;
import com.lidiabazhenova.webapp.model.Product;
import com.lidiabazhenova.webapp.model.User;
import com.lidiabazhenova.webapp.service.ProductService;
import com.lidiabazhenova.webapp.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/add-user.do")
public class AddUsersServlet extends HttpServlet {

    public AddUsersServlet() {
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views/add-user.jsp");
        dis.forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        final User user = new User.UserBuilder()
                .setLogin(request.getParameter("login"))
                .setFirstName(request.getParameter("firstName"))
                .setLastName(request.getParameter("lastName"))
                .setPassword(request.getParameter("password"))
                .build();

        if (user.validateNotBlank()) {
            try {
                UserService.getInstance().insertUser(user);
                request.setAttribute("users", UserService.getInstance().getUsers());
                response.sendRedirect("/list-users.do");
            } catch (DataSourceException e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("errorMessage", "Empty Credentials!!");
            RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views//add-user.jsp");
            dis.forward(request, response);
            return;
        }
    }
}


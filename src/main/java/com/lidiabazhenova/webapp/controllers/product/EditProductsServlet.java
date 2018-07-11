package com.lidiabazhenova.webapp.controllers.product;

import com.lidiabazhenova.webapp.exception.DataSourceException;
import com.lidiabazhenova.webapp.model.User;
import com.lidiabazhenova.webapp.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/edit-user.do")
public class EditProductsServlet extends HttpServlet {
    User user;

    public EditProductsServlet() {
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        try {
            user = UserService.getInstance().getUser(Long.valueOf(request.getParameter("userId")));

            request.setAttribute("user", user);
        } catch (DataSourceException e) {
            e.printStackTrace();
        }
        RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views/edit-user.jsp");
        dis.forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        final User newUser = new User.UserBuilder()
                .setUserId(user.getUserId())
                .setLogin(request.getParameter("login"))
                .setFirstName(request.getParameter("firstName"))
                .setLastName(request.getParameter("lastName"))
                .setPassword(request.getParameter("password"))
                .build();

        if (user.validateNotBlank()) {
            try {
                UserService.getInstance().updateUser(newUser);
                request.setAttribute("users", UserService.getInstance().getUsers());
                response.sendRedirect("/list-users.do");
            } catch (DataSourceException e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("errorMessage", "Empty Credentials!!");
            RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views//edit-user.jsp");
            dis.forward(request, response);
            return;
        }
    }
}



package com.lidiabazhenova.webapp.controllers.user;

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
import java.util.List;

@WebServlet(urlPatterns = "/list-users.do")
public class ListUsersServlet extends HttpServlet {


    public ListUsersServlet() throws DataSourceException {
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        List<User> users = null;
        try {
            users = UserService.getInstance().getUsers();
        } catch (DataSourceException e) {
            e.printStackTrace();
        }
        request.setAttribute("users", users);
        RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views/list-users.jsp");
        dis.forward(request, response);
    }

//    protected void doPost(HttpServletRequest request,
//                          HttpServletResponse response) throws ServletException, IOException {
//        final User user = new User.UserBuilder()
//                .setLogin(request.getParameter("login"))
//                .setFirstName(request.getParameter("firstName"))
//                .setLastName(request.getParameter("lastName"))
//                .setPassword(request.getParameter("password"))
//                .build();
//    }
}



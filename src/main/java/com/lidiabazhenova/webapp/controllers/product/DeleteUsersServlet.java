package com.lidiabazhenova.webapp.controllers.product;

import com.lidiabazhenova.webapp.exception.DataSourceException;
import com.lidiabazhenova.webapp.model.Product;
import com.lidiabazhenova.webapp.model.User;
import com.lidiabazhenova.webapp.service.ProductService;
import com.lidiabazhenova.webapp.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delete-user.do")
public class DeleteUsersServlet extends HttpServlet {

    public DeleteUsersServlet() throws DataSourceException {
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        final User user = new User.UserBuilder()
                .setUserId(Long.valueOf(request.getParameter("userId"))).build();
        try {
            UserService.getInstance().deleteUser(user.getUserId());
            response.sendRedirect("/list-users.do");
        } catch (DataSourceException e) {
            e.printStackTrace();
        }

    }
}



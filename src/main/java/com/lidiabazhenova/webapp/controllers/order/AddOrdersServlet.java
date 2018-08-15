package com.lidiabazhenova.webapp.controllers.order;

import com.lidiabazhenova.webapp.exception.DataSourceException;
import com.lidiabazhenova.webapp.model.Order;
import com.lidiabazhenova.webapp.service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/add-order.do")
public class AddOrdersServlet extends HttpServlet {

    public AddOrdersServlet() {
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views/add-order.jsp");
        dis.forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        final Order order = new Order.OrderBuilder()
                .setOrderTitle(request.getParameter("orderTitle"))
                .build();

        try {
            OrderService.getInstance().addOrder(order);
            request.setAttribute("orders", OrderService.getInstance().getOrders());
            response.sendRedirect("/list-orders.do?orderId=" + order.getOrderTitle());
        } catch (DataSourceException e) {
            e.printStackTrace();
        }
    }
}




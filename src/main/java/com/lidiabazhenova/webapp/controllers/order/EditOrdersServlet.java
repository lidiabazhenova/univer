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

@WebServlet(urlPatterns = "/edit-order.do")
public class EditOrdersServlet extends HttpServlet {
    Order order;

    public EditOrdersServlet() {
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        try {
            order = OrderService.getInstance().getOrder(Long.valueOf(request.getParameter("orderId")));
            request.setAttribute("order", order);
            RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views/edit-order.jsp");
            dis.forward(request, response);
        } catch (DataSourceException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        final Order newOrder = new Order.OrderBuilder()
                .setOrderId(order.getOrderId())
                .setOrderTitle(request.getParameter("orderTitle"))
                .build();

        try {
            OrderService.getInstance().updateOrder(newOrder);
            request.setAttribute("orders", OrderService.getInstance().getOrders());
            response.sendRedirect("/list-orders.do");
        } catch (DataSourceException e) {
            e.printStackTrace();
        }
    }
}



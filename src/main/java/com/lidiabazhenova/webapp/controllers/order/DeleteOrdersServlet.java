package com.lidiabazhenova.webapp.controllers.order;

import com.lidiabazhenova.webapp.exception.DataSourceException;
import com.lidiabazhenova.webapp.model.Order;
import com.lidiabazhenova.webapp.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delete-order.do")
public class DeleteOrdersServlet extends HttpServlet {

    public DeleteOrdersServlet() throws DataSourceException {
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        final Order order = new Order.OrderBuilder()
                .setOrderId(Long.valueOf(request.getParameter("orderId"))).build();
        try {
            OrderService.getInstance().deleteOrder(order.getOrderId());
            response.sendRedirect("/list-orders.do");
        } catch (DataSourceException e) {
            e.printStackTrace();
        }
    }
}



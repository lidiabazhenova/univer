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
import java.util.List;

@WebServlet(urlPatterns = "/list-orders.do")
public class ListOrdersServlet extends HttpServlet {


    public ListOrdersServlet() throws DataSourceException {
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders = null;
        try {
            orders = OrderService.getInstance().getOrders();
        } catch (DataSourceException e) {
            e.printStackTrace();
        }
        request.setAttribute("orders", orders);
        RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views/list-orders.jsp");
        dis.forward(request, response);
    }
}



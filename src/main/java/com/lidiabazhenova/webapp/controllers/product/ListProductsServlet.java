package com.lidiabazhenova.webapp.controllers.product;

import com.lidiabazhenova.webapp.exception.DataSourceException;
import com.lidiabazhenova.webapp.model.Order;
import com.lidiabazhenova.webapp.model.Product;
import com.lidiabazhenova.webapp.service.OrderService;
import com.lidiabazhenova.webapp.service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/list-products.do")
public class ListProductsServlet extends HttpServlet {

    public ListProductsServlet() throws DataSourceException {
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = null;
        Order order = null;

        try {
            Long orderId = Long.valueOf(request.getParameter("orderId"));
            products = ProductService.getInstance().getProductsByOrderId(orderId);
            order = OrderService.getInstance().getOrder(orderId);
        } catch (DataSourceException e) {
            e.printStackTrace();
        }
        request.setAttribute("products", products);
        request.setAttribute("order", order);
        RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views/list-products.jsp");
        dis.forward(request, response);
    }
}



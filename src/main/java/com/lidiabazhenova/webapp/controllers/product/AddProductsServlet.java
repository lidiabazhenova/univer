package com.lidiabazhenova.webapp.controllers.product;

import com.lidiabazhenova.webapp.exception.DataSourceException;
import com.lidiabazhenova.webapp.model.Order;
import com.lidiabazhenova.webapp.model.Product;
import com.lidiabazhenova.webapp.service.OrderService;
import com.lidiabazhenova.webapp.service.ProductService;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/add-product.do")
public class AddProductsServlet extends HttpServlet {

    public AddProductsServlet() {
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        try {
            final long orderId = Long.valueOf(request.getParameter("orderId"));
            Order order = OrderService.getInstance().getOrder(orderId);

            request.setAttribute("order", order);
        } catch (DataSourceException e) {
            e.printStackTrace();
        }

        RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views/add-product.jsp");
        dis.forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        final Product product = new Product.ProductBuilder()
                .setOrderId(Long.valueOf(request.getParameter("orderId")))
                .setProductUrl(request.getParameter("productUrl"))
                .setProductName(request.getParameter("productName"))
                .setProductPrice(Double.parseDouble(request.getParameter("productPrice")))
                .setProductQuantity(Double.parseDouble(request.getParameter("productQuantity")))
                .build();

        try {
            ProductService.getInstance().addProduct(product);
            request.setAttribute("products", ProductService.getInstance().getProducts());
            response.sendRedirect("/list-products.do?orderId=" + product.getOrderId());
        } catch (DataSourceException e) {
            e.printStackTrace();
        }
    }
}




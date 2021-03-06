package com.lidiabazhenova.webapp.controllers.product;

import com.lidiabazhenova.webapp.exception.DataSourceException;
import com.lidiabazhenova.webapp.model.Product;
import com.lidiabazhenova.webapp.service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/edit-product.do")
public class EditProductsServlet extends HttpServlet {
    Product product;

    public EditProductsServlet() {
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        try {
            final long orderId = Long.valueOf(request.getParameter("orderId"));
            product = ProductService.getInstance().getProduct(Long.valueOf(request.getParameter("productId")));
            request.setAttribute("orderId", orderId);
            request.setAttribute("product", product);
            RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views/edit-product.jsp");
            dis.forward(request, response);
        } catch (DataSourceException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        final long orderId = Long.valueOf(request.getParameter("orderId"));

        final Product newProduct = new Product.ProductBuilder()
                .setProductId(product.getProductId())
                .setProductUrl(request.getParameter("productUrl"))
                .setProductName(request.getParameter("productName"))
                .setProductPrice(Double.parseDouble(request.getParameter("productPrice")))
                .setProductQuantity(Integer.parseInt(request.getParameter("productQuantity")))
                .build();

        try {
            ProductService.getInstance().updateProduct(newProduct);
            request.setAttribute("products", ProductService.getInstance().getProducts());
            response.sendRedirect("/list-products.do?orderId=" + orderId);
        } catch (DataSourceException e) {
            e.printStackTrace();
        }
    }
}
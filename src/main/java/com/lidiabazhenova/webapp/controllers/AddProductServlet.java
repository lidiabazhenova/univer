package com.lidiabazhenova.webapp.controllers;

import com.lidiabazhenova.webapp.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/add-product.do")
public class AddProductServlet extends HttpServlet {
    private ProductService productService = new ProductService();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/add-product.jsp").forward(
                request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        final Product product = new Product.ProductBuilder().setProductName(request.getParameter("productName"))
                .setProductUrl(request.getParameter("productUrl")).build();
        productService.addProduct(product);
        request.setAttribute("products", productService.retrieveProducts());
        response.sendRedirect("/list-products.do");
    }
    private boolean validateUser(String user, String password) {
        if (user.equals("user") && password.equals("password"))
            return true;

        return false;
    }
}



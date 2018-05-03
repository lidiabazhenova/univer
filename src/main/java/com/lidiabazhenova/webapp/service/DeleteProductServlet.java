package com.lidiabazhenova.webapp.service;

import com.lidiabazhenova.webapp.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delete-product.do")
public class DeleteProductServlet extends HttpServlet {
    private ProductService productService = new ProductService();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        productService.deleteProduct(new Product(request.getParameter("product"), request.getParameter("productName")));
        response.sendRedirect("/list-products.do");
    }
}



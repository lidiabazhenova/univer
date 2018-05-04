package com.lidiabazhenova.webapp.controllers;

import com.lidiabazhenova.webapp.model.Product;
import com.lidiabazhenova.webapp.service.ProductService;

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
        final Product product = new Product.ProductBuilder().setProductUrl(request.getParameter("product"))
                .setProductName(request.getParameter("productName")).build();
        productService.deleteProduct(product);
        response.sendRedirect("/list-products.do");
    }
}



package com.lidiabazhenova.webapp.service;

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
        String newProductUrl = request.getParameter("productUrl");
        String newProductName = request.getParameter("productName");
        productService.addProduct(new Product(newProductUrl, newProductName));
        request.setAttribute("products", productService.retrieveProducts());
        response.sendRedirect("/list-products.do");
    }
}



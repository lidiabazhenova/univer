package com.lidiabazhenova.webapp.controllers;

import com.lidiabazhenova.webapp.service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/list-products.do")
public class ListProductServlet extends HttpServlet {
    private ProductService productService = new ProductService();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("products", productService.retrieveProducts());
        RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views/list-products.jsp");
        dis.forward(request, response);
    }
}



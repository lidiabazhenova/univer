package com.lidiabazhenova.webapp.controllers;

import com.lidiabazhenova.webapp.dao.ProductDao;
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
import java.util.List;

@WebServlet(urlPatterns = "/list-products.do")
public class ListProductServlet extends HttpServlet {



    public ListProductServlet() throws DataSourceException {
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = null;
        try {
            products = ProductService.getInstance().retrieveProducts();
        } catch (DataSourceException e) {
            e.printStackTrace();
        }
        request.setAttribute("products", products);
        RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views/list-products.jsp");
        dis.forward(request, response);
    }
}



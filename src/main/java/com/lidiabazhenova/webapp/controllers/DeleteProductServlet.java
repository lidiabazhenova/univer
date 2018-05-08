package com.lidiabazhenova.webapp.controllers;

import com.lidiabazhenova.webapp.exception.DataSourceException;
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

    public DeleteProductServlet() throws DataSourceException {
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        final Product product = new Product.ProductBuilder()
                .setProductId(Long.valueOf(request.getParameter("productId"))).build();
        try {
            ProductService.getInstance().deleteProduct(product.getProductId());
            response.sendRedirect("/list-products.do");
        } catch (DataSourceException e) {
            e.printStackTrace();
        }

    }
}



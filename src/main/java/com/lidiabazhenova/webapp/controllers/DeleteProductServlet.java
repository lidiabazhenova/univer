//package com.lidiabazhenova.webapp.controllers;
//
//import com.lidiabazhenova.webapp.model.Product;
//import com.lidiabazhenova.webapp.dao.ProductDao;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet(urlPatterns = "/delete-product.do")
//public class DeleteProductServlet extends HttpServlet {
//    private ProductDao productDao = new ProductDao();
//
//    protected void doGet(HttpServletRequest request,
//                         HttpServletResponse response) throws ServletException, IOException {
//        final Product product = new Product.ProductBuilder().setProductUrl(request.getParameter("product"))
//                .setProductName(request.getParameter("productName")).build();
//        productDao.deleteProduct(product);
//        response.sendRedirect("/list-products.do");
//    }
//}
//
//

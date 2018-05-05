//package com.lidiabazhenova.webapp.controllers;
//
//import com.lidiabazhenova.webapp.model.Product;
//import com.lidiabazhenova.webapp.dao.ProductDao;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet(urlPatterns = "/add-product.do")
//public class AddProductServlet extends HttpServlet {
//    private ProductDao productDao = new ProductDao();
//
//    protected void doGet(HttpServletRequest request,
//                         HttpServletResponse response) throws ServletException, IOException {
//        RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views/add-product.jsp");
//        dis.forward(request, response);
//    }
//
//    protected void doPost(HttpServletRequest request,
//                          HttpServletResponse response) throws ServletException, IOException {
//        final Product product = new Product.ProductBuilder().setProductName(request.getParameter("productName"))
//                .setProductUrl(request.getParameter("productUrl")).build();
//
//        if (product.validateNotBlank()) {
//            productDao.addProduct(product);
//            request.setAttribute("products", productDao.retrieveProducts());
//            response.sendRedirect("/list-products.do");
//        } else {
//            request.setAttribute("errorMessage", "Empty Credentials!!");
//            RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views//add-product.jsp");
//            dis.forward(request, response);
//            return;
//        }
//    }
//}
//
//

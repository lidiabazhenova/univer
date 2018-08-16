package com.lidiabazhenova.webapp.controllers.buyproduct;

import com.lidiabazhenova.webapp.exception.DataSourceException;
import com.lidiabazhenova.webapp.model.History;
import com.lidiabazhenova.webapp.model.Product;
import com.lidiabazhenova.webapp.service.HistoryService;
import com.lidiabazhenova.webapp.service.ProductService;
import com.lidiabazhenova.webapp.webdriver.WebDriverSelenium;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/buy-products.do")
public class BuyProductsServlet extends HttpServlet {

    public BuyProductsServlet() throws DataSourceException {
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        try {
            Long orderId = Long.valueOf(request.getParameter("orderId"));
            final List<Product> products = ProductService.getInstance().getProductsByOrderId(orderId);
            final StringBuilder result = WebDriverSelenium.runSeleniumWebdriver(products);
            final History history = new History();
            history.setOrderId(orderId);
            history.setDescription(result.toString());
            history.setDate(new Date());
            HistoryService.getInstance().addHistory(history);
            response.sendRedirect("/list-orders.do");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
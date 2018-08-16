package com.lidiabazhenova.webapp.controllers.history;

import com.lidiabazhenova.webapp.exception.DataSourceException;
import com.lidiabazhenova.webapp.model.History;
import com.lidiabazhenova.webapp.model.Order;
import com.lidiabazhenova.webapp.model.Product;
import com.lidiabazhenova.webapp.service.HistoryService;
import com.lidiabazhenova.webapp.service.OrderService;
import com.lidiabazhenova.webapp.service.ProductService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@WebServlet(urlPatterns = "/history.do")
public class HistoryServlet extends HttpServlet {

    public HistoryServlet() {
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        List<History> historyList;
        Order order;
        Long orderId;

        try {
            orderId = Long.valueOf(request.getParameter("orderId"));
            historyList = HistoryService.getInstance().getHistoryByOrderId(orderId);
            request.setAttribute("history", historyList);
            order = OrderService.getInstance().getOrder(orderId);
            request.setAttribute("order", order);
            RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views/history.jsp");
            dis.forward(request, response);
        } catch (DataSourceException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}




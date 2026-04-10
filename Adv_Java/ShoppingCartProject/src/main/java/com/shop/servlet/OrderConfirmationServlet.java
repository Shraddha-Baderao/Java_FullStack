package com.shop.servlet;

import com.shop.model.Order;
import com.shop.model.User;
import com.shop.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/order-confirmation")
public class OrderConfirmationServlet extends HttpServlet {

    private final OrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        User user = (User) session.getAttribute("user");

        String orderIdParam = req.getParameter("orderId");
        if (orderIdParam == null || orderIdParam.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/products");
            return;
        }

        try {
            int   orderId = Integer.parseInt(orderIdParam);
            Order order   = orderService.getOrderById(orderId);

            // Security: ensure the order belongs to this user
            if (order == null || order.getUserId() != user.getUserId()) {
                resp.sendRedirect(req.getContextPath() + "/products");
                return;
            }

            req.setAttribute("order", order);
            req.getRequestDispatcher("/customer/order-confirmation.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/customer/order-confirmation.jsp").forward(req, resp);
        }
    }
}

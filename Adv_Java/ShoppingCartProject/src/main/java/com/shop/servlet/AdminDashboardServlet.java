package com.shop.servlet;

import com.shop.model.User;
import com.shop.service.OrderService;
import com.shop.service.ProductService;
import com.shop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {

    private final ProductService productService = new ProductService();
    private final UserService    userService    = new UserService();
    private final OrderService   orderService   = new OrderService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (!isAdmin(req, resp)) return;
        try {
            req.setAttribute("totalProducts", productService.getAllProducts().size());
            req.setAttribute("totalUsers",    userService.getAllCustomers().size());
            req.setAttribute("totalOrders",   orderService.getAllOrders().size());
            req.setAttribute("recentOrders",  orderService.getAllOrders().subList(
                    0, Math.min(5, orderService.getAllOrders().size())));
            req.getRequestDispatcher("/admin/dashboard.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/admin/dashboard.jsp").forward(req, resp);
        }
    }

    static boolean isAdmin(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return false;
        }
        User user = (User) session.getAttribute("user");
        if (!user.isAdmin()) {
            resp.sendRedirect(req.getContextPath() + "/products");
            return false;
        }
        return true;
    }
}

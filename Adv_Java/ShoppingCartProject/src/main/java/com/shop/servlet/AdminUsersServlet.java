package com.shop.servlet;

import com.shop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/users")
public class AdminUsersServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (!AdminDashboardServlet.isAdmin(req, resp)) return;
        try {
            req.setAttribute("users", userService.getAllCustomers());
            req.getRequestDispatcher("/admin/users.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/admin/users.jsp").forward(req, resp);
        }
    }
}

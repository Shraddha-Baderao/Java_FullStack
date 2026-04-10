package com.shop.servlet;

import com.shop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Already logged in? Redirect
        if (req.getSession(false) != null && req.getSession().getAttribute("user") != null) {
            resp.sendRedirect(req.getContextPath() + "/products");
            return;
        }
        req.getRequestDispatcher("/customer/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String fullName  = req.getParameter("fullName");
        String email     = req.getParameter("email");
        String password  = req.getParameter("password");
        String phone     = req.getParameter("phone");
        String address   = req.getParameter("address");

        try {
            String error = userService.register(fullName, email, password, phone, address);
            if (error != null) {
                req.setAttribute("error", error);
                req.setAttribute("fullName", fullName);
                req.setAttribute("email", email);
                req.setAttribute("phone", phone);
                req.setAttribute("address", address);
                req.getRequestDispatcher("/customer/register.jsp").forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/login?registered=true");
            }
        } catch (Exception e) {
            req.setAttribute("error", "An error occurred: " + e.getMessage());
            req.getRequestDispatcher("/customer/register.jsp").forward(req, resp);
        }
    }
}

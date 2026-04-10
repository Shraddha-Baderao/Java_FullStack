package com.shop.servlet;

import com.shop.model.User;
import com.shop.service.CartService;
import com.shop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private final ProductService productService = new ProductService();
    private final CartService    cartService    = new CartService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String keyword    = req.getParameter("search");
            String categoryId = req.getParameter("categoryId");

            if (keyword != null && !keyword.trim().isEmpty()) {
                req.setAttribute("products",  productService.searchProducts(keyword));
                req.setAttribute("keyword",   keyword);
            } else if (categoryId != null && !categoryId.isEmpty()) {
                req.setAttribute("products",  productService.getProductsByCategory(Integer.parseInt(categoryId)));
                req.setAttribute("selectedCategory", Integer.parseInt(categoryId));
            } else {
                req.setAttribute("products",  productService.getAllProducts());
            }

            req.setAttribute("categories", productService.getAllCategories());

            // Cart count badge
            HttpSession session = req.getSession(false);
            if (session != null && session.getAttribute("user") != null) {
                User u = (User) session.getAttribute("user");
                req.setAttribute("cartCount", cartService.getCartCount(u.getUserId()));
            }

            req.getRequestDispatcher("/customer/products.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/customer/products.jsp").forward(req, resp);
        }
    }
}

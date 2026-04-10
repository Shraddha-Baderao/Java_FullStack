package com.shop.servlet;

import com.shop.model.CartItem;
import com.shop.model.User;
import com.shop.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private final CartService cartService = new CartService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = getLoggedInUser(req, resp);
        if (user == null) return;

        try {
            List<CartItem> items = cartService.getCartItems(user.getUserId());
            BigDecimal     total = cartService.getCartTotal(items);
            req.setAttribute("cartItems",  items);
            req.setAttribute("cartTotal", total.doubleValue());
            req.setAttribute("cartCount",  items.stream().mapToInt(CartItem::getQuantity).sum());
            req.getRequestDispatcher("/customer/cart.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/customer/cart.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = getLoggedInUser(req, resp);
        if (user == null) return;

        String action = req.getParameter("action");
        try {
            if ("add".equals(action)) {
            	String productParam = req.getParameter("productId");
            	if (productParam == null || productParam.isEmpty()) {
            	    resp.sendRedirect(req.getContextPath() + "/products?error=missingProduct");
            	    return;
            	}
            	int productId = Integer.parseInt(productParam);
                int quantity  = 1;
                String qParam = req.getParameter("quantity");
                if (qParam != null && !qParam.isEmpty()) quantity = Integer.parseInt(qParam);
                cartService.addToCart(user.getUserId(), productId, quantity);
                resp.sendRedirect(req.getContextPath() + "/cart?added=true");

            } else if ("remove".equals(action)) {
                int cartId = Integer.parseInt(req.getParameter("cartId"));
                cartService.removeFromCart(cartId, user.getUserId());
                resp.sendRedirect(req.getContextPath() + "/cart?removed=true");

            } else {
                resp.sendRedirect(req.getContextPath() + "/cart");
            }
        } catch (Exception e) {
        	 e.printStackTrace(); // 🔥 VERY IMPORTANT
        	    resp.sendRedirect(req.getContextPath() + "/cart?error=true");
        }
    }

    private User getLoggedInUser(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            session = req.getSession(true);
            session.setAttribute("redirectAfterLogin", req.getContextPath() + "/cart");
            resp.sendRedirect(req.getContextPath() + "/login");
            return null;
        }
        return (User) session.getAttribute("user");
    }
}

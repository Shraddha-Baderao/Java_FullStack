package com.shop.servlet;

import com.shop.model.CartItem;
import com.shop.model.Order;
import com.shop.model.User;
import com.shop.service.CartService;
import com.shop.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    private final CartService  cartService  = new CartService();
    private final OrderService orderService = new OrderService();

    /** Show checkout / payment form */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = requireLogin(req, resp);
        if (user == null) return;

        try {
            List<CartItem> items = cartService.getCartItems(user.getUserId());
            if (items == null || items.isEmpty()) {
                resp.sendRedirect(req.getContextPath() + "/cart?empty=true");
                return;
            }
            BigDecimal total = cartService.getCartTotal(items);
            req.setAttribute("cartItems",  items);
            req.setAttribute("cartTotal",  total);
            req.setAttribute("cartCount",  items.stream().mapToInt(CartItem::getQuantity).sum());
            req.getRequestDispatcher("/customer/checkout.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/customer/checkout.jsp").forward(req, resp);
        }
    }

    /** Process payment and place order */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = requireLogin(req, resp);
        if (user == null) return;

        req.setCharacterEncoding("UTF-8");
        String cardholderName = req.getParameter("cardholderName");
        String cardNumber     = req.getParameter("cardNumber");
        String expiryDate     = req.getParameter("expiryDate");
        String cvv            = req.getParameter("cvv");

        try {
            List<CartItem> items = cartService.getCartItems(user.getUserId());
            int orderId = orderService.placeOrder(
                    user.getUserId(), items, cardholderName, cardNumber, expiryDate, cvv);

            resp.sendRedirect(req.getContextPath() + "/order-confirmation?orderId=" + orderId);

        } catch (Exception e) {
            // Reload payment page with error
            try {
                List<CartItem> items = cartService.getCartItems(user.getUserId());
                BigDecimal total = cartService.getCartTotal(items);
                req.setAttribute("cartItems",      items);
                req.setAttribute("cartTotal",      total);
                req.setAttribute("cartCount",      items.stream().mapToInt(CartItem::getQuantity).sum());
                req.setAttribute("error",          e.getMessage());
                req.setAttribute("cardholderName", cardholderName);
                req.setAttribute("expiryDate",     expiryDate);
            } catch (Exception ignored) {}
            req.getRequestDispatcher("/customer/checkout.jsp").forward(req, resp);
        }
    }

    private User requireLogin(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            req.getSession(true).setAttribute("redirectAfterLogin",
                    req.getContextPath() + "/checkout");
            resp.sendRedirect(req.getContextPath() + "/login");
            return null;
        }
        return (User) session.getAttribute("user");
    }
}

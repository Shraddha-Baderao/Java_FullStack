package com.shop.service;

import com.shop.dao.OrderDAO;
import com.shop.model.CartItem;
import com.shop.model.Order;

import java.sql.SQLException;
import java.util.List;

/**
 * Service layer for Order-related business logic.
 */
public class OrderService {

    private final OrderDAO orderDAO = new OrderDAO();

    /**
     * Validates payment details and places the order.
     * Returns order ID on success, or throws exception on failure.
     */
    public int placeOrder(int userId, List<CartItem> cartItems,
                          String cardholderName, String cardNumber,
                          String expiryDate, String cvv) throws Exception {

        // Basic validations
        if (cartItems == null || cartItems.isEmpty())
            throw new Exception("Your cart is empty.");
        if (cardholderName == null || cardholderName.trim().isEmpty())
            throw new Exception("Cardholder name is required.");
        String cleanCard = cardNumber != null ? cardNumber.replaceAll("\\s+", "") : "";
        if (cleanCard.length() < 12 || cleanCard.length() > 19)
            throw new Exception("Invalid card number.");
        if (!cleanCard.matches("\\d+"))
            throw new Exception("Card number must contain only digits.");
        if (expiryDate == null || !expiryDate.matches("(0[1-9]|1[0-2])/\\d{2}"))
            throw new Exception("Invalid expiry date. Use MM/YY format.");
        if (cvv == null || !cvv.matches("\\d{3,4}"))
            throw new Exception("Invalid CVV.");

        return orderDAO.placeOrder(userId, cartItems,
                                   cardholderName.trim(), cleanCard, expiryDate.trim());
    }

    public Order getOrderById(int orderId) throws SQLException {
        return orderDAO.getOrderById(orderId);
    }

    public List<Order> getOrdersByUser(int userId) throws SQLException {
        return orderDAO.getOrdersByUser(userId);
    }

    public List<Order> getAllOrders() throws SQLException {
        return orderDAO.getAllOrders();
    }
}

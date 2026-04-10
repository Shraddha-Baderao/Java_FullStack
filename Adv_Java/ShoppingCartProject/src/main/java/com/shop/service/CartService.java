package com.shop.service;

import com.shop.dao.CartDAO;
import com.shop.model.CartItem;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/**
 * Service layer for Cart-related business logic.
 */
public class CartService {

    private final CartDAO cartDAO = new CartDAO();

    public boolean addToCart(int userId, int productId, int quantity) throws SQLException {
        if (quantity < 1) quantity = 1;
        return cartDAO.addToCart(userId, productId, quantity);
    }

    public List<CartItem> getCartItems(int userId) throws SQLException {
        return cartDAO.getCartItems(userId);
    }

    public boolean removeFromCart(int cartId, int userId) throws SQLException {
        return cartDAO.removeFromCart(cartId, userId);
    }

    public int getCartCount(int userId) throws SQLException {
        return cartDAO.getCartCount(userId);
    }

    public BigDecimal getCartTotal(List<CartItem> items) {
        return items.stream()
                .map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

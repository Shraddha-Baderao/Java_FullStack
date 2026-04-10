package com.shop.dao;

import com.shop.model.CartItem;
import com.shop.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Cart operations.
 */
public class CartDAO {

    // ---------------------------------------------------------------
    // Add product to cart (or increase quantity if already present)
    // ---------------------------------------------------------------
    public boolean addToCart(int userId, int productId, int quantity) throws SQLException {
        // Check if item already in cart
        String checkSql = "SELECT cart_id, quantity FROM cart WHERE user_id = ? AND product_id = ?";
        try (Connection conn = DBConnection.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(checkSql)) {
                ps.setInt(1, userId);
                ps.setInt(2, productId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // Update quantity
                        int newQty  = rs.getInt("quantity") + quantity;
                        int cartId  = rs.getInt("cart_id");
                        String upd  = "UPDATE cart SET quantity = ? WHERE cart_id = ?";
                        try (PreparedStatement ups = conn.prepareStatement(upd)) {
                            ups.setInt(1, newQty);
                            ups.setInt(2, cartId);
                            return ups.executeUpdate() > 0;
                        }
                    }
                }
            }
            // Insert new row
            String ins = "INSERT INTO cart (user_id, product_id, quantity) VALUES (?,?,?)";
            try (PreparedStatement ps = conn.prepareStatement(ins)) {
                ps.setInt(1, userId);
                ps.setInt(2, productId);
                ps.setInt(3, quantity);
                return ps.executeUpdate() > 0;
            }
        }
    }

    // ---------------------------------------------------------------
    // Get all cart items for a user (with product details)
    // ---------------------------------------------------------------
    public List<CartItem> getCartItems(int userId) throws SQLException {
        String sql = "SELECT c.cart_id, c.user_id, c.product_id, c.quantity, c.added_at, " +
                     "p.name AS product_name, p.price AS unit_price, p.image_url " +
                     "FROM cart c JOIN products p ON c.product_id = p.product_id " +
                     "WHERE c.user_id = ? ORDER BY c.added_at DESC";
        List<CartItem> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CartItem item = new CartItem();
                    item.setCartId(rs.getInt("cart_id"));
                    item.setUserId(rs.getInt("user_id"));
                    item.setProductId(rs.getInt("product_id"));
                    item.setProductName(rs.getString("product_name"));
                    item.setUnitPrice(rs.getBigDecimal("unit_price"));
                    item.setImageUrl(rs.getString("image_url"));
                    item.setQuantity(rs.getInt("quantity"));
                    item.setAddedAt(rs.getTimestamp("added_at"));
                    list.add(item);
                }
            }
        }
        return list;
    }

    // ---------------------------------------------------------------
    // Remove one item from cart
    // ---------------------------------------------------------------
    public boolean removeFromCart(int cartId, int userId) throws SQLException {
        String sql = "DELETE FROM cart WHERE cart_id = ? AND user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cartId);
            ps.setInt(2, userId);
            return ps.executeUpdate() > 0;
        }
    }

    // ---------------------------------------------------------------
    // Clear entire cart for a user (after order placed)
    // ---------------------------------------------------------------
    public void clearCart(Connection conn, int userId) throws SQLException {
        String sql = "DELETE FROM cart WHERE user_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        }
    }

    // ---------------------------------------------------------------
    // Count items in cart (for badge in header)
    // ---------------------------------------------------------------
    public int getCartCount(int userId) throws SQLException {
        String sql = "SELECT COALESCE(SUM(quantity),0) FROM cart WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        }
    }
}

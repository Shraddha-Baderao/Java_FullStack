package com.shop.dao;

import com.shop.model.*;
import com.shop.util.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Order, OrderItem, and Payment entities.
 * Uses a single transaction for placing an order.
 */
public class OrderDAO {

    // ---------------------------------------------------------------
    // PLACE ORDER (transactional: order + items + payment + stock)
    // ---------------------------------------------------------------
    public int placeOrder(int userId, List<CartItem> cartItems,
                          String cardholderName, String cardNumber,
                          String expiryDate) throws SQLException {

        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            // 1. Calculate total
            BigDecimal total = cartItems.stream()
                    .map(CartItem::getSubtotal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            // 2. Insert into orders
            int orderId;
            String orderSql = "INSERT INTO orders (user_id, total_amount, status) VALUES (?,?,'CONFIRMED')";
            try (PreparedStatement ps = conn.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, userId);
                ps.setBigDecimal(2, total);
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    keys.next();
                    orderId = keys.getInt(1);
                }
            }

            // 3. Insert order items & reduce stock
            String itemSql = "INSERT INTO order_items (order_id, product_id, quantity, unit_price) VALUES (?,?,?,?)";
            String stockSql = "UPDATE products SET quantity = quantity - ? WHERE product_id = ?";
            try (PreparedStatement itemPs  = conn.prepareStatement(itemSql);
                 PreparedStatement stockPs = conn.prepareStatement(stockSql)) {
                for (CartItem ci : cartItems) {
                    itemPs.setInt(1, orderId);
                    itemPs.setInt(2, ci.getProductId());
                    itemPs.setInt(3, ci.getQuantity());
                    itemPs.setBigDecimal(4, ci.getUnitPrice());
                    itemPs.addBatch();

                    stockPs.setInt(1, ci.getQuantity());
                    stockPs.setInt(2, ci.getProductId());
                    stockPs.addBatch();
                }
                itemPs.executeBatch();
                stockPs.executeBatch();
            }

            // 4. Insert payment (store only last 4 digits of card)
            String last4   = cardNumber.replaceAll("\\s+", "");
            last4 = last4.substring(Math.max(0, last4.length() - 4));
            String paySQL  = "INSERT INTO payments (order_id, cardholder_name, card_last4, expiry_date, amount, status) VALUES (?,?,?,?,?,'SUCCESS')";
            try (PreparedStatement ps = conn.prepareStatement(paySQL)) {
                ps.setInt(1, orderId);
                ps.setString(2, cardholderName);
                ps.setString(3, last4);
                ps.setString(4, expiryDate);
                ps.setBigDecimal(5, total);
                ps.executeUpdate();
            }

            // 5. Clear cart
            String clearCart = "DELETE FROM cart WHERE user_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(clearCart)) {
                ps.setInt(1, userId);
                ps.executeUpdate();
            }

            conn.commit();
            return orderId;

        } catch (SQLException e) {
            if (conn != null) { try { conn.rollback(); } catch (SQLException ignored) {} }
            throw e;
        } finally {
            DBConnection.close(conn);
        }
    }

    // ---------------------------------------------------------------
    // GET ORDER by ID (with items and payment)
    // ---------------------------------------------------------------
    public Order getOrderById(int orderId) throws SQLException {
        String sql = "SELECT o.*, u.full_name AS customer_name, u.email AS customer_email " +
                     "FROM orders o JOIN users u ON o.user_id = u.user_id WHERE o.order_id = ?";
        Order order = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) order = mapOrderRow(rs);
            }
        }
        if (order != null) {
            order.setItems(getOrderItems(orderId));
            order.setPayment(getPaymentByOrderId(orderId));
        }
        return order;
    }

    // ---------------------------------------------------------------
    // GET ALL ORDERS for one user
    // ---------------------------------------------------------------
    public List<Order> getOrdersByUser(int userId) throws SQLException {
        String sql = "SELECT o.*, u.full_name AS customer_name, u.email AS customer_email " +
                     "FROM orders o JOIN users u ON o.user_id = u.user_id " +
                     "WHERE o.user_id = ? ORDER BY o.created_at DESC";
        return fetchOrders(sql, userId);
    }

    // ---------------------------------------------------------------
    // GET ALL ORDERS (admin)
    // ---------------------------------------------------------------
    public List<Order> getAllOrders() throws SQLException {
        String sql = "SELECT o.*, u.full_name AS customer_name, u.email AS customer_email " +
                     "FROM orders o JOIN users u ON o.user_id = u.user_id ORDER BY o.created_at DESC";
        List<Order> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Order ord = mapOrderRow(rs);
                ord.setPayment(getPaymentByOrderId(ord.getOrderId()));
                list.add(ord);
            }
        }
        return list;
    }

    // ---------------------------------------------------------------
    // Private helpers
    // ---------------------------------------------------------------
    private List<Order> fetchOrders(String sql, int userId) throws SQLException {
        List<Order> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapOrderRow(rs));
            }
        }
        return list;
    }

    private Order mapOrderRow(ResultSet rs) throws SQLException {
        Order o = new Order();
        o.setOrderId(rs.getInt("order_id"));
        o.setUserId(rs.getInt("user_id"));
        o.setCustomerName(rs.getString("customer_name"));
        o.setCustomerEmail(rs.getString("customer_email"));
        o.setTotalAmount(rs.getBigDecimal("total_amount"));
        o.setStatus(rs.getString("status"));
        o.setCreatedAt(rs.getTimestamp("created_at"));
        return o;
    }

    private List<OrderItem> getOrderItems(int orderId) throws SQLException {
        String sql = "SELECT oi.*, p.name AS product_name FROM order_items oi " +
                     "JOIN products p ON oi.product_id = p.product_id WHERE oi.order_id = ?";
        List<OrderItem> items = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderItem item = new OrderItem();
                    item.setItemId(rs.getInt("item_id"));
                    item.setOrderId(rs.getInt("order_id"));
                    item.setProductId(rs.getInt("product_id"));
                    item.setProductName(rs.getString("product_name"));
                    item.setQuantity(rs.getInt("quantity"));
                    item.setUnitPrice(rs.getBigDecimal("unit_price"));
                    items.add(item);
                }
            }
        }
        return items;
    }

    private Payment getPaymentByOrderId(int orderId) throws SQLException {
        String sql = "SELECT * FROM payments WHERE order_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Payment pay = new Payment();
                    pay.setPaymentId(rs.getInt("payment_id"));
                    pay.setOrderId(rs.getInt("order_id"));
                    pay.setCardholderName(rs.getString("cardholder_name"));
                    pay.setCardLast4(rs.getString("card_last4"));
                    pay.setExpiryDate(rs.getString("expiry_date"));
                    pay.setAmount(rs.getBigDecimal("amount"));
                    pay.setStatus(rs.getString("status"));
                    pay.setPaidAt(rs.getTimestamp("paid_at"));
                    return pay;
                }
            }
        }
        return null;
    }
}

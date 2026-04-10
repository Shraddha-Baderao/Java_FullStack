package com.shop.dao;

import com.shop.model.Category;
import com.shop.model.Product;
import com.shop.util.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Product entity.
 */
public class ProductDAO {

    // ---------------------------------------------------------------
    // SELECT ALL with category name
    // ---------------------------------------------------------------
    public List<Product> getAllProducts() throws SQLException {
        String sql = "SELECT p.*, c.name AS category_name " +
                     "FROM products p LEFT JOIN categories c ON p.category_id = c.category_id " +
                     "ORDER BY p.created_at DESC";
        return fetchList(sql);
    }

    // ---------------------------------------------------------------
    // SELECT by category
    // ---------------------------------------------------------------
    public List<Product> getProductsByCategory(int categoryId) throws SQLException {
        String sql = "SELECT p.*, c.name AS category_name " +
                     "FROM products p LEFT JOIN categories c ON p.category_id = c.category_id " +
                     "WHERE p.category_id = ? ORDER BY p.name";
        List<Product> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        }
        return list;
    }

    // ---------------------------------------------------------------
    // SELECT by ID
    // ---------------------------------------------------------------
    public Product getProductById(int productId) throws SQLException {
        String sql = "SELECT p.*, c.name AS category_name " +
                     "FROM products p LEFT JOIN categories c ON p.category_id = c.category_id " +
                     "WHERE p.product_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        }
        return null;
    }

    // ---------------------------------------------------------------
    // SEARCH by name/description
    // ---------------------------------------------------------------
    public List<Product> searchProducts(String keyword) throws SQLException {
        String sql = "SELECT p.*, c.name AS category_name " +
                     "FROM products p LEFT JOIN categories c ON p.category_id = c.category_id " +
                     "WHERE p.name LIKE ? OR p.description LIKE ? ORDER BY p.name";
        List<Product> list = new ArrayList<>();
        String kw = "%" + keyword + "%";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kw);
            ps.setString(2, kw);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        }
        return list;
    }

    // ---------------------------------------------------------------
    // INSERT
    // ---------------------------------------------------------------
    public boolean addProduct(Product p) throws SQLException {
        String sql = "INSERT INTO products (name, description, price, category_id, quantity, image_url) VALUES (?,?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setBigDecimal(3, p.getPrice());
            ps.setInt(4, p.getCategoryId());
            ps.setInt(5, p.getQuantity());
            ps.setString(6, p.getImageUrl());
            return ps.executeUpdate() > 0;
        }
    }

    // ---------------------------------------------------------------
    // UPDATE
    // ---------------------------------------------------------------
    public boolean updateProduct(Product p) throws SQLException {
        String sql = "UPDATE products SET name=?, description=?, price=?, category_id=?, quantity=?, image_url=? WHERE product_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setBigDecimal(3, p.getPrice());
            ps.setInt(4, p.getCategoryId());
            ps.setInt(5, p.getQuantity());
            ps.setString(6, p.getImageUrl());
            ps.setInt(7, p.getProductId());
            return ps.executeUpdate() > 0;
        }
    }

    // ---------------------------------------------------------------
    // DELETE
    // ---------------------------------------------------------------
    public boolean deleteProduct(int productId) throws SQLException {
        String sql = "DELETE FROM products WHERE product_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            return ps.executeUpdate() > 0;
        }
    }

    // ---------------------------------------------------------------
    // Reduce stock after order
    // ---------------------------------------------------------------
    public void reduceStock(Connection conn, int productId, int qty) throws SQLException {
        String sql = "UPDATE products SET quantity = quantity - ? WHERE product_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, qty);
            ps.setInt(2, productId);
            ps.executeUpdate();
        }
    }

    // ---------------------------------------------------------------
    // All categories
    // ---------------------------------------------------------------
    public List<Category> getAllCategories() throws SQLException {
        String sql = "SELECT * FROM categories ORDER BY name";
        List<Category> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Category c = new Category();
                c.setCategoryId(rs.getInt("category_id"));
                c.setName(rs.getString("name"));
                c.setDescription(rs.getString("description"));
                list.add(c);
            }
        }
        return list;
    }

    // ---------------------------------------------------------------
    // Private helpers
    // ---------------------------------------------------------------
    private List<Product> fetchList(String sql) throws SQLException {
        List<Product> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    private Product mapRow(ResultSet rs) throws SQLException {
        Product p = new Product();
        p.setProductId(rs.getInt("product_id"));
        p.setName(rs.getString("name"));
        p.setDescription(rs.getString("description"));
        p.setPrice(rs.getBigDecimal("price"));
        p.setCategoryId(rs.getInt("category_id"));
        p.setCategoryName(rs.getString("category_name"));
        p.setQuantity(rs.getInt("quantity"));
        p.setImageUrl(rs.getString("image_url"));
        p.setCreatedAt(rs.getTimestamp("created_at"));
        return p;
    }
}

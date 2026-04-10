package com.shop.service;

import com.shop.dao.ProductDAO;
import com.shop.model.Category;
import com.shop.model.Product;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/**
 * Service layer for Product-related business logic.
 */
public class ProductService {

    private final ProductDAO productDAO = new ProductDAO();

    public List<Product> getAllProducts() throws SQLException {
        return productDAO.getAllProducts();
    }

    public List<Product> getProductsByCategory(int categoryId) throws SQLException {
        return productDAO.getProductsByCategory(categoryId);
    }

    public Product getProductById(int id) throws SQLException {
        return productDAO.getProductById(id);
    }

    public List<Product> searchProducts(String keyword) throws SQLException {
        if (keyword == null || keyword.trim().isEmpty()) return getAllProducts();
        return productDAO.searchProducts(keyword.trim());
    }

    public List<Category> getAllCategories() throws SQLException {
        return productDAO.getAllCategories();
    }

    /** Returns error message or null on success */
    public String addProduct(String name, String description, String priceStr,
                             int categoryId, int quantity, String imageUrl) throws SQLException {
        if (name == null || name.trim().isEmpty())    return "Product name is required.";
        if (priceStr == null || priceStr.trim().isEmpty()) return "Price is required.";
        BigDecimal price;
        try { price = new BigDecimal(priceStr.trim()); }
        catch (NumberFormatException e) { return "Invalid price format."; }
        if (price.compareTo(BigDecimal.ZERO) < 0) return "Price cannot be negative.";

        Product p = new Product();
        p.setName(name.trim());
        p.setDescription(description);
        p.setPrice(price);
        p.setCategoryId(categoryId);
        p.setQuantity(Math.max(0, quantity));
        p.setImageUrl(imageUrl != null && !imageUrl.trim().isEmpty() ? imageUrl.trim() : "default.jpg");
        return productDAO.addProduct(p) ? null : "Failed to add product.";
    }

    /** Returns error message or null on success */
    public String updateProduct(int productId, String name, String description,
                                String priceStr, int categoryId,
                                int quantity, String imageUrl) throws SQLException {
        if (name == null || name.trim().isEmpty()) return "Product name is required.";
        BigDecimal price;
        try { price = new BigDecimal(priceStr.trim()); }
        catch (NumberFormatException e) { return "Invalid price format."; }

        Product p = productDAO.getProductById(productId);
        if (p == null) return "Product not found.";
        p.setName(name.trim());
        p.setDescription(description);
        p.setPrice(price);
        p.setCategoryId(categoryId);
        p.setQuantity(Math.max(0, quantity));
        if (imageUrl != null && !imageUrl.trim().isEmpty()) p.setImageUrl(imageUrl.trim());
        return productDAO.updateProduct(p) ? null : "Failed to update product.";
    }

    public boolean deleteProduct(int productId) throws SQLException {
        return productDAO.deleteProduct(productId);
    }
}

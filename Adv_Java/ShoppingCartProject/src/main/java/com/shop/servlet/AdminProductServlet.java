package com.shop.servlet;

import com.shop.model.Product;
import com.shop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/products")
public class AdminProductServlet extends HttpServlet {

    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (!AdminDashboardServlet.isAdmin(req, resp)) return;

        String action = req.getParameter("action");
        try {
            if ("edit".equals(action)) {
                int productId = Integer.parseInt(req.getParameter("id"));
                Product p = productService.getProductById(productId);
                req.setAttribute("product",    p);
                req.setAttribute("categories", productService.getAllCategories());
                req.getRequestDispatcher("/admin/edit-product.jsp").forward(req, resp);
            } else if ("add".equals(action)) {
                req.setAttribute("categories", productService.getAllCategories());
                req.getRequestDispatcher("/admin/add-product.jsp").forward(req, resp);
            } else if ("delete".equals(action)) {
                int productId = Integer.parseInt(req.getParameter("id"));
                productService.deleteProduct(productId);
                resp.sendRedirect(req.getContextPath() + "/admin/products?deleted=true");
            } else {
                req.setAttribute("products", productService.getAllProducts());
                req.getRequestDispatcher("/admin/products.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/admin/products.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (!AdminDashboardServlet.isAdmin(req, resp)) return;
        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        try {
            if ("add".equals(action)) {
                String error = productService.addProduct(
                        req.getParameter("name"),
                        req.getParameter("description"),
                        req.getParameter("price"),
                        Integer.parseInt(req.getParameter("categoryId")),
                        Integer.parseInt(req.getParameter("quantity")),
                        req.getParameter("imageUrl")
                );
                if (error != null) {
                    req.setAttribute("error",      error);
                    req.setAttribute("categories", productService.getAllCategories());
                    req.getRequestDispatcher("/admin/add-product.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect(req.getContextPath() + "/admin/products?added=true");
                }

            } else if ("update".equals(action)) {
                int productId = Integer.parseInt(req.getParameter("productId"));
                String error = productService.updateProduct(
                        productId,
                        req.getParameter("name"),
                        req.getParameter("description"),
                        req.getParameter("price"),
                        Integer.parseInt(req.getParameter("categoryId")),
                        Integer.parseInt(req.getParameter("quantity")),
                        req.getParameter("imageUrl")
                );
                if (error != null) {
                    req.setAttribute("error",      error);
                    req.setAttribute("product",    productService.getProductById(productId));
                    req.setAttribute("categories", productService.getAllCategories());
                    req.getRequestDispatcher("/admin/edit-product.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect(req.getContextPath() + "/admin/products?updated=true");
                }
            }
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/admin/products.jsp").forward(req, resp);
        }
    }
}

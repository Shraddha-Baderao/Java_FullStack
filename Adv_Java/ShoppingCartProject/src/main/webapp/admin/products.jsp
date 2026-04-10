<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.shop.model.Product" %>
<%
    List<Product> products = (List<Product>) request.getAttribute("products");
    String ctx = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Products – ShopEase Admin</title>
  <link rel="stylesheet" href="<%= ctx %>/css/style.css">
</head>
<body>
<div class="admin-layout">
  <%@ include file="/WEB-INF/admin-sidebar.jsp" %>

  <main class="admin-main">
    <div style="display:flex;justify-content:space-between;align-items:center;margin-bottom:1.75rem;flex-wrap:wrap;gap:1rem">
      <h2 style="color:var(--navy)">📦 Products</h2>
      <a href="<%= ctx %>/admin/products?action=add" class="btn btn-amber">+ Add New Product</a>
    </div>

    <!-- Alerts -->
    <% if (request.getParameter("added")   != null) { %><div class="alert alert-success">✓ Product added successfully.</div><% } %>
    <% if (request.getParameter("updated") != null) { %><div class="alert alert-success">✓ Product updated successfully.</div><% } %>
    <% if (request.getParameter("deleted") != null) { %><div class="alert alert-success">✓ Product deleted.</div><% } %>
    <% if (request.getAttribute("error")   != null) { %><div class="alert alert-danger">⚠ <%= request.getAttribute("error") %></div><% } %>

    <div class="table-wrapper">
      <table>
        <thead>
          <tr>
            <th>#</th>
            <th>Name</th>
            <th>Category</th>
            <th>Price</th>
            <th>Stock</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <% if (products == null || products.isEmpty()) { %>
            <tr><td colspan="6" class="text-center" style="padding:2rem;color:var(--gray-400)">No products found. <a href="<%= ctx %>/admin/products?action=add">Add one!</a></td></tr>
          <% } else { for (Product p : products) { %>
            <tr>
              <td style="color:var(--gray-400);font-size:.8rem">#<%= p.getProductId() %></td>
              <td>
                <strong><%= p.getName() %></strong>
                <% if (p.getDescription() != null && !p.getDescription().isEmpty()) { %>
                  <br><small style="color:var(--gray-400)">
                    <%= p.getDescription().length() > 60
                        ? p.getDescription().substring(0, 60) + "…"
                        : p.getDescription() %>
                  </small>
                <% } %>
              </td>
              <td><span style="background:var(--gray-100);padding:.2rem .6rem;border-radius:20px;font-size:.78rem">
                <%= p.getCategoryName() != null ? p.getCategoryName() : "–" %>
              </span></td>
              <td><strong>₹<%= String.format("%,.2f", p.getPrice()) %></strong></td>
              <td>
                <span class="badge-stock <%= p.isInStock() ? "badge-instock" : "badge-outstock" %>">
                  <%= p.getQuantity() %> units
                </span>
              </td>
              <td>
                <div style="display:flex;gap:.5rem">
                  <a href="<%= ctx %>/admin/products?action=edit&id=<%= p.getProductId() %>"
                     class="btn btn-outline btn-sm">✏ Edit</a>
                  <a href="<%= ctx %>/admin/products?action=delete&id=<%= p.getProductId() %>"
                     class="btn btn-danger btn-sm"
                     onclick="return confirm('Delete \'<%= p.getName() %>\'? This cannot be undone.')">🗑 Delete</a>
                </div>
              </td>
            </tr>
          <% } } %>
        </tbody>
      </table>
    </div>
  </main>
</div>
</body>
</html>

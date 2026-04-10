<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.shop.model.Product, com.shop.model.Category, com.shop.model.User" %>
<%
    List<Product>  products   = (List<Product>)  request.getAttribute("products");
    List<Category> categories = (List<Category>) request.getAttribute("categories");
    String         keyword    = (String)         request.getAttribute("keyword");
    Integer selCat = (Integer) request.getAttribute("selectedCategory");
    String ctx = request.getContextPath();
    HttpSession s = request.getSession(false);
    User user = s != null ? (User) s.getAttribute("user") : null;
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Products – ShopEase</title>
  <link rel="stylesheet" href="<%= ctx %>/css/style.css">
</head>
<body>
<%@ include file="/WEB-INF/navbar.jsp" %>

<div class="page-header">
  <div class="container">
    <h1>🛍 Our Products</h1>
    <p>Discover quality products at great prices</p>
  </div>
</div>

<div class="container" style="padding-bottom:3rem">

  <!-- Alerts -->
  <% if (request.getParameter("added") != null) { %>
    <div class="alert alert-success">✓ Product added to your cart!</div>
  <% } %>
  <% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-danger">⚠ <%= request.getAttribute("error") %></div>
  <% } %>

  <!-- Filter Bar -->
  <div class="filter-bar">
    <!-- Search -->
    <form action="<%= ctx %>/products" method="get" class="search-bar" style="flex:1">
      <input type="text" name="search" class="form-control"
             placeholder="Search products…"
             value="<%= keyword != null ? keyword : "" %>">
      <button type="submit" class="btn btn-primary">🔍 Search</button>
      <% if (keyword != null) { %>
        <a href="<%= ctx %>/products" class="btn btn-outline btn-sm">✕ Clear</a>
      <% } %>
    </form>

    <!-- Category Filter -->
    <form action="<%= ctx %>/products" method="get">
      <select name="categoryId" class="form-control" onchange="this.form.submit()">
        <option value="">All Categories</option>
        <% if (categories != null) { for (Category c : categories) { %>
          <option value="<%= c.getCategoryId() %>"
            <%= selCat != null && selCat == c.getCategoryId() ? "selected" : "" %>>
            <%= c.getName() %>
          </option>
        <% } } %>
      </select>
    </form>
  </div>

  <!-- Product Grid -->
  <% if (products == null || products.isEmpty()) { %>
    <div class="text-center" style="padding:4rem">
      <div style="font-size:4rem">📦</div>
      <h3 style="margin:.75rem 0 .5rem">No products found</h3>
      <p>Try a different search or browse all categories.</p>
      <a href="<%= ctx %>/products" class="btn btn-primary mt-2">Browse All</a>
    </div>
  <% } else { %>
    <p style="color:var(--gray-400);font-size:.85rem;margin-bottom:.5rem">
      Showing <%= products.size() %> product<%= products.size() != 1 ? "s" : "" %>
    </p>
    <div class="products-grid">
      <% for (Product p : products) { %>
        <div class="card product-card">
          <div class="product-img-placeholder">🛒</div>
          <div class="card-body">
            <span class="product-category"><%= p.getCategoryName() != null ? p.getCategoryName() : "General" %></span>
            <h4 class="product-name"><%= p.getName() %></h4>
            <p class="product-desc"><%= p.getDescription() != null ? p.getDescription() : "" %></p>
            <span class="badge-stock <%= p.isInStock() ? "badge-instock" : "badge-outstock" %>">
              <%= p.isInStock() ? "✓ In Stock (" + p.getQuantity() + ")" : "✗ Out of Stock" %>
            </span>
            <div class="product-price">₹<%= String.format("%,.2f", p.getPrice()) %></div>
          </div>
          <div class="card-footer" style="padding:.75rem 1.5rem">
            <% if (p.isInStock()) { %>
              <% if (user != null && !user.isAdmin()) { %>
                <form action="<%= ctx %>/cart" method="post">
                  <input type="hidden" name="action"    value="add">
                  <input type="hidden" name="productId" value="<%= p.getProductId() %>">
                  <input type="hidden" name="quantity"  value="1">
                  <button type="submit" class="btn btn-amber btn-block">🛒 Add to Cart</button>
                </form>
              <% } else if (user == null) { %>
                <a href="<%= ctx %>/login" class="btn btn-outline btn-block">Login to Buy</a>
              <% } else { %>
                <span style="font-size:.8rem;color:var(--gray-400)">Admin view</span>
              <% } %>
            <% } else { %>
              <button class="btn btn-block" disabled style="background:var(--gray-200);color:var(--gray-400);cursor:not-allowed">Out of Stock</button>
            <% } %>
          </div>
        </div>
      <% } %>
    </div>
  <% } %>
</div>

<footer>&copy; 2025 <strong>ShopEase</strong>. Advanced Java Project.</footer>
</body>
</html>

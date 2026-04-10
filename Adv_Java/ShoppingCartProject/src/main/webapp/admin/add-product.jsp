<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.shop.model.Category" %>
<%
    List<Category> categories = (List<Category>) request.getAttribute("categories");
    String ctx = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Add Product – ShopEase Admin</title>
  <link rel="stylesheet" href="<%= ctx %>/css/style.css">
</head>
<body>
<div class="admin-layout">
  <%@ include file="/WEB-INF/admin-sidebar.jsp" %>

  <main class="admin-main">
    <div style="display:flex;align-items:center;gap:1rem;margin-bottom:1.75rem">
      <a href="<%= ctx %>/admin/products" class="btn btn-outline btn-sm">← Back</a>
      <h2 style="color:var(--navy)">+ Add New Product</h2>
    </div>

    <% if (request.getAttribute("error") != null) { %>
      <div class="alert alert-danger">⚠ <%= request.getAttribute("error") %></div>
    <% } %>

    <div class="card" style="max-width:680px">
      <div class="card-body">
        <form action="<%= ctx %>/admin/products" method="post">
          <input type="hidden" name="action" value="add">

          <div class="form-group">
            <label class="form-label">Product Name *</label>
            <input type="text" name="name" class="form-control" required
                   placeholder="e.g. Wireless Bluetooth Headphones">
          </div>

          <div class="form-group">
            <label class="form-label">Description</label>
            <textarea name="description" class="form-control" rows="3"
                      placeholder="Brief product description…"></textarea>
          </div>

          <div class="form-row">
            <div class="form-group">
              <label class="form-label">Price (₹) *</label>
              <input type="number" name="price" class="form-control" required
                     step="0.01" min="0" placeholder="0.00">
            </div>
            <div class="form-group">
              <label class="form-label">Stock Quantity *</label>
              <input type="number" name="quantity" class="form-control" required
                     min="0" placeholder="0">
            </div>
          </div>

          <div class="form-group">
            <label class="form-label">Category *</label>
            <select name="categoryId" class="form-control" required>
              <option value="">Select a category…</option>
              <% if (categories != null) { for (Category c : categories) { %>
                <option value="<%= c.getCategoryId() %>"><%= c.getName() %></option>
              <% } } %>
            </select>
          </div>

          <div class="form-group">
            <label class="form-label">Image Filename</label>
            <input type="text" name="imageUrl" class="form-control"
                   placeholder="e.g. headphones.jpg">
            <span class="form-text">Place image file in /webapp/images/ folder.</span>
          </div>

          <div style="display:flex;gap:1rem;margin-top:1.5rem">
            <button type="submit" class="btn btn-amber btn-lg">💾 Add Product</button>
            <a href="<%= ctx %>/admin/products" class="btn btn-outline btn-lg">Cancel</a>
          </div>
        </form>
      </div>
    </div>
  </main>
</div>
</body>
</html>

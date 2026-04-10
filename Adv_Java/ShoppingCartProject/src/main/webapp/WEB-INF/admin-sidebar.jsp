<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.shop.model.User" %>
<%
    String ctx     = request.getContextPath();
    String uri     = request.getRequestURI();
    User   adminUser = (User) request.getSession().getAttribute("user");
%>
<aside class="admin-sidebar">
  <div class="sidebar-brand">⚙ Shop<span>Ease</span><br>
    <small style="font-size:.72rem;color:rgba(255,255,255,.5);font-family:'DM Sans',sans-serif">Admin Panel</small>
  </div>

  <nav class="sidebar-nav">
    <a href="<%= ctx %>/admin/dashboard"
       class="<%= uri.contains("dashboard") ? "active" : "" %>">
      <span class="nav-icon">📊</span> Dashboard
    </a>
    <a href="<%= ctx %>/admin/products"
       class="<%= uri.contains("/admin/products") ? "active" : "" %>">
      <span class="nav-icon">📦</span> Products
    </a>
    <a href="<%= ctx %>/admin/users"
       class="<%= uri.contains("/admin/users") ? "active" : "" %>">
      <span class="nav-icon">👥</span> Customers
    </a>
    <a href="<%= ctx %>/admin/orders"
       class="<%= uri.contains("/admin/orders") ? "active" : "" %>">
      <span class="nav-icon">🧾</span> Orders
    </a>
    <hr style="border-color:rgba(255,255,255,.1);margin:.75rem 1.25rem">
    <a href="<%= ctx %>/products">
      <span class="nav-icon">🛍</span> View Store
    </a>
    <a href="<%= ctx %>/logout">
      <span class="nav-icon">🚪</span> Logout
    </a>
  </nav>

  <div style="padding:1.25rem;margin-top:auto;font-size:.75rem;color:rgba(255,255,255,.35)">
    Logged in as<br>
    <strong style="color:rgba(255,255,255,.6)"><%= adminUser != null ? adminUser.getFullName() : "Admin" %></strong>
  </div>
</aside>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login – ShopEase</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<div class="auth-wrapper">
  <div class="auth-card">
    <div class="auth-logo">Shop<span>Ease</span></div>
    <p class="auth-sub">Your premium online store</p>

    <h2>Welcome Back</h2>

    <% if (request.getAttribute("error") != null) { %>
      <div class="alert alert-danger">⚠ <%= request.getAttribute("error") %></div>
    <% } %>
    <% if (request.getAttribute("success") != null) { %>
      <div class="alert alert-success">✓ <%= request.getAttribute("success") %></div>
    <% } %>

    <form action="<%= request.getContextPath() %>/login" method="post">
      <div class="form-group">
        <label class="form-label">Email Address</label>
        <input type="email" name="email" class="form-control" required
               placeholder="you@example.com"
               value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>">
      </div>
      <div class="form-group">
        <label class="form-label">Password</label>
        <input type="password" name="password" class="form-control" required placeholder="••••••••">
      </div>
      <button type="submit" class="btn btn-primary btn-block btn-lg mt-2">Login</button>
    </form>

    <p class="auth-footer-text">
      Don't have an account? <a href="<%= request.getContextPath() %>/register">Register here</a>
    </p>
    <p class="auth-footer-text mt-1" style="font-size:.78rem; color:#9aa5b4;">
      Admin: admin@shop.com / admin123
    </p>
  </div>
</div>

<footer>
  &copy; 2025 <strong>ShopEase</strong>. Advanced Java Project.
</footer>
</body>
</html>

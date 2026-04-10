<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Register – ShopEase</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<div class="auth-wrapper">
  <div class="auth-card" style="max-width:500px">
    <div class="auth-logo">Shop<span>Ease</span></div>
    <p class="auth-sub">Create your account today</p>

    <h2>Create Account</h2>

    <% if (request.getAttribute("error") != null) { %>
      <div class="alert alert-danger">⚠ <%= request.getAttribute("error") %></div>
    <% } %>

    <form action="<%= request.getContextPath() %>/register" method="post">

      <div class="form-group">
        <label class="form-label">Full Name *</label>
        <input type="text" name="fullName" class="form-control" required
               placeholder="John Doe"
               value="<%= request.getAttribute("fullName") != null ? request.getAttribute("fullName") : "" %>">
      </div>

      <div class="form-group">
        <label class="form-label">Email Address *</label>
        <input type="email" name="email" class="form-control" required
               placeholder="you@example.com"
               value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>">
      </div>

      <div class="form-group">
        <label class="form-label">Password *</label>
        <input type="password" name="password" class="form-control" required
               placeholder="At least 6 characters" minlength="6">
        <span class="form-text">Minimum 6 characters</span>
      </div>

      <div class="form-row">
        <div class="form-group">
          <label class="form-label">Phone Number</label>
          <input type="tel" name="phone" class="form-control"
                 placeholder="+91 9999999999"
                 value="<%= request.getAttribute("phone") != null ? request.getAttribute("phone") : "" %>">
        </div>
      </div>

      <div class="form-group">
        <label class="form-label">Delivery Address</label>
        <textarea name="address" class="form-control" rows="2"
                  placeholder="Street, City, State, PIN"><%= request.getAttribute("address") != null ? request.getAttribute("address") : "" %></textarea>
      </div>

      <button type="submit" class="btn btn-amber btn-block btn-lg mt-2">Create Account</button>
    </form>

    <p class="auth-footer-text">
      Already have an account? <a href="<%= request.getContextPath() %>/login">Login here</a>
    </p>
  </div>
</div>

<footer>
  &copy; 2025 <strong>ShopEase</strong>. Advanced Java Project.
</footer>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.shop.model.Order, com.shop.model.Payment" %>
<%
    String     ctx          = request.getContextPath();
    Integer    totalProd    = (Integer)    request.getAttribute("totalProducts");
    Integer    totalUsers   = (Integer)    request.getAttribute("totalUsers");
    Integer    totalOrders  = (Integer)    request.getAttribute("totalOrders");
    List<Order> recentOrders = (List<Order>) request.getAttribute("recentOrders");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Dashboard – ShopEase Admin</title>
  <link rel="stylesheet" href="<%= ctx %>/css/style.css">
</head>
<body>
<div class="admin-layout">
  <%@ include file="/WEB-INF/admin-sidebar.jsp" %>

  <main class="admin-main">
    <h2 style="margin-bottom:1.75rem;color:var(--navy)">📊 Dashboard</h2>

    <!-- Stat Cards -->
    <div class="stats-grid">
      <div class="stat-card">
        <div class="stat-icon blue">📦</div>
        <div>
          <div class="stat-label">Total Products</div>
          <div class="stat-value"><%= totalProd != null ? totalProd : 0 %></div>
        </div>
      </div>
      <div class="stat-card">
        <div class="stat-icon green">👥</div>
        <div>
          <div class="stat-label">Customers</div>
          <div class="stat-value"><%= totalUsers != null ? totalUsers : 0 %></div>
        </div>
      </div>
      <div class="stat-card">
        <div class="stat-icon amber">🧾</div>
        <div>
          <div class="stat-label">Total Orders</div>
          <div class="stat-value"><%= totalOrders != null ? totalOrders : 0 %></div>
        </div>
      </div>
      <div class="stat-card">
        <div class="stat-icon purple">✅</div>
        <div>
          <div class="stat-label">Store Status</div>
          <div class="stat-value" style="font-size:1rem;color:var(--green)">Online</div>
        </div>
      </div>
    </div>

    <!-- Quick Actions -->
    <div style="display:flex;gap:1rem;flex-wrap:wrap;margin-bottom:2rem">
      <a href="<%= ctx %>/admin/products?action=add" class="btn btn-amber">+ Add Product</a>
      <a href="<%= ctx %>/admin/orders"              class="btn btn-primary">View All Orders</a>
      <a href="<%= ctx %>/admin/users"               class="btn btn-outline">View Customers</a>
    </div>

    <!-- Recent Orders -->
    <div class="card">
      <div class="card-body" style="padding-bottom:0">
        <h3 style="margin-bottom:1.25rem;color:var(--navy)">🕐 Recent Orders</h3>
      </div>
      <% if (recentOrders == null || recentOrders.isEmpty()) { %>
        <div class="card-body text-center" style="color:var(--gray-400)">No orders yet.</div>
      <% } else { %>
        <div class="table-wrapper" style="border-radius:0">
          <table>
            <thead>
              <tr>
                <th>Order ID</th>
                <th>Customer</th>
                <th>Amount</th>
                <th>Status</th>
                <th>Date</th>
              </tr>
            </thead>
            <tbody>
              <% for (Order o : recentOrders) { %>
                <tr>
                  <td><strong>#<%= o.getOrderId() %></strong></td>
                  <td>
                    <%= o.getCustomerName() %><br>
                    <small style="color:var(--gray-400)"><%= o.getCustomerEmail() %></small>
                  </td>
                  <td><strong>₹<%= String.format("%,.2f", o.getTotalAmount()) %></strong></td>
                  <td><span class="status-badge status-<%= o.getStatus().toLowerCase() %>"><%= o.getStatus() %></span></td>
                  <td style="font-size:.82rem;color:var(--gray-600)"><%= o.getCreatedAt() %></td>
                </tr>
              <% } %>
            </tbody>
          </table>
        </div>
        <div style="padding:1rem 1.5rem">
          <a href="<%= ctx %>/admin/orders" style="font-size:.85rem;color:var(--navy);font-weight:600">View all orders →</a>
        </div>
      <% } %>
    </div>
  </main>
</div>

<style>
  body { background: var(--gray-100); }
  footer { display: none; }
</style>
</body>
</html>

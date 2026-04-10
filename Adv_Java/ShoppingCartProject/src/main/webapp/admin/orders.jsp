<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.shop.model.Order, com.shop.model.Payment" %>
<%
    List<Order> orders = (List<Order>) request.getAttribute("orders");
    String ctx = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Orders – ShopEase Admin</title>
  <link rel="stylesheet" href="<%= ctx %>/css/style.css">
</head>
<body>
<div class="admin-layout">
  <%@ include file="/WEB-INF/admin-sidebar.jsp" %>

  <main class="admin-main">
    <div style="display:flex;justify-content:space-between;align-items:center;margin-bottom:1.75rem">
      <h2 style="color:var(--navy)">🧾 All Orders</h2>
      <span style="background:var(--gray-200);padding:.35rem .85rem;border-radius:20px;font-size:.85rem;font-weight:600">
        Total: <%= orders != null ? orders.size() : 0 %>
      </span>
    </div>

    <% if (request.getAttribute("error") != null) { %>
      <div class="alert alert-danger">⚠ <%= request.getAttribute("error") %></div>
    <% } %>

    <div class="table-wrapper">
      <table>
        <thead>
          <tr>
            <th>Order ID</th>
            <th>Customer</th>
            <th>Amount</th>
            <th>Status</th>
            <th>Payment Card</th>
            <th>Cardholder</th>
            <th>Pay Status</th>
            <th>Date</th>
          </tr>
        </thead>
        <tbody>
          <% if (orders == null || orders.isEmpty()) { %>
            <tr>
              <td colspan="8" class="text-center" style="padding:2.5rem;color:var(--gray-400)">
                No orders placed yet.
              </td>
            </tr>
          <% } else { for (Order o : orders) {
                Payment pay = o.getPayment(); %>
            <tr>
              <td><strong>#<%= o.getOrderId() %></strong></td>
              <td>
                <strong><%= o.getCustomerName() %></strong><br>
                <small style="color:var(--gray-400)"><%= o.getCustomerEmail() %></small>
              </td>
              <td><strong style="color:var(--navy)">₹<%= String.format("%,.2f", o.getTotalAmount()) %></strong></td>
              <td>
                <span class="status-badge status-<%= o.getStatus().toLowerCase() %>">
                  <%= o.getStatus() %>
                </span>
              </td>
              <td style="font-size:.85rem">
                <%= pay != null ? pay.getMaskedCard() : "–" %>
              </td>
              <td style="font-size:.85rem">
                <%= pay != null ? pay.getCardholderName() : "–" %>
              </td>
              <td>
                <% if (pay != null) { %>
                  <span class="status-badge <%= "SUCCESS".equals(pay.getStatus()) ? "status-confirmed" : "status-cancelled" %>">
                    <%= pay.getStatus() %>
                  </span>
                <% } else { %>–<% } %>
              </td>
              <td style="font-size:.82rem;color:var(--gray-600)">
                <%= o.getCreatedAt() != null ? o.getCreatedAt().toString().substring(0,16) : "–" %>
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

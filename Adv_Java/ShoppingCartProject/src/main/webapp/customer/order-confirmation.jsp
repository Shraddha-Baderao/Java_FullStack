<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.shop.model.Order, com.shop.model.OrderItem, com.shop.model.Payment" %>
<%
    Order   order   = (Order)   request.getAttribute("order");
    Payment payment = order != null ? order.getPayment() : null;
    String  ctx     = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Order Confirmed – ShopEase</title>
  <link rel="stylesheet" href="<%= ctx %>/css/style.css">
  <style>
    @keyframes pop { 0%{transform:scale(0)} 80%{transform:scale(1.1)} 100%{transform:scale(1)} }
    .check-circle { animation: pop .5s ease forwards; }
  </style>
</head>
<body>
<%@ include file="/WEB-INF/navbar.jsp" %>

<% if (order == null) { %>
  <div class="container text-center" style="padding:4rem">
    <h2>Order not found.</h2>
    <a href="<%= ctx %>/products" class="btn btn-primary mt-2">Back to Products</a>
  </div>
<% } else { %>

<div class="confirmation-hero">
  <div class="check-circle">✓</div>
  <h1>Order Confirmed!</h1>
  <p>Thank you for your purchase. Your order #<strong><%= order.getOrderId() %></strong> is confirmed.</p>
</div>

<div class="container" style="padding:2rem 1rem 3rem">

  <div class="order-detail-grid">

    <!-- Order Items -->
    <div class="card">
      <div class="card-body">
        <h3 style="margin-bottom:1rem;color:var(--navy)">📦 Order Items</h3>
        <% if (order.getItems() != null) { for (OrderItem item : order.getItems()) { %>
          <div style="display:flex;justify-content:space-between;padding:.65rem 0;border-bottom:1px solid var(--gray-200);font-size:.9rem">
            <div>
              <strong><%= item.getProductName() %></strong>
              <span style="color:var(--gray-400);margin-left:.5rem">× <%= item.getQuantity() %></span>
            </div>
            <strong>₹<%= String.format("%,.2f", item.getSubtotal()) %></strong>
          </div>
        <% } } %>
        <div style="display:flex;justify-content:space-between;padding:.9rem 0 0;font-weight:700;font-size:1.1rem;color:var(--navy)">
          <span>Total Paid</span>
          <span>₹<%= String.format("%,.2f", order.getTotalAmount()) %></span>
        </div>
      </div>
    </div>

    <!-- Payment & Order Info -->
    <div>
      <div class="card mb-2" style="margin-bottom:1rem">
        <div class="card-body">
          <h3 style="margin-bottom:1rem;color:var(--navy)">💳 Payment Info</h3>
          <% if (payment != null) { %>
            <table style="width:100%;font-size:.9rem">
              <tr><td style="padding:.4rem 0;color:var(--gray-600)">Payment ID</td>
                  <td style="font-weight:600">#<%= payment.getPaymentId() %></td></tr>
              <tr><td style="padding:.4rem 0;color:var(--gray-600)">Card</td>
                  <td style="font-weight:600"><%= payment.getMaskedCard() %></td></tr>
              <tr><td style="padding:.4rem 0;color:var(--gray-600)">Cardholder</td>
                  <td style="font-weight:600"><%= payment.getCardholderName() %></td></tr>
              <tr><td style="padding:.4rem 0;color:var(--gray-600)">Status</td>
                  <td><span class="status-badge status-confirmed"><%= payment.getStatus() %></span></td></tr>
              <tr><td style="padding:.4rem 0;color:var(--gray-600)">Amount</td>
                  <td style="font-weight:700;color:var(--navy)">₹<%= String.format("%,.2f", payment.getAmount()) %></td></tr>
            </table>
          <% } %>
        </div>
      </div>

      <div class="card">
        <div class="card-body">
          <h3 style="margin-bottom:1rem;color:var(--navy)">🗒 Order Details</h3>
          <table style="width:100%;font-size:.9rem">
            <tr><td style="padding:.4rem 0;color:var(--gray-600)">Order ID</td>
                <td style="font-weight:600">#<%= order.getOrderId() %></td></tr>
            <tr><td style="padding:.4rem 0;color:var(--gray-600)">Status</td>
                <td><span class="status-badge status-confirmed"><%= order.getStatus() %></span></td></tr>
            <tr><td style="padding:.4rem 0;color:var(--gray-600)">Date</td>
                <td style="font-weight:600"><%= order.getCreatedAt() %></td></tr>
          </table>
        </div>
      </div>
    </div>

  </div>

  <div class="text-center" style="margin-top:2rem">
    <a href="<%= ctx %>/products" class="btn btn-amber btn-lg">🛍 Continue Shopping</a>
  </div>
</div>

<% } %>

<footer>&copy; 2025 <strong>ShopEase</strong>. Advanced Java Project.</footer>
</body>
</html>

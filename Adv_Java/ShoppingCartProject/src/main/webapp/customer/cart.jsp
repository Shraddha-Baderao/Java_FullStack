<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.shop.model.CartItem" %>
<%
    List<CartItem> cartItems = (List<CartItem>) request.getAttribute("cartItems");
    Object cartTotal = request.getAttribute("cartTotal");
    String ctx = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>My Cart – ShopEase</title>
  <link rel="stylesheet" href="<%= ctx %>/css/style.css">
</head>
<body>
<%@ include file="/WEB-INF/navbar.jsp" %>

<div class="page-header">
  <div class="container">
    <h1>🛒 My Cart</h1>
    <p>Review your selected items before checkout</p>
  </div>
</div>

<div class="container" style="padding-bottom:3rem">

  <% if (request.getParameter("removed") != null) { %>
    <div class="alert alert-success">✓ Item removed from cart.</div>
  <% } %>
  <% if (request.getParameter("empty") != null) { %>
    <div class="alert alert-info">ℹ Your cart is empty. Add some products first!</div>
  <% } %>

  <% if (cartItems == null || cartItems.isEmpty()) { %>
    <div class="text-center" style="padding:5rem 1rem">
      <div style="font-size:5rem">🛒</div>
      <h2 style="margin:.75rem 0 .5rem">Your cart is empty</h2>
      <p>Looks like you haven't added anything yet.</p>
      <a href="<%= ctx %>/products" class="btn btn-amber mt-2 btn-lg">Browse Products</a>
    </div>

  <% } else { %>
    <div class="cart-layout">
      <!-- Cart Items -->
      <div>
        <% for (CartItem item : cartItems) { %>
          <div class="cart-item">
            <div class="cart-item-img">🛒</div>
            <div>
              <div class="cart-item-name"><%= item.getProductName() %></div>
              <div class="cart-item-price">
                ₹<%= String.format("%,.2f", item.getUnitPrice()) %> × <%= item.getQuantity() %>
              </div>
            </div>
            <div>
              <div class="cart-item-subtotal mb-2">₹<%= String.format("%,.2f", item.getSubtotal()) %></div>
              <form action="<%= ctx %>/cart" method="post">
                <input type="hidden" name="action" value="remove">
                <input type="hidden" name="cartId" value="<%= item.getCartId() %>">
                <button type="submit" class="btn btn-danger btn-sm">🗑 Remove</button>
              </form>
            </div>
          </div>
        <% } %>
        <a href="<%= ctx %>/products" class="btn btn-outline mt-2">← Continue Shopping</a>
      </div>

      <!-- Summary -->
      <div class="cart-summary">
        <h3 style="margin-bottom:1rem">Order Summary</h3>
        <div class="cart-summary-row">
          <span>Subtotal (<%= cartItems.size() %> item<%= cartItems.size()!=1?"s":"" %>)</span>
          <span>₹<%= String.format("%,.2f", cartTotal) %></span>
        </div>
        <div class="cart-summary-row">
          <span>Shipping</span>
          <span style="color:var(--green);font-weight:600">FREE</span>
        </div>
        <div class="cart-summary-row">
          <span>Tax (GST 18%)</span>
          <% double tax = ((Number)cartTotal).doubleValue() * 0.18; %>
          <span>₹<%= String.format("%,.2f", tax) %></span>
        </div>
        <div class="cart-summary-row total">
          <span>Total</span>
          <% double total = ((Number)cartTotal).doubleValue() + tax; %>
          <span>₹<%= String.format("%,.2f", total) %></span>
        </div>
        <a href="<%= ctx %>/checkout" class="btn btn-amber btn-block btn-lg mt-2">
          🔒 Proceed to Checkout
        </a>
        <p style="font-size:.75rem;color:var(--gray-400);text-align:center;margin-top:.75rem">
          🔐 Secure & encrypted payment
        </p>
      </div>
    </div>
  <% } %>
</div>

<footer>&copy; 2025 <strong>ShopEase</strong>. Advanced Java Project.</footer>
</body>
</html>

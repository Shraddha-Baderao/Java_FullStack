<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.shop.model.CartItem" %>
<%
    List<CartItem> cartItems = (List<CartItem>) request.getAttribute("cartItems");
    Object cartTotal = request.getAttribute("cartTotal");
    String ctx = request.getContextPath();
    double gst   = cartTotal != null ? ((Number)cartTotal).doubleValue() * 0.18 : 0;
    double grand = cartTotal != null ? ((Number)cartTotal).doubleValue() + gst   : 0;
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Checkout – ShopEase</title>
  <link rel="stylesheet" href="<%= ctx %>/css/style.css">
</head>
<body>
<%@ include file="/WEB-INF/navbar.jsp" %>

<div class="page-header">
  <div class="container">
    <h1>🔒 Secure Checkout</h1>
    <p>Complete your purchase safely</p>
  </div>
</div>

<div class="container" style="padding-bottom:3rem">

  <% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-danger">⚠ <%= request.getAttribute("error") %></div>
  <% } %>

  <div class="checkout-layout">
    <!-- Payment Form -->
    <div class="payment-card">
      <h3>💳 Payment Details</h3>
      <div class="card-icons">💳 🏦 📱</div>

      <form action="<%= ctx %>/checkout" method="post" id="paymentForm">

        <div class="form-group">
          <label class="form-label">Cardholder Name *</label>
          <input type="text" name="cardholderName" class="form-control" required
                 placeholder="As printed on card"
                 value="<%= request.getAttribute("cardholderName") != null ? request.getAttribute("cardholderName") : "" %>">
        </div>

        <div class="form-group">
          <label class="form-label">Card Number *</label>
          <input type="text" name="cardNumber" id="cardNumber" class="form-control" required
                 placeholder="1234 5678 9012 3456"
                 maxlength="19" oninput="formatCard(this)">
        </div>

        <div class="form-row">
          <div class="form-group">
            <label class="form-label">Expiry Date *</label>
            <input type="text" name="expiryDate" class="form-control" required
                   placeholder="MM/YY" maxlength="5"
                   oninput="formatExpiry(this)"
                   value="<%= request.getAttribute("expiryDate") != null ? request.getAttribute("expiryDate") : "" %>">
          </div>
          <div class="form-group">
            <label class="form-label">CVV *</label>
            <input type="password" name="cvv" class="form-control" required
                   placeholder="•••" maxlength="4" pattern="\d{3,4}">
          </div>
        </div>

        <div style="background:var(--gray-100);border-radius:var(--radius-sm);padding:1rem;margin:1rem 0">
          <p style="font-size:.8rem;color:var(--gray-600)">
            🔐 Your payment information is encrypted and secure. We never store your full card details.
          </p>
        </div>

        <button type="submit" class="btn btn-amber btn-block btn-lg">
          💰 Pay ₹<%= String.format("%,.2f", grand) %>
        </button>
      </form>
    </div>

    <!-- Order Summary -->
    <div>
      <div class="card" style="margin-bottom:1.5rem">
        <div class="card-body">
          <h4 style="margin-bottom:1rem;color:var(--navy)">Order Summary</h4>
          <% if (cartItems != null) { for (CartItem item : cartItems) { %>
            <div style="display:flex;justify-content:space-between;padding:.5rem 0;border-bottom:1px solid var(--gray-200);font-size:.88rem">
              <span><strong><%= item.getProductName() %></strong><br>
                <span style="color:var(--gray-400)">Qty: <%= item.getQuantity() %></span>
              </span>
              <span style="font-weight:600">₹<%= String.format("%,.2f", item.getSubtotal()) %></span>
            </div>
          <% } } %>
          <div style="display:flex;justify-content:space-between;padding:.6rem 0;font-size:.9rem">
            <span>Subtotal</span><span>₹<%= String.format("%,.2f", cartTotal) %></span>
          </div>
          <div style="display:flex;justify-content:space-between;padding:.6rem 0;font-size:.9rem">
            <span>GST (18%)</span><span>₹<%= String.format("%,.2f", gst) %></span>
          </div>
          <div style="display:flex;justify-content:space-between;padding:.75rem 0;font-weight:700;font-size:1.05rem;color:var(--navy);border-top:2px solid var(--gray-200);margin-top:.25rem">
            <span>Total</span><span>₹<%= String.format("%,.2f", grand) %></span>
          </div>
        </div>
      </div>

      <a href="<%= ctx %>/cart" class="btn btn-outline btn-block">← Edit Cart</a>
    </div>
  </div>
</div>

<footer>&copy; 2025 <strong>ShopEase</strong>. Advanced Java Project.</footer>

<script>
function formatCard(input) {
  let v = input.value.replace(/\D/g, '').substring(0, 16);
  input.value = v.match(/.{1,4}/g)?.join(' ') || v;
}
function formatExpiry(input) {
  let v = input.value.replace(/\D/g, '').substring(0, 4);
  if (v.length >= 2) v = v.substring(0, 2) + '/' + v.substring(2);
  input.value = v;
}
document.getElementById('paymentForm').addEventListener('submit', function(e) {
  const btn = this.querySelector('button[type=submit]');
  btn.textContent = '⏳ Processing…';
  btn.disabled = true;
});
</script>
</body>
</html>

<%
    User navUser = null;
    HttpSession navSession = request.getSession(false);
    if (navSession != null) navUser = (User) navSession.getAttribute("user");

    String contextPath = request.getContextPath();  // ✅ FIXED
    String uri = request.getRequestURI();

    Integer cartCount = (Integer) request.getAttribute("cartCount");
    if (cartCount == null) cartCount = 0;
%>

<nav class="navbar">
  <div class="navbar-inner">
    <a href="<%= contextPath %>/products" class="navbar-brand">Shop<span>Ease</span></a>

    <ul class="navbar-nav">
      <li>
        <a href="<%= contextPath %>/products"
           class="nav-link <%= uri.contains("/products") ? "active" : "" %>">🛍 Products</a>
      </li>

      <% if (navUser == null) { %>
        <li><a href="<%= contextPath %>/login" class="nav-link">Login</a></li>
        <li><a href="<%= contextPath %>/register" class="nav-link btn-nav">Register</a></li>

      <% } else if (!navUser.isAdmin()) { %>
        <li>
          <a href="<%= contextPath %>/cart" class="nav-link cart-badge">
            🛒 Cart
            <% if (cartCount > 0) { %>
              <span class="badge"><%= cartCount %></span>
            <% } %>
          </a>
        </li>

        <li>
          <span class="nav-link" style="color:rgba(255,255,255,.5)">
            Hi, <%= navUser.getFullName().split(" ")[0] %>
          </span>
        </li>

        <li><a href="<%= contextPath %>/logout" class="nav-link btn-nav">Logout</a></li>

      <% } else { %>
        <li>
          <a href="<%= contextPath %>/admin/dashboard"
             class="nav-link <%= uri.contains("admin") ? "active" : "" %>">⚙ Admin</a>
        </li>
        <li><a href="<%= contextPath %>/logout" class="nav-link btn-nav">Logout</a></li>
      <% } %>
    </ul>
  </div>
</nav>
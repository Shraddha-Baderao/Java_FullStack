<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.shop.model.User" %>
<%
    List<User> users = (List<User>) request.getAttribute("users");
    String ctx = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Customers – ShopEase Admin</title>
  <link rel="stylesheet" href="<%= ctx %>/css/style.css">
</head>
<body>
<div class="admin-layout">
  <%@ include file="/WEB-INF/admin-sidebar.jsp" %>

  <main class="admin-main">
    <div style="display:flex;justify-content:space-between;align-items:center;margin-bottom:1.75rem">
      <h2 style="color:var(--navy)">👥 Registered Customers</h2>
      <span style="background:var(--gray-200);padding:.35rem .85rem;border-radius:20px;font-size:.85rem;font-weight:600">
        Total: <%= users != null ? users.size() : 0 %>
      </span>
    </div>

    <% if (request.getAttribute("error") != null) { %>
      <div class="alert alert-danger">⚠ <%= request.getAttribute("error") %></div>
    <% } %>

    <div class="table-wrapper">
      <table>
        <thead>
          <tr>
            <th>#</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Address</th>
            <th>Joined</th>
          </tr>
        </thead>
        <tbody>
          <% if (users == null || users.isEmpty()) { %>
            <tr>
              <td colspan="6" class="text-center" style="padding:2.5rem;color:var(--gray-400)">
                No registered customers yet.
              </td>
            </tr>
          <% } else { int i = 1; for (User u : users) { %>
            <tr>
              <td style="color:var(--gray-400);font-size:.8rem"><%= i++ %></td>
              <td>
                <div style="display:flex;align-items:center;gap:.6rem">
                  <div style="width:34px;height:34px;border-radius:50%;background:var(--navy);color:white;display:flex;align-items:center;justify-content:center;font-size:.85rem;font-weight:700;flex-shrink:0">
                    <%= u.getFullName().charAt(0) %>
                  </div>
                  <strong><%= u.getFullName() %></strong>
                </div>
              </td>
              <td><a href="mailto:<%= u.getEmail() %>" style="color:var(--navy)"><%= u.getEmail() %></a></td>
              <td><%= u.getPhone() != null && !u.getPhone().isEmpty() ? u.getPhone() : "–" %></td>
              <td style="max-width:200px;font-size:.82rem;color:var(--gray-600)">
                <%= u.getAddress() != null && !u.getAddress().isEmpty()
                    ? (u.getAddress().length() > 50 ? u.getAddress().substring(0,50)+"…" : u.getAddress())
                    : "–" %>
              </td>
              <td style="font-size:.82rem;color:var(--gray-600)"><%= u.getCreatedAt() != null ? u.getCreatedAt().toString().substring(0,10) : "–" %></td>
            </tr>
          <% } } %>
        </tbody>
      </table>
    </div>
  </main>
</div>
</body>
</html>

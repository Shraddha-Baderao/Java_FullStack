<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%
    Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
    String  message    = (String)  request.getAttribute("javax.servlet.error.message");
    String  ctx        = request.getContextPath();
    boolean is404 = statusCode != null && statusCode == 404;
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title><%= is404 ? "404 Not Found" : "Error" %> – ShopEase</title>
  <link rel="stylesheet" href="<%= ctx %>/css/style.css">
</head>
<body>
<div style="min-height:100vh;display:flex;align-items:center;justify-content:center;
            background:linear-gradient(135deg,var(--navy) 0%,var(--navy-mid) 100%);padding:2rem">
  <div class="text-center" style="color:white">
    <div style="font-size:6rem;margin-bottom:1rem"><%= is404 ? "🔍" : "⚠" %></div>
    <h1 style="color:var(--amber);font-size:4rem;margin-bottom:.5rem">
      <%= statusCode != null ? statusCode : "Error" %>
    </h1>
    <h2 style="color:white;margin-bottom:1rem">
      <%= is404 ? "Page Not Found" : "Something Went Wrong" %>
    </h2>
    <p style="color:rgba(255,255,255,.65);max-width:400px;margin:0 auto 2rem">
      <%= is404
          ? "The page you're looking for doesn't exist or has been moved."
          : (message != null ? message : "An unexpected error occurred. Please try again.") %>
    </p>
    <a href="<%= ctx %>/products" class="btn btn-amber btn-lg">🏠 Back to Home</a>
  </div>
</div>
</body>
</html>

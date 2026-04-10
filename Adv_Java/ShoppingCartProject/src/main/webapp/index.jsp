<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.shop.model.User" %>
<%
    HttpSession s = request.getSession(false);
    if (s != null && s.getAttribute("user") != null) {
        User u = (User) s.getAttribute("user");
        response.sendRedirect(request.getContextPath() + (u.isAdmin() ? "/admin/dashboard" : "/products"));
    } else {
        response.sendRedirect(request.getContextPath() + "/products");
    }
%>

package com.shop.service;

import com.shop.dao.UserDAO;
import com.shop.model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Service layer for User-related business logic.
 */
public class UserService {

    private final UserDAO userDAO = new UserDAO();

    public String register(String fullName, String email, String password,
                           String phone, String address) throws SQLException {
        if (fullName == null || fullName.trim().isEmpty()) return "Full name is required.";
        if (email    == null || email.trim().isEmpty())    return "Email is required.";
        if (password == null || password.length() < 6)    return "Password must be at least 6 characters.";
        if (userDAO.emailExists(email.trim()))             return "Email is already registered.";

        User user = new User(fullName.trim(), email.trim().toLowerCase(),
                             password, phone, address);
        return userDAO.registerUser(user) ? null : "Registration failed. Please try again.";
    }

    public User login(String email, String password) throws SQLException {
        if (email == null || password == null) return null;
        return userDAO.login(email.trim().toLowerCase(), password);
    }

    public List<User> getAllCustomers() throws SQLException {
        return userDAO.getAllCustomers();
    }

    public User getUserById(int id) throws SQLException {
        return userDAO.getUserById(id);
    }
}

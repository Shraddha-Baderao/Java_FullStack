-- ============================================================
--  Online Shopping Cart System - Database Schema
--  Database: MySQL
-- ============================================================

CREATE DATABASE IF NOT EXISTS shopping_cart_db;
USE shopping_cart_db;

-- ---------------------------------------------------------------
-- 1. USERS TABLE
-- ---------------------------------------------------------------
CREATE TABLE IF NOT EXISTS users (
    user_id       INT AUTO_INCREMENT PRIMARY KEY,
    full_name     VARCHAR(100) NOT NULL,
    email         VARCHAR(100) NOT NULL UNIQUE,
    password      VARCHAR(255) NOT NULL,
    phone         VARCHAR(15),
    address       TEXT,
    role          ENUM('CUSTOMER','ADMIN') DEFAULT 'CUSTOMER',
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ---------------------------------------------------------------
-- 2. CATEGORIES TABLE
-- ---------------------------------------------------------------
CREATE TABLE IF NOT EXISTS categories (
    category_id   INT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(100) NOT NULL,
    description   TEXT
);

-- ---------------------------------------------------------------
-- 3. PRODUCTS TABLE
-- ---------------------------------------------------------------
CREATE TABLE IF NOT EXISTS products (
    product_id    INT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(150) NOT NULL,
    description   TEXT,
    price         DECIMAL(10,2) NOT NULL,
    category_id   INT,
    quantity      INT DEFAULT 0,
    image_url     VARCHAR(255),
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES categories(category_id) ON DELETE SET NULL
);

-- ---------------------------------------------------------------
-- 4. CART TABLE
-- ---------------------------------------------------------------
CREATE TABLE IF NOT EXISTS cart (
    cart_id       INT AUTO_INCREMENT PRIMARY KEY,
    user_id       INT NOT NULL,
    product_id    INT NOT NULL,
    quantity      INT DEFAULT 1,
    added_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id)    REFERENCES users(user_id)    ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE CASCADE
);

-- ---------------------------------------------------------------
-- 5. ORDERS TABLE
-- ---------------------------------------------------------------
CREATE TABLE IF NOT EXISTS orders (
    order_id      INT AUTO_INCREMENT PRIMARY KEY,
    user_id       INT NOT NULL,
    total_amount  DECIMAL(10,2) NOT NULL,
    status        ENUM('PENDING','CONFIRMED','SHIPPED','DELIVERED','CANCELLED') DEFAULT 'CONFIRMED',
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- ---------------------------------------------------------------
-- 6. ORDER ITEMS TABLE
-- ---------------------------------------------------------------
CREATE TABLE IF NOT EXISTS order_items (
    item_id       INT AUTO_INCREMENT PRIMARY KEY,
    order_id      INT NOT NULL,
    product_id    INT NOT NULL,
    quantity      INT NOT NULL,
    unit_price    DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (order_id)   REFERENCES orders(order_id)   ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE CASCADE
);

-- ---------------------------------------------------------------
-- 7. PAYMENTS TABLE
-- ---------------------------------------------------------------
CREATE TABLE IF NOT EXISTS payments (
    payment_id        INT AUTO_INCREMENT PRIMARY KEY,
    order_id          INT NOT NULL UNIQUE,
    cardholder_name   VARCHAR(100) NOT NULL,
    card_last4        CHAR(4) NOT NULL,
    expiry_date       VARCHAR(7) NOT NULL,
    amount            DECIMAL(10,2) NOT NULL,
    status            ENUM('SUCCESS','FAILED') DEFAULT 'SUCCESS',
    paid_at           TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE
);

-- ---------------------------------------------------------------
-- SEED DATA
-- ---------------------------------------------------------------
-- Default Admin (password: admin123)
INSERT INTO users (full_name, email, password, role)
VALUES ('Administrator', 'admin@shop.com', 'admin123', 'ADMIN');

-- Categories
INSERT INTO categories (name, description) VALUES
('Electronics',  'Gadgets, devices, and electronic accessories'),
('Clothing',     'Men, Women and Kids apparel'),
('Books',        'Academic, fiction and non-fiction books'),
('Home & Kitchen','Furniture, appliances and kitchen items'),
('Sports',       'Sports equipment and fitness accessories');

-- Sample Products
INSERT INTO products (name, description, price, category_id, quantity, image_url) VALUES
('Wireless Bluetooth Headphones', 'Over-ear noise cancelling headphones with 30hr battery', 2999.00, 1, 50, 'headphones.jpg'),
('Smartphone 5G',                 'Latest 5G smartphone with 128GB storage and AMOLED display', 24999.00, 1, 30, 'smartphone.jpg'),
('USB-C Laptop Charger',          '65W fast charging USB-C adapter, universal compatibility', 1499.00, 1, 100,'charger.jpg'),
('Men Cotton T-Shirt',            'Premium 100% cotton round-neck t-shirt', 499.00, 2, 200,'tshirt.jpg'),
('Women Kurti Set',               'Printed cotton kurti with palazzo, festive collection', 1299.00, 2, 150,'kurti.jpg'),
('Java Programming Book',         'Complete guide to Java SE & EE with project examples', 699.00, 3, 80, 'java_book.jpg'),
('Data Structures & Algorithms',  'Comprehensive DSA book with practice problems', 599.00, 3, 60, 'dsa_book.jpg'),
('Non-stick Cookware Set',        '5-piece aluminium non-stick cookware set with lids', 2499.00, 4, 40, 'cookware.jpg'),
('Yoga Mat',                      'Anti-slip eco-friendly yoga mat 6mm thickness', 899.00, 5, 120,'yoga_mat.jpg'),
('Dumbbell Set 5kg',              'Pair of rubber coated dumbbells for home workouts', 1799.00, 5, 70, 'dumbbells.jpg');

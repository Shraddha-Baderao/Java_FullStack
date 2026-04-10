# 🛒 Online Shopping Cart System
### Advanced Java Final Project — JSP, Servlets & JDBC

---

## 📌 Project Overview

A full-stack **Dynamic Web Application** built with Java EE technologies implementing an
end-to-end online shopping experience with separate Customer and Admin portals.

| Technology     | Details                                  |
|----------------|------------------------------------------|
| **Frontend**   | HTML5, CSS3, JSP (Java Server Pages)     |
| **Backend**    | Java Servlets (Java EE 4.0)              |
| **Database**   | MySQL 8.x via JDBC                       |
| **Server**     | Apache Tomcat 9.x                        |
| **Build Tool** | Maven 3.x                                |
| **IDE**        | Eclipse IDE (Dynamic Web Project)        |
| **Architecture** | MVC — Controller → Service → DAO → JSP |

---

## 🏗 Project Structure

```
ShoppingCartProject/
├── pom.xml                                      ← Maven build config
├── sql/
│   └── schema.sql                               ← Database schema + seed data
└── src/main/
    ├── java/com/shop/
    │   ├── model/                               ← Entity / POJO classes
    │   │   ├── User.java
    │   │   ├── Product.java
    │   │   ├── Category.java
    │   │   ├── CartItem.java
    │   │   ├── Order.java
    │   │   ├── OrderItem.java
    │   │   └── Payment.java
    │   ├── dao/                                 ← Data Access Objects (JDBC)
    │   │   ├── UserDAO.java
    │   │   ├── ProductDAO.java
    │   │   ├── CartDAO.java
    │   │   └── OrderDAO.java
    │   ├── service/                             ← Business Logic Layer
    │   │   ├── UserService.java
    │   │   ├── ProductService.java
    │   │   ├── CartService.java
    │   │   └── OrderService.java
    │   ├── servlet/                             ← Controller Layer (Servlets)
    │   │   ├── RegisterServlet.java             @WebServlet("/register")
    │   │   ├── LoginServlet.java                @WebServlet("/login")
    │   │   ├── LogoutServlet.java               @WebServlet("/logout")
    │   │   ├── ProductServlet.java              @WebServlet("/products")
    │   │   ├── CartServlet.java                 @WebServlet("/cart")
    │   │   ├── CheckoutServlet.java             @WebServlet("/checkout")
    │   │   ├── OrderConfirmationServlet.java    @WebServlet("/order-confirmation")
    │   │   ├── AdminDashboardServlet.java       @WebServlet("/admin/dashboard")
    │   │   ├── AdminProductServlet.java         @WebServlet("/admin/products")
    │   │   ├── AdminUsersServlet.java           @WebServlet("/admin/users")
    │   │   └── AdminOrdersServlet.java          @WebServlet("/admin/orders")
    │   └── util/
    │       └── DBConnection.java                ← JDBC connection utility
    └── webapp/
        ├── index.jsp                            ← Root redirect
        ├── error.jsp                            ← 404 / 500 error page
        ├── css/
        │   └── style.css                        ← Main stylesheet
        ├── WEB-INF/
        │   ├── web.xml                          ← Deployment descriptor
        │   ├── navbar.jsp                       ← Shared navigation bar
        │   └── admin-sidebar.jsp                ← Admin sidebar include
        ├── customer/                            ← Customer-facing pages
        │   ├── login.jsp
        │   ├── register.jsp
        │   ├── products.jsp
        │   ├── cart.jsp
        │   ├── checkout.jsp
        │   └── order-confirmation.jsp
        └── admin/                               ← Admin panel pages
            ├── dashboard.jsp
            ├── products.jsp
            ├── add-product.jsp
            ├── edit-product.jsp
            ├── users.jsp
            └── orders.jsp
```

---

## ⚙️ Setup Instructions

### Step 1 — Prerequisites
- ✅ JDK 11 or higher
- ✅ Apache Tomcat 9.x
- ✅ MySQL Server 8.x
- ✅ Eclipse IDE for Enterprise Java (or IntelliJ with Java EE support)
- ✅ Maven 3.x (bundled with IDE or installed separately)
- ✅ MySQL Connector/J (included via Maven dependency)

---

### Step 2 — Database Setup

1. Open **MySQL Workbench** or the MySQL command-line client.
2. Run the file: `sql/schema.sql`

```sql
source /path/to/sql/schema.sql;
```

This will:
- Create the `shopping_cart_db` database
- Create all 7 tables: `users`, `categories`, `products`, `cart`, `orders`, `order_items`, `payments`
- Insert 1 default admin, 5 categories, and 10 sample products

---

### Step 3 — Configure Database Connection

Open: `src/main/java/com/shop/util/DBConnection.java`

Update these constants to match your local MySQL setup:

```java
private static final String URL      = "jdbc:mysql://localhost:3306/shopping_cart_db?useSSL=false&serverTimezone=UTC";
private static final String USERNAME = "root";
private static final String PASSWORD = "your_mysql_password";  // ← CHANGE THIS
```

---

### Step 4 — Import into Eclipse

**Option A — Maven Project (Recommended):**
1. `File → Import → Maven → Existing Maven Projects`
2. Select the `ShoppingCartProject` folder
3. Eclipse will auto-resolve all dependencies

**Option B — Dynamic Web Project:**
1. `File → New → Dynamic Web Project`
2. Copy source files manually into `src/` and `WebContent/`
3. Add `mysql-connector-java-8.0.33.jar` to `WEB-INF/lib/`
4. Configure Tomcat server in Eclipse

---

### Step 5 — Configure Tomcat in Eclipse

1. `Window → Preferences → Server → Runtime Environments`
2. Add Apache Tomcat 9.x, point to installation folder
3. Right-click project → `Run As → Run on Server`
4. Select your Tomcat instance → Finish

---

### Step 6 — Run the Application

1. Right-click the project → `Run As → Run on Server`
2. Open browser: **http://localhost:8080/ShoppingCartSystem/**

---

## 🔑 Login Credentials

| Role     | Email              | Password   |
|----------|--------------------|------------|
| Admin    | admin@shop.com     | admin123   |
| Customer | (register yourself) | —         |

---

## 🗺 Application URL Map

| URL                           | Description                     | Access   |
|-------------------------------|---------------------------------|----------|
| `/`                           | Redirect to products            | Public   |
| `/register`                   | Customer registration           | Public   |
| `/login`                      | Login page                      | Public   |
| `/logout`                     | Invalidate session              | Any      |
| `/products`                   | Browse / search products        | Public   |
| `/products?search=keyword`    | Search products                 | Public   |
| `/products?categoryId=N`      | Filter by category              | Public   |
| `/cart`                       | View shopping cart              | Customer |
| `/checkout`                   | Payment form                    | Customer |
| `/order-confirmation`         | Order success page              | Customer |
| `/admin/dashboard`            | Admin dashboard with stats      | Admin    |
| `/admin/products`             | Manage products (CRUD)          | Admin    |
| `/admin/products?action=add`  | Add new product form            | Admin    |
| `/admin/products?action=edit` | Edit product form               | Admin    |
| `/admin/users`                | View all customers              | Admin    |
| `/admin/orders`               | View all orders + payments      | Admin    |

---

## 🏛 MVC Architecture

```
Browser Request
      │
      ▼
 ┌─────────────┐
 │  Servlet    │  ← Controller Layer (handles HTTP request/response)
 │  (Controller)│
 └──────┬──────┘
        │ calls
        ▼
 ┌─────────────┐
 │   Service   │  ← Business Logic Layer (validates, processes)
 │   Layer     │
 └──────┬──────┘
        │ calls
        ▼
 ┌─────────────┐
 │    DAO      │  ← Data Access Layer (SQL via JDBC)
 │   Layer     │
 └──────┬──────┘
        │ queries
        ▼
 ┌─────────────┐
 │   MySQL DB  │  ← Persistent storage
 └─────────────┘
        │ results
        ▼
 ┌─────────────┐
 │    Model    │  ← POJO classes (User, Product, etc.)
 └──────┬──────┘
        │ set as attributes
        ▼
 ┌─────────────┐
 │  JSP View   │  ← Presentation Layer (renders HTML)
 └─────────────┘
```

---

## 🗄 Database Schema (ERD Summary)

```
users ──────────── cart ──────── products ──── categories
  │                                  │
  └── orders ──── order_items ───────┘
         │
         └── payments
```

### Tables:
| Table         | Key Columns                                               |
|---------------|-----------------------------------------------------------|
| `users`       | user_id, full_name, email, password, role                 |
| `categories`  | category_id, name, description                            |
| `products`    | product_id, name, price, category_id, quantity, image_url |
| `cart`        | cart_id, user_id, product_id, quantity                    |
| `orders`      | order_id, user_id, total_amount, status                   |
| `order_items` | item_id, order_id, product_id, quantity, unit_price       |
| `payments`    | payment_id, order_id, cardholder_name, card_last4, amount |

---

## ✅ Features Implemented

### Customer Module
- [x] User Registration with validation
- [x] Secure Login / Logout with HttpSession
- [x] Browse all products with category and search filters
- [x] Add to Cart (increments quantity if already in cart)
- [x] View Cart with subtotals and order summary
- [x] Remove items from Cart
- [x] Checkout with payment form (Card No., Expiry, CVV, Name)
- [x] Client-side card number auto-formatting
- [x] Order Confirmation page with masked card display
- [x] Cart count badge in navigation

### Admin Module
- [x] Secure Admin Login (role-based redirect)
- [x] Dashboard with stats (products, users, orders)
- [x] Add new products with all fields
- [x] Edit existing products
- [x] Delete products (with confirmation dialog)
- [x] View all registered customers
- [x] View all orders with payment details

### Security
- [x] Role-based access control (CUSTOMER / ADMIN)
- [x] Session management via HttpSession
- [x] Admin pages protected — redirect to login if unauthorized
- [x] Order ownership verification (users can only view own orders)
- [x] Only last 4 card digits stored in database
- [x] Server-side input validation in Service layer
- [x] SQL Injection prevention via PreparedStatement throughout

---

## 📝 Notes for Submission

1. **No plain-text passwords in production** — this project stores plain text for academic simplicity. In production, use BCrypt.
2. **Image handling** — image filenames are stored in DB; place actual image files in `/webapp/images/`. The UI gracefully shows emoji placeholders if images are missing.
3. **Transaction safety** — order placement uses a single JDBC transaction (autoCommit=false) ensuring atomicity across orders, order_items, payments, stock update, and cart clear.
4. **GST calculation** — GST is calculated on the client/JSP side for display only; the stored total in the database is the pre-tax subtotal from the cart.

---

*Developed as Advanced Java Final Project | JSP + Servlets + JDBC + MySQL | MVC Architecture*

package com.shop.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * Model class representing the 'orders' table.
 */
public class Order {

    private int             orderId;
    private int             userId;
    private String          customerName;   // joined from users
    private String          customerEmail;  // joined from users
    private BigDecimal      totalAmount;
    private String          status;
    private Timestamp       createdAt;
    private List<OrderItem> items;
    private Payment         payment;

    public Order() {}

    // ---- Getters & Setters ----

    public int  getOrderId()                     { return orderId; }
    public void setOrderId(int orderId)          { this.orderId = orderId; }

    public int  getUserId()                      { return userId; }
    public void setUserId(int userId)            { this.userId = userId; }

    public String getCustomerName()                          { return customerName; }
    public void   setCustomerName(String customerName)       { this.customerName = customerName; }

    public String getCustomerEmail()                         { return customerEmail; }
    public void   setCustomerEmail(String customerEmail)     { this.customerEmail = customerEmail; }

    public BigDecimal getTotalAmount()                           { return totalAmount; }
    public void       setTotalAmount(BigDecimal totalAmount)     { this.totalAmount = totalAmount; }

    public String getStatus()                    { return status; }
    public void   setStatus(String status)       { this.status = status; }

    public Timestamp getCreatedAt()                          { return createdAt; }
    public void      setCreatedAt(Timestamp createdAt)       { this.createdAt = createdAt; }

    public List<OrderItem> getItems()                        { return items; }
    public void            setItems(List<OrderItem> items)   { this.items = items; }

    public Payment getPayment()                      { return payment; }
    public void    setPayment(Payment payment)       { this.payment = payment; }
}

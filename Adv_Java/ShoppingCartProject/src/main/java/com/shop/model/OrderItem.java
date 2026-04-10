package com.shop.model;

import java.math.BigDecimal;

/**
 * Model class representing the 'order_items' table.
 */
public class OrderItem {

    private int        itemId;
    private int        orderId;
    private int        productId;
    private String     productName;   // joined from products
    private int        quantity;
    private BigDecimal unitPrice;

    public OrderItem() {}

    public BigDecimal getSubtotal() {
        if (unitPrice == null) return BigDecimal.ZERO;
        return unitPrice.multiply(new BigDecimal(quantity));
    }

    // ---- Getters & Setters ----

    public int  getItemId()                  { return itemId; }
    public void setItemId(int itemId)        { this.itemId = itemId; }

    public int  getOrderId()                     { return orderId; }
    public void setOrderId(int orderId)          { this.orderId = orderId; }

    public int  getProductId()                   { return productId; }
    public void setProductId(int productId)      { this.productId = productId; }

    public String getProductName()                       { return productName; }
    public void   setProductName(String productName)     { this.productName = productName; }

    public int  getQuantity()                    { return quantity; }
    public void setQuantity(int quantity)        { this.quantity = quantity; }

    public BigDecimal getUnitPrice()                     { return unitPrice; }
    public void       setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
}

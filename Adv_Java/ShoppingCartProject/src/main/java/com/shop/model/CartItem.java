package com.shop.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Model class representing one row in the 'cart' table,
 * enriched with product details for display.
 */
public class CartItem {

    private int        cartId;
    private int        userId;
    private int        productId;
    private String     productName;
    private BigDecimal unitPrice;
    private String     imageUrl;
    private int        quantity;
    private Timestamp  addedAt;

    public CartItem() {}

    // ---- Computed ----
    public BigDecimal getSubtotal() {
        if (unitPrice == null) return BigDecimal.ZERO;
        return unitPrice.multiply(new BigDecimal(quantity));
    }

    // ---- Getters & Setters ----

    public int  getCartId()                  { return cartId; }
    public void setCartId(int cartId)        { this.cartId = cartId; }

    public int  getUserId()                  { return userId; }
    public void setUserId(int userId)        { this.userId = userId; }

    public int  getProductId()                   { return productId; }
    public void setProductId(int productId)      { this.productId = productId; }

    public String getProductName()                       { return productName; }
    public void   setProductName(String productName)     { this.productName = productName; }

    public BigDecimal getUnitPrice()                     { return unitPrice; }
    public void       setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }

    public String getImageUrl()                      { return imageUrl; }
    public void   setImageUrl(String imageUrl)       { this.imageUrl = imageUrl; }

    public int  getQuantity()                    { return quantity; }
    public void setQuantity(int quantity)        { this.quantity = quantity; }

    public Timestamp getAddedAt()                        { return addedAt; }
    public void      setAddedAt(Timestamp addedAt)       { this.addedAt = addedAt; }
}

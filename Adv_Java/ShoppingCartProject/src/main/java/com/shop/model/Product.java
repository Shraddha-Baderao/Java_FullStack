package com.shop.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Model class representing the 'products' table.
 */
public class Product {

    private int        productId;
    private String     name;
    private String     description;
    private BigDecimal price;
    private int        categoryId;
    private String     categoryName;   // joined from categories table
    private int        quantity;
    private String     imageUrl;
    private Timestamp  createdAt;

    public Product() {}

    // ---- Getters & Setters ----

    public int getProductId()                        { return productId; }
    public void setProductId(int productId)          { this.productId = productId; }

    public String getName()                  { return name; }
    public void   setName(String name)       { this.name = name; }

    public String getDescription()                       { return description; }
    public void   setDescription(String description)     { this.description = description; }

    public BigDecimal getPrice()                     { return price; }
    public void       setPrice(BigDecimal price)     { this.price = price; }

    public int  getCategoryId()                      { return categoryId; }
    public void setCategoryId(int categoryId)        { this.categoryId = categoryId; }

    public String getCategoryName()                          { return categoryName; }
    public void   setCategoryName(String categoryName)       { this.categoryName = categoryName; }

    public int  getQuantity()                    { return quantity; }
    public void setQuantity(int quantity)        { this.quantity = quantity; }

    public String getImageUrl()                      { return imageUrl; }
    public void   setImageUrl(String imageUrl)       { this.imageUrl = imageUrl; }

    public Timestamp getCreatedAt()                          { return createdAt; }
    public void      setCreatedAt(Timestamp createdAt)       { this.createdAt = createdAt; }

    public boolean isInStock() { return quantity > 0; }
}

package com.shop.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Model class representing the 'payments' table.
 */
public class Payment {

    private int        paymentId;
    private int        orderId;
    private String     cardholderName;
    private String     cardLast4;       // only last 4 digits stored
    private String     expiryDate;
    private BigDecimal amount;
    private String     status;          // "SUCCESS" or "FAILED"
    private Timestamp  paidAt;

    public Payment() {}

    // ---- Getters & Setters ----

    public int  getPaymentId()                       { return paymentId; }
    public void setPaymentId(int paymentId)          { this.paymentId = paymentId; }

    public int  getOrderId()                     { return orderId; }
    public void setOrderId(int orderId)          { this.orderId = orderId; }

    public String getCardholderName()                            { return cardholderName; }
    public void   setCardholderName(String cardholderName)       { this.cardholderName = cardholderName; }

    public String getCardLast4()                         { return cardLast4; }
    public void   setCardLast4(String cardLast4)         { this.cardLast4 = cardLast4; }

    public String getExpiryDate()                        { return expiryDate; }
    public void   setExpiryDate(String expiryDate)       { this.expiryDate = expiryDate; }

    public BigDecimal getAmount()                        { return amount; }
    public void       setAmount(BigDecimal amount)       { this.amount = amount; }

    public String getStatus()                    { return status; }
    public void   setStatus(String status)       { this.status = status; }

    public Timestamp getPaidAt()                         { return paidAt; }
    public void      setPaidAt(Timestamp paidAt)         { this.paidAt = paidAt; }

    /** Returns masked card number for display, e.g. **** **** **** 1234 */
    public String getMaskedCard() {
        return "**** **** **** " + (cardLast4 != null ? cardLast4 : "****");
    }
}

package com.jpmorgan.stockmarket.entity;

import com.jpmorgan.stockmarket.entity.enums.TradeType;

import java.util.Date;

/**
 * Class for Trade entity
 * @author Sukesh Singh
 */
public class Trade {

    private Stock stock;

    private Date timestamp;

    private int quantity;

    private TradeType type;

    private double price;

    /**
     * Constructor
     * @param Stock stock
     * @param Date timestamp
     * @param int quantity
     * @param Tradetype type
     * @param Double price
     */
    public Trade(Stock stock, Date timestamp, int quantity, TradeType type, double price) {
        super();
        this.stock = stock;
        this.timestamp = timestamp;
        this.quantity = quantity;
        this.type = type;
        this.price = price;
    }

    /**
     * Getter method of stock
     * @return Stock stock
     */
    public Stock getStock() {
        return stock;
    }

    /**
     * Setter method of Stock
     * @param Stock stock
     */
    public void setStock(Stock stock) {
        this.stock = stock;
    }

    /**
     * Getter method of Timestamp
     * @return Date Timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * Setter method of Timestamp
     * @param Date Timestamp
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Getter method of Quantity
     * @return int quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Setter method of Quantity
     * @param int quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Getter method of TradeType
     * @return Tradetype type
     */
    public TradeType getType() {
        return type;
    }

    /**
     * Setter method of TradeType
     * @param TradeType type
     */
    public void setType(TradeType type) {
        this.type = type;
    }

    /**
     * Getter method of Price
     * @return Double price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter method of Price
     * @param Double price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Method compares timestamp
     * @param Trade trade
     * @return int
     */
    public int compareTo(Trade trade) {
        return trade.getTimestamp().compareTo(this.timestamp);
    }

}

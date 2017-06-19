package com.jpmorgan.stockmarket.entity;

import com.jpmorgan.stockmarket.entity.enums.StockType;

/**
 * Class for Stock entity
 * @author Sukesh Singh
 */
public class Stock {

    private String stockSymbol;

    private StockType type;

    private double lastDividend;

    private double fixedDividend;

    private double parValue;

    /**
     * Constructor
     * @param String stockSymbol
     * @param StockType type
     * @param Double lastDividend
     * @param Double fixedDividend
     * @param Double parValue
     */
    public Stock(String stockSymbol, StockType type, double lastDividend, double fixedDividend, double parValue) {
        this.stockSymbol = stockSymbol;
        this.type = type;
        this.lastDividend = lastDividend;
        this.fixedDividend = fixedDividend;
        this.parValue = parValue;
    }

    /**
     * Getter method of StockSymbol
     * @return String stockSymbol
     */
    public String getStockSymbol() {
        return stockSymbol;
    }

    /**
     * Setter method of StockSymbol
     * @param String stockSymbol
     */
    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    /**
     * Getter method of Type
     * @return StockType type
     */
    public StockType getType() {
        return type;
    }

    /**
     * Setter method of Type
     * @param StockType type
     */
    public void setType(StockType type) {
        this.type = type;
    }

    /**
     * Getter method of LastDividend
     * @return Double lastDividend
     */
    public double getLastDividend() {
        return lastDividend;
    }

    /**
     * Setter method of LastDividend
     * @param Double lastDividend
     */
    public void setLastDividend(double lastDividend) {
        this.lastDividend = lastDividend;
    }

    /**
     * Getter method of FixedDividend
     * @return Double fixedDividend
     */
    public double getFixedDividend() {
        return fixedDividend;
    }

    /**
     * Setter method of FixedDividend
     * @param Double fixedDividend
     */
    public void setFixedDividend(double fixedDividend) {
        this.fixedDividend = fixedDividend;
    }

    /**
     * Getter method of ParValue
     * @return Double parValue
     */
    public double getParValue() {
        return parValue;
    }

    /**
     * Setter method of ParValue
     * @param Double parValue
     */
    public void setParValue(double parValue) {
        this.parValue = parValue;
    }


}

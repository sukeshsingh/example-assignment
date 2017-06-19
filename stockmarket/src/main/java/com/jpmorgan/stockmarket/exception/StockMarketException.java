package com.jpmorgan.stockmarket.exception;

/**
 * Exception Class for Stock Market Application
 * @author Sukesh Singh
 */
public class StockMarketException extends  Exception {

    /**
     * Constructor
     * @param String message
     */
    public StockMarketException(String message) {
        super(message);
    }
}

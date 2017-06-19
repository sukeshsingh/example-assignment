package com.jpmorgan.stockmarket.service;

import com.jpmorgan.stockmarket.entity.Stock;
import com.jpmorgan.stockmarket.entity.Trade;

import java.util.List;

/**
 * Interface
 * @author Sukesh Singh
 */
public interface TradeService {

    /**
     * Method to save a trade
     * @param Trade trade
     */
    void save(Trade trade);

    /**
     * Method to get a trade record
     * @param Stock stock
     * @param int minute
     * @return List
     */
    List<Trade> getStockTradeRecord(Stock stock, int minutes);

    /**
     * Method to get a trade record
     * @param Trade trade
     */
    void getTradeRecord(Trade trade);

    /**
     * Method to get all the trades
     * @return List
     */
    List<Trade> getAllTrades();
}

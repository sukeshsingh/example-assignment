package com.jpmorgan.stockmarket.repositories;

import com.jpmorgan.stockmarket.entity.Stock;
import com.jpmorgan.stockmarket.entity.Trade;

import java.util.List;

/**
 * Interface to save and get trade records
 * @author Sukesh Singh
 */
public interface ITradeRepository<T> {

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
    List<T> getTradeRecord(Stock stock, int minute);

    /**
     * Method to get all the trades
     * @return List
     */
    List<Trade> getAllTrades();
}

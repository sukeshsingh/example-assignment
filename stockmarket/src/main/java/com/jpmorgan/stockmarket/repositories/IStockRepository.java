package com.jpmorgan.stockmarket.repositories;

import com.jpmorgan.stockmarket.entity.Trade;

import java.util.List;

/**
 * Interface to save and get all stock values
 * @author Sukesh Singh
 */
public interface IStockRepository<T> {

    /**
     * Method to record a trade
     * @param T stock
     */
    void save(T stock);

    /**
     * Method to get the Stock values
     * @param String stockSymbol
     * @return T
     */
    T getStock(String stockSymbol);

    /**
     * Method to get dividendYield for the stock
     * @param T stock
     * @param Double price
     * @return Double
     */
    double getDividendYield(T stock, double price);

    /**
     * Method to get PERatio for the stock
     * @param T stock
     * @param Double price
     * @return Double
     */
    double getPERatio(T stock, double price);

    /**
     * Method to get volume weighted stock price
     * @param List trades
     * @return Double
     */
    double getVolumeWeightedStockPrice(List<Trade> trades);

    /**
     * Method to get GBCE using geometric mean
     * @param List trades
     * @return Double
     */
    double getGBCE(List<Trade> trades);
}

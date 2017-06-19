package com.jpmorgan.stockmarket.service;

import com.jpmorgan.stockmarket.entity.Stock;
import com.jpmorgan.stockmarket.entity.Trade;

import java.util.List;

/**
 * Interface
 * @author Sukesh Singh
 */
public interface StockService {

     /**
      * Method to record a trade
      * @param Stock stock
      */
     void save(Stock stock);

     /**
      * Method to get the Stock values
      * @param String symbol
      * @return Stock
      */
     Stock getStock(String symbol);

     /**
      * Method to get dividendYield for the stock
      * @param Stock stock
      * @param Double price
      * @return Double
      */
     double getDividendYield(Stock stock, double price);

     /**
      * Method to get PERatio for the stock
      * @param Stock stock
      * @param Double price
      * @return Double
      */
     double getPERatio(Stock stock, double price);

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

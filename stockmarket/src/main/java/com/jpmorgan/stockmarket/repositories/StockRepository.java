package com.jpmorgan.stockmarket.repositories;

import com.jpmorgan.stockmarket.calculation.StockTradeCalcs;
import com.jpmorgan.stockmarket.entity.Stock;
import com.jpmorgan.stockmarket.entity.Trade;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class implements interface IStockRepository
 * @author Sukesh Singh
 */

@Repository
public class StockRepository implements IStockRepository<Stock>{


    private Map<String, Stock> stockMap = new HashMap<>();

    /**
     * Method to record a trade
     * @param Stock stock
     */
    @Override
    public void save(Stock stock) {
        this.stockMap.put(stock.getStockSymbol(), stock);
    }

    /**
     * Method to get the Stock values
     * @param String stockSymbol
     * @return Stock
     */
    @Override
    public Stock getStock(String stockSymbol) {

        return this.stockMap.get(stockSymbol);
    }

    /**
     * Method to get dividendYield for the stock
     * @param Stock stock
     * @param Double price
     * @return Double
     */
    @Override
    public double getDividendYield(Stock stock, double price) {

        return StockTradeCalcs.calDividendYield(stock, price);
    }

    /**
     * Method to get PERatio for the stock
     * @param Stock stock
     * @param Double price
     * @return Double
     */
    @Override
    public double getPERatio(Stock stock, double price) {
        return StockTradeCalcs.calPERatio(stock, price);
    }

    /**
     * Method to get volume weighted stock price
     * @param List trades
     * @return Double
     */
    @Override
    public double getVolumeWeightedStockPrice(List<Trade> trades) {
        return StockTradeCalcs.calVolumeWeightedStockPrice(trades);
    }

    /**
     * Method to get GBCE using geometric mean
     * @param List trades
     * @return Double
     */
    @Override
    public double getGBCE(List<Trade> trades) {
        return StockTradeCalcs.calGBCE(trades);
    }


}

package com.jpmorgan.stockmarket.service.impl;

import com.jpmorgan.stockmarket.entity.Stock;
import com.jpmorgan.stockmarket.entity.Trade;
import com.jpmorgan.stockmarket.repositories.IStockRepository;
import com.jpmorgan.stockmarket.repositories.StockRepository;
import com.jpmorgan.stockmarket.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class implements inerface StockService
 * @author Sukesh Singh
 */

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private static StockServiceImpl instance = null;
    @Autowired
    private IStockRepository<Stock> stockRepository = new StockRepository();


    public static StockServiceImpl getInstance() {
        if (instance == null) {
            instance = new StockServiceImpl();
        }
        return instance;
    }

    /**
     * Method to record a trade
     * @param Stock stock
     */
    @Override
    public void save(Stock stock){
        this.stockRepository.save(stock);
    }

    /**
     * Method to get the Stock values
     * @param String stockSymbol
     * @return Stock
     */
    @Override
    public Stock getStock(String stockSymbol) {
        return this.stockRepository.getStock(stockSymbol);
    }

    /**
     * Method to get dividendYield for the stock
     * @param Stock stock
     * @param Double price
     * @return Double
     */
    @Override
    public double getDividendYield(Stock stock, double price) {
        return this.stockRepository.getDividendYield(stock, price);
    }

    /**
     * Method to get PERatio for the stock
     * @param Stock stock
     * @param Double price
     * @return Double
     */
    @Override
    public double getPERatio(Stock stock, double price) {
        return this.stockRepository.getPERatio(stock,price);
    }

    /**
     * Method to get volume weighted stock price
     * @param List trades
     * @return Double
     */
    @Override
    public double getVolumeWeightedStockPrice(List<Trade> trades) {
        return this.stockRepository.getVolumeWeightedStockPrice(trades);
    }

    /**
     * Method to get GBCE using geometric mean
     * @param List trades
     * @return Double
     */
    @Override
    public double getGBCE(List<Trade> trades) {
        return this.stockRepository.getGBCE(trades);
    }

}

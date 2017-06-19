package com.jpmorgan.stockmarket.service.impl;

import com.jpmorgan.stockmarket.entity.Stock;
import com.jpmorgan.stockmarket.entity.Trade;
import com.jpmorgan.stockmarket.repositories.ITradeRepository;
import com.jpmorgan.stockmarket.repositories.TradeRepository;
import com.jpmorgan.stockmarket.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Class implements interface TradeService
 * @author Sukesh Singh
 */
public class TradeServiceImpl implements TradeService{

    @Autowired
    private ITradeRepository<Trade> tradeRepository = new TradeRepository();

    @Autowired
    private static TradeServiceImpl instance = null;

    public static TradeService getInstance() {
        if (instance == null) {
            instance = new TradeServiceImpl();
        }
        return instance;
    }

    /**
     * Method to save a trade
     * @param Trade trade
     */
    @Override
    public void save(Trade trade) {
        this.tradeRepository.save(trade);
    }

    /**
     * Method to get a trade record
     * @param Stock stock
     * @param int minutes
     * @return List
     */
    @Override
    public List<Trade> getStockTradeRecord(Stock stock, int minutes) {
        return this.tradeRepository.getTradeRecord(stock, minutes);
    }

    /**
     * Method to get a trade record
     * @param Trade trade
     */
    @Override
    public void getTradeRecord(Trade trade) {
        if (trade != null && trade.getStock() != null) {
            this.tradeRepository.save(trade);
        }
    }

    /**
     * Method to get all the trades
     * @return List
     */
    @Override
    public List<Trade> getAllTrades() {
        return this.tradeRepository.getAllTrades();
    }
}

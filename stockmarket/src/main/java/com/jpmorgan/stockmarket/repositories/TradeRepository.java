package com.jpmorgan.stockmarket.repositories;

import com.jpmorgan.stockmarket.entity.Stock;
import org.springframework.stereotype.Repository;

import com.jpmorgan.stockmarket.entity.Trade;

import java.util.*;

/**
 * Class which implements interface ITradeRepository
 * @author Sukesh Singh
 */

@Repository
public class TradeRepository implements ITradeRepository<Trade> {

    private Map<String, List<Trade>> tradeMap = new HashMap<String, List<Trade>>();

    //constructor
    public TradeRepository(){
        this.tradeMap = new HashMap<>();
    }

    /**
     * Method to save a trade
     * @param Trade trade
     */
    @Override
    public void save(Trade trade) {
        List<Trade> tradeList = new ArrayList<Trade>();
        if (tradeMap.containsKey(trade.getStock().getStockSymbol())) {
            tradeList = tradeMap.get(trade.getStock().getStockSymbol());
        }
        tradeList.add(trade);
        tradeMap.put(trade.getStock().getStockSymbol(), tradeList);
    }

    /**
     * Method to get a trade record
     * @param Stock stock
     * @param int minutes
     * @return List
     */
    @Override
    public List<Trade> getTradeRecord(Stock stock, int minutes) {
        List<Trade> tradeList = tradeMap.get(stock.getStockSymbol());
        Calendar previous = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        List<Trade> recentTrades = new ArrayList<>();
        if(tradeList!=null){
            for(Trade trade : tradeList){
                previous.setTime(trade.getTimestamp());
                long diff = now.getTimeInMillis() - previous.getTimeInMillis();
                if(diff <= 5 * 60 * 1000)
                {
                    recentTrades.add(trade);
                }
            }
        }
        return recentTrades;
    }

    /**
     * Method to get all the trades
     * @return List
     */
    @Override
    public List<Trade> getAllTrades() {
        List<Trade> tradeResult = new ArrayList<Trade>();
        for (String stockSymbol: tradeMap.keySet()) {
            tradeResult.addAll(tradeMap.get(stockSymbol));
        }
        return tradeResult;
    }
}
package com.jpmorgan.stockmarket.repositories;

import com.jpmorgan.stockmarket.StockmarketApplicationTests;
import com.jpmorgan.stockmarket.entity.Stock;
import com.jpmorgan.stockmarket.entity.Trade;
import com.jpmorgan.stockmarket.entity.enums.StockType;
import com.jpmorgan.stockmarket.entity.enums.TradeType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Class to setup and test TradeRepository
 * @author Sukesh Singh
 */
public class TradeRepositoryTest extends StockmarketApplicationTests {

    @Autowired
    private ITradeRepository<Trade> tradeRepository;

    private Stock stock1;
    private Stock stock2;
    private Stock stock3;

    @Before
    public void setUp() {
        tradeRepository = new TradeRepository();
        stock1 = new Stock("POP", StockType.COMMON, 8, 0, 100);
        stock2 = new Stock("ALE", StockType.COMMON, 23, 0, 60);
        stock3 = new Stock("GIN", StockType.PREFERRED, 8, 2, 100);
    }

    @Test
    public void testAddTrade() {
        Trade trade = new Trade(stock1, Calendar.getInstance().getTime(), 1, TradeType.BUY, 1.0);
        tradeRepository.save(trade);
        assertEquals(1, tradeRepository.getAllTrades().size());
    }

    @Test
    public void testGetTradeRecord() {
        Trade trade = new Trade(stock1,
                Calendar.getInstance().getTime(), 1, TradeType.BUY, 1.0);
        tradeRepository.save(trade);

        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, -16);
        Trade trade2 = new Trade(stock1, c.getTime(), 1, TradeType.BUY, 1.0);
        tradeRepository.save(trade2);
        List<Trade> trades = tradeRepository.getTradeRecord(stock1, 15);
        assertEquals(1, trades.size());
    }

    @Test
    public void testGetAllTrades() {

        Trade trade = new Trade(stock1,
                Calendar.getInstance().getTime(), 1, TradeType.BUY, 1.0);
        tradeRepository.save(trade);

        Trade trade2 = new Trade(stock2,
                Calendar.getInstance().getTime(), 1, TradeType.BUY, 1.0);
        tradeRepository.save(trade2);

        Trade trade3 = new Trade(stock3,
                Calendar.getInstance().getTime(), 1, TradeType.BUY, 1.0);
        tradeRepository.save(trade3);

        assertEquals(3, tradeRepository.getAllTrades().size());
    }
}

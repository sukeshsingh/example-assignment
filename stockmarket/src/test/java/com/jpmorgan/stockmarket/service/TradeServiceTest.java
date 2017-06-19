package com.jpmorgan.stockmarket.service;

import com.jpmorgan.stockmarket.StockmarketApplicationTests;
import com.jpmorgan.stockmarket.entity.Stock;
import com.jpmorgan.stockmarket.entity.Trade;
import com.jpmorgan.stockmarket.entity.enums.StockType;
import com.jpmorgan.stockmarket.entity.enums.TradeType;
import com.jpmorgan.stockmarket.service.impl.TradeServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Class to test TradeService
 * @author Sukesh Singh
 */
public class TradeServiceTest extends StockmarketApplicationTests {

    private TradeService tradeService;
    private Stock stock;

    @Before
    public void setup() {
        tradeService = new TradeServiceImpl();
        stock = new Stock("POP", StockType.COMMON, 8, 0, 100);
    }

    @Test
    public void testRecordTrade() {
        Trade trade = new Trade(stock, Calendar.getInstance().getTime(), 1, TradeType.BUY, 1.0);
        tradeService.save(trade);
        List<Trade> result = tradeService.getStockTradeRecord(stock, 15);
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void testGetAllTradesForLast15Minutes() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, -16);
        Trade firstTrade = new Trade(stock, c.getTime(), 1, TradeType.BUY, 1.0);
        tradeService.save(firstTrade);

        Date time = Calendar.getInstance().getTime();
        Trade secondTrade = new Trade(stock, time, 1, TradeType.BUY, 1.0);
        tradeService.save(secondTrade);

        List<Trade> result = tradeService.getStockTradeRecord(stock, 15);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(time, result.get(0).getTimestamp());
    }

    @Test
    public void testGetAllTrades() {
        tradeService.save(new Trade(stock, Calendar.getInstance().getTime(), 1, TradeType.BUY, 1.0));
        tradeService.save(new Trade(stock, Calendar.getInstance().getTime(), 1, TradeType.BUY, 1.0));
        tradeService.save(new Trade(stock, Calendar.getInstance().getTime(), 1, TradeType.BUY, 1.0));
        List<Trade> result = tradeService.getAllTrades();
        assertEquals(3, result.size());
    }
}

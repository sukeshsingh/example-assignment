package com.jpmorgan.stockmarket.service;

import com.jpmorgan.stockmarket.StockmarketApplicationTests;
import com.jpmorgan.stockmarket.entity.Stock;
import com.jpmorgan.stockmarket.entity.Trade;
import com.jpmorgan.stockmarket.entity.enums.StockType;
import com.jpmorgan.stockmarket.entity.enums.TradeType;
import com.jpmorgan.stockmarket.service.impl.StockServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Class to test StockService
 * @author Sukesh Singh
 */
public class StockServiceTest extends StockmarketApplicationTests {

    private static final int PRICE = 0;
    private StockService stockService;
    private Stock stock1;
    private Stock stock2;
    private Stock stock3;

    @Before
    public void setup() {
        stockService = new StockServiceImpl();
        stock1 = new Stock("TEA", StockType.COMMON, 0, 0, 100);
        stock2 = new Stock("GIN", StockType.PREFERRED, 8, 2, 100);
    }

    @Test
    public void testAddAndGetStock() {
        stockService.save(stock1);
        Stock result = stockService.getStock(stock1.getStockSymbol());
        assertEquals(stock1, result);
    }

    @Test
    public void testCalculateDividendYield() {
        assertEquals(0, stockService.getDividendYield(stock1, 2.5), 0);
    }


    @Test
    public void testCalculateDividendYieldPreferred() {
        assertEquals(80, stockService.getDividendYield(stock2, 2.5), 0);
    }

    @Test
    public void testCalculatePERatio() {
        assertEquals(0.31, stockService.getPERatio(stock2, 2.5), 0);
    }

    @Test
    public void testCalculateVolumeWeightedStockPrice() {
        List<Trade> trades = new ArrayList<Trade>();
        trades.add(new Trade(stock1, Calendar.getInstance().getTime(), 1, TradeType.BUY, 2));
        trades.add(new Trade(stock1, Calendar.getInstance().getTime(), 3, TradeType.BUY, 1.5));
        trades.add(new Trade(stock1, Calendar.getInstance().getTime(), 1, TradeType.BUY, 4));
        assertEquals(2.1, stockService.getVolumeWeightedStockPrice(trades), 0);
    }

    @Test
    public void testCalculateGBCE() {
        List<Trade> trades = new ArrayList<Trade>();
        trades.add(new Trade(stock1, Calendar.getInstance().getTime(), 1, TradeType.BUY, 2));
        trades.add(new Trade(stock2, Calendar.getInstance().getTime(), 3, TradeType.BUY, 1.5));
        trades.add(new Trade(stock3, Calendar.getInstance().getTime(), 1, TradeType.BUY, 3));
        assertEquals(2.08, stockService.getGBCE(trades), 0);
    }
}

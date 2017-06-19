package com.jpmorgan.stockmarket.repositories;

import com.jpmorgan.stockmarket.entity.Stock;
import com.jpmorgan.stockmarket.entity.enums.StockType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Class for Setup Stock repository
 * @author Sukesh Singh
 */

public class StockRepositoryTest {

    @Autowired
    private IStockRepository<Stock> stockRepository;
    private Stock stock;

    @Before
    public void setUp(){
        stockRepository = new StockRepository();
        stock = new Stock("POP", StockType.COMMON, 8, 0, 100);
    }

    @Test
    public void testAddStock(){
        stockRepository.save(stock);
        assertEquals(stock, stockRepository.getStock(stock.getStockSymbol()));
    }

}

package com.jpmorgan.stockmarket.calculation;

import com.jpmorgan.stockmarket.entity.Stock;
import com.jpmorgan.stockmarket.entity.Trade;
import com.jpmorgan.stockmarket.entity.enums.StockType;
import com.jpmorgan.stockmarket.utils.NumberUtils;

import java.util.List;

/**
 * Class which has all calculations for stock markets and trades
 * @author Sukesh Singh
 */
public class StockTradeCalcs {

    /**
     * Method calculates dividend Yield for preferred and common
     * @param Stock stock
     * @param Double price
     * @return Double
     */
    public static double calDividendYield(Stock stock, double price) {
        if (StockType.PREFERRED.equals(stock.getType())) {
            return (stock.getFixedDividend() * stock.getParValue())/ price;
        }
        double result = stock.getLastDividend() / price;
        return NumberUtils.round(result, 2);
    }

    /**
     * Method calculates PERRatio
     * @param Stock stock
     * @param Double price
     * @return Double
     */
    public static double calPERatio(Stock stock, double price) {
        double result = price / stock.getLastDividend();
        return NumberUtils.round(result, 2);
    }

    /**
     * Method calculates volume weighted stock price
     * @param List trades
     * @return Double
     */
    public static double calVolumeWeightedStockPrice(List<Trade> trades) {
        double sumOfPriceQuantity = 0;
        int sumOfQuantity = 0;

        for (Trade trade : trades) {
            sumOfPriceQuantity = sumOfPriceQuantity + (trade.getPrice() * trade.getQuantity());
            sumOfQuantity = sumOfQuantity + trade.getQuantity();
        }
        double result = sumOfPriceQuantity / sumOfQuantity;
        return NumberUtils.round(result, 2);
    }

    /**
     * Method calculates GBCE using geometric mean
     * @param List trades
     * @return Double
     */
    public static double calGBCE(List<Trade> trades) {
        double total = 1;
        for (Trade trade : trades) {
            total = total * trade.getPrice();
        }
        double result = Math.pow(total, (1D / trades.size()));
        return NumberUtils.round(result, 2);
    }

}

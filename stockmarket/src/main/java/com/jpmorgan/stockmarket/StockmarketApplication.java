package com.jpmorgan.stockmarket;

import com.jpmorgan.stockmarket.entity.Stock;
import com.jpmorgan.stockmarket.entity.Trade;
import com.jpmorgan.stockmarket.entity.enums.StockType;
import com.jpmorgan.stockmarket.entity.enums.TradeType;
import com.jpmorgan.stockmarket.exception.StockMarketException;
import com.jpmorgan.stockmarket.service.StockService;
import com.jpmorgan.stockmarket.service.TradeService;
import com.jpmorgan.stockmarket.service.impl.StockServiceImpl;
import com.jpmorgan.stockmarket.service.impl.TradeServiceImpl;
import com.jpmorgan.stockmarket.utils.Constants;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;
import java.util.List;
import java.util.Scanner;


/**
 * Main Class of StockMarket Application
 * @author Sukesh Singh
 */
@Configuration
@SpringBootApplication
public class StockmarketApplication implements CommandLineRunner{


	private static StockService stockService;


	private static TradeService tradeService;

    private static Scanner scanner;

	public static void main(String[] args) {
		SpringApplication.run(StockmarketApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
        System.out.println("Stock Market loading ...");
        loadStocks();
        commandMenu();
        scanner = new Scanner(System.in);
        String choice= null;

        while (true) {
            choice = scanner.nextLine();
            if ("q".equals(choice)) {
                scanner.close();
                System.exit(0);
            } else {
                try {
                    int option = Integer.parseInt(choice);
                    Stock stockFromUser;
                    double priceFromUser;

                    switch (option) {
                        case 1:
                            stockFromUser = getStockFromUser();
                            priceFromUser = getStockPriceFromUser();
                            calculateDividendYield(stockFromUser, priceFromUser);
                            break;
                        case 2:
                            stockFromUser = getStockFromUser();
                            priceFromUser = getStockPriceFromUser();
                            calculatePERatio(stockFromUser, priceFromUser);
                            break;
                        case 3:
                            stockFromUser = getStockFromUser();
                            int quantityFromUser = getQuantityFromUser();
                            TradeType tradeTypeFromUser = getTradeType();
                            priceFromUser = getStockPriceFromUser();
                            recordTrade(stockFromUser, quantityFromUser, tradeTypeFromUser, priceFromUser);
                            break;
                        case 4:
                            stockFromUser = getStockFromUser();
                            calculateVolumeWeightedStockPrice(stockFromUser);
                            break;
                        case 5:
                            calculateGBCE();
                            break;
                        default:
                            break;
                    }
                } catch (NumberFormatException e) {
                    printResult("Invalid Option");
                } catch (StockMarketException e1) {
                    printResult(e1.getMessage());
                }
                System.out.println("");
                commandMenu();
            }
        }

	}

    // Method to display the command menu
	private static void commandMenu() {
		System.out.println("1: Calculate Dividend Yield");
		System.out.println("2: Calculate P/E Ratio");
		System.out.println("3: Record Trade");
		System.out.println("4: Calculate Volume Weighted Stock Price");
		System.out.println("5: Calculate GBCE All Share Index");
		System.out.println("q: Exit");
	}

	//Method to save the stocks with the below inputs
    private static void loadStocks() {
        stockService = StockServiceImpl.getInstance();
        tradeService = TradeServiceImpl.getInstance();

        stockService.save(new Stock("TEA", StockType.COMMON, 0, 0, 100));
        stockService.save(new Stock("POP", StockType.COMMON, 8, 0, 100));
        stockService.save(new Stock("ALE", StockType.COMMON, 23, 0, 60));
        stockService.save(new Stock("GIN", StockType.PREFERRED, 8, 2, 100));
        stockService.save(new Stock("JOE", StockType.COMMON, 13, 0, 250));
    }

    /**
     * Method to get and print the calculated dividend yield
     * @param Stock stock
     */
    private static void calculateDividendYield(Stock stock, double price) {
        double result = stockService.getDividendYield(stock, price);
        printResult("Dividend Yield: " + result);
    }

    /**
     * Method to get and print the calculated PERatio
     * @param Stock stock
     * @param Double price
     */
    private static void calculatePERatio(Stock stock, double price) {
        double result = stockService.getPERatio(stock, price);
        printResult("PE Ratio: " + result);
    }

    /**
     * Method to get and print the calculated volume weighted stock price
     * @param Stock stock
     */
    private static void calculateVolumeWeightedStockPrice(Stock stock) {
        List<Trade> trades = tradeService.getStockTradeRecord(stock, Constants.FIFTEEN_MINUTES);
        if (trades == null || trades.isEmpty()) {
            printResult("Volume Weighted Stock Price: No trades");
        } else {
            double result = stockService.getVolumeWeightedStockPrice(trades);
            printResult("Volume Weighted Stock Price: " + result);
        }
    }

    /**
     * Method to save the trade record
     * @param Stock stock
     * @param int quantity
     * @param TradeType type
     * @param Double price
     */
    private static void recordTrade(Stock stock, int quantity, TradeType type, double price) {
        tradeService.getTradeRecord(new Trade(stock, Calendar.getInstance().getTime(), quantity, type, price));
        printResult("Trade has been recorded");
    }

    //Method to get and print the calculated GBCE
    private static void calculateGBCE() {
        List<Trade> allTrades = tradeService.getAllTrades();
        if (allTrades == null || allTrades.isEmpty()) {
            printResult("Unable to calculate GBCE: Theer is no trades");
        } else {
            printResult("GBCE: " + stockService.getGBCE(allTrades));
        }
    }

    private static void printResult(String result) {
        System.out.println(Constants.LINE_SEPARATOR);
        System.out.println(result);
        System.out.println(Constants.LINE_SEPARATOR);
    }
    //Method to get StockSymbol from user
    private static Stock getStockFromUser() throws StockMarketException {
        System.out.println("Please enter stock symbol(In Caps)");
        String stockSymbol = scanner.nextLine();
        Stock stock = stockService.getStock(stockSymbol);
        if (stock == null) {
            throw new StockMarketException("Stock not found");
        }
        return stock;
    }

    //Method to get stock price from the user
    private static double getStockPriceFromUser() throws StockMarketException {
        System.out.println("Please input stock price");
        String stockPrice = scanner.nextLine();
        try {
            double result = Double.parseDouble(stockPrice);
            if (result <= 0) {
                throw new StockMarketException("Invalid price: Must be greated than 0");
            }
            return result;
        } catch (NumberFormatException e) {
            throw new StockMarketException("Invalid price: Not a number");
        }
    }

    //Method to get trade type from the user
    private static TradeType getTradeType() throws StockMarketException {
        System.out.println("Please input trade type (BUY/SELL)");
        String type = scanner.nextLine();
        try {
            return TradeType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new StockMarketException("Invalid trade type: Must be BUY or SELL");
        }
    }

    //Method to get the quantity from the user
    private static int getQuantityFromUser() throws StockMarketException {
        System.out.println("Please input quantity");
        String quantity = scanner.nextLine();
        try {
            int result = Integer.parseInt(quantity);
            if (result <= 0) {
                throw new StockMarketException("Invalid quantity: Must be greated than 0");
            }
            return result;
        } catch (NumberFormatException e) {
            throw new StockMarketException("Invalid quantity: Not a number");
        }
    }
}

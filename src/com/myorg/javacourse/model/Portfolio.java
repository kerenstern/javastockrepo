package com.myorg.javacourse.model;

import java.util.*;

/**
 * Portfolio Class Creates and holds an array of stocks
 * 
 * @author Keren
 * 
 */

public class Portfolio {

	private String title = "default title";
	public Stock stocks[];
	public int portfolioSize = 0;

	private final static int MAX_PORTFOLIO_SIZE = 5;

	private float balance;

	private int BUY = 0;
	private int SELL = 1;
	private int REMOVE = 2;
	private int HOLD = 3;

	public enum ALGO_RECOMMENDATION {
		BUY, SELL, REMOVE, HOLD
	}

	// ******************
	// Constructors
	// ******************
	/**
	 * Creates new empty stock array
	 */
	public Portfolio() {
		this("Portfolio");
	}

	public Portfolio(String title) {
		Stock[] stocks = new Stock[MAX_PORTFOLIO_SIZE];
		setStocks(stocks);
		setTitle(title);
	}

	/**
	 * @param portfolio
	 */
	public Portfolio(Portfolio portfolio) {
		this(portfolio, portfolio.getTitle());
	}

	/**
	 * @param portfolio
	 * @param title
	 */
	public Portfolio(Portfolio portfolio, String title) {
		this(title, portfolio.getStocks(), portfolio.getPortfolioSize());
	}

	/**
	 * Copy constructor
	 * 
	 * @param title
	 * @param stocksToCopy
	 * @param numberOfStocks
	 *            Copies portfolio Runs over stocksToCopy array and adds to
	 *            Stock array
	 */
	public Portfolio(String title, Stock[] stocksToCopy, int numberOfStocks) {
		this(title);
		for (int i = 0; i < numberOfStocks; i++) {
			Stock stockToCopy = new Stock(stocksToCopy[i]);
			addStock(stockToCopy);
		}
	}

	// **************************************
	// Add/remove stock from portfolio
	// **************************************

	/**
	 * Adds stock to Stocks array (if max size not reached) and increments
	 * portfolio size
	 * 
	 * @param Stock
	 * 
	 */
	public void addStock(Stock Stock) {
		addStock(Stock, 1);
	}

	public void addStock(Stock Stock, int quantity) {
		if (portfolioSize < MAX_PORTFOLIO_SIZE) {
			if (!checkIfStockExists(Stock)) {
				stocks[portfolioSize] = Stock;
				setPortfolioSize(portfolioSize + 1);
			}
			Stock.updateStockQuantity(quantity);
		} else {
			System.out.print("Can’t add new stock, portfolio can have only "
					+ MAX_PORTFOLIO_SIZE + " stocks");
		}
	}

	public boolean checkIfStockExists(Stock Stock) {
		Stock[] stocks = getStocks();
		for (int i = 0; i < portfolioSize; i++) {
			if (stocks[i].getSymbol() == Stock.getSymbol()) {
				return true;
			}
		}
		return false;
	}

	public boolean sellStock(String stockSymbol, int quantity) {
		Stock[] stocks = getStocks();
		for (int i = 0; i < portfolioSize; i++) {
			if (stocks[i].getSymbol() == stockSymbol) {
				if (quantity == -1) {
					quantity = stocks[i].getStockQuantity();
				}
				if (quantity <= stocks[i].getStockQuantity()) {
					float revenue = quantity * stocks[i].getBid();
					if (updateBalance(revenue)) {
						stocks[i].updateStockQuantity(0 - quantity);
						return true;
					}
				} else {
					System.out.print("Not enough stocks to sell.");
				}
			}
		}
		return false;
	}

	/**
	 * @param stockSymbol
	 *            Removes stock by stock symbol
	 */
	public boolean removeStock(String stockSymbol, int quantity) {
		Stock[] stocks = getStocks();
		for (int i = 0; i < portfolioSize; i++) {
			String currentStockSymbol = stocks[i].getSymbol();
			if (currentStockSymbol == stockSymbol) {
				sellStock(stockSymbol, quantity);
				removeFromStockArray(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * @param Stock
	 *            stock
	 * 
	 */
	public boolean buyStock(Stock stock, int quantity) {
		// if quantity = -1, get maximum quantity of stocks we can buy
		if (quantity == -1) {
			float currentBalance = getBalance();
			float currentBid = stock.getBid();
			float remainder = currentBalance % currentBid;
			if (remainder == 0) {
				quantity = (int) (currentBalance / currentBid);
			} else {
				quantity = (int) ((currentBalance - remainder) / currentBid);
			}
		}
		float stockPrice = stock.getAsk() * quantity;
		if (updateBalance(0 - stockPrice)) {
			addStock(stock, quantity);
			return true;
		}
		return false;
	}

	/**
	 * @param stockIndex
	 *            Removes stock by looping over all stocks after stock index and
	 *            copying them to previous array position (current pos -1)
	 */
	public void removeFromStockArray(int stockIndex) {
		Stock[] stocks = getStocks();
		if (portfolioSize > 0) {
			for (int i = stockIndex; i < portfolioSize - 1; i++) {
				stocks[i] = stocks[i + 1];
			}
			setStocks(stocks);
			setPortfolioSize(portfolioSize - 1);
		}
	}

	/**
	 * Creates new stock with new bid in position of old stock
	 * 
	 * @param stockIndex
	 * @param newBid
	 */
	public void setNewBid(int stockIndex, float newBid) {
		stocks[stockIndex] = new Stock(stocks[stockIndex], newBid);
	}

	// TODO: If you are asked to buy stocks with more money than available in
	// balance ­ the purchase won’t be executed. Instead, it will print to
	// console: // “Not enough balance to complete purchase.”
	// ******************
	// Update balance
	// ******************
	public boolean updateBalance(float amount) {
		float newBalance = this.balance + amount;
		if (newBalance >= 0) {
			this.balance = newBalance;
			return true;
		} else {
			System.out.print("Not enough balance to complete purchase.");
		}
		return false;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}

	public void setPortfolioSize(int portfolioSize) {
		this.portfolioSize = portfolioSize;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	// ******************
	// Getters
	// ******************

	public String getTitle() {
		return title;
	}

	public Stock[] getStocks() {
		return stocks;
	}

	public int getPortfolioSize() {
		return portfolioSize;
	}

	public float getBalance() {
		return balance;
	}

	public String getHtmlString() {
		String portfolioHtml = "<h1>" + getTitle() + "</h1>";
		portfolioHtml += "Total portfolio value: " + getTotalValue() + ", ";
		portfolioHtml += "Total stocks value: " + getStocksValue() + ", ";	
		portfolioHtml += "Total balance: " + getBalance() + "</br>";
		for (int i = 0; i < portfolioSize; i++) {
			portfolioHtml += stocks[i].getHtmlDescription() + "<br>";
		}
		return portfolioHtml;
	}

	public float getStocksValue() {
		float stocksValue = 0;
		Stock[] stocks = getStocks();
		for (int i = 0; i < portfolioSize; i++) {
			float stockValue = stocks[i].getStockQuantity() * stocks[i].getAsk();
			stocksValue += stockValue;
		}
		return stocksValue;
	}
	public float getTotalValue() {
		return getStocksValue() + getBalance();
	}

}

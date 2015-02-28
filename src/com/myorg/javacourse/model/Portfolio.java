package com.myorg.javacourse.model;

import java.util.*;

/**
 * Portfolio Class Holds an array of stocks and portfolio balance
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
	 * Adds 1 stock to Stocks array
	 * 
	 * @param Stock
	 * 
	 */
	public void addStock(Stock Stock) {
		addStock(Stock, 1);
	}

	/**
	 * Adds set quantity of stock to Stocks array. Checks whether max size not
	 * reached and stock doesn't already exist. Increments portfolio size if
	 * stock added
	 * 
	 * @param Stock
	 * @param quantity
	 * 
	 */
	public void addStock(Stock Stock, int quantity) {
		if (portfolioSize < MAX_PORTFOLIO_SIZE) {
			if (getStockBySymbol(Stock.getSymbol()) == null) {
				stocks[portfolioSize] = Stock;
				setPortfolioSize(portfolioSize + 1);
			}
			Stock.updateStockQuantity(quantity);
		} else {
			System.out.print("Can’t add new stock, portfolio can have only "
					+ MAX_PORTFOLIO_SIZE + " stocks");
		}
	}

	/**
	 * Sells stock with a certain symbol
	 * 
	 * If quantity = -1, sells all stocks of symbol. Else sells specified
	 * quantity
	 * 
	 * @param stockSymbol
	 * @param quantity
	 * 
	 */

	public boolean sellStock(String stockSymbol, int quantity) {
		Stock stockToSell = getStockBySymbol(stockSymbol);
		// Check if stock exists
		if (stockToSell != null) {
			if (quantity == -1) {
				// If quantity = -1 - sell all stock
				quantity = stockToSell.getStockQuantity();
			}
			// Check if have enough stock to sell required quantity
			if (quantity <= stockToSell.getStockQuantity()) {
				float revenue = quantity * stockToSell.getBid();
				updateBalance(revenue);
				stockToSell.updateStockQuantity(0 - quantity);
				return true;
			} else {
				System.out.print("Not enough stocks to sell.");
			}
		}
		return false;
	}

	/**
	 * @param stockSymbol
	 *            Sells stock and removes it from array
	 */
	public boolean removeStock(String stockSymbol, int quantity) {
		Stock stockToRemove = getStockBySymbol(stockSymbol);
		// Check if stock exists
		if (stockToRemove != null) {
			sellStock(stockSymbol, quantity);
			removeStock(stockSymbol);
			return true;
		}
		return false;
	}

	/**
	 * @param Stock
	 * @param quantity
	 * 
	 */
	public boolean buyStock(Stock stock, int quantity) {
		float currentAsk = stock.getAsk();
		// if quantity = -1, get maximum quantity of stocks we can buy
		if (quantity == -1) {
			float currentBalance = getBalance();
			//Checks how many stocks can be bought with current balance
			quantity = (int) (currentBalance / currentAsk);
			if (quantity < 1) {
				System.out
						.print("You do not have enough balance to buy any stocks with symbol: "
								+ stock.getSymbol());
				return false;
			}
		}
		//Calls updateBalance method to see whether there is enough balance left to buy stock
		float totalStockPrice = currentAsk * quantity;
		if (updateBalance(0 - totalStockPrice)) {
			addStock(stock, quantity);
			return true;
		} else {
			System.out.print("You do not have enough balance to buy "
					+ quantity + " stocks with symbol: " + stock.getSymbol());
		}
		return false;
	}

	/**
	 * @param stockIndex
	 *            Removes stock by looping over all stocks after stock index and
	 *            copying them to previous array position (current pos -1) *
	 */
	public void removeStock(String stockSymbol) {
		Stock[] stocks = getStocks();
		int stockIndex = getStockPositionInArray(stockSymbol);
		if (stockIndex != -1 && portfolioSize > 0) {
			for (int i = stockIndex; i < portfolioSize - 1; i++) {
				stocks[i] = stocks[i + 1];
			}
			setStocks(stocks);
			setPortfolioSize(portfolioSize - 1);
		}
	}

	/**
	 * @param StockSymbol
	 *  Returns position of stock in array
	 */

	public int getStockPositionInArray(String StockSymbol) {
		Stock[] stocks = getStocks();
		for (int i = 0; i < portfolioSize; i++) {
			if (stocks[i].getSymbol() == StockSymbol) {
				return i;
			}
		}
		return -1;
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

	/**
	 * Loops over stocks array and returns stock matching a symbol
	 * 
	 * @param StockSymbol
	 * 
	 */

	private Stock getStockBySymbol(String StockSymbol) {
		Stock[] stocks = getStocks();
		for (int i = 0; i < portfolioSize; i++) {
			if (stocks[i].getSymbol() == StockSymbol) {
				return stocks[i];
			}
		}
		return null;
	}

	// ******************
	// Update balance
	// ******************
	
	/**
	 * @param amount
	 * @return true if enough balance to update
	 */
	public boolean updateBalance(float amount) {
		float newBalance = this.balance + amount;
		if (newBalance >= 0) {
			setBalance(newBalance);
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

	private void setPortfolioSize(int portfolioSize) {
		this.portfolioSize = portfolioSize;
	}

	private void setBalance(float balance) {
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
			float stockValue = stocks[i].getStockQuantity()
					* stocks[i].getBid();
			stocksValue += stockValue;
		}
		return stocksValue;
	}

	public float getTotalValue() {
		return getStocksValue() + getBalance();
	}

}

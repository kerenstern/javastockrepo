package com.myorg.javacourse.model;
 
/**
 * Portfolio Class
 * Creates and holds an array of stocks
 * 
 * @author Keren
 * 
 */

public class Portfolio {

	private String title = "default title";
	public Stock stocks[];
	public int portfolioSize = 0;

	private final static int MAX_PORTFOLIO_SIZE = 5;

	private int recommendation;
	private int stockQuantity;
	private int BUY = 0;
	private int SELL = 1;
	private int REMOVE = 2;
	private int HOLD = 3;

// ******************
//		Constructors
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
	 * @param title
	 * @param stocksToCopy
	 * @param numberOfStocks
	 * Copies portfolio
	 * Runs over stocksToCopy array and adds to Stock array
	 */
	public Portfolio(String title, Stock[] stocksToCopy, int numberOfStocks) {
		this(title);
		for (int i = 0; i < numberOfStocks; i++) {
			Stock stockToCopy = new Stock(stocksToCopy[i]);
			addStock(stockToCopy);
		}
	}
	
	// **************************************
    //	Add/remove stock from portfolio
	// **************************************

	/**
	 * Adds stock to Stocks array (if max size not reached) and increments portfolio size
	 * @param Stock
	 */
	public void addStock(Stock Stock) {
		if (portfolioSize < MAX_PORTFOLIO_SIZE) {
			stocks[portfolioSize] = Stock;
			setPortfolioSize(portfolioSize + 1);
		}
	}

	/**
	 * @param stockIndex
	 * Removes stock by looping over all stocks after stock index and copying them to previous array position (current pos -1)
	 */
	public void removeStock(int stockIndex) {
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
	 * @param stockIndex
	 * @param newBid
	 */
	public void setNewBid(int stockIndex, float newBid) {
		stocks[stockIndex] = new Stock(stocks[stockIndex], newBid);
	}

	// ******************
    //	Setters
	// ******************
	public void setTitle(String title) {
		this.title = title;
	}

	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}

	public void setPortfolioSize(int portfolioSize) {
		this.portfolioSize = portfolioSize;
	}

	// ******************
    //	Getters
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

	public String getHtmlString() {
		String portfolioHtml = "<h1>" + getTitle() + "</h1>";
		for (int i = 0; i < portfolioSize; i++) {
			portfolioHtml += stocks[i].getHtmlDescription() + "<br>";
		}
		return portfolioHtml;
	}
	
}

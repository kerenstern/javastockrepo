package com.myorg.javacourse.service;

import com.myorg.javacourse.model.Portfolio;
import com.myorg.javacourse.model.Stock;

/**
 * 
 * 
 * Manages an instance of portfolio and enables copying
 * @author Keren
 *
 */
public class PortfolioManager {

	public Portfolio portfolio;
	String portfolioTitle = "My Portfolio";
	
	/**
	 * Creates portfolio with set values
	 */
	public Portfolio getPortfolio() {
		Portfolio portfolio = new Portfolio();
		
		String portfolioTitle = "Exercise 7 Portfolio";
		portfolio.setTitle(portfolioTitle);
		
		//Add 10,000 to balance
		portfolio.updateBalance(10000);
		
		//Buy 3 stocks
		String PIHSymbol = "PIH";
		Float PIHAsk = (float) 10.0;
		Float PIHBid = (float) 8.5;
		Long PIHDate = 1416002400000L;
		Integer PIHQuantity = 20;
		
		Stock PIHStock = new Stock(PIHSymbol, PIHAsk, PIHBid, PIHDate);
		portfolio.buyStock(PIHStock, PIHQuantity);
		
		String AALSymbol = "AAL";
		Float AALAsk = (float) 30.0;
		Float AALBid = (float) 25.5;	
		Long AALDate = 1416002400000L;	
		Integer AALQuantity = 30;
		
		Stock AALStock = new Stock(AALSymbol, AALAsk, AALBid, AALDate);
		portfolio.buyStock(AALStock, AALQuantity);
		
		String CAASSymbol = "CAAS";
		Float CAASAsk = (float) 20.0;
		Float CAASBid = (float) 15.5;	
		Long CAASDate = 1416002400000L;	
		Integer CAASQuantity = 40;
		
		Stock CAASStock = new Stock(CAASSymbol, CAASAsk, CAASBid, CAASDate);		
		portfolio.buyStock(CAASStock, CAASQuantity);
		
//		Sell all AAL stock
		portfolio.sellStock("AAL", -1);
		
//		Remove + sell CAAS stock
		portfolio.removeStock("CAAS", -1);
		
		return portfolio;
		
	}
	
	
	/**
	 * Returns a copy of portfolio
	 * @param portfolio
	 * @return
	 */
	public Portfolio copyPortfolio(Portfolio portfolio) {
		Portfolio copyPortfolio = new Portfolio(portfolio);
		return copyPortfolio;
	}
	
	/**
	 * Returns a copy of portfolio
	 * @param portfolio
	 * @param title
	 * @return
	 */
	public Portfolio copyPortfolio(Portfolio portfolio, String title) {
		Portfolio copyPortfolio = new Portfolio(portfolio, title);
		return copyPortfolio;
	}

}

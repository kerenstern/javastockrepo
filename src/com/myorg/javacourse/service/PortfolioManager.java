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
	public PortfolioManager() {
		Portfolio portfolio = new Portfolio();
		
		String portfolioTitle = "Portfolio1";
		portfolio.setTitle(portfolioTitle);
		
		String PIHSymbol = "PIH";
		Float PIHAsk = (float) 13.1;
		Float PIHBid = (float) 12.4;
		Long PIHDate = 1416002400000L;
		
		Stock PIHStock = new Stock(PIHSymbol, PIHAsk, PIHBid, PIHDate);
		portfolio.addStock(PIHStock);
		
		String AALSymbol = "AAL";
		Float AALAsk = (float) 5.78;
		Float AALBid = (float) 5.5;	
		Long AALDate = 1416002400000L;	
		
		Stock AALStock = new Stock(AALSymbol, AALAsk, AALBid, AALDate);
		portfolio.addStock(AALStock);
		
		String CAASSymbol = "CAAS";
		Float CAASAsk = (float) 32.2;
		Float CAASBid = (float) 31.5;	
		Long CAASDate = 1416002400000L;		
		
		Stock CAASStock = new Stock(CAASSymbol, CAASAsk, CAASBid, CAASDate);
		
		portfolio.addStock(CAASStock);
		
		setPortfolio(portfolio);
	}
	
	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}
	
	public Portfolio getPortfolio() {
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

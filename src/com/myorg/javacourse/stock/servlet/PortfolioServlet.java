package com.myorg.javacourse.stock.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myorg.javacourse.service.*;
import com.myorg.javacourse.model.*;

public class PortfolioServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("text/html");
		
		PortfolioManager portfolioManager = new PortfolioManager();
		
		Portfolio portfolio1 = portfolioManager.getPortfolio();
		resp.getWriter().println(portfolio1.getHtmlString());
		
		String portfolio2Title = "Portfolio 2";
		Portfolio portfolio2 = portfolioManager.copyPortfolio(portfolio1, portfolio2Title);		
		resp.getWriter().println(portfolio2.getHtmlString());
			
		int removeStockIndex = 0;
		portfolio1.removeStock(removeStockIndex);
		resp.getWriter().println(portfolio1.getHtmlString());
		
		resp.getWriter().println(portfolio2.getHtmlString());
		
		int portfolio2LastBidIndex = portfolio2.getPortfolioSize() - 1;
		float newBid = 55.55f;
		portfolio2.setNewBid(portfolio2LastBidIndex, newBid);
		
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		
	}

}

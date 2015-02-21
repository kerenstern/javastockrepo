package com.myorg.javacourse.servlet;

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
		
		// Defines portfolio 1
		Portfolio portfolio1 = portfolioManager.getPortfolio();
		resp.getWriter().println(portfolio1.getHtmlString());

		resp.getWriter().println("<h2>Sell AAL</h2>");
		portfolio1.sellStock("AAL", -1);
		resp.getWriter().println(portfolio1.getHtmlString());
		
		resp.getWriter().println("<h2>Remove CAAS</h2>");
		portfolio1.removeStock("CAAS", -1);
		resp.getWriter().println(portfolio1.getHtmlString());

//		//Returns a copy of portfolio1 with a new title and sets it to portfolio2
//		String portfolio2Title = "Portfolio 2";
//		Portfolio portfolio2 = portfolioManager.copyPortfolio(portfolio1, portfolio2Title);		
//		resp.getWriter().println(portfolio2.getHtmlString());
//			
////		Removes first stock from portfolio1
//		int removeStockIndex = 0;
//		portfolio1.removeFromStockArray(removeStockIndex);
//		resp.getWriter().println(portfolio1.getHtmlString());		
//		resp.getWriter().println(portfolio2.getHtmlString());
//		
////		Changes bid of last stock in portfolio2
//		int portfolio2LastBidIndex = portfolio2.getPortfolioSize() - 1;
//		float newBid = 55.55f;
//		portfolio2.setNewBid(portfolio2LastBidIndex, newBid);
//		
//		resp.getWriter().println(portfolio1.getHtmlString());
//		resp.getWriter().println(portfolio2.getHtmlString());
	}
}

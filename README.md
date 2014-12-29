Myappreadme

package com.myorg.javacourse;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Exercise_2Servlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		int num1, num2, num3, result;
		String resultString;
		num1 = 4;
		num2 = 3;
		num3 = 7;
		result = (num1 + num2) * num3;
		resultString = new String("<h1>Result of "+num1+" + "+num2+ "*" +num3+"="+result+"</h1>");
		resp.setContentType("text/html");
		resp.getWriter().println("Hello, world");
		resp.getWriter().println(resultString);
	}
}

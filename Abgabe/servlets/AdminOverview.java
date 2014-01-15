import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class AdminOverview extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
	
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession();

		String funktion  = req.getParameter("f");
		String userID    = (String) session.getAttribute("uid");
		String userRolle = (String) session.getAttribute("urolle");


		out.println("ausgabe");
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doGet(req,res);
	}
}
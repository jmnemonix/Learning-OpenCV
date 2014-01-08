/*

	Kleiner Wahrenkorb


*/

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class KlWarenkorb extends HttpServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		// String sqlUsr  = "dw54";
		// String sqlPswd = "";

		HttpSession session = req.getSession();
	
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		
		out.println("Warenkorb");
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		doPost(req,res);
	}
}

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class Fehler extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
	
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String fehler  = req.getParameter("e");

		if(fehler.equals("120")){
			out.println("&Uuml;berpr&uuml;ffe bitte deine Nutzerdaten!");
		}
	}
}
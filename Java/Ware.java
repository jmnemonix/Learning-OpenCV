/*

Login Management


*/

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class LogIn extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		String sqlUsr  = "dw54";
		String sqlPswd = "";

		String WarenURL = "http://praxi.mt.haw-hamburg.de/~dw54/"; // Ware in JSP EInpflegen

		HttpSession session = req.getSession();
	
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		String wgruppe = req.getParameter("wgruppe");
		
		
		try{
			Class.forName("org.gjt.mm.mysql.Driver");
		}
		catch (ClassNotFoundException e){
			out.println("DB-Treiber nicht da!");
		}

		try{
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+sqlUsr, sqlUsr, sqlPswd);

			Statement st = con.createStatement();

			
			ResultSet rs = st.executeQuery("SELECT * FROM ware WHERE warengruppe LIKE '"+wgruppe+"'");

			while(rs.next()){
				int		wid				= (int)		rs.getString("id");
				String	wname			= 			rs.getString("name");
				//String	wbeschreibung	= 			rs.getString("beschreibung");
				//int		wbestand		= (int) 	rs.getString("bestand");
				//int		wverkaufbar		= (int) 	rs.getString("verkaufbar");
				//String	wbildUrl		=			rs.getString("bildUrl");
				//int		warengruppe		= (int)		rs.getString("warengruppe");
				float	wpreis			= (float)	rs.getString("preis");

				out.println("<div class='ware' id='"+wid+"'><img src='"+wid+".jpg' alt='"+wid+".jpg'><a class='wname' href='"+WarenURL+"?w="+wid+"'>"+wname+"</a></div>");
			}

			
			st.close();
			con.close();
		}
		catch (Exception e){
			out.println(" MySQL Exception: " + e.getMessage());
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doGet(req,res);
	}
}

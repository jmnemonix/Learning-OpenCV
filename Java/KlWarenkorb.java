/*

	Kleiner Wahrenkorb


*/

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.text.DecimalFormat;

public class KlWarenkorb extends HttpServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		String sqlUsr  = "dw54";
		String sqlPswd = "";

		HttpSession session = req.getSession();
	
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		String userID    = (String) session.getAttribute("uid");

		try{ Class.forName("org.gjt.mm.mysql.Driver"); }
		catch (ClassNotFoundException e){ out.println("DB-Treiber nicht da!"); }

		/*	Es wird kurz ueberpruefft, ob ein Benutzer schon eingeloggt ist
			Da er sich dann nicht mehr registrieren darf */
		String logCheck = "";
		logCheck = (String) session.getAttribute("iEingeloggt");
		if(logCheck.equals("true")) {
			try{
				
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+sqlUsr, sqlUsr, sqlPswd);

				Statement st1 = con.createStatement();

				// Suche einen Warenkorb raus (Status = 0)
				ResultSet rs1 = st1.executeQuery("SELECT * FROM warenkorb WHERE kundeID LIKE '"+userID+"'");


				int     summeWare   = 0;
				double  summe       = 0.0;
				int 	position    = 0;

				while(rs1.next()){
					String wareID = rs1.getString("wareID");
					int    anzahl = rs1.getInt("anzahl");

					Statement st2 = con.createStatement();
					ResultSet rs2 = st2.executeQuery("SELECT * FROM ware WHERE id LIKE '"+wareID+"'");


					double preis = 0;
					while(rs2.next()){
						preis = rs2.getDouble("preis");
					}
					st2.close();

					position++;


					summeWare = summeWare+anzahl;
					summe = summe+(anzahl*preis);



				}
				if(position <= 0) out.println("Du hast nichts im Warenkorb");
				else			 out.println("<a href='index.jsp?p=wagen'>Du hast "+position+" Produkte ("+summeWare+" insg.) f&uuml;r "+format(summe)+" &euro; im Warenkorb</a>");
				
				st1.close();
				con.close();
			}
			catch (Exception e){
				out.println(" MySQL Exception: " + e.getMessage());
			}
		}
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doPost(req,res);
	}
	public static String format(double i){
		DecimalFormat f = new DecimalFormat("#0.00");
		double toFormat = ((double)Math.round(i*100))/100;
		return f.format(toFormat);
	}
}

/*

	Kleiner Wahrenkorb


*/

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class Bestellen extends HttpServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		String sqlUsr  = "dw54";
		String sqlPswd = "";

		HttpSession session = req.getSession();
	
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		String userID    = (String) session.getAttribute("uid");

		String funktion = "";

		funktion     = req.getParameter("f");
		String myAdresse    = req.getParameter("adresse");
		String myBestellen  = req.getParameter("bestellen");
		String myLeeren     = req.getParameter("leeren");


		try{ Class.forName("org.gjt.mm.mysql.Driver"); }
		catch (ClassNotFoundException e){ out.println("DB-Treiber nicht da!"); }

		/*	Es wird kurz ueberpruefft, ob ein Benutzer schon eingeloggt ist
			Da er sich dann nicht mehr registrieren darf */
		String logCheck = "";
		logCheck = (String) session.getAttribute("iEingeloggt");
/*
		if((funktion.equals("aendern"))&&(logCheck.equals("true"))) {
			String wareID   = req.getParameter("id");
			String anzahl   = req.getParameter("anzahl");
			String aendern  = req.getParameter("aendern");
			String loeschen = req.getParameter("loeschen");

			out.println("eingabe: "+wareID+" - "+anzahl+" - "+aendern+" - "+loeschen);

		}*/
		
		if(logCheck.equals("true")) {
			try{
				
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+sqlUsr, sqlUsr, sqlPswd);

				Statement st1 = con.createStatement();

				if(myBestellen == "Bestellen"){

					// Suche einen Warenkorb raus (Status = 0)
					ResultSet rs1 = st1.executeQuery("SELECT * FROM warenkorb WHERE kundeID LIKE '"+userID+"'");


					int     summeWare   = 0;
					double  summe       = 0.0;
					int 	position    = 0;
					String  ausgabe     = "";

					int i = st1.executeUpdate("INSERT INTO `bestellungen` (`id` ,`kundeID` ,`adresse` ,`bemerkung` ,`status`)VALUES (NULL , '"+userID+"', 'RDBstr. 12 202020 Hamburg', '', '1')");
					Statement st2 = con.createStatement();

					// Suche einen Warenkorb raus (Status = 0)
					ResultSet rs2 = st2.executeQuery("SELECT * FROM bestellungen WHERE kundeID LIKE '"+userID+"' AND status=1");
					boolean erster = false;
					while(rs2.next()){
						String neueBestID = rs2.getString("id");
						if(!erster){
							while(rs1.next()){
								String wareID = rs1.getString("wareID");
								int    anzahl = rs1.getInt("anzahl");
								position++;
								int j = st1.executeUpdate("INSERT INTO `bestWare` (`bestellungID` ,`position` ,`wareID` ,`anzahl`)VALUES ('"+neueBestID+"', '"+position+"', '"+wareID+"', '"+anzahl+"')");
								//int i = st.executeUpdate("insert into `benutzer` (`name`, `email`, `passwort`, `rolle`)  values ('" + name + "','" + email + "','" + pswd + "','10')");
							}
						}
					}


					out.println(ausgabe);
				}
				else if(myLeeren == "leeren"){
					int k = st1.executeUpdate("DELETE FROM `warenkorb` WHERE `warenkorb`.`kundeID` = "+userID);

				}
				st1.close();
				con.close();
				res.sendRedirect( "http://praxi.mt.haw-hamburg.de/~dw54/" );
			}
			catch (Exception e){
				out.println(" MySQL Exception: " + e.getMessage());
			}
		}
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doPost(req,res);
	}
}

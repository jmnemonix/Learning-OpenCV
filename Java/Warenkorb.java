/*

	Kleiner Wahrenkorb


*/

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class Warenkorb extends HttpServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		String sqlUsr  = "dw54";
		String sqlPswd = "";

		HttpSession session = req.getSession();
	
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		String userID    = (String) session.getAttribute("uid");

		String funktion = "";
		funktion  = req.getParameter("f");

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

				// Suche einen Warenkorb raus (Status = 0)
				ResultSet rs1 = st1.executeQuery("SELECT * FROM warenkorb WHERE kundeID LIKE '"+userID+"'");




				int     summeWare   = 0;
				double  summe       = 0.0;
				int 	position    = 0;
				String  ausgabe     = "";

				ausgabe = ausgabe+"<div class='Warenkorb'>";
				ausgabe = ausgabe + "<table border='0'><tr><th>Position</th><th>Bezeichnung</th><th>Anzahl</th><th>Preis</th><th>Betrag</th><th>L&ouml;schen</th></tr>\n";

				while(rs1.next()){
					
					String wareID = rs1.getString("wareID");
					int    anzahl = rs1.getInt("anzahl");
					position++;

					Statement st2 = con.createStatement();
					ResultSet rs2 = st2.executeQuery("SELECT * FROM ware WHERE id LIKE '"+wareID+"'");

					double preis = 0;
					String bezeichnung = "";
					while(rs2.next()){
						preis = rs2.getDouble("preis");
						bezeichnung = rs2.getString("name");
					}
					st2.close();

					ausgabe = ausgabe+"\n<tr><th><form method='POST' action='http://praxi.mt.haw-hamburg.de/~dw54/servlet/Warenkorb'><input type='hidden' name='f' value='aendern'>"+position+"</th><th>"+bezeichnung+"</th><th><input type='hidden' name='id' value='"+wareID+"'><input type='text' name='anzahl' value='"+anzahl+"'></th><th>"+preis+" &euro;</th><th>"+(anzahl*preis)+" &euro;</th><th><input type='submit' value='Aendern' name='aendern' disabled><input type='submit' value='Loeschen' name='loeschen' disabled></form></th></tr>";

					summeWare = summeWare+anzahl;
					summe = summe+(anzahl*preis);
				}


				ausgabe = ausgabe+"\n<tr><th>&nbsp;</th><th>&nbsp;</th><th>&nbsp;</th><th>&nbsp;</th><th>&nbsp;</th><th>&nbsp;</th></tr>";
				ausgabe = ausgabe+"\n<tr><th>Summe</th><th>&nbsp;</th><th>&nbsp;</th><th>&nbsp;</th><th>"+summe+" &euro;</th><th>&nbsp;</th></tr>";

				ausgabe = ausgabe+"\n</table>";
				ausgabe = ausgabe+"\n<form method='GET' action='http://praxi.mt.haw-hamburg.de/~dw54/servlet/Bestellen'>Adresse: <input type='text' name='adresse'>\n<input type='submit' value='Bestellen' name='bestellen'><input type='submit' value='Leeren' name='leeren'></table>";
				ausgabe = ausgabe+"</div>";

				out.println(ausgabe);
				
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
}

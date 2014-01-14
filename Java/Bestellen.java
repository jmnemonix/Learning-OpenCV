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

		boolean gibAus = false;


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

				if(myBestellen.equals("Bestellen")){

					// Den Eigenen Warenkorb auslesen
					ResultSet rs1 = st1.executeQuery("SELECT * FROM warenkorb WHERE kundeID LIKE '"+userID+"'");

					// Nachprueffen ob es noch offene Bestellungen gibt
					Statement st2 = con.createStatement();
					ResultSet rs2 = st2.executeQuery("SELECT * FROM bestellungen WHERE kundeID LIKE '"+userID+"' AND status=1");
					int alteBestID = 0;

					int position = 0;
					while(rs2.next()){
						// die letzte offene Bestellung wird hierbei uebernommen
						alteBestID = rs2.getInt("id");
						if(gibAus) out.println("<br>hat id: "+alteBestID);

						// TODO um fehler zu vermeiden, muss unbedingt geguckt werden wieviele positionen fuer diese Bestellung schon existieren !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
						// Es wird die anzahl der Positionen in der gegebenen Bestellung ermittelt
						Statement st6 = con.createStatement();
						ResultSet rs6 = st6.executeQuery("SELECT * FROM bestWare WHERE bestellungID LIKE '"+alteBestID+"'");
						position = 0;
						while(rs6.next()){
							position++;
						}
						st6.close();
					}
					if(gibAus) out.println("<br>positionen: "+position);

					if(alteBestID == 0){
						// Sollte es noch keine offene Bestellung (Status == 1) fuer diesen Kunden geben, wird eine angelegt

						position = 0;
						Statement st4 = con.createStatement();
						int best = st4.executeUpdate("INSERT INTO `bestellungen` (`id` ,`kundeID` ,`adresse` ,`bemerkung` ,`status`) VALUES (NULL , '"+userID+"', '"+myAdresse+"', '', '1')");

						if(gibAus) out.println("<br>best: "+best);
						
						// als naechstes wird von der neu angelegten Bestellung die id rausgefunden
						Statement st3 = con.createStatement();
						ResultSet rs3 = st3.executeQuery("SELECT * FROM bestellungen WHERE kundeID LIKE '"+userID+"' AND status=1");
						while(rs3.next()){
							// die letzte offene Bestellung wird hierbei uebernommen
							alteBestID = rs3.getInt("id");
							if(gibAus) out.println("<br>hat id: "+alteBestID);
						}
						st3.close();
						st4.close();
					}

					// die einzelnen positionen der Bestellung aus dem Warenkorb holen und in bestWare schreiben
					while(rs1.next()){
						position++;
						String wareID = rs1.getString("wareID");
						int    anzahl = rs1.getInt("anzahl");


						if(gibAus) out.println("<br>ware id: "+wareID+" anzahl "+anzahl);

						Statement st5 = con.createStatement();
						int bestPosition = st5.executeUpdate("INSERT INTO `bestWare` (`bestellungID` ,`position` ,`wareID` ,`anzahl`)VALUES ('"+alteBestID+"', '"+position+"', '"+wareID+"', '"+anzahl+"')");


						if(gibAus) out.println("<br>bestPosition: "+bestPosition);
						st5.close();
					}

					if(gibAus) out.println("wir sind am ende");
					Statement st7 = con.createStatement();

					// die bestellung wird abgeschlossen indem der status auf 2 geaendert wird
					int k = st7.executeUpdate("UPDATE `bestellungen` SET `status` = '2' WHERE `kundeID` = "+userID+" AND `status` = '1'");

					// nun wird der Waarenkorb geleert
					k = st7.executeUpdate("DELETE FROM `warenkorb` WHERE `warenkorb`.`kundeID` = "+userID);

					if(gibAus) out.println("<br><br>Warenkorb leer");
					else res.sendRedirect( "http://praxi.mt.haw-hamburg.de/~dw54/" );


					st7.close();
					st2.close();
				}
				else if(myLeeren == "leeren"){
					int k = st1.executeUpdate("DELETE FROM `warenkorb` WHERE `warenkorb`.`kundeID` = "+userID);

					if(gibAus){
						if(k > 0) out.println("leer "+k);
						else out.println("nein nicht leer "+k);
					}
					if(!gibAus) res.sendRedirect( "http://praxi.mt.haw-hamburg.de/~dw54/" );
				}


				st1.close();
				con.close();
				//res.sendRedirect( "http://praxi.mt.haw-hamburg.de/~dw54/" );
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
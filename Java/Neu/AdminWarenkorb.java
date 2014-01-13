/*

	Kleiner Wahrenkorb


*/

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class AdminWarenkorb extends HttpServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		String sqlUsr  = "dw54";
		String sqlPswd = "";

		HttpSession session = req.getSession();
	
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		String userID    = (String) session.getAttribute("uid");
		String uRolle    = (String) session.getAttribute("urolle");

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
		
		if((logCheck.equals("true"))&&(uRolle.equals("1"))) {
			try{
				
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+sqlUsr, sqlUsr, sqlPswd);

				Statement st1 = con.createStatement();

				// Suche einen Warenkorb raus (Status = 0)
				ResultSet rs1 = st1.executeQuery("SELECT `warenkorb`.`kundeID`, `warenkorb`.`wareID`, `warenkorb`.`anzahl`, `ware`.`name` FROM `warenkorb`, `ware` WHERE `warenkorb`.`wareID` = `ware`.`id` ORDER BY `warenkorb`.`kundeID`");




				String  ausgabe     = "";

				ausgabe = ausgabe+"<div class='WarenkorbListe'>";
				ausgabe = ausgabe + "<table border='0'><tr><th>Kundennr</th><th>Ware ID</th><th>Bezeichnung</th><th>Anzahl</th><th>Optionen</th></tr>\n";

				while(rs1.next()){
					String kundeID = rs1.getString("kundeID");
					String wareID  = rs1.getString("wareID");
					int    anzahl  = rs1.getInt("anzahl");
					String wName   = rs1.getString("name");

					ausgabe = ausgabe+"\n<tr><th><a href='index.jsp?p=admin&adm=5&uid="+kundeID+"'>"+kundeID+"</a></th><th>"+wareID+"</th><th>"+wName+"</th><th>"+anzahl+"</th><th>&nbsp;</th></tr>";

				}

				ausgabe = ausgabe+"\n</table>";
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

/*

Benutzerverwaltung

Funktionen 		Umgesetzt
- benutzer 		ja
- change		nein
- admls			nein
- delete 		nein
- activate 		nein


*/

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class Ware extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		String sqlUsr  = "dw54";
    	String sqlPswd = "";
	
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession();

		String funktion  = req.getParameter("f");
		String userRolle = (String) session.getAttribute("urolle");

		String logCheck = (String) session.getAttribute("iEingeloggt");


		//out.println("system message: funktion: ("+funktion+") logCheck: ("+logCheck+") userID: ("+userID+")");


		//DB-Treiber einbinden
		try { Class.forName("org.gjt.mm.mysql.Driver"); }
		catch (ClassNotFoundException e) { out.println("DB-Treiber nicht da!"); }

		try
		{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+sqlUsr, sqlUsr, sqlPswd);
			Statement st = con.createStatement();

			if(logCheck.equals("true")){
				if ((funktion.equals("admchange"))&&(userRolle.equals("1"))) { // -------------------------------------------------------------- der Admin aendert ein Produkt
					String produktID = req.getParameter("pr");

					ResultSet rs = st.executeQuery("SELECT * FROM ware WHERE id LIKE '"+produktID+"'");

					while(rs.next())
					{
						String pid    = rs.getString("id");
						String pname  = rs.getString("name");
						String pwg    = rs.getString("warengruppe");
						String pbild  = rs.getString("bild");
						String pbest  = rs.getString("bestand");
						String pbesch = rs.getString("beschreibung");
						String ppreis = rs.getString("preis");

						String zeile0 = "<form method='POST' action='http://praxi.mt.haw-hamburg.de/servlet/Ware'><input type='hidden' name='f' value='pchange'><table border='0'>";
						String zeile1 = "<tr><th>Produkt Nr</th><th>"+pid+"<input type='hidden' name='pid' value="+pid+"></th></tr><tr><th>Warengruppe</th><th><input type='text' name='wgruppe' value='"+pwg+"'></th></tr>";
						String zeile2 = "<tr><th>Name</th><th><input type='text' name='pname' value='"+pname+"'></th></tr>";
						String zeile3 = "<tr><th><img src='"+pbild+"' alt='Bild'></th><th><input type='text' name='bild' value='"+pbild+"'></th></tr>";
						String zeile4 = "<tr><th>Bestand</th><th><input type='text' name='bestand' value='"+pbest+"'></th></tr>";
						String zeile5 = "<tr><th>Beschreibung</th><th><input type='text' name='beschreib' value='"+pbesch+"'></th></tr>";
						String zeile6 = "<tr><th>Preis</th><th><input type='text' name='preis' value='"+ppreis+"'> &euro;</th></tr>";
						String zeile7 = "</table><input type='submit' value='&Auml;ndern' name='pUpd'><input type='submit' value='L&ouml;schen' name='pDel'></form>";

						out.println( zeile0+zeile1+zeile2+zeile3+zeile4+zeile5+zeile6+zeile7);

					}

					st.close();
					con.close();

				}
				else if ((funktion.equals("newForm"))&&(userRolle.equals("1"))) { // -------------------------------------------------------------- der Admin erstellt ein neues Produkt


					String zeile0 = "<form method='POST' action='http://praxi.mt.haw-hamburg.de/servlet/Ware'><input type='hidden' name='f' value='pnew'><table border='0'>";
					String zeile1 = "<tr><th>Warengruppe</th><th><input type='text' name='wgruppe'></th></tr>";
					String zeile2 = "<tr><th>Name</th><th><input type='text' name='pname'></th></tr>";
					String zeile3 = "<tr><th>Bild</th><th><input type='text' name='bild'></th></tr>";
					String zeile4 = "<tr><th>Bestand</th><th><input type='text' name='bestand'></th></tr>";
					String zeile5 = "<tr><th>Beschreibung</th><th><input type='text' name='beschreib'></th></tr>";
					String zeile6 = "<tr><th>Preis</th><th><input type='text' name='preis'> &euro;</th></tr>";
					String zeile7 = "</table><input type='submit' value='Senden' name='senden'></form>";

					out.println( zeile0+zeile1+zeile2+zeile3+zeile4+zeile5+zeile6+zeile7);

				}
				else if ((funktion.equals("delete"))&&(userRolle.equals("1"))) { // ----------------------------------------------------------------- Produkt Loeschen
					String produktID = req.getParameter("pr");

					int i = st.executeUpdate("DELETE FROM ware WHERE id = "+produktID);

					out.println("Produkt ID: "+produktID+"<br> L&ouml;sch-Status: "+i);

				}
				else if ((funktion.equals("admls"))&&(userRolle.equals("1"))) { // ------------------------------------------------------------------ Produkte auflisten

					ResultSet rs = st.executeQuery("SELECT * FROM `ware`");
					out.println("<table border='0'><tr><th>ID</th><th>Warengruppe</th><th>Name</th><th>Bestand</th><th>Preis</th><th>Optionen</th></tr>");

					while(rs.next())
					{
						String pid    = rs.getString("id");
						String pname  = rs.getString("name");
						String pwg    = rs.getString("warengruppe");
						String pbest  = rs.getString("bestand");
						String ppreis = rs.getString("preis");

						
						out.println("<tr><th>"+pid+"</th><th>"+pwg+"</th><th>"+pname+"</th><th>"+pbest+"</th><th>"+ppreis+" &euro;</th><th><a href='index.jsp?p=admin&adm=6&pid="+pid+"'>Bearbeiten</a></th></tr>");

					}
					//out.println("<tr><th>&nbsp;</th><th>"+pwg+"</th><th>"+pname+"</th><th>"+pbest+"</th><th>"+ppreis+" &euro;</th><th><a href='index.jsp?p=admin&adm=6&pid="+pid+"'>Bearbeiten</a></th></tr>");
					out.println("</table>");
					out.println("<a href='index.jsp?p=admin&adm=7'>Neues Produkt Hinzuf&uuml;gen</a>");

					st.close();
					con.close();
				}
				else out.println("Keine Daten ...");
			}
			else out.println("kein logcheck");
		}
		catch (Exception e)
		{
			out.println(" MySQL Exception: " + e.getMessage());
		}

		
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		doGet(req,res);
	}
}

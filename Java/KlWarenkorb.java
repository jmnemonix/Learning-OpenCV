/*

	Kleiner Wahrenkorb


*/

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

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
				ResultSet rs1 = st1.executeQuery("SELECT * FROM bestellungen WHERE kundeID LIKE '"+userID+"' AND status LIKE '0'");

				boolean keinEintrag = true;
				int     anzahlWare  = 0;
				int     summeWare   = 0;
				double  summe       = 0.0;

				while(rs1.next()){
					String bestID = rs1.getString("id");

					Statement st2 = con.createStatement();

					ResultSet rs2 = st2.executeQuery("SELECT * FROM bestWare WHERE bestellungID LIKE '"+bestID+"'");

					while(rs2.next()){
						int anzahl    = rs2.getInt("anzahl");
						anzahlWare    = anzahlWare+1;
						summeWare     = summeWare+anzahl;

						String wareID = rs2.getString("wareID");

						Statement st3 = con.createStatement();

						ResultSet rs3 = st3.executeQuery("SELECT * FROM ware WHERE id LIKE '"+wareID+"'");
						int preis = 0;
						while(rs3.next()){
							preis = rs3.getInt("preis");
						}
						st3.close();

						summe = summe+(anzahl*preis);
						keinEintrag = false;
					}
					st2.close();
				}
				if(keinEintrag) out.println("Du hast nichts im Warenkorb");
				else			out.println("<a href=''>Du hast "+anzahlWare+"("+summeWare+") Produkte ("+summe+" &euro;) im Warenkorb</a>");
				
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

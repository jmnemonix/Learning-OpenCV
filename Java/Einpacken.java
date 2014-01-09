/*

Register Management


*/

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class Einpacken extends HttpServlet{


	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		String sqlUsr  = "dw54";
		String sqlPswd = "";

		HttpSession session = req.getSession();
	
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		String produktID  = req.getParameter("pr");
		String userID     = (String) session.getAttribute("uid");

		try{ Class.forName("org.gjt.mm.mysql.Driver"); }
		catch (ClassNotFoundException e){ out.println("DB-Treiber nicht da!"); }

		/*	Es wird kurz ueberpruefft, ob ein Benutzer schon eingeloggt ist
			Da er sich dann nicht mehr registrieren darf */
		String logCheck = "";
		logCheck = (String) session.getAttribute("iEingeloggt");
		if(logCheck.equals("true")) {
			try{
				
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+sqlUsr, sqlUsr, sqlPswd);

				Statement st = con.createStatement();

				ResultSet rs = st.executeQuery("SELECT * FROM warenkorb WHERE kundeID LIKE '"+userID+"' AND wareID LIKE '"+produktID+"'");

				boolean keinEintrag = true;
				int anzahl = 0;

				while(rs.next()){
					anzahl = rs.getInt("anzahl");
					keinEintrag = false;
				}
				if(!keinEintrag){
					anzahl++;

					int i = st.executeUpdate("UPDATE `warenkorb` SET `anzahl` = '"+anzahl+"' WHERE `warenkorb`.`kundeID` ="+userID+" AND `warenkorb`.`wareID` ="+produktID+" ");
				}
				else{
					int i = st.executeUpdate("INSERT INTO `warenkorb` (`kundeID`, `wareID`, `anzahl`) VALUES ('"+userID+"', '"+produktID+"', '1')");
				}

				Statement st2 = con.createStatement();
				ResultSet rs2 = st2.executeQuery("SELECT * FROM ware WHERE id LIKE '"+produktID+"'");
				String warengruppe = "1";
				while(rs2.next()){
					warengruppe = rs2.getString("warengruppe");
				}
				res.sendRedirect( "http://praxi.mt.haw-hamburg.de/~dw54/index.jsp?p=katalog&wg="+warengruppe );
				//out.println("ende");
				
				st.close();
				con.close();
			}
			catch (Exception e){
				out.println(" MySQL Exception: " + e.getMessage());
			}
		}
	}
}
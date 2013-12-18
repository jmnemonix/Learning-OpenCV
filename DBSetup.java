/*

	DB Setup
	========
	Datenbanken einmal einrichten!

*/
// TODO: das teil hier in eine JSP packen

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class DBSetup{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		String telname = req.getParameter("tname");
		String telnr= req.getParameter("tnr");
		int i = Integer.parseInt(telnr);

//DB-Treiber einbinden
		try{
			Class.forName("org.gjt.mm.mysql.Driver");  //Da sind die Treiber
		}
		catch (ClassNotFoundException e){
			out.println("DB-Treiber nicht da!");
		}

// Connection zum DB-Server eroeffnen
		try{
			
			
			try {
				BufferedReader in = new BufferedReader(new FileReader("../../../dbsetup.txt")); // Textdatei von "/public_html/WEB-INF/classes" aus gesehen
				String zeile = null;
				String usr  = in.readLine();
				String pswd = in.readLine(); // ACHTUNG DATEI MUSS MINDESTENS ZWEI ZEILEN HABEN --- SONST EXCEPTION!
				
				
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+usr, usr, pswd);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			
			

//Jetzt einen SQL-Befehl vorbereiten
			Statement st = con.createStatement();  //(Noch) leerer SQL-Befehl
			try{
				st.executeUpdate("drop table if exists tel;");
			}
			catch (Exception e){
				out.println("Problem mit: DROP TABLE tel !!");
			}
// Jetzt normales SQL-Skript, aber innerhalb des Servlets
			st.executeUpdate("create table Benutzer(name char(11), nr int)");
			st.executeUpdate("insert into tel values ('Hugo',4444)");
			st.executeUpdate("insert into tel values ('Mini',4444)");
//Dynamisch eingegebene Daten
			st.executeUpdate("insert into tel values ('"+telname+"',"+telnr+")");

//Response Webseite aufbauen
			out.println("<html><head><title>DB Setup</title></head>");
			out.println("<body>DB Setup<hr><br>");

//ResultSet mit Cursor bearbeiten und ausgeben
			ResultSet rs = st.executeQuery("select * from tel");

//Hier die Cursor-Schleife
			while(rs.next()){
				String sname = rs.getString("name");
				String snr = rs.getString("nr");
				out.println(sname + "  " + snr + "<br> ");
			}
			out.println("<hr> </body> </html>");
			st.close();
			con.close();
		}
		catch (Exception e){
			out.println(" MySQL Exception: " + e.getMessage());
		}
	}
}
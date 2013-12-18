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
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dwNN", "dwNN", "****");

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
			st.executeUpdate("insert into tel values ('Hajo',5555)");
			st.executeUpdate("insert into tel values ('Maxi',7777)");
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
/*

LogIn Class


*/

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class LogIn extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		String email = req.getParameter("email");
		String pswd = req.getParameter("pswd");
		

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

			}
			catch (IOException e) {
				e.printStackTrace();
			}
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+usr, usr, pswd);

//Jetzt einen SQL-Befehl vorbereiten
			Statement st = con.createStatement();  //(Noch) leerer SQL-Befehl

//Response Webseite aufbauen
			out.println("<html><head><title>Login Test</title></head>");
			out.println("<body>Login Test<hr><br>");

//ResultSet mit Cursor bearbeiten und ausgeben
			String queryString = "SELECT * FROM benutzer WHERE email LIKE '"+email+"'";
			out.println(queryString);

			ResultSet rs = st.executeQuery(queryString);

//Hier die Cursor-Schleife
			while(rs.next()){
				String id = rs.getString("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String passwort = rs.getString("passwort");
				String rolle = rs.getString("rolle");
				out.println("<br>Name: "+name+" EMail: "+email+" PSWD: "+passwort+" Rolle: "+rolle);
			}
			out.println("<hr> </body> </html>");
			st.close();
			con.close();
		}
		catch (Exception e){
			out.println(" MySQL Exception: " + e.getMessage());
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doGet(req,res);
	}
}

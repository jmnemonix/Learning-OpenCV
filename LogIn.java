/*

LogIn Class


*/

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class LogIn extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
	// TODO: in POST Methode umwandeln
	
		HttpSession session = req.getSession();
	
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
			String sqlUsr  = "dw54";
			String sqlPswd = "";
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+sqlUsr, sqlUsr, sqlPswd);

//Jetzt einen SQL-Befehl vorbereiten
			Statement st = con.createStatement();  //(Noch) leerer SQL-Befehl

//Response Webseite aufbauen
			//out.println("<html><head><title>Login Test</title></head>");
			//out.println("<body>Login Test<hr><br><br><br>");
			
			//out.println(" <br>eingaben email: "+email+" und password: ("+pswd+") <br>");
			//out.println("<form method='get' action = 'http://praxi.mt.haw-hamburg.de/~dw54/servlet/LogIn'>Login: <input type='email' name='email'><input type='password' name='pswd'><input type='submit' value='Submit' name='absenden'></form> ");
			
			
			//String queryString = "SELECT * FROM benutzer WHERE email LIKE '"+email+"'";
			//out.println(queryString);
			
			ResultSet rs = st.executeQuery("SELECT * FROM benutzer WHERE email LIKE '"+email+"'");
			//ResultSet rs = st.executeQuery(queryString);

//Hier die Cursor-Schleife
			while(rs.next()){
				String oid = rs.getString("id");
				String oname = rs.getString("name");
				String oemail = rs.getString("email");
				String opasswort = rs.getString("passwort");
				String orolle = rs.getString("rolle");
				out.println("<br>"+oid+" Name: "+oname+" EMail: "+oemail+" PSWD: ("+opasswort+") Rolle: "+orolle+"<br><br>");
				
				
				if((oemail.equals(email))&&(opasswort.equals(pswd))){
					session.setAttribute("iEingeloggt", "true");
					session.setAttribute("uname",oname);
					session.setAttribute("umail",oemail);
					session.setAttribute("urolle",orolle);
					session.setAttribute("uid",oid);
					
					res.sendRedirect( "http://praxi.mt.haw-hamburg.de/~dw54/" );
				}
				else{
					session.invalidate();
					session.setAttribute("iEingeloggt", "false");
					
					res.sendRedirect( "http://praxi.mt.haw-hamburg.de/~dw54/index.jsp?p='err01'" );
				}
			}
			
			
			//out.println("<hr> </body> </html>");
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

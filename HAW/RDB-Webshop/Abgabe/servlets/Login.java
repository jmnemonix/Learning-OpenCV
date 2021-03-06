/*

Login Management


*/

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class Login extends HttpServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		String sqlUsr  = "dw54";
		String sqlPswd = "";

		HttpSession session = req.getSession();
	
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		String email = req.getParameter("email");
		String pswd = req.getParameter("pswd");
		
		if((email.equals(""))||(pswd.equals(""))){
			res.sendRedirect( "http://praxi.mt.haw-hamburg.de/~dw54/index.jsp?p=err&err=120" );
		}
		else{
			try{
				Class.forName("org.gjt.mm.mysql.Driver");
			}
			catch (ClassNotFoundException e){
				out.println("DB-Treiber nicht da!");
			}

			try{
				
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+sqlUsr, sqlUsr, sqlPswd);

				Statement st = con.createStatement();

				
				ResultSet rs = st.executeQuery("SELECT * FROM benutzer WHERE email LIKE '"+email+"'");

				boolean datensatzGefunden = false;
				while(rs.next()){
					String oid = rs.getString("id");
					String oname = rs.getString("name");
					String oemail = rs.getString("email");
					String opasswort = rs.getString("passwort");
					String orolle = rs.getString("rolle");
					
					datensatzGefunden = true;
					
					if((oemail.equals(email))&&(opasswort.equals(pswd))){
						session.setAttribute("iEingeloggt", "true");
						session.setAttribute("uname",oname);
						session.setAttribute("umail",oemail);
						session.setAttribute("urolle",orolle);
						session.setAttribute("uid",oid);
						
						res.sendRedirect( "http://praxi.mt.haw-hamburg.de/~dw54/" );
					}
					else{
						session.setAttribute("iEingeloggt", "false");
						session.invalidate();
						
						res.sendRedirect( "http://praxi.mt.haw-hamburg.de/~dw54/index.jsp?p=err&err=120" );
					}
					if(!datensatzGefunden) res.sendRedirect( "http://praxi.mt.haw-hamburg.de/~dw54/index.jsp?p=err&err=120" );
				}
				
				st.close();
				con.close();
			}
			catch (Exception e){
				out.println(" MySQL Exception: " + e.getMessage());
			}
		}
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.sendRedirect( "http://praxi.mt.haw-hamburg.de/~dw54/index.jsp?p=err&err=120" );
		// TODO richtigern fehler code senden
	}
}

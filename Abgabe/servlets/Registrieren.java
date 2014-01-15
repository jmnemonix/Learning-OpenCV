/*

Register Management


*/

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class Registrieren extends HttpServlet{


	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		String sqlUsr  = "dw54";
		String sqlPswd = "";

		HttpSession session = req.getSession();
	
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		String name  = req.getParameter("name");
		String email = req.getParameter("email");

		try{ Class.forName("org.gjt.mm.mysql.Driver"); }
		catch (ClassNotFoundException e){ out.println("DB-Treiber nicht da!"); }

		/*	Es wird kurz ueberpruefft, ob ein Benutzer schon eingeloggt ist
			Da er sich dann nicht mehr registrieren darf */
		/*String logCheck = "";
		logCheck = (String) session.getAttribute("iEingeloggt");
		if(!(logCheck.equals("true"))) {*/
			try{
				
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+sqlUsr, sqlUsr, sqlPswd);

				Statement st = con.createStatement();

				ResultSet rs = st.executeQuery("SELECT * FROM benutzer WHERE email LIKE '"+email+"'");

				boolean keinEintrag = true;

				while(rs.next()){
					String oemail = rs.getString("email");
					
					if(oemail.equals(email)){
						// Wenn es einen Benutzer mit dieser EMail schon da nicht registrieren
						keinEintrag = false;
						
						res.sendRedirect( "http://praxi.mt.haw-hamburg.de/~dw54/index.jsp?p=err&err=15" );
					}
					else{
						keinEintrag = true;
					}

				}
				if(keinEintrag){


					String pswd = gPSWD(8); // Passwort Generieren

					int i = st.executeUpdate("insert into `benutzer` (`name`, `email`, `passwort`, `rolle`)  values ('" + name + "','" + email + "','" + pswd + "','10')");

					if (i > 0) {
						out.println("Dein neues Passwort: <b>"+pswd+"</b><br><a href='http://praxi.mt.haw-hamburg.de/~dw54/'>zur&uuml;ck</a>");
					} else {
						out.println("Registrieren nicht m&ouml;glich");
					} 
							
						//res.sendRedirect( "http://praxi.mt.haw-hamburg.de/~dw54/" );
				}
				
				st.close();
				con.close();
			}
			catch (Exception e){
				out.println(" MySQL Exception: " + e.getMessage());
			}
		//}
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.sendRedirect( "http://praxi.mt.haw-hamburg.de/~dw54/index.jsp?p='err'" );
	}
	private String gPSWD(int l){

		String[] zeichen = {"0","1","2","3","4","5","6","7","8","9",
							"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
							"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
							"!","$","%","&","(",")","@","?","#","+","*",".",",","-","_" };

		int zlaenge = zeichen.length;

		String passwort = "";

		for(int i=0; i<l; i++){
			int zufall = zufall(0,zlaenge);
			passwort = passwort+zeichen[zufall];
		}

		return passwort;
	} 
	private int zufall(int min, int max){
		return (int) (Math.random() * (max - min) + min);
	}
}

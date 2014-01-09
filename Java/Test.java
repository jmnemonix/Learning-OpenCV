/*

Register Management


*/

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class Test extends HttpServlet{


	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{

		HttpSession session = req.getSession();
	
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		String name  = req.getParameter("name");
		String email = req.getParameter("email");

		out.println(gPSWD(8));

	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doPost(req,res);
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

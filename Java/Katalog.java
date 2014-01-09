//Das JDBC-Servlet "TelDBServlet1.java"
//Zuerst: ganz viele "imports"
import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

//Hier die Klasse mit Vererbung
public class Katalog extends HttpServlet
{
   public void doGet(HttpServletRequest req, HttpServletResponse res)
       throws ServletException, IOException
   {

      String sqlUsr  = "dw54";
      String sqlPswd = "";


      res.setContentType("text/html");
      PrintWriter out = res.getWriter();
      HttpSession session = req.getSession();

      String wGruppe = req.getParameter("wg");


      //boolean istEingeloggt = false;
      String logCheck ="";
      logCheck = (String) session.getAttribute("iEingeloggt");
      //if(logCheck.equals("true")) istEingeloggt = true;


      //DB-Treiber einbinden
      try { Class.forName("org.gjt.mm.mysql.Driver"); }
      catch (ClassNotFoundException e) { out.println("DB-Treiber nicht da!"); }

      try
      {
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+sqlUsr, sqlUsr, sqlPswd);
         Statement st = con.createStatement();


         ResultSet rs = st.executeQuery("SELECT * FROM ware WHERE warengruppe LIKE '"+wGruppe+"'");

         //Hier die Cursor-Schleife
         while(rs.next())
         {

            //  id name beschreibung warengruppe preis
             String pid      = rs.getString("id");
             String pname    = rs.getString("name");
             String pbesch   = rs.getString("beschreibung");
             String ppreis   = rs.getString("preis");
             String pbestand = rs.getString("bestand");

             String zeile1 = "<div class='produkt'><p class='produktname'>"+pname+"</p><div class='bild'><img src='"+pid+".png'></div>";
             String zeile2 = "<div class='beschreibung'><p>"+pbesch+"</p><p>Preis: "+ppreis+" &euro; Bestand: "+pbestand+"</p>";
             String zeile3 = "<p>"+(logCheck == "true" ? "<a href='http://praxi.mt.haw-hamburg.de/~dw54/servlet/Einpacken?pr="+pid+"'>Einpacken</a>" : "&nbsp;")+"</p>";
             String zeile4 = "</div><div style='clear:left'></div></div>";

             out.println( zeile1+zeile2+zeile3+zeile4 );
         }


         st.close();
         con.close();
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








 




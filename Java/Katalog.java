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
      res.setContentType("text/html");
      PrintWriter out = res.getWriter();

      String wGruppe = req.getParameter("wg");

      //DB-Treiber einbinden
      try { Class.forName("org.gjt.mm.mysql.Driver"); }
      catch (ClassNotFoundException e) { out.println("DB-Treiber nicht da!"); }

      try
      {
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dwNN", "dwNN", "****");
         Statement st = con.createStatement();


         ResultSet rs = st.executeQuery("SELECT * FROM ware WHERE warengruppe LIKE '"+wGruppe+"'");

         //Hier die Cursor-Schleife
         while(rs.next())
         {
             String sname = rs.getString("name");
             String snr = rs.getString("nr");
             out.println(sname + "  " + snr + "<br> ");
         }

         
         st.close();
         con.close();
     }
     catch (Exception e)
     {
         out.println(" MySQL Exception: " + e.getMessage());
     }
     
   }
}








 




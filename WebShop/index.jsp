<%@page language="java" contentType="text/html"%>
<%@page session="true" %>
<%
	//session = request.getSession();
	
	/* =============== LOGINCHECK =============== */
	String function = request.getParameter("f");
	
	String istEingeloggt = (String) session.getAttribute("iEingeloggt");
	
	
	
	/* =============== WEBSHOP SETUP =============== */
	
	String serverURL = "http://praxi.mt.haw-hamburg.de/~dw54"; // OHNE abschliessenden Slash
	
	String shopTitle = "nerd@m4ch1n3";		// Name des Webshops
	String trennTitel = " | ";				// Element um den Namen des Shops und Namen der Seite zu trennen
	String h1Title;							// 
	String jspTitle;						// 
	
	String CStyleS = "style/main.css";		// Hier kann der Style des Webshops einfach verändert werden.
	
	
	/* =============== DEBUG MODE =============== */
	
	String jSessionID = (String) session.getId();
	
	String UserData = ((String)session.getAttribute("uid"))+" "+((String)session.getAttribute("urolle"))+" "+((String)session.getAttribute("iEingeloggt"))+" "+((String)session.getAttribute("uname"))+" "+((String)session.getAttribute("umail"));
	
	boolean debug = true;
	
	/* =============== WELCHE SEITE =============== */
	
	String tmpPage = request.getParameter("p");
	String myPage;
	
	if(tmpPage == null) myPage = "home";
	else myPage = tmpPage;
	
	if(myPage.equals("home"))			h1Title = "Home";
	else if(myPage.equals("wagen"))		h1Title = "Einkaufswagen";
	else if(myPage.equals("reg"))		h1Title = "Registrieren";
	else if(myPage.equals("katalog"))	h1Title = "Katalog";
	else if(myPage.equals("checkout"))	h1Title = "Bezahlen";
	else if(myPage.equals("err01"))		h1Title = "Falscher Login";
	else								h1Title = "Fehler";
	
	jspTitle = shopTitle+trennTitel+h1Title;
	
%>
<HTML>
<HEAD>
	<TITLE><%=jspTitle%></TITLE>
	<link rel="stylesheet" href="<%=CStyleS%>">
</HEAD>
<BODY>
<h1><%=h1Title%></h1>
<% if(debug) {%>
<p>jSessionID = <%=jSessionID%> | tmp = <%=tmpPage%> | my = <%=myPage%></p>
<p><%=UserData%></p>
<% } %>

<div id="topbar">
<div id="snavi"><a href="index.jsp?p=home">Home</a> <a href="index.jsp?p=katalog">Katalog</a></div>
<div id="logbox">
<%
	if(istEingeloggt != "true")
	{
%>
<form method='get' action = '<%=serverURL%>/servlet/LogIn'>Login: <input type='email' name='email'>
 <input type='password' name='pswd'><input type='submit' value='Submit' name='absenden'> oder <a href='index.jsp?p=reg'>Registrieren</a>
</form> 
<%
	}
	else{
%>
IST EINGELOGGT! <a href="logout.jsp">Logout</a>
<%
	}
%>
 
</div>
</div>

<div id="contend">

</div>


</BODY>
</HTML>
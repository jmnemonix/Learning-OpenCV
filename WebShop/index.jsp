<%@page language="java" contentType="text/html"%>
<%@page session="true" %>
<%
	//session = request.getSession();
	
	/* =============== LOGINCHECK =============== */
	String function = request.getParameter("f");
	
	String istEingeloggt = (String) session.getAttribute("iEingeloggt");

	String benutzerRolle = (String) session.getAttribute("urolle");
	
	
	
	/* =============== WEBSHOP SETUP =============== */
	
	String serverURL = "http://praxi.mt.haw-hamburg.de/~dw54"; // OHNE abschliessenden Slash
	
	String shopTitle = "nerd@m4ch1n3";		// Name des Webshops
	String trennTitle = " | ";				// Element um den Namen des Shops und Namen der Seite zu trennen
	String h1Title;							// 
	String jspTitle;						// 
	
	String CStyleS = "main.css";		// Hier kann der Style des Webshops einfach verändert werden.

	/* =============== PRAESENTATION MODE =============== */

	if(benutzerRolle == ""){
		String presentationMode = (String) session.getAttribute("preMode");
	}
	
	
	/* =============== DEBUG MODE =============== */
	
	String jSessionID = (String) session.getId();
	
	String userData = ((String)session.getAttribute("uid"))+" "+((String)session.getAttribute("urolle"))+" "+((String)session.getAttribute("iEingeloggt"))+" "+((String)session.getAttribute("uname"))+" "+((String)session.getAttribute("umail"));
	
	boolean debug = true;
	
	/* =============== WELCHE SEITE =============== */
	
	String tmpPage = request.getParameter("p");
	String myPage;

	String javaContend = "";//Contend.ref(myPage);
	
	if(tmpPage == null) myPage = "home";
	else myPage = tmpPage;
	
	if(myPage.equals("home")){
		h1Title = "Home";
		javaContend = "Willkommen in meinem Webshop!!<br>Hier gibt es news:";
	}
	else if(myPage.equals("wagen")){
		h1Title = "Einkaufswagen";
		javaContend = "Dein Einkaufswagen scheint leer zu sein!";
	}
	else if(myPage.equals("reg")){
		h1Title = "Registrieren";
		javaContend = "du kannst dich momentan NICHT registrieren";
	}
	else if(myPage.equals("katalog")){
		h1Title = "Katalog";		// TODO: Warengruppen
		javaContend ="katttttttattatatalog";
	}
	else if(myPage.equals("ware")){
		h1Title = "";//ware("ware");		// TODO: Ware ausgeben
	}
	else if(myPage.equals("checkout")){
		h1Title = "Bezahlen";
	}
	else if(myPage.equals("agb")){
		h1Title = "AGB";
		javaContend ="Da dieser Webschop keine gesch&auml;fte abschliesst gibt es auch keine AGB";
	}
	else if(myPage.equals("impressum")){
		h1Title = "Impressum";
		javaContend = "Impressionnnenennenene";
	}
	else if(myPage.equals("kontakt")){
		h1Title = "Kontakt";
		javaContend = "Kontakt unerw&uuml;nscht";
	}
	else if(myPage.equals("err")){
		h1Title = "Fehlerbehandlung";
		javaContend = "FEHLER";
	}
	else if(myPage.equals("prem")){
		h1Title = "Presentation Mode";
		javaContend = "hier kommt der code";
	}
	else if(myPage.equals("admin")){
		h1Title = "Backend";
	}
	else{
		h1Title = "Fehler";
	}
	
	jspTitle = shopTitle+trennTitle+h1Title;


	/* =============== AUSGABE =============== */
%>


<HTML>
<HEAD>
	<TITLE><%=jspTitle%></TITLE>
	<link rel="stylesheet" href="<%=CStyleS%>">

<% if(debug) {%><meta http-equiv="refresh" content="2; URL=index.jsp"><% } %>

  	<link rel="stylesheet" title="Obsidian" href="highlight.js/styles/obsidian.css">
	<script src="highlight.js/highlight.pack.js"></script>
	<script>
		//hljs.tabReplace = '    ';
		hljs.initHighlightingOnLoad();
	</script>

</HEAD>
<BODY>

<% if(debug) {%>
<div id="debug">
	<p>DEBUG BOX</p>
	<p>jSessionID = <%=jSessionID%> | tmp = <%=tmpPage%> | my = <%=myPage%></p>
	<p><%=userData%></p>
</div>
<% } %>

<div id="noJS">Dieses Seite ben&ouml;tigt JavaScript!</div>

<div id="container">

	<div id="kopf"><h1>nerd@m4ch1n3 &tilde;&dollar; </h1></div>

	<div id="topbar">
		<div id="snavi"><a href="index.jsp?p=home">Home</a> <a href="index.jsp?p=katalog">Katalog</a></div>
		<div id="warenkorb"><a href="">Du hast x1 Produkte (x2 &euro;) in deinem Warenkorb</a></div>
		<div id="logbox">
<%
	if(istEingeloggt != "true")
	{
%>
			<form method='POST' action = '<%=serverURL%>/servlet/Login'>
				<input type='email' name='email'><input type='password' name='pswd'><input id="absenden" type='submit' value='Login' name='absenden'> oder <a href='index.jsp?p=reg'>Registrieren</a> 
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
		<div style="clear:left"></div>
	</div>
	<div id="inhalt">
		<h2><%=h1Title%></h2>
		inhalt

		<div id="Navigation">
    		<ul>
      			<li><a href="home">Startseite</a></li>
     			<li><a href="katalog">Katalog</a></li>
     		</ul>
		</div>

		<div id="contend">
<%=javaContend%>
		</div>

	</div>

	<div id="fusszeile">
		&copy; 2014 - <a href="index.jsp?p=agb">ABG</a> <a href="index.jsp?p=impressum">Impressum</a> <a href="index.jsp?p=kontakt">Kontakt</a>
	</div>

</div>

</BODY>
</HTML>
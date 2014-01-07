<%@page language="java" contentType="text/html"%>
<%@page session="true" %>
<%
	//session = request.getSession();
	
	/* =============== LOGINCHECK =============== */
/*
	String action = request.getParameter("action");

	if(action == "logout"){
		session.invalidate();
		response.sendRedirect("http://praxi.mt.haw-hamburg.de/~dw54/");
	}*/
	
	String istEingeloggt = (String) session.getAttribute("iEingeloggt");

	String benutzerRolle = (String) session.getAttribute("urolle");
	
	String benutzerName = (String)session.getAttribute("uname");
	
	
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
	
	String userData = ("uid: "+(String)session.getAttribute("uid"))+" rolle: "+((String)session.getAttribute("urolle"))+" eingeloggt: "+((String)session.getAttribute("iEingeloggt"))+" name: "+((String)session.getAttribute("uname"))+" mail: "+((String)session.getAttribute("umail"));
	
	boolean debug = false;
	
	/* =============== WELCHE SEITE =============== */


	String wGruppe = request.getParameter("wg");
	
	String tmpPage = request.getParameter("p");
	String myPage;

	String javaContend = "";//Contend.ref(myPage);

	boolean easyContend = false;
	/*
		Mit easyContend wird festgelegt,
		ob der Contend im if-Statement oder
		spaeter festgelegt wird
	*/
	
	if(tmpPage == null) myPage = "home";
	else myPage = tmpPage;
	
	if(myPage.equals("home")){
		h1Title = "Home";
		javaContend = "Willkommen in meinem Webshop!!<br>Hier gibt es news:";
		easyContend = true;
	}
	else if(myPage.equals("wagen")){
		h1Title = "Einkaufswagen";
		javaContend = "Dein Einkaufswagen scheint leer zu sein!";
		easyContend = true;
	}
	else if(myPage.equals("reg")){
		h1Title = "Registrieren";
		javaContend = "du kannst dich momentan NICHT registrieren";
		easyContend = true;
	}
	else if(myPage.equals("katalog")){
		h1Title = "Katalog";

		easyContend = false;
	}
	else if(myPage.equals("ware")){
		h1Title = "";//ware("ware");		// TODO: Ware ausgeben

		// Die Aktuelle Warengruppe muss im title erscheinen

		easyContend = false;
	}
	else if(myPage.equals("checkout")){
		h1Title = "Bezahlen";
		easyContend = false;
	}
	else if(myPage.equals("agb")){
		h1Title = "AGB";
		javaContend ="Da dieser Webschop keine gesch&auml;fte abschliesst gibt es auch keine AGB";
		easyContend = true;
	}
	else if(myPage.equals("impressum")){
		h1Title = "Impressum";
		javaContend = "Impressionnnenennenene";
		easyContend = true;
	}
	else if(myPage.equals("kontakt")){
		h1Title = "Kontakt";
		javaContend = "Kontakt unerw&uuml;nscht";
		easyContend = true;
	}
	else if(myPage.equals("err")){
		h1Title = "Fehlerbehandlung";
		javaContend = "FEHLER";
		easyContend = true;
	}
	else if(myPage.equals("prem")){
		h1Title = "Presentation Mode";
		javaContend = "hier kommt der code";
		easyContend = true;
	}
	else if(myPage.equals("benutzer")){
		h1Title = "Benutzer";
		easyContend = false;
	}
	else if(myPage.equals("admin")){
		h1Title = "Backend";
		easyContend = false;
	}
	else{
		h1Title = "Fehler";
		easyContend = true;
	}
	
	jspTitle = shopTitle+trennTitle+h1Title;


	/* =============== AUSGABE =============== */
%>


<HTML>
<HEAD>
	<TITLE><%=jspTitle%></TITLE>
	<link rel="stylesheet" href="<%=CStyleS%>">

<% /* if(debug) {%><meta http-equiv="refresh" content="2; URL=index.jsp"><% } */ %>

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
	<p><%=request.getRemoteHost()%></p>
</div>
<% } %>



<div id="container">

	<div id="kopf"><h1><a href="index.jsp?p=home">nerd@m4ch1n3 &tilde;&dollar;</a></h1></div>

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
Hallo <a href="index.jsp?p=benutzer"><%=benutzerName%></a>! Du bist eingeloggt! <a href="<%=serverURL%>/servlet/Logout">Logout</a>
<%
	}
%>
		</div>
		<div style="clear:left"></div>
	</div>
	<div id="inhalt">
		<h2><%=h1Title%></h2>

		<div id="Navigation">
    		<ul>
      			<li><a href="index.jsp?p=katalog&wg=1">Warengruppe 1</a></li>
     			<li><a href="index.jsp?p=katalog&wg=2">Warengruppe 2</a></li>
     			<li><a href="index.jsp?p=katalog&wg=3">Warengruppe 3</a></li>
     		</ul>
		</div>

		<div id="contend">

<% if(!easyContend) { 

	if(myPage.equals("katalog")){
			String path = "/servlet/Katalog?wg="+wGruppe;
	%> 
		
		<jsp:include page="<%=path%>"/>

	<%
	}
}
else { %>
	<%=javaContend%>
<% } %>

		</div>

	</div>

	<div id="fusszeile">
		&copy; 2014 - <a href="index.jsp?p=agb">ABG</a> <a href="index.jsp?p=impressum">Impressum</a> <a href="index.jsp?p=kontakt">Kontakt</a>
	</div>

</div>

</BODY>
</HTML>
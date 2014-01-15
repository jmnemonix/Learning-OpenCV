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
	
	String istEingeloggt = "";

	String benutzerRolle = "";
	
	String benutzerName  = "";

    istEingeloggt = (String) session.getAttribute("iEingeloggt");
	benutzerRolle = (String) session.getAttribute("urolle");
	benutzerName  = (String) session.getAttribute("uname");
	
	
	/* =============== WEBSHOP SETUP =============== */
	
	String serverURL = "http://praxi.mt.haw-hamburg.de/~dw54"; // OHNE abschliessenden Slash
	
	String shopTitle = "nerd@m4ch1n3";		// Name des Webshops
	String trennTitle = " | ";				// Element um den Namen des Shops und Namen der Seite zu trennen
	String h1Title = "";							// 
	String jspTitle = "";						// 

	/* =============== PRAESENTATION MODE =============== */

	/*if(benutzerRolle == ""){
		String presentationMode = (String) session.getAttribute("preMode");
	}*/
	
	/* =============== WELCHE SEITE =============== */


	String wGruppe  = request.getParameter("wg");
	String tmpError = request.getParameter("err");
	
	String tmpPage = request.getParameter("p");
	String myPage;

	String javaContend = "";//Contend.ref(myPage);

	boolean easyContend = false;
	boolean backend = false;
	/*
		Mit easyContend wird festgelegt,
		ob der Contend im if-Statement oder
		spaeter festgelegt wird
	*/

	if(tmpPage == null) myPage = "home";
	else myPage = tmpPage;


	String adminRolle = "1";
	
	if((adminRolle.equals(benutzerRolle))&&(myPage.equals("admin"))) backend = true; /* Nur der Admin kann das Backend anzeigen*/


	
	if(myPage.equals("home")){
		h1Title = "Home";
		easyContend = true;
	}
	else if(myPage.equals("wagen")){
		easyContend = false;
		h1Title = "Warenkorb";
	}
	else if(myPage.equals("best")){
		easyContend = false;
		h1Title = "Deine Bestellungen";
	}
	else if(myPage.equals("reg")){
		h1Title = "Registrieren";
		easyContend = false;
	}
	else if(myPage.equals("katalog")){
		h1Title = "Katalog";
		easyContend = false;
	}
	else if(myPage.equals("checkout")){
		h1Title = "Bezahlen";
		easyContend = false;
	}
	else if(myPage.equals("agb")){
		h1Title = "AGB";
		easyContend = true;
	}
	else if(myPage.equals("impressum")){
		h1Title = "Impressum";
		easyContend = true;
	}
	else if(myPage.equals("kontakt")){
		h1Title = "Kontakt";
		easyContend = true;
	}
	else if(myPage.equals("benutzer")){
		h1Title = "Deine Daten";
		easyContend = false;
	}
	else if(myPage.equals("err")){
		h1Title = "Fehler!";
		easyContend = false;
	}
	else if(myPage.equals("prem")){
		h1Title = "Presentation Mode";
		easyContend = true;
	}
	else if(myPage.equals("admin")){
		h1Title = "Backend";
		easyContend = false;
	}
	else{
		h1Title = "Fehler";
		easyContend = true;
	}
	
	jspTitle = ""+shopTitle+trennTitle+h1Title;
	


	/* =============== AUSGABE =============== */
	String htmlpath = "/html.jsp?p=head&t="+jspTitle;
	String html2path = "/html.jsp?p=kopf";
%>

<jsp:include page="<%=htmlpath%>"/>

<jsp:include page="<%=html2path%>"/>

	<div id="inhalt">
		<h2><%=h1Title%></h2>

<jsp:include page="/html.jsp?p=navi"/>

		<div id="contend">

<% if(easyContend) {
			/* Wenn man nur eine Zeile statische Ausgabe benoetigt, kann man diese per easyContend Definieren */
	String easyContendpath = "/easyContend.jsp?p="+myPage;
%>

<jsp:include page="<%=easyContendpath%>"/>

<%
}
else{
	
	if(myPage.equals("katalog")){ /* ---------------------------------------------------------------------------------- Katalog */
			String path = "/servlet/Katalog?wg="+wGruppe;
	%> 
		<jsp:include page="<%=path%>"/>

	<%
	}
	else if(myPage.equals("err")){ /* ------------------------------------------------------------------------------- Fehler */

		String fehlerseite = request.getParameter("err");

		String path = "/servlet/Fehler?e="+fehlerseite;
	%> 
		<jsp:include page="<%=path%>"/>
	<%
	}
	else if(myPage.equals("wagen")){ /* ------------------------------------------------------------------------------- Warenkorb */
			String path = "/servlet/Warenkorb";
	%> 
		
		<jsp:include page="<%=path%>"/>

	<%
	}
	else if(myPage.equals("best")){ /* ------------------------------------------------------------------------------- Bestellungen */
			String path = "/servlet/Bestellungen";
	%> 
		
		<jsp:include page="<%=path%>"/>

	<%
	}
	else if(myPage.equals("reg")){ /* --------------------------------------------------------------------------------- Registrieren Formular */
	%> 

<jsp:include page="/html.jsp?p=regi"/>

	<%
	}
	else if(myPage.equals("benutzer")){ /* ---------------------------------------------------------------------------- Benutzer Daten */
			String path = "/servlet/Benutzer?f=benutzer";
	%> 
		
		<jsp:include page="<%=path%>"/>
		<a href="index.jsp?p=best">Meine Bestellungen</a>

	<%
	}
	else if((myPage.equals("admin"))&&(!backend)){
		%>Keine Berechtigung<%
	}
	else if((myPage.equals("admin"))&&(backend)){ /* ------------------------------------------------------------------ Admin Bereich */
			String path = "";


			String tmpAdmin = request.getParameter("adm");
			String myAdmin;

			if(tmpAdmin == null) myAdmin = "1";
			else myAdmin = tmpAdmin;

			String adUID = request.getParameter("uid");
			String adPID = request.getParameter("pid");

					/* adm */

			if      (myAdmin.equals("1"))	path = "/servlet/AdminOverview";
			else if (myAdmin.equals("2"))	path = "/servlet/Benutzer?f=admls";
			else if (myAdmin.equals("3"))	path = "/servlet/Ware?f=admls";
			else if (myAdmin.equals("4"))	path = "/servlet/Bestellungen?f=list";
			else if (myAdmin.equals("5"))	path = "/servlet/Benutzer?f=admchange&usr="+adUID;
			else if (myAdmin.equals("6"))	path = "/servlet/Ware?f=admchange&pr="+adPID;
			else if (myAdmin.equals("7"))	path = "/servlet/Ware?f=newForm";
			else if (myAdmin.equals("8"))	path = "/servlet/AdminWarenkorb";
			else 							path = "/servlet/AdminOverview";
	%> 
		<div id="AdminBox">
			<ul>
				<li><a href="index.jsp?p=admin&adm=1">Overview</a></li>
				<li><a href="index.jsp?p=admin&adm=2">Benutzer</a></li>
				<li><a href="index.jsp?p=admin&adm=3">Ware</a></li>
				<li><a href="index.jsp?p=admin&adm=4">Bestellungen</a></li>
				<li><a href="index.jsp?p=admin&adm=8">Warenk&ouml;rbe</a></li>
			</ul>
			<jsp:include page="<%=path%>"/>
		</div>
		

	<%
	}
}

%>


<jsp:include page="/html.jsp?p=ende"/>
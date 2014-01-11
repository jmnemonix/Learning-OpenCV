<%@page language="java" contentType="text/html"%>
<%@page session="true" %>
<%

	/* 										===== HTML Ausgabe =====

		Inhalt:


			- HTML Head
			- Kopf Bereich der Seite (NICHT <HEAD>!!!)
			- Die Navigations Liste mit den Warengruppen
			- Ende der Seite
			- Registrierungsformular
			- </div>


	 */

	String part = request.getParameter("p");
	
	String istEingeloggt = "";
	String benutzerRolle = "";
	String benutzerName  = "";

    istEingeloggt = (String) session.getAttribute("iEingeloggt");
	benutzerRolle = (String) session.getAttribute("urolle");
	benutzerName  = (String) session.getAttribute("uname");


	
	String CStyleS = "main.css";		// Hier kann der Style des Webshops einfach verändert werden.
	
	String serverURL = "http://praxi.mt.haw-hamburg.de/~dw54"; // OHNE abschliessenden Slash

	String snavi = "";
	String adminRolle = "1";
	String stringtrue = "true";
	
	if(adminRolle.equals(benutzerRolle)){
		snavi = "<a style='color:red;' href='index.jsp?p=admin'>Adminbereich</a>";
	}
	else {
		snavi = "&nbsp;";
	}

		/* =============== DEBUG MODE =============== */
	
	String jSessionID = (String) session.getId();
	
	String userData = ("uid: "+(String)session.getAttribute("uid"))+" rolle: "+((String)session.getAttribute("urolle"))+" eingeloggt: "+((String)session.getAttribute("iEingeloggt"))+" name: "+((String)session.getAttribute("uname"))+" mail: "+((String)session.getAttribute("umail"));
	
	boolean debug = false;


if(part.equals("head")){ // ------------------------------------------------------------------------------------------------------------------------- HTML Head
	String jspTitle = request.getParameter("t");
%>
<HTML>
<HEAD>
	<TITLE><%=jspTitle%></TITLE>
	<link rel="stylesheet" title="Std" href="<%=CStyleS%>">
  	<link rel="stylesheet" title="Obsidian" href="highlight.js/styles/obsidian.css">
	<script src="highlight.js/highlight.pack.js"></script>
	<script>
		//hljs.tabReplace = '    ';
		hljs.initHighlightingOnLoad();
	</script>

</HEAD>
<BODY>

<%
}
else if(part.equals("kopf")){ // -------------------------------------------------------------------------------------------------------------------- Kopf Bereich der Seite (NICHT <HEAD>!!!)

	if(debug) {
		%> <div id="debug"><p>DEBUG BOX</p><p>jSessionID = <%=jSessionID%> | </p><p><%=userData%></p><p><%=request.getRemoteHost()%></p></div> <%
	}
%>

<div id="container">

	<div id="kopf"><h1><a href="index.jsp?p=home">nerd@m4ch1n3 &tilde;&dollar;</a></h1></div>

	<div id="topbar">
<%
	if(istEingeloggt != "true")
	{
%>
		<div id="logbox">
			<form method='POST' action='<%=serverURL%>/servlet/Login'>
				<input type='email' name='email'><input type='password' name='pswd'><input id="absenden" type='submit' value='Login' name='absenden'> oder <a href='index.jsp?p=reg'>Registrieren</a> 
			</form>
		</div>
		<div id="snavi">&nbsp;</div>
		<div id="warenkorb">Warenkorb: bitte Einloggen</div>

<%
	}
	else{
%>
		<div id="logbox"> Hallo <a href="index.jsp?p=benutzer"><%=benutzerName%></a>! Du bist eingeloggt! <a href="<%=serverURL%>/servlet/Logout">Logout</a></div>
		<div id="snavi"><%=snavi%></div>
		<div id="warenkorb"><jsp:include page="/servlet/KlWarenkorb"/></div>
<%
	}
%>
		<div style="clear:left"></div>
	</div>

<%
}
else if(part.equals("navi")){  // ------------------------------------------------------------------------------------------------------------------- Die Navigations Liste mit den Warengruppen
%>

		<div id="Navigation">
    		<ul>
      			<li><a href="index.jsp">Home</a></li>
      			<li><a href="index.jsp?p=katalog&wg=1">USB-Sticks</a></li>
     			<li><a href="index.jsp?p=katalog&wg=2">&Uuml;berleben</a></li>
     			<li><a href="index.jsp?p=katalog&wg=3">Brille und Co.</a></li>
     			<li><a href="index.jsp?p=katalog&wg=4">Getr&auml;nke</a></li>
     			<li><a href="index.jsp?p=katalog&wg=5">Batches</a></li>
     			<li><a href="index.jsp?p=katalog&wg=6">R&auml;tsel</a></li>
     			<li><a href="index.jsp?p=katalog&wg=7">Sammlerst&uuml;cke</a></li>
     		</ul>
		</div>

<%
}
else if(part.equals("ende")){  // ------------------------------------------------------------------------------------------------------------------- Ende der Seite
%>
</div> </div> 
	<div id="fusszeile">
		&copy; 2014 - <a href="index.jsp?p=agb">ABG</a> <a href="index.jsp?p=impressum">Impressum</a> <a href="index.jsp?p=kontakt">Kontakt</a>
	</div>

</div>

</BODY>
</HTML>
<%
}
else if(part.equals("regi")){  // ------------------------------------------------------------------------------------------------------------------- Registrierungsformular
%>
<form method='POST' action='http://praxi.mt.haw-hamburg.de/~dw54/servlet/Registrieren'>
	<p>Dein Name: <input type='text' name='name' ></p>
	<p>Deine E-Mail: <input type='email' name='email'></p>
	<input type='submit' value='Registrieren' name='register'>
</form>
<%
}
else{
	%>IRGENDWAS L&Auml;UFT HIER SCHIEFF<%
}
%>
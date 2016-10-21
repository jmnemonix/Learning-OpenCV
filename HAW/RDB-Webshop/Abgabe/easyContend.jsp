<%@page language="java" contentType="text/html"%>
<%@page session="true" %>
<%
	String tmpPage = request.getParameter("p");
	String myPage;

	String javaContend = "";

	if(tmpPage == null) myPage = "home";
	else myPage = tmpPage;

	if(myPage.equals("home"))			javaContend = "Willkommen in meinem Webshop!!<br>Hier gibt es news:";
	else if(myPage.equals("wagen"))		javaContend = "";
	else if(myPage.equals("best"))		javaContend = "";
	else if(myPage.equals("reg"))		javaContend = "Du kannst dich momentan NICHT registrieren<br>sp&auml;ter erscheint hier ein Formular";
	else if(myPage.equals("katalog"))	javaContend = "";
	else if(myPage.equals("checkout"))	javaContend = "";
	else if(myPage.equals("agb"))		javaContend = "Da dieser Webschop keine gesch&auml;fte abschliesst gibt es auch keine AGB";
	else if(myPage.equals("impressum"))	javaContend = "Dies ist eine Studentische Leistung in Form eines Fiktiven Webshops. Hier wird NICHTS verkauft!";
	else if(myPage.equals("kontakt"))	javaContend = "Kontakt unerw&uuml;nscht";
	else if(myPage.equals("benutzer"))	javaContend = "";
	else if(myPage.equals("err"))		javaContend = "<p class='fehler'>FEHLER!</p><p>Es ist ein Fehler aufgetreten!</p>";
	else if(myPage.equals("prem"))		javaContend = "hier kommt der code";
	else if(myPage.equals("admin"))		javaContend = "";
	else								javaContend = "Seite nicht gefunden!";

%>
<%=javaContend%>
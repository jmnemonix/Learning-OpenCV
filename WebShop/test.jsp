<%@page language="java" contentType="text/html"%>
<%@page session="true" %>
<%
	
	String istEingeloggt = "";

	String benutzerRolle = "";
	
	String benutzerName  = "";

    istEingeloggt = (String) session.getAttribute("iEingeloggt");
	benutzerRolle = (String) session.getAttribute("urolle");
	benutzerName  = (String) session.getAttribute("uname");
%>

<%=istEingeloggt%>
<%=benutzerRolle%>
<%=benutzerName%>
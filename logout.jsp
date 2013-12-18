<%
session.invalidate();

String jspTitle = "Auf Wiedersehen!";
%>
<HTML>
<HEAD>
	<TITLE><%=jspTitle%></TITLE>
	<meta http-equiv="refresh" content="0; URL=http://praxi.mt.haw-hamburg.de/~dw54/">
</HEAD>
<BODY>
<h2>Auf Wiedersehen</h2>
Sie sind nun Ausgeloggt!
<br>Wenn Sie nicht automatisch weitergeleitet werden <a href="index.jsp">hier</a> klicken!
</BODY>
</HTML>
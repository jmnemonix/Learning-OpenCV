<%@page language="java" contentType="text/css"%>
<%@page session="true" %>
<%
	String content = request.getParameter("cnt");

	String[] colors = {	"#201f1d", // Body background-color
						"white", // Body color
						"#2d491f", // Rahmen
						"",		// Hintergrund Container
						"",		// Link
						""		// Link Hover
	};

%>

/*
		===== Grundlegende ELemente =====
*/

  body {
    color: <%=colors[1]%>;
    background-color: <%=colors[0]%>;
    font-size: 100.01%;
    font-family: Helvetica,Arial,sans-serif;
    margin: 0; padding: 1em 0;
    text-align: center;
  }

  a {
  	color: #1f3349;
  	text-decoration: none;
  }
  a:hover{
  	color: #1f6825;
  }

  #debug {
  	display: none;
  	color: #900;
  	background-color: #FDA;
  	border: 5px solid;
  }

  #container {
  	color: <%=colors[2]%>;
  	background-color: black;
    text-align: left;
    margin: 0 auto;
    width: 95%;
    padding: 0;
   /* background: #ffffe0 url(hintergrund.gif) repeat-y; */
    border: 1px solid #2d491f; <%=colors[2]%>
  }

  #kopf {
  	color: #8ac;
    font-size: 1.5em;
    height: 100px;
    margin: 0; padding: 0.3em;
    text-align: center;
    background: black url(gnu1.png) no-repeat 90% 50%;
    border-bottom: 1px solid <%=colors[2]%>;
  }
  #topbar {
  	height: 30px;
    border-bottom: 1px solid <%=colors[2]%>;
    padding-top: 5px;
    padding-bottom: 5px;

  }
  #snavi {
  	float: left;
  	width: 120px;
  	margin-left: 10px;
  }
  #logbox {
  	text-align: right;
  	float: right;
  	/*width: 120px;*/

  	padding-right: 40px;
  }
  #warenkorb {
  	border: 1px dashed <%=colors[2]%>;
  	float: right;
  	/*width: 120px;*/
  	margin-right: 20px;
  }

  * html div#inhalt {
    height: 1em;  /* Workaround gegen den 3-Pixel-Bug des Internet Explorer bis Version 6 */
    margin-bottom: 0;
  }
  #inhalt {
    margin: 0 0 1em 220px;
    padding: 0 1em;
  }

  #fusszeile {
    clear: both;
    font-size: 0.83em;
    margin: 0; padding: 0.1em;
    text-align: center;
    color:<%=colors[2]%>;
    background-color: black;
    border-top: 1px solid <%=colors[2]%>;
  }
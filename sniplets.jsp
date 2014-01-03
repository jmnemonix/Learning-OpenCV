<% /*
<pre><code class="java">	
	/* =============== WELCHE SEITE =============== */
	
	String tmpPage = request.getParameter("p");
	String myPage;
	
	if(tmpPage == null) myPage = "home";
	else myPage = tmpPage;
	
	if(myPage.equals("home"))			h1Title = "Home";
	else if(myPage.equals("wagen"))		h1Title = "Einkaufswagen";
	else if(myPage.equals("reg"))		h1Title = "Registrieren";
	else if(myPage.equals("katalog"))	h1Title = "Katalog";
	else if(myPage.equals("ware"))		h1Title = "";//ware("ware");
	else if(myPage.equals("checkout"))	h1Title = "Bezahlen";
	else if(myPage.equals("agb"))		h1Title = "AGB";
	else if(myPage.equals("impressum"))	h1Title = "Impressum";
	else if(myPage.equals("kontakt"))	h1Title = "Kontakt";
	else if(myPage.equals("err01"))		h1Title = "Falscher Login";
	else if(myPage.equals("prem"))		h1Title = "Presentation Mode";
	else								h1Title = "Fehler";
</code></pre>
*/
%>
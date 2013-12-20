STRUCKTUR
=======

 WebShop
	|
	--- index.jsp
	|		|
	|		--- Home
	|		|
	|		|
	|		--- Einkaufswagen
	|		|
	|		|
	|		--- Registrieren
	|		|
	|		|
	|		--- Katalog
	|		|
	|		|
	|		--- Waren Details
	|		|
	|		|
	|		--- AGB
	|		|
	|		|
	|		--- Impressum
	|		|
	|		|
	|		--- Kontakt
	|		|
	|		|
	|		--- Verwaltung
	|		|		|
	|		|		--- Ware
	|		|		|
	|		|		|
	|		|		--- Bestellungen
	|		|		|
	|		|		|
	|		|		--- Benutzer
	|		|		|
	|		|		|
	|		|		--- Datenbanken
	|		|
	|		|
	|		--- Presentation Mode
	|		|
	|		|
	|		--- Falscher Login
	|		|
	|		|
	|		--- Fehler
	|
	|
	--- logout.jsp
	|
	|
	|
	--- /servlet/Login
	|
	|
	|
	--- /servlet/Ware



	else if(myPage.equals("ware"))		h1Title = "";//ware("ware");
	else if(myPage.equals("checkout"))	h1Title = "Bezahlen";
	else								h1Title = "Fehler";
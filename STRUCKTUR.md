STRUCKTUR
=======

 WebShop
	|
	o-- index.jsp
	|		|
	|		|
	|		o-- Home
	|		|
	|		|
	|		o-- Einkaufswagen
	|		|
	|		|
	|		o-- Registrieren > /servlet/Registrieren
	|		|
	|		|
	|		o-- Registrierung erfolgreich
	|		|
	|		|
	|		o-- Katalog
	|		|
	|		|
	|		o-- Waren Details
	|		|
	|		|
	|		o-- AGB
	|		|
	|		|
	|		o-- Impressum
	|		|
	|		|
	|		o-- Kontakt
	|		|
	|		|
	|		o-- Benutzer : Seine Daten einsehen und editieren8
	|		|
	|		|
	|		o-- Verwaltung
	|		|		|
	|		|		|
	|		|		o-- Ware
	|		|		|
	|		|		|
	|		|		o-- Bestellungen
	|		|		|
	|		|		|
	|		|		o-- Benutzer
	|		|		|
	|		|		|
	|		|		o-- Datenbanken
	|		|
	|		|
	|		o-- Presentation Mode			???
	|		|
	|		|
	|		o-- Falscher Login
	|		|
	|		|
	|		o-- Fehler
	|
	|
	|
	o-- /servlet/Logout (--> index.jsp)
	|		|
	|		|
	|		o-> index.jsp > Home
	|
	|
	|
	o-- /servlet/Login (--> index.jsp)
	|		|
	|		|
	|		o-> index.jsp > Home
	|		|
	|		|
	|		o-> index.jsp > Falsche Login
	|
	|
	|
	o-- /servlet/Registrieren (--> index.jsp)
	|		|
	|		|
	|		o-> index.jsp > registrierung erfolgreich
	|
	|
	|
	o-- /servlet/Katalog : Wird in index.jsp eingebunden : listed alle Produkte einer Warengruppe auf
	|
	|
	|
	o-- /servlet/Ware
			|
			|
			o-< index.jsp < Ware
			|
			|
			0-< index.jsp < Katalog



	else if(myPage.equals("ware"))		h1Title = "";//ware("ware");
	else if(myPage.equals("checkout"))	h1Title = "Bezahlen";
	else								h1Title = "Fehler";

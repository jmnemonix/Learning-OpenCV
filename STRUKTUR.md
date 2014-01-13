Struktur
=======

Warum werden Servlets eingebunden und nicht Klassen und ihre Funktionen benutzt?
Weil Praxi eine unglaubliche Zicke ist! Klassen zu benutzen ist unpraktisch ... zumindest auf Praxi

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



Aufgaben
=======

Wer 		Was							Erlegigt		Wo

Alle 		Begrüssung					Ja				index.jsp
Alle 		News Anzeigen 				Bonus
Alle 		AGB, Impressum Anzeigen 	Ja 				index.jsp
Alle 		Konakt aufnehmen			Bonus

Alle 		Fehler angezeigt			Nein			index.jsp

Benutzer	Einloggen					Ja 				Login.java
Benutzer	Ausloggen					Ja 				Logout.java
Benutzer	Daten Einsehen 				Ja				index.jsp & Benutzer.java
Benutzer 	Daten ändern 				Nein			Benutzer.java

Kunde		Kategorien Browsen			Ja 				index.jsp und Katalog.java
Kunde		Registrieren				Ja
Kunde		Ein Passwort generiert		Ja
Kunde		Waren in Warenkorb			Ja
Kunde		Warenkorb Anzeigen			Ja
Kunde		Warenkorb Editieren 		Nein
Kunde		Bestellen 					Nein

Admin		Benutzer Anzeigen lassen	Ja
Admin		Einen Nutzer bearbeiten		Jain
Admin 		Waren auflisten				Ja
Admin		Ware bearbeiten				Nein
Admin		Ware löschen				Nein
Admin		Bestellungen auflisten		Ja
Admin		Bestellungen Versenden		Ja   ???
Admin		Bestellung löschen			Nein
Admin		Datenbank Zurücksetzen		Nein <<< das kommt wohl zum Schluss
Admin		News Verwalten				Bonus



ToDo
=======

- Diese Datei in eine Vernünftige Form bringen


- benutzer können ihre daten einsehen und editieren
	A) eine Seite in index.jsp hinzufügen (erledigt)
	B) Benutzer.java diese funktion hinzufügen
	C) in index.jsp einbinden

- Benutzer Verwalten

- Presentation mode hinzufügen

- Module für Admin Bereich erstellen

- struktur updaten



- Überall Einkaufswagen in Warenkorb umbennenen


Fehler
=======

Nummer 			Bedeutung

1				Test Fehler
100				Zugangsdaten nicht richtig
101				EMail schon im System

120				Falsche Nutzerdaten

404				Seite nicht gefunden

999				Die Ware ist leider nicht mehr verfügbar

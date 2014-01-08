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

Benutzer	Einloggen					Ja 				Login.java
Benutzer	Ausloggen					Ja 				Logout.java
Benutzer	Daten Einsehen 				Nein

Kunde		Kategorien Browsen			Jain			index.jsp und Katalog.java
-----		Produkt Details Anzeigen 	----										>	Wird nicht benötigt, da der Katalog schon alle benötigten daten liefert
Kunde		Registrieren				Idee
Kunde		Ein Passwort generiert		Nein
Kunde		Waren in Warenkorb			Nein
			Dabei Bestandprüffen		Nein
Kunde		Anzahl Ware zum Warenkorb 	Nein			Katalog.java
Kunde		Warenkorb Anzeigen			Nein
Kunde		Warenkorb Editieren 		Nein
Kunde		Bestellen 					Nein

Admin		Benutzer Verwalten			Nein
Admin 		Waren Verwalten				Nein
Admin		Bestellungen Verwalten		Nein
Admin		Datenbank Zurücksetzen		Nein
Admin		News Verwalten				Bonus



ToDo
=======

- Diese Datei in eine Vernünftige Form bringen
- Einpacken.java bearbeiten
- Warenkorb.java bearbeiten
- Dem Kunden die Möglichkeit geben, sich zu regestrieren
	A) Formular erstellen und in index.jsp einpflegen
	B) Registrierung.java bearbeiten
		- daten validieren
		- email adresse auf vorhandensein prüffen
		- ggf. daten in DB schreiben
		- generiertes passwort ausgeben
- im katalog überprüffen ob besucher eingeloggt und ggf. felder ausblenden
- neue ware.sql einspielen
- warengruppen in index.jsp updaten (Eigendlich Erledigt)

- Benutzer Verwalten
	A) eine index.jsp Seite hinzufügen
	B) eine Benutzer.java erstellen
	C) Benutzer.java gibt eine Liste aller Benutzer aus ODER eine bearbeiten formular für einen einzelnen ODER schreibt änderungen in dei DB
		- BenutzerID BenutzerName BenutzerMail Rolle bemerkung [aktivieren] [bearbeiten] [löschen]
		- id name email passwort bemerkung rolle [update]
		- daten in datenbank schreiben
		(Könnte diese Datei auch die Aufgaben von Registrierung.java übernehmen???)

- Presentation mode hinzufügen

- Module für Admin Bereich erstellen






- Überall Einkaufswagen in Warenkorb umbennenen


Fehler
=======

Nummer 			Bedeutung

1				Test Fehler
100				Zugangsdaten nicht richtig
404				Seite nicht gefunden

999				Die Ware ist leider nicht mehr verfügbar

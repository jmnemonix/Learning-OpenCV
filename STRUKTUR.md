Struktur
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



Aufgaben
=======

Wer 		Aufgaben					Erlegigt		Wo

Alle 		Begrüssung					Ja				index.jsp
Alle 		News Anzeigen 				Bonus
Alle 		AGB, Impressum Anzeigen 	Ja 				index.jsp
Alle 		Konakt aufnehmen			Bonus

Benutzer	Einloggen					Ja 				Login.java
Benutzer	Ausloggen					Ja 				Logout.java
Benutzer	Daten Einsehen 				Nein

Kunde		Kategorien Browsen			Jain			index.jsp und Katalog.java
Kunde		Registrieren				Nein
Kunde		Ein Passwort generiert		Nein
Kunde		Waren in Einkaufswagen		Nein
Kunde		Einkaufswagen Anzeigen		Nein
Kunde		Bestellen 					Nein

Admin		Benutzer Verwalten			Nein
Admin 		Waren Verwalten				Nein
Admin		Bestellungen Verwalten		Nein
Admin		Datenbank Zurücksetzen		Nein
Admin		News Verwalten				Bonus


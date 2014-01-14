INSERT INTO `warenkorb` (`kundeID`, `wareID`, `anzahl`) VALUES ('1', '2', '1');

UPDATE `dw54`.`warenkorb` SET `anzahl` = '7' WHERE `warenkorb`.`kundeID` =2 AND `warenkorb`.`wareID` =1;
UPDATE `dw54`.`bestellungen` SET `status` = '3' WHERE `bestellungen`.`id` =1;

INSERT INTO `bestellungen` (`id` ,`kundeID` ,`adresse` ,`bemerkung` ,`status`)VALUES (NULL , '1000', 'rdbstr. 12 202020 Hamburg', 'bem', '1');
INSERT INTO `bestWare` (`bestellungID` ,`position` ,`wareID` ,`anzahl`)VALUES ('3', '1', '11', '5');

DELETE * FROM `warenkorb` WHERE `warenkorb`.`kundeID` = 1

# sehr schöne verknüpfung von datensätzen zu gebrauchen bei bestellungen und bestWare
SELECT studenten.name, fachnoten.fach, fachnoten.note
  FROM studenten, fachnoten
    WHERE studenten.matrikelnr = fachnoten.matrikelnr;

SELECT `bestellungen`.`id`, `bestellungen`.`kundeID`, `bestellungen`.`adresse`, `bestellungen`.`status`, `bestWare`.`position`, `bestWare`.`anzahl`, `ware`.`preis`, `ware`.`name`
	FROM `ware`, `bestellungen`, `bestWare`
		WHERE `bestellungen`.`kundeID` = '1' AND `bestWare`.`bestellungID` = `bestellungen`.`id` AND `bestWare`.`wareID` = `ware`.`ID`
			ORDER BY `bestellungen`.`id`, `bestWare`.`position`;

SELECT warenkorb.kundeID, warenkorb.wareID, warenkorb.anzahl, ware.name FROM warenkorb, ware WHERE warenkorb.wareID = ware.id ORDER BY warenkorb.kundeID;
SELECT `warenkorb`.`kundeID`, `warenkorb`.`wareID`, `warenkorb`.`anzahl`, `ware`.`name` FROM `benutzer`, `warenkorb`, `ware` WHERE `warenkorb`.`kundeID`=`benutzer`.`id` AND `warenkorb`.`wareID` = `ware`.`id` ORDER BY `warenkorb`.`kundeID`

>>>>>>>
1 	rdbstr. 12 202020 Hamburg 	3 	1 	5 	200.02 	2 TB Oranen USB-Stick
1 	rdbstr. 12 202020 Hamburg 	3 	2 	2 	14.39 	Messer

Super Cool

DELETE FROM `dw54`.`warenkorb` WHERE `warenkorb`.`kundeID` = 10000 AND `warenkorb`.`wareID` = 12;

INSERT INTO `warenkorb` (`kundeID`, `wareID`, `anzahl`) VALUES ('1', '2', '1');

UPDATE `dw54`.`warenkorb` SET `anzahl` = '7' WHERE `warenkorb`.`kundeID` =2 AND `warenkorb`.`wareID` =1;
UPDATE `dw54`.`bestellungen` SET `status` = '3' WHERE `bestellungen`.`id` =1;

INSERT INTO `dw54`.`bestellungen` (`id` ,`kundeID` ,`adresse` ,`bemerkung` ,`status`)VALUES (NULL , '1000', 'rdbstr. 12 202020 Hamburg', 'bem', '1');
INSERT INTO `dw54`.`bestWare` (`bestellungID` ,`position` ,`wareID` ,`anzahl`)VALUES ('3', '1', '11', '5');

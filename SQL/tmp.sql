INSERT INTO `warenkorb` (`kundeID`, `wareID`, `anzahl`) VALUES ('1', '2', '1');

UPDATE `dw54`.`warenkorb` SET `anzahl` = '7' WHERE `warenkorb`.`kundeID` =2 AND `warenkorb`.`wareID` =1;
UPDATE `dw54`.`bestellungen` SET `status` = '3' WHERE `bestellungen`.`id` =1;

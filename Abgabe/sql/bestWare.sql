CREATE TABLE IF NOT EXISTS `bestWare` (
  `bestellungID` int(5) NOT NULL,
  `position` int(5) NOT NULL,
  `wareID` int(5) NOT NULL,
  `anzahl` int(6) NOT NULL,
  FOREIGN KEY(bestellungID) REFERENCES bestellungen(id) ON DELETE CASCADE,
  FOREIGN KEY(wareID) REFERENCES ware(id) ON DELETE CASCADE,
  PRIMARY KEY (`bestellungID`,`position`)
)ENGINE=INNODB;

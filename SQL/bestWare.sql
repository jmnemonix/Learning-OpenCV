CREATE TABLE IF NOT EXISTS `bestWare` (
  `bestellungID` int(5) NOT NULL,
  `position` int(5) NOT NULL,
  `wareID` int(5) NOT NULL,
  `anzahl` int(6) NOT NULL,
  FOREIGN KEY(bestellungID) REFERENCES bestellungen(id) ON DELETE CASCADE,
  FOREIGN KEY(wareID) REFERENCES ware(id),
  PRIMARY KEY (`bestellungID`,`position`)
)ENGINE=INNODB;

# bei naechstem einspielen in DB ggf. nicht mitnehmen

INSERT INTO `bestWare` (`bestellungID`, `position`, `wareID`, `anzahl`) VALUES
(1, 1, 2, 5),
(1, 2, 5, 2),
(2, 1, 3, 1),
(3, 1, 11, 5),
(5, 1, 6, 2),
(5, 2, 7, 3),
(5, 3, 8, 3);

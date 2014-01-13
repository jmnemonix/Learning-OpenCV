CREATE TABLE IF NOT EXISTS `bestWare` (
  `bestellungID` int(5) NOT NULL,
  `position` int(5) NOT NULL,
  `wareID` int(5) NOT NULL,
  `anzahl` int(6) NOT NULL,
  PRIMARY KEY (`bestellungID`,`position`),
# der FOREIGN KEY dient als referenz auf die zugehörige Bestellung
  FOREIGN KEY(bestellungID)
    REFERENCES bestellungen(id) ON DELETE CASCADE,
# der FOREIGN KEY dient als referenz auf die bestellte Ware
 FOREIGN KEY(wareID)
    REFERENCES ware(id) ON DELETE CASCADE
);

# bei naechstem einspielen in DB ggf. nicht mitnehmen

INSERT INTO `bestWare` (`bestellungID`, `position`, `wareID`, `anzahl`) VALUES
(1, 1, 2, 5),
(1, 2, 5, 2),
(2, 1, 3, 1),
(3, 1, 11, 5),
(4, 1, 6, 2),
(4, 2, 7, 3),
(4, 3, 8, 3);

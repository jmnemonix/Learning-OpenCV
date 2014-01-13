CREATE TABLE IF NOT EXISTS `bestWare` (
  `bestellungID` int(5) NOT NULL,
  `position` int(5) NOT NULL,
  `wareID` int(5) NOT NULL,
  `anzahl` int(6) NOT NULL,
  PRIMARY KEY (`bestellungID`,`position`),
# der FOREIGN KEY dient als referenz auf die zugehörige Bestellung
  FOREIGN KEY(bestellungID)
    REFERENCES bestellungen(id),
# wird die Bestellung gelöscht, werden auch alle referenzierten artikel gelöscht
    ON DELETE CASCADE,
# der FOREIGN KEY dient als referenz auf die bestellte Ware
 FOREIGN KEY(wareID)
    REFERENCES ware(id)
);

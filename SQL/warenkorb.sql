CREATE TABLE IF NOT EXISTS `warenkorb` (
  `kundeID` int(5) NOT NULL,
  `wareID` int(5) NOT NULL,
  `anzahl` int(6) NOT NULL,
  PRIMARY KEY (`kundeID`,`wareID`),
  FOREIGN KEY(wareID) REFERENCES ware(id) ON DELETE CASCADE,
  FOREIGN KEY(kundeID) REFERENCES benutzer(id) ON DELETE CASCADE
);

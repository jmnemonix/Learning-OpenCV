CREATE TABLE IF NOT EXISTS `warenkorb` (
  `kundeID` int(5) NOT NULL,
  `wareID` int(5) NOT NULL,
  `anzahl` int(6) NOT NULL,
  PRIMARY KEY (`kundeID`,`wareID`),
  FOREIGN KEY(wareID) REFERENCES ware(id) ON DELETE CASCADE,
  FOREIGN KEY(kundeID) REFERENCES benutzer(id) ON DELETE CASCADE
);

# beim naechsten eintrag in DB ggf weglassen

INSERT INTO `warenkorb` (`kundeID`, `wareID`, `anzahl`) VALUES
(1006, 1, 1),
(1007, 9, 1),
(1008, 3, 1),
(1008, 1, 39),
(1008, 2, 10),
(1007, 10, 1),
(1007, 1, 1),
(1005, 16, 1),
(1005, 6, 1),
(1005, 20, 1),
(1005, 1, 2),
(1000, 14, 1),
(1000, 2, 1),
(1000, 1, 1),
(2, 5, 3),
(1, 1, 6),
(1006, 2, 1),
(1, 5, 18),
(1, 2, 1),
(1, 20, 1);

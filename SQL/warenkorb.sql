CREATE TABLE IF NOT EXISTS `warenkorb` (
  `kundeID` int(5) NOT NULL,
  `wareID` int(5) NOT NULL,
  `anzahl` int(6) NOT NULL,
  PRIMARY KEY (`kundeID`,`wareID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

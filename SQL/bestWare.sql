CREATE TABLE IF NOT EXISTS `bestWare` (
  `bestellungID` int(5) NOT NULL,
  `position` int(5) NOT NULL,
  `wareID` int(5) NOT NULL,
  `anzahl` int(6) NOT NULL,
  PRIMARY KEY (`bestellungID`,`position`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

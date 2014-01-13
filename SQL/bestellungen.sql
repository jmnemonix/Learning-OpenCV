CREATE TABLE IF NOT EXISTS `bestellungen` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `kundeID` int(5) NOT NULL,
  `adresse` varchar(50) NOT NULL,
  `bemerkung` varchar(140) NOT NULL,
  `status` int(2) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY(kundeID) REFERENCES benutzer(id) ON DELETE CASCADE
)ENGINE=INNODB;

# auch hier ggf nicht mitnehmen:

INSERT INTO `bestellungen` (`id`, `kundeID`, `adresse`, `bemerkung`, `status`) VALUES
(1, 1, 'rdbstr. 12 202020 Hamburg', '', 3),
(2, 1000, 'rdbstr. 12 202020 Hamburg', 'bem', 2),
(3, 1000, 'rdbstr. 12 202020 Hamburg', 'bem', 1),
(5, 1, 'neue straße 1 555 city', 'der kunde will das blaue messer', 1);

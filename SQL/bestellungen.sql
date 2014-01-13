CREATE TABLE IF NOT EXISTS `bestellungen` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `kundeID` int(5) NOT NULL,
  `adresse` varchar(50) NOT NULL,
  `bemerkung` varchar(140) NOT NULL,
  `status` int(2) NOT NULL,
  PRIMARY KEY (`id`)
);

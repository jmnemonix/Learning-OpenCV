
CREATE TABLE IF NOT EXISTS `ware` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `beschreibung` varchar(250) NOT NULL,
  `warengruppe` int(3) NOT NULL,
  `preis` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;



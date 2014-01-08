CREATE TABLE IF NOT EXISTS `ware` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `beschreibung` varchar(250) NOT NULL,
  `warengruppe` int(3) NOT NULL,
  `bestand` int(5) NOT NULL,
  `preis` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;


INSERT INTO `ware` (`id`, `name`, `beschreibung`, `warengruppe`, `bestand`, `preis`) VALUES
(1, 'Dart Waber USB-Stick', 'Cooler 8GB USB-Stick', 1, 5678, 19.59),
(2, 'Oranen USB-Stick', 'Sicher deine Daten auf der Orange', 1, 3882, 13.99),
(3, 'Christbaumkugeln', 'christbaumkugeln sind sehr sch&ouml;n und geh&ouml;ren in jedes &Uuml;berlebesset', 2, 55555, 200),
(4, 'Messer', 'allzweck Messer', 2, 99999, 4.39),
(5, 'Kaffee', 'Kaffee wird zum &Uuml;berleben ben&ouml;tigt', 2, 6666, 22.49),
(6, 'Brat-Max Brille', 'Die Brille eines kommilitonen', 3, 22, 55.59),
(7, 'Club-Mate', 'Leckere Mate ... leider momentan nur leere Flaschen verf&uuml;gbar', 4, 300, 0.88),
(8, 'Wasser', 'Wasser', 4, 3982, 51),
(9, 'Leet-Mate', 'Mate Mate Mate', 4, 33333, 0.77),
(10, 'Kabelbinder', 'zum binden von Kabeln', 2, 50000, 1);

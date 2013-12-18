-- Datenbank backup

CREATE TABLE IF NOT EXISTS `benutzer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(30) NOT NULL,
  `password` varchar(32) NOT NULL,
  `bemerkung` varchar(120) DEFAULT NULL,
  `rolle` int(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;


INSERT INTO `benutzer` (`id`, `email`, `password`, `bemerkung`, `rolle`) VALUES
(1, 'admin@shop', 'asdf', NULL, 1),
(2, 'kunde@shop', 'qwer', NULL, 10);

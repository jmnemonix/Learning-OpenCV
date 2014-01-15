CREATE TABLE IF NOT EXISTS `ware` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `bild` varchar(12) NOT NULL,
  `beschreibung` varchar(250) NOT NULL,
  `warengruppe` int(3) NOT NULL,
  `bestand` int(5) NOT NULL,
  `preis` decimal(6,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`)
) ENGINE=INNODB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;


INSERT INTO `ware` (`id`, `name`, `bild`,`beschreibung`, `warengruppe`, `bestand`, `preis`) VALUES
(1,  '8 GB Dunkler Werner',    'dv.png',     'Cooler 8GB USB-Stick',                                                                          1,  5678, 19.59),
(2,  '2 TB Oranen USB-Stick',  'orange.png', 'Sicher deine Daten auf der Orange',                                                             1,  3882, 200.02),
(3,  '4 TB Zitrone USB-Stick', 'zitr.png',   'Sicher deine Daten auf der Zitrone',                                                            1,    82, 300.34),
(4,  '2 MB pny USB-Stick',     'pny.png',    'Sicher deine Daten hinten im pny',                                                              1,   332, 400.11),
(5,  '120 GB Kiwi USB-Stick',  'kiwi.png',   'Sicher deine Daten hinten in der Kiwi<br>ACHTUNG: Kann NICHT fliegen!',                         1,    42, 5.99),
(6,  'Messer',                 'messer.png', 'allzweck Messer',                                                                               2, 99999, 14.39),
(7,  'Kabelbinder',            'kb.png',     'zum binden von Kabeln<br>sollte man immer bei sich führen',                                     2, 50000, 1),
(8,  'Kaffee',                 'kaffee.png', 'Kaffee wird zum &Uuml;berleben ben&ouml;tigt',                                                  2,  6666, 22.49),
(9,  'Kerzen',                 'kerze.png',  'leuchten',                                                                                      2,  6666, 0.44),
(10,  'USB Survival OSes',     'usb.png',    'Sollte die Zombie-Appokalypse hereinbrecen, haben sie fuer jede Situation das richtige System', 2,  6666, 42.42),
(11, 'Max Brille',             'max.png',    'kein Nerd ohne Brille',                                                                         3,    22, 2.52),
(12, 'Club-Mate',              'cmate.png',  'Leckere Mate ... leider momentan nur leere Flaschen verf&uuml;gbar',                            4,   300, 0.88),
(13, 'Mio Mio Mate',           'mio.png',    'Leckere Mate ... leider momentan nur leere Flaschen verf&uuml;gbar',                            4,   300, 0.88),
(14, 'Wasser',                 'wasser.png', 'Wasser',                                                                                        4,  3982, 51),
(15, 'Gnu Batch',              'gnu1.png',   'Show the GNU',                                                                                  5, 33325, 1),
(16, 'Hack a Day',             'had.png',    'Show ur hck',                                                                                   5,    66, 2),
(17, 'Raetsel 1',              'r1.png',     'Ein sehr anspruchvolles Rätsel',                                                                6,  5555, 0.4),
(18, 'Raetsel 2',              'r2.png',     'uuuiuiuii',                                                                                     6,  3982, 0.4),
(19, 'CPU',                    'cpu.png',    'Ein echter singlecore CPU aus dem Jahr 11111010110',                                            7, 22233, 1000),
(20, 'Zahnstocher',            'zs.png',     'Ein sehr seltener Zahnstocher eines Riesen',                                                    7,    30, 20000);




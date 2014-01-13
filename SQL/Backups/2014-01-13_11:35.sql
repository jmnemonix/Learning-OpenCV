-- phpMyAdmin SQL Dump
-- version 3.3.7deb5build0.10.10.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 13. Januar 2014 um 11:34
-- Server Version: 5.1.61
-- PHP-Version: 5.3.3-1ubuntu9.10

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `dw54`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `benutzer`
--

CREATE TABLE IF NOT EXISTS `benutzer` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `passwort` varchar(34) NOT NULL,
  `bemerkung` varchar(120) DEFAULT NULL,
  `rolle` int(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10002 ;

--
-- Daten für Tabelle `benutzer`
--

INSERT INTO `benutzer` (`id`, `name`, `email`, `passwort`, `bemerkung`, `rolle`) VALUES
(1, 'admin', 'admin@shop', 'asdf', NULL, 1),
(10000, 'Rene Descartes', 'kunde@shop', 'qwert', NULL, 10),
(10001, 'Platon', 'kunde2@shop', 'qwert', NULL, 10);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `bestellungen`
--

CREATE TABLE IF NOT EXISTS `bestellungen` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `kundeID` int(5) NOT NULL,
  `adresse` varchar(50) NOT NULL,
  `bemerkung` varchar(140) NOT NULL,
  `status` int(2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `kundeID` (`kundeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Daten für Tabelle `bestellungen`
--


-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `bestWare`
--

CREATE TABLE IF NOT EXISTS `bestWare` (
  `bestellungID` int(5) NOT NULL,
  `position` int(5) NOT NULL,
  `wareID` int(5) NOT NULL,
  `anzahl` int(6) NOT NULL,
  PRIMARY KEY (`bestellungID`,`position`),
  KEY `wareID` (`wareID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `bestWare`
--


-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `ware`
--

CREATE TABLE IF NOT EXISTS `ware` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `bild` varchar(12) NOT NULL,
  `beschreibung` varchar(250) NOT NULL,
  `warengruppe` int(3) NOT NULL,
  `bestand` int(5) NOT NULL,
  `preis` decimal(6,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Daten für Tabelle `ware`
--

INSERT INTO `ware` (`id`, `name`, `bild`, `beschreibung`, `warengruppe`, `bestand`, `preis`) VALUES
(1, '8 GB Dunkler Werner', 'dv.png', 'Cooler 8GB USB-Stick', 1, 5678, '19.59'),
(2, '2 TB Oranen USB-Stick', 'orange.png', 'Sicher deine Daten auf der Orange', 1, 3882, '200.02'),
(3, '4 TB Zitrone USB-Stick', 'zitr.png', 'Sicher deine Daten auf der Zitrone', 1, 82, '300.34'),
(4, '2 MB pny USB-Stick', 'pny.png', 'Sicher deine Daten hinten im pny', 1, 332, '400.11'),
(5, '120 GB Kiwi USB-Stick', 'kiwi.png', 'Sicher deine Daten hinten in der Kiwi<br>ACHTUNG: Kann NICHT fliegen!', 1, 42, '5.99'),
(6, 'Messer', 'messer.png', 'allzweck Messer', 2, 99999, '14.39'),
(7, 'Kabelbinder', 'kb.png', 'zum binden von Kabeln<br>sollte man immer bei sich führen', 2, 50000, '1.00'),
(8, 'Kaffee', 'kaffee.png', 'Kaffee wird zum &Uuml;berleben ben&ouml;tigt', 2, 6666, '22.49'),
(9, 'Kerzen', 'kerze.png', 'leuchten', 2, 6666, '0.44'),
(10, 'USB Survival OSes', 'usb.png', 'Sollte die Zombie-Appokalypse hereinbrecen, haben sie fuer jede Situation das richtige System', 2, 6666, '42.42'),
(11, 'Max Brille', 'max.png', 'kein Nerd ohne Brille', 3, 22, '2.52'),
(12, 'Club-Mate', 'cmate.png', 'Leckere Mate ... leider momentan nur leere Flaschen verf&uuml;gbar', 4, 300, '0.88'),
(13, 'Mio Mio Mate', 'mio.png', 'Leckere Mate ... leider momentan nur leere Flaschen verf&uuml;gbar', 4, 300, '0.88'),
(14, 'Wasser', 'wasser.png', 'Wasser', 4, 3982, '51.00'),
(15, 'Gnu Batch', 'gnu1.png', 'Show the GNU', 5, 33325, '1.00'),
(16, 'Hack a Day', 'had.png', 'Show ur hck', 5, 66, '2.00'),
(17, 'Raetsel 1', 'r1.png', 'Ein sehr anspruchvolles Rätsel', 6, 5555, '0.40'),
(18, 'Raetsel 2', 'r2.png', 'uuuiuiuii', 6, 3982, '0.40'),
(19, 'CPU', 'cpu.png', 'Ein echter singlecore CPU aus dem Jahr 11111010110', 7, 22233, '1000.00'),
(20, 'Zahnstocher', 'zs.png', 'Ein sehr seltener Zahnstocher eines Riesen', 7, 30, '9999.99');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `warenkorb`
--

CREATE TABLE IF NOT EXISTS `warenkorb` (
  `kundeID` int(5) NOT NULL,
  `wareID` int(5) NOT NULL,
  `anzahl` int(6) NOT NULL,
  PRIMARY KEY (`kundeID`,`wareID`),
  KEY `wareID` (`wareID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `warenkorb`
--

INSERT INTO `warenkorb` (`kundeID`, `wareID`, `anzahl`) VALUES
(1, 1, 5),
(1, 11, 2);

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `bestellungen`
--
ALTER TABLE `bestellungen`
  ADD CONSTRAINT `bestellungen_ibfk_1` FOREIGN KEY (`kundeID`) REFERENCES `benutzer` (`id`) ON DELETE CASCADE;

--
-- Constraints der Tabelle `bestWare`
--
ALTER TABLE `bestWare`
  ADD CONSTRAINT `bestWare_ibfk_1` FOREIGN KEY (`bestellungID`) REFERENCES `bestellungen` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `bestWare_ibfk_2` FOREIGN KEY (`wareID`) REFERENCES `ware` (`id`);

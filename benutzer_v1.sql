CREATE TABLE `benutzer` (
	`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
	`email` VARCHAR( 30 ) NOT NULL ,
	`password` VARCHAR( 32 ) NOT NULL ,
	`bemerkung` VARCHAR( 120 ) NULL ,
	`rolle` INT( 2 ) NOT NULL
) ENGINE = MYISAM ;
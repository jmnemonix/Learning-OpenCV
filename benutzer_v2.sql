CREATE TABLE `dw54`.`benutzer` (
	`id` INT( 5 ) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
	`name` VARCHAR( 30 ) NOT NULL ,
	`email` VARCHAR( 30 ) NOT NULL ,
	`passwort` VARCHAR( 34 ) NOT NULL ,
	`bemerkung` VARCHAR( 120 ) NULL ,
	`rolle` INT( 2 ) NOT NULL
) ENGINE = MYISAM CHARACTER SET utf8 COLLATE utf8_general_ci;
CREATE TABLE `imarket`.`users` (
  `User_Id` INT NOT NULL AUTO_INCREMENT,
  `Username` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(60) NOT NULL,
  `Email` VARCHAR(45) NULL,
  `Created_at` TIMESTAMP NOT NULL,
  `Modified_at` TIMESTAMP NOT NULL,
  PRIMARY KEY (`User_Id`));

CREATE TABLE `imarket`.`roles` (
  `Role_Id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Created_at` TIMESTAMP NOT NULL,
  `Modified_at` TIMESTAMP NOT NULL,
  PRIMARY KEY (`Role_Id`));

CREATE TABLE `imarket`.`users_roles` (
  `User_Id` INT NOT NULL ,
  `Role_Id` INT NOT NULL,
  `Created_at` TIMESTAMP NOT NULL,
  `Modified_at` TIMESTAMP NOT NULL,
  PRIMARY KEY (`User_Id`, `Role_Id`),
  CONSTRAINT `users_Id`
      FOREIGN KEY (`User_Id`)
      REFERENCES `imarket`.`users` (`User_Id`)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  CONSTRAINT `roles_Id`
        FOREIGN KEY (`Role_Id`)
        REFERENCES `imarket`.`roles` (`Role_Id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION)
      ;
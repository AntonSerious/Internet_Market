CREATE TABLE `imarket`.`orders` (
  `Order_Id` INT NOT NULL AUTO_INCREMENT,
  `Order_Price` DECIMAL(8,2) NULL,
  `CustomerId` INT NOT NULL,
  `Created_at` TIMESTAMP NULL,
  `Modified_at` TIMESTAMP NULL,
  PRIMARY KEY (`Order_Id`),
  INDEX `Customer_Id_idx` (`CustomerId` ASC) VISIBLE,
  CONSTRAINT `Customer_Id`
    FOREIGN KEY (`CustomerId`)
    REFERENCES `imarket`.`customers` (`Customer_Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
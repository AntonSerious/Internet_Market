CREATE TABLE `imarket`.`orders_details` (
  `Order_Detail_Id` INT NOT NULL,
  `Order_Id` INT NOT NULL,
  `Product_Id` INT NOT NULL,
  `Quantity` INT NULL,
  `Created_at` VARCHAR(45) NULL,
  `Modified_at` VARCHAR(45) NULL,
  PRIMARY KEY (`Order_Detail_Id`),
  INDEX `Product_Id_idx` (`Product_Id` ASC) VISIBLE,
  CONSTRAINT `Order_Id`
    FOREIGN KEY (`Order_Id`)
    REFERENCES `imarket`.`orders` (`Order_Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Product_Id`
    FOREIGN KEY (`Product_Id`)
    REFERENCES `imarket`.`products` (`Product_Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

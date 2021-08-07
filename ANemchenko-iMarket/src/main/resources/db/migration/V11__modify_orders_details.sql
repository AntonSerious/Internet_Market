ALTER TABLE `imarket`.`orders_details` 
CHANGE COLUMN `Product_Id` `Product_Id` INT NOT NULL ,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`Order_Detail_Id`),
ADD CONSTRAINT `Order_id`
  FOREIGN KEY (`Order_Id`)
  REFERENCES `imarket`.`orders` (`Order_Id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `imarket`.`orders_details` 
ADD CONSTRAINT `Product_Id`
  FOREIGN KEY (`Product_Id`)
  REFERENCES `imarket`.`products` (`Product_Id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
ALTER TABLE `imarket`.`cust_buyings_info`
DROP FOREIGN KEY `idCustomer`,
DROP FOREIGN KEY `idProduct`;
ALTER TABLE `imarket`.`cust_buyings_info`
CHANGE COLUMN `idRecord` `Record_Id` INT NOT NULL AUTO_INCREMENT ,
CHANGE COLUMN `idCustomer` `Customer_Id` INT NOT NULL ,
CHANGE COLUMN `idProduct` `Product_Id` INT NOT NULL ,
CHANGE COLUMN `buyPrice` `Buy_Price` DECIMAL(10,0) NOT NULL ,
CHANGE COLUMN `comment` `Comment` VARCHAR(45) NULL DEFAULT NULL ;
ALTER TABLE `imarket`.`cust_buyings_info`
ADD CONSTRAINT `idCustomer`
  FOREIGN KEY (`Customer_Id`)
  REFERENCES `imarket`.`customers` (`Customer_Id`),
ADD CONSTRAINT `idProduct`
  FOREIGN KEY (`Product_Id`)
  REFERENCES `imarket`.`products` (`productId`);

ALTER TABLE `imarket`.`products`
CHANGE COLUMN `productId` `Product_Id` INT NOT NULL AUTO_INCREMENT ,
CHANGE COLUMN `title` `Title` VARCHAR(45) NOT NULL ,
CHANGE COLUMN `price` `Price` DECIMAL(10,0) NOT NULL ;

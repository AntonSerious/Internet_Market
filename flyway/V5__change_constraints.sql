ALTER TABLE `imarket`.`cust_buyings_info`
DROP FOREIGN KEY `idCustomer`,
DROP FOREIGN KEY `idProduct`;
ALTER TABLE `imarket`.`cust_buyings_info`
ADD CONSTRAINT `idCustomer`
  FOREIGN KEY (`Customer_Id`)
  REFERENCES `imarket`.`customers` (`Customer_Id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
ADD CONSTRAINT `idProduct`
  FOREIGN KEY (`Product_Id`)
  REFERENCES `imarket`.`products` (`Product_Id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
